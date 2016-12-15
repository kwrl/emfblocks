package no.hal.emfblocks.editor.eclipse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.part.EditorPart;

public class ResourceEditorHelper implements IResourceChangeListener {

	private ResourceEditorPart resourceEditorPart;
	
	public ResourceEditorHelper(ResourceEditorPart resourceEditorPart) {
		this.resourceEditorPart = resourceEditorPart;
	}

	protected EditorPart getEditorPart() {
		return resourceEditorPart;
	}

	protected boolean isDirty() {
		return getEditorPart().isDirty();
	}

	protected IEditorSite getSite() {
		return getEditorPart().getEditorSite();
	}

	protected EditingDomain getEditingDomain() {
		return resourceEditorPart.getEditingDomain();
	}

	protected void asyncExec(Runnable runnable) {
		resourceEditorPart.asyncExec(runnable);
	}
	
	/**
	 * Resources that have been removed since last activation.
	 */
	Collection<Resource> removedResources = new ArrayList<Resource>();

	/**
	 * Resources that have been changed since last activation.
	 */
	Collection<Resource> changedResources = new UniqueEList<Resource>();

	/**
	 * Resources that have been saved.
	 */
	Collection<Resource> savedResources = new ArrayList<Resource>();

	class ResourceDeltaVisitor implements IResourceDeltaVisitor {
		protected ResourceSet resourceSet = getEditingDomain().getResourceSet();
		protected Collection<Resource> changedResources = new ArrayList<Resource>();
		protected Collection<Resource> removedResources = new ArrayList<Resource>();
		
		public boolean visit(final IResourceDelta delta) {
			if (delta.getResource().getType() == IResource.FILE) {
				if (delta.getKind() == IResourceDelta.REMOVED || delta.getKind() == IResourceDelta.CHANGED) {
					URI uri = URI.createPlatformResourceURI(delta.getFullPath().toString(), true);
					final Resource resource = resourceSet.getResource(uri, false);
					if (resource != null) {
						if (delta.getKind() == IResourceDelta.REMOVED) {
							removedResources.add(resource);
						} else {
							if ((delta.getFlags() & IResourceDelta.MARKERS) != 0) {
								// DiagnosticDecorator.DiagnosticAdapter.update(resource,
								// markerHelper
								// .getMarkerDiagnostics(resource,
								// (IFile) delta.getResource(), false));
							}
							if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
								if (!savedResources.remove(resource)) {
									changedResources.add(resource);
								}
							}
						}
					}
				}
				return false;
			}
			
			return true;
		}
		
		public Collection<Resource> getChangedResources() {
			return changedResources;
		}
		
		public Collection<Resource> getRemovedResources() {
			return removedResources;
		}
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {

			final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
			delta.accept(visitor);

			if (!visitor.getRemovedResources().isEmpty()) {
				asyncExec(new Runnable() {
					public void run() {
						removedResources.addAll(visitor.getRemovedResources());
						if (!getEditorPart().isDirty()) {
							getSite().getPage().closeEditor(getEditorPart(), false);
						}
					}
				});
			}
			if (!visitor.getChangedResources().isEmpty()) {
				asyncExec(new Runnable() {
					public void run() {
						changedResources.addAll(visitor.getChangedResources());
						System.out.println(getSite().getPage().getActiveEditor());
						boolean handleChange = true; // getSite().getPage().getActiveEditor() == getEditorPart()
						if (handleChange) {
							handleActivate();
						}
					}
				});
			}
		} catch (CoreException exception) {
			EcoreEditorPlugin.INSTANCE.log(exception);
		}
	}

	/**
	 * Handles activation of the editor or it's associated views.
	 */
	protected void handleActivate() {
//		if (removedResources.isEmpty() && !changedResources.isEmpty()) {
//			for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
//				if (!changedResources.contains(resource)) {
//					URI uri = resource.getURI();
//					if (!"java".equals(uri.scheme()) && !NON_DYNAMIC_EXTENSIONS.contains(uri.fileExtension())) {
//						for (Iterator<EObject> i = resource.getAllContents(); i.hasNext();) {
//							EObject eObject = i.next();
//							if (changedResources.contains(eObject.eClass().eResource())) {
//								changedResources.add(resource);
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
		// Recompute the read only state.
		//
		// if (getEditingDomain().getResourceToReadOnlyMap() != null) {
		// getEditingDomain().getResourceToReadOnlyMap().clear();
		//
		// // Refresh any actions that may become enabled or disabled.
		// //
		// setSelection(getSelection());
		// }
		if (!removedResources.isEmpty()) {
			if (handleDirtyConflict()) {
				getSite().getPage().closeEditor(getEditorPart(), false);
			} else {
				removedResources.clear();
				changedResources.clear();
				savedResources.clear();
			}
		} else if (!changedResources.isEmpty()) {
			changedResources.removeAll(savedResources);
			resourceEditorPart.handleChangedResources(changedResources);
			changedResources.clear();
			savedResources.clear();
		}
	}

	/**
	 * Shows a dialog that asks if conflicting changes should be discarded. <!--
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog.openQuestion(getSite().getShell(), "File conflict", "File changed from outside editor, discard changes?");
	}

	/**
	 * This returns whether something has been persisted to the URI of the
	 * specified resource. The implementation uses the URI converter from the
	 * editor's resource set to try to open an input stream.
	 */
	boolean isPersisted(Resource resource) {
		URIConverter uriConverter = getEditingDomain().getResourceSet().getURIConverter();
		boolean result = false;
		try {
			InputStream stream = uriConverter.createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		} catch (IOException e) {
			// ignore
		}
		return result;
	}
}
