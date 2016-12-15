package no.hal.emfblocks.editor.eclipse;

import java.io.IOException;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

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
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.palette.PalettePackage;

public class BlockEditorPart extends ResourceEditorPart {

	protected ComposedAdapterFactory adapterFactory;
	protected EditingDomain editingDomain;

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	private DecoratedModelInstance instance;

	private ActionSource viewDisposer = new ActionSource();
	private EMFBlockEditor blockEditor = null;
	private CutEditor cutEditor = null;

	private BlocksConfigLoader blocksConfigProvider = new SingleResourceConfigLoader(new ResourceRelativeConfigURIProvider());

//	private ResourceChangeHandler resourceChangeHandler;
	
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);

		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack(), new HashMap<Resource, Boolean>()) {
			public boolean isReadOnly(Resource resource) {
				return super.isReadOnly(resource) || (! resource.getURI().isPlatformResource());
			}
		};
		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
			@Override
			public void commandStackChanged(EventObject event) {
				firePropertyChange(PROP_DIRTY);
				final Command lastCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
				if (lastCommand != null) {
					boolean[] updates = getUpdateTuple(lastCommand.getAffectedObjects(), new boolean[3]);
					update(false, updates);							
				}
			}
		});
		URI resourceURI = EditUIUtil.getURI(getEditorInput(), editingDomain.getResourceSet().getURIConverter());
		instance = new DecoratedModelInstance(editingDomain.getResourceSet(), resourceURI, blocksConfigProvider);
	}

	private String[] updatePackages = { MappingPackage.eNS_URI, PalettePackage.eNS_URI, LayoutPackage.eNS_URI };
	
	protected boolean[] getUpdateTuple(Collection<?> elements, boolean... domains) {
		for (Object object : elements) {
			if (object instanceof EObject) {
				EPackage pack = ((EObject) object).eClass().getEPackage();
				for (int i = 0; i < updatePackages.length; i++) {
					if (updatePackages[i].equals(pack.getNsURI())) {
						domains[i] = true;
					}
				}
			}
		}
		return domains;
	}
	
	@Override
	public void dispose() {
//		if (resourceChangeHandler != null) {
//			ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeHandler);
//			resourceChangeHandler = null;
//		}
		disposeFxControls();
		super.dispose();
	}
	
	private FXCanvas fxCanvas;
	private Scene fxScene; 
			
	@FXML private Parent wRootContent;
	@FXML private Pane workAreaParent;
	@FXML private Pane paletteParent;
	@FXML private Pane trashParent;
	@FXML private Pane cutEditorParent;
	@FXML private TreeView<Object> configTree;
	@FXML private GridPane configPropertiesGrid;

	private String defaultLayout = "Layout1";
	
	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		fxScene = new Scene(createFxControls(defaultLayout));
		fxCanvas.setScene(fxScene);
	}
	
	protected void updateFxControls() {
		fxScene.setRoot(createFxControls(defaultLayout));
	}

	protected Parent createFxControls(String layoutName) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(getClass().getSimpleName() + layoutName + ".fxml"));
		loader.setController(this);
		Region fxRoot = null;
		try {
			fxRoot = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// block editor
		if (blockEditor != null) {
			blockEditor.dispose();
		}
		blockEditor = new EMFBlockEditor(editingDomain, adapterFactory, instance);
		blockEditor.installUI(wRootContent, workAreaParent, paletteParent, trashParent);
		
		// cut editor
		if (cutEditor != null) {
			cutEditor.dispose();
		}
		cutEditor = new CutEditor(blockEditor.getRoot().getTypeHierarchy());
		cutEditorParent.getChildren().setAll(cutEditor.getUI());
		
		// config editor
		ResourceSet rootNode = editingDomain.getResourceSet();
		configTree.setRoot(new AdapterFactoryTreeItem(rootNode, configTree, adapterFactory));
		AdapterFactoryTreeCellFactory cellFact = new PatchedAdapterFactoryTreeCellFactory(adapterFactory);
		cellFact.addCellCreationListener(new EditingDomainCellDropAdapter(editingDomain)); // Doesn't work
		cellFact.addCellCreationListener(new CellDragAdapter());
		cellFact.addCellCreationListener(new CellPopupAdapter(editingDomain));

		configTree.setCellFactory(cellFact);

		GridPane container = configPropertiesGrid; // new GridPane();
		configTree.getSelectionModel().selectedItemProperty().addListener((property, oldValue, newValue) -> {
			initView(container, newValue);
		});
		initView(container, configTree.getSelectionModel().getSelectedItem());
		return fxRoot;
	}

	private void disposeFxControls() {
		if (blockEditor != null) {
			blockEditor.dispose();
			blockEditor = null;
		}
		if (cutEditor != null) {
			cutEditor.dispose();
			cutEditor = null;
		}
		if (configTree != null) {
			configTree.setRoot(null);
			configTree = null;
		}
	}

	@Override
	public void setFocus() {
		fxCanvas.setFocus();
	}

	//
	
	protected EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}

	private Map<String, Object> saveOptions = new HashMap<>();
	{
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);		
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSaveAs() {
//		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
//		saveAsDialog.open();
//		IPath path = saveAsDialog.getResult();
//		if (path != null) {
//			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
//			if (file != null) {
//				URI newURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
//				FileEditorInput newEditorInput = new FileEditorInput(file);
//				editingDomain.getResourceSet().getResources().get(0).setURI(newURI);
//				setInputWithNotify(newEditorInput);
//				setPartName(newEditorInput.getName());
//				IProgressMonitor progressMonitor = getProgressMonitor();
//				doSave(progressMonitor);
//			}
//		}
	}

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
	
	/**
	 * Handles what to do with changed resources on activation.
	 */
	void handleChangedResources(Collection<Resource> changedResources) {
		super.handleChangedResources(changedResources);
		boolean[] updates = new boolean[3];
		for (Resource resource : changedResources) {
			updates = getUpdateTuple(resource.getContents(), updates);
		}
		update(true, updates);
	}

	protected void update(boolean external, boolean... domains) {
		boolean mapping = domains[0], palette = domains[1], layout = domains[2];
		System.out.println((mapping ? "" : "!") + "mapping+" + (palette ? "" : "!") + "palette+" + (layout ? "" : "!") + "layout");
		if (mapping || palette || (layout && external)) {
			asyncExec(new Runnable() {
				public void run() {
					disposeFxControls();
					URI resourceURI = EditUIUtil.getURI(getEditorInput(), editingDomain.getResourceSet().getURIConverter());
					instance = new DecoratedModelInstance(editingDomain.getResourceSet(), resourceURI, blocksConfigProvider);
					updateFxControls();
				}
			});
		}
	}

	//
	
//	private class ResourceChangeHandler implements IResourceChangeListener, IResourceDeltaVisitor { 
//
//		Collection<Resource> changedResources = new ArrayList<Resource>();
//		Collection<Resource> removedResources = new ArrayList<Resource>();
//
//		public boolean visit(final IResourceDelta delta) {
//			// keep on visiting, if we haven't come to a FILE
//			if (delta.getResource().getType() != IResource.FILE) {
//				return true;
//			}
//			if (delta.getKind() == IResourceDelta.REMOVED || delta.getKind() == IResourceDelta.CHANGED) {
//				URI resourceURI = URI.createPlatformResourceURI(delta.getFullPath().toString(), true);
//				final Resource resource = editingDomain.getResourceSet().getResource(resourceURI, false);
//				if (resource != null) {
//					if (delta.getKind() == IResourceDelta.REMOVED) {
//						removedResources.add(resource);
//					} else if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
//						System.out.println("changed: " + resourceURI);
//						if (! changedResources.contains(resource)) {
//							System.out.println("added: " + resourceURI);
//							changedResources.add(resource);
//						} else {
//							System.out.println("already added: " + resourceURI);							
//						}
//					}
//				}
//			}
//			return false;
//		}
//
//		private Runnable updater = null;
//
//		public void resourceChanged(IResourceChangeEvent event) {
//			IResourceDelta delta = event.getDelta();
//			try {
//				delta.accept(this);
//				if (updater == null) {
//					updater = new Runnable() {
//						@Override
//						public void run() {
//							Collection<Resource> changed = reallyChanged(changedResources);
//							System.out.println("change: " + changed.size());
//							changedResources.clear();
//							if (changed != null && changed.size() > 0 && fxCanvas != null && (! fxCanvas.isDisposed())) {
//								System.out.println("Update editor");
//								fxCanvas.getDisplay().asyncExec(new Runnable() {
//									@Override
//									public void run() {
//										updater = null;
//										final Composite comp = fxCanvas.getParent();
//										disposePartControl();
//										updateModel();
//										updatePartControl(comp);
//										comp.redraw();
//									}
//								});
//							}					
//						}
//					};
//				}
//				if (fxCanvas != null && (! fxCanvas.isDisposed())) {
//					fxCanvas.getDisplay().asyncExec(updater);
//				}
//			}
//			catch (CoreException exception) {
//			}
//		}
//	}
	
//	private Collection<Resource> reallyChanged(Collection<Resource> changedResources) {
//		Collection<Resource> actually = new ArrayList<>();
//		for (Resource resource : changedResources) {
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			try {
//				resource.save(output, saveOptions);
//				output.close();
//				byte[] buffer1 = output.toByteArray();
//				InputStream inputStream = URIConverter.INSTANCE.createInputStream(resource.getURI());
//				byte[] buffer2 = new byte[buffer1.length];
//				int pos = 0, len = 0;
//				while ((len = inputStream.read(buffer2, pos, buffer2.length - pos)) >= 0) {
//					pos += len;
//					if (pos == buffer1.length) {
//						if (inputStream.read() != -1) {
//							actually.add(resource);
//						}
//						break;
//					}
//				}
//				if (pos < buffer1.length || (! Arrays.equals(buffer1, buffer2))) {
//					actually.add(resource);
//				}
//				inputStream.close();
//			} catch (IOException e) {
//			}
//		}
//		return null;
//	}
}
