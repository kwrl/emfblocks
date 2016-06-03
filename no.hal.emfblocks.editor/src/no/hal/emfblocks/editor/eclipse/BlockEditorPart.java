package no.hal.emfblocks.editor.eclipse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryTreeCellFactory;
import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryTreeItem;
import at.bestsolution.efxclipse.runtime.emf.edit.ui.dnd.CellDragAdapter;
import at.bestsolution.efxclipse.runtime.emf.edit.ui.dnd.EditingDomainCellDropAdapter;
import javafx.embed.swt.FXCanvas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import no.hal.emfblocks.cuteditor.CutEditor;
import no.hal.emfblocks.editor.CellPopupAdapter;
import no.hal.emfblocks.editor.DecoratedModelInstance;
import no.hal.emfblocks.editor.EMFBlockEditor;
import no.hal.emfblocks.editor.efxclipse.PatchedAdapterFactoryTreeCellFactory;
import no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory;
import no.hal.emfblocks.javafx.ActionSource;

public class BlockEditorPart extends EditorPart implements ISelectionProvider, IEditingDomainProvider {

	private DecoratedModelInstance instance;

	private EditingDomain editingDomain;
	private ComposedAdapterFactory adapterFactory;

	private ActionSource viewDisposer = new ActionSource();
	private EMFBlockEditor blockEditor = null;
	private CutEditor cutEditor = null;

	private BlocksConfigLoader blocksConfigProvider = new SingleResourceConfigLoader(new ResourceRelativeConfigURIProvider());
	
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {		
		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		site.setSelectionProvider(this);
//		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);

		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack(), new HashMap<Resource, Boolean>());
		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
			@Override
			public void commandStackChanged(EventObject event) {
				firePropertyChange(PROP_DIRTY);
			}
		});
		ResourceSet resourceSet = editingDomain.getResourceSet();
		URI resourceURI = EditUIUtil.getURI(getEditorInput(), resourceSet.getURIConverter());
		instance = new DecoratedModelInstance(resourceSet, resourceURI, blocksConfigProvider);
		doSave(getProgressMonitor());
		
		for(Resource resource : resourceSet.getResources()) {
			URI uri = resource.getURI();
			if (uri.isPlatformResource() || uri.isFile()) {
				try {
					resource.save(null);
				} catch (IOException e) {
				}
			}
		}
	}

	private FXCanvas fxCanvas;
	
	@FXML private Parent wRootContent;
	@FXML private Pane workAreaParent;
	@FXML private Pane paletteParent;
	@FXML private Pane trashParent;
	@FXML private Pane cutEditorParent;
	@FXML private TreeView<Object> configTree;
	@FXML private GridPane configPropertiesGrid;

	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(getClass().getSimpleName() + "Layout1.fxml"));
		loader.setController(this);
		Region fxRoot = null;
		try {
			fxRoot = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// block editor
		blockEditor = new EMFBlockEditor(editingDomain, adapterFactory, instance);
		blockEditor.installUI(wRootContent, workAreaParent, paletteParent, trashParent);
		
		// cut editor
		cutEditor = new CutEditor(blockEditor.getRoot().getTypeHierarchy());
		cutEditorParent.getChildren().add(cutEditor.getUI());
		
		// config editor
		ResourceSet rootNode = editingDomain.getResourceSet();
		TreeView<Object> treeView = configTree; // new TreeView<>();

		treeView.setRoot(new AdapterFactoryTreeItem(rootNode, treeView, adapterFactory));
		AdapterFactoryTreeCellFactory cellFact = new PatchedAdapterFactoryTreeCellFactory(adapterFactory);
		cellFact.addCellCreationListener(new EditingDomainCellDropAdapter(editingDomain)); // Doesn't work
		cellFact.addCellCreationListener(new CellDragAdapter());
		cellFact.addCellCreationListener(new CellPopupAdapter(editingDomain));

		treeView.setCellFactory(cellFact);

		GridPane container = configPropertiesGrid; // new GridPane();
		treeView.getSelectionModel().selectedItemProperty().addListener((property, oldValue, newValue) -> {
			initView(container, newValue);
		});
		initView(container, treeView.getSelectionModel().getSelectedItem());

		Scene fxScene = new Scene(fxRoot);
		fxCanvas.setScene(fxScene);
	}
	
	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public void setFocus() {
		fxCanvas.setFocus();
	}

	//
	
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}

	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	@Override
	public boolean isDirty() {
		return ((BasicCommandStack) editingDomain.getCommandStack()).isSaveNeeded();
	}

	private Map<String, Object> saveOptions = new HashMap<>();
	{
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		for(Resource resource : editingDomain.getResourceSet().getResources()) {
			URI uri = resource.getURI();
			if (uri.isPlatformResource() || uri.isFile()) {
				try {
					resource.save(saveOptions);
				} catch (IOException e) {
				}
			}
		}
		((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				URI newURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				FileEditorInput newEditorInput = new FileEditorInput(file);
				editingDomain.getResourceSet().getResources().get(0).setURI(newURI);
				setInputWithNotify(newEditorInput);
				setPartName(newEditorInput.getName());
				IProgressMonitor progressMonitor = getProgressMonitor();
				doSave(progressMonitor);
			}
		}
	}

	private IProgressMonitor getProgressMonitor() {
		IStatusLineManager slm = getActionBars().getStatusLineManager();
		IProgressMonitor progressMonitor = slm != null ? slm.getProgressMonitor() : new NullProgressMonitor();
		return progressMonitor;
	}

	//

//	private void select(Tab n) {
//		if (n == configurationTab) {
//			if (blockEditor != null) {
//				blockEditor.dispose();
//				blockEditor = null;
//				cutEditor.dispose();
//				cutEditor = null;
//				initConfigurationEditor();
//				blocksTab.setContent(null);
//				cutEditorTab.setContent(null);
//			}
//		} else if (blockEditor == null) {
//			initBlockEditor();
//		}
//	}

	private void initView(GridPane container, TreeItem<Object> selectedItem) {
		viewDisposer.fireEvents();
		container.getChildren().clear();

		if (selectedItem != null && selectedItem.getValue() instanceof EObject) {
			DefaultAttributeFormFactory formFactory = DefaultAttributeFormFactory.instance();
			EObject obj = (EObject) selectedItem.getValue();
			int row = 0;
			for (EAttribute attr : obj.eClass().getEAllAttributes()) {
				try {
					if (attr.isChangeable()) {
						Node[] nodes = formFactory.createForm(editingDomain, obj, attr, viewDisposer);
						for (int x = 0; x < nodes.length; x++) {
							container.add(nodes[x], x, row);
						}
						row++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
}
