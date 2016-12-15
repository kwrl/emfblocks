package no.hal.emfblocks.editor.eclipse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.EditorPart;

public abstract class ResourceEditorPart extends EditorPart implements ISelectionProvider, IEditingDomainProvider {
	
	protected ResourceEditorHelper resourceEditorHelper;
	
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {		
		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		site.setSelectionProvider(this);

		resourceEditorHelper = new ResourceEditorHelper(this);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceEditorHelper, IResourceChangeEvent.POST_CHANGE);
	}
	
	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceEditorHelper);
		super.dispose();
	}

	// ISelectionProvider

	protected ISelection editorSelection = StructuredSelection.EMPTY;

	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}
	
	@Override
	public ISelection getSelection() {
		return editorSelection;
	}

	@Override
	public void setSelection(ISelection selection) {
		editorSelection = selection;
		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}

	//
	
	@Override
	public boolean isDirty() {
		return ((BasicCommandStack) getEditingDomain().getCommandStack()).isSaveNeeded();
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply saves the model
	 */
	public void doSave(IProgressMonitor progressMonitor) {
		// Save only resources that have actually changed.
		//
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		//
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			//
			@Override
			public void execute(IProgressMonitor monitor) {
				// Save the resources to the file system.
				//
				boolean first = true;
				for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
					if ((first || !resource.getContents().isEmpty() || resourceEditorHelper.isPersisted(resource))
							&& !getEditingDomain().isReadOnly(resource)) {
						try {
							long timeStamp = resource.getTimeStamp();
							resource.save(saveOptions);
							if (resource.getTimeStamp() != timeStamp) {
								resourceEditorHelper.savedResources.add(resource);
							}
						} catch (Exception exception) {
//							resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
						}
						first = false;
					}
				}
			}
		};

//		updateProblemIndication = false;
		try {
			// This runs the options, and shows progress.
			//
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

			// Refresh the necessary state.
			//
			((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			EcoreEditorPlugin.INSTANCE.log(exception);
		}
//		updateProblemIndication = true;
//		updateProblemIndication();
	}
	
	protected void asyncExec(Runnable runnable) {
		getSite().getShell().getDisplay().asyncExec(runnable);
	}

	/**
	 * Handles what to do with changed resources on activation.
	 */
	void handleChangedResources(Collection<Resource> changedResources) {
		if (! changedResources.isEmpty() && (!isDirty() || resourceEditorHelper.handleDirtyConflict())) {
			if (isDirty()) {
				changedResources.addAll(getEditingDomain().getResourceSet().getResources());
			}
			getEditingDomain().getCommandStack().flush();

			// updateProblemIndication = false;
			List<Resource> unloadedResources = new ArrayList<Resource>();
			for (Resource resource : changedResources) {
				if (resource.isLoaded()) {
					resource.unload();
					unloadedResources.add(resource);
				}
			}

			for (Resource resource : unloadedResources) {
				try {
					resource.load(Collections.EMPTY_MAP);
				} catch (IOException exception) {
					// if (!resourceToDiagnosticMap.containsKey(resource)) {
					// resourceToDiagnosticMap.put(resource,
					// analyzeResourceProblems(resource, exception));
					// }
				}
			}

			if (AdapterFactoryEditingDomain.isStale(getSelection())) {
				setSelection(StructuredSelection.EMPTY);
			}

			// updateProblemIndication = true;
			// updateProblemIndication();
		}
	}
}
