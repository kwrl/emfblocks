package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.command.SetCommand;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import no.hal.emfblocks.editor.ui.FXSplittingTabPane;
import no.hal.emfblocks.editor.ui.FXSplittingTabPane.TabAddHandler;
import no.hal.emfblocks.editor.ui.FXSplittingTabPane.TabDeleteHandler;
import no.hal.emfblocks.editor.ui.FXSplittingTabPane.TabRearrangementCallback;
import no.hal.emfblocks.hierarchy.TypeHierarchy;
import no.hal.emfblocks.hierarchy.TypePrimitive;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.WorkAreaRepresentation;
import no.hal.emfblocks.mapping.WorkAreaMapping;

public class EMFWorkAreaManager implements TabAddHandler, TabDeleteHandler, TabRearrangementCallback {
	protected final EMFBlockEditor editor;
	protected final HashMap<Tab, EObject> rootObjectsByTab = new HashMap<>();
	protected final HashMap<EObject, EObjectWorkArea> rootsByObject = new HashMap<>();
	protected final OrphanWorkArea orphans;
	protected final Tab orphansTab;
	protected final LinkedHashMap<EClass, WorkAreaMapping> workAreaTypes;
	protected final FXSplittingTabPane split;

	protected boolean preventRecursion = false;
	protected Adapter resourceAdapter, layoutAdapter;

	public EMFWorkAreaManager(EMFBlockEditor editor) {
		this.editor = editor;
		workAreaTypes = new LinkedHashMap<EClass, WorkAreaMapping>();

		TypeHierarchy th = editor.root.getTypeHierarchy();
		for (int i = 0; i < th.getNumValues(); i++) {
			TypePrimitive t = th.getValue(i);
			if (t.getKey() instanceof EClass) {
				EClass c = (EClass) t.getKey();
				if (!c.isAbstract()) {
					WorkAreaMapping wamm = editor.getMappingModel().getWorkAreaMapping(c);
					if (wamm != null)
						workAreaTypes.put(c, wamm);
				}
			}
		}

		layoutAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (!preventRecursion && (msg
						.getFeature() == LayoutPackage.Literals.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX
						|| msg.getFeature() == LayoutPackage.Literals.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX)) {
					preventRecursion = true;
					WorkAreaRepresentation repr = (WorkAreaRepresentation) msg.getNotifier();
					EObject obj = repr.getObject();
					EMFWorkArea w;
					if (obj == null)
						w = orphans;
					else
						w = rootsByObject.get(obj);
					assert w != null;
					int tabIndex = repr.getPreferredTabIndex();
					int tabPaneIndex = (repr.getPreferredTabPaneIndex() == 1) ? 1 : 0;
					split.moveNode(w.getDecoratedUI(), tabPaneIndex, tabIndex);
					preventRecursion = false;
				}
			}
		};

		split = new FXSplittingTabPane(workAreaTypes.size() == 0 ? null : this, this, this, (e) -> {
			editor.root.defaultFocus();
		});
		split.setPrefWidth(480);
		split.setPrefHeight(480);
		orphans = new OrphanWorkArea(this);
		orphansTab = split.addNode(0, -1, "Resource", orphans.getDecoratedUI(), false, false);
		orphans.getRepresentation().eAdapters().add(layoutAdapter);

		// Add representations on startup:
		preventRecursion = true;
		try {
			for (EObject obj : editor.getModelInstance().getInstanceResource().getContents()) {
				WorkAreaMapping wamm = editor.getMappingModel().getWorkAreaMapping(obj.eClass());
				if (wamm != null) {
					EObjectWorkArea w = new EObjectWorkArea(this, wamm, obj);
					addObjectAsTab(w);
				}
			}
		} finally {
			preventRecursion = false;
		}

		resourceAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (!preventRecursion && msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
					preventRecursion = true;
					try {
						if (msg.getNewValue() == null) {
							if (msg.getOldValue() instanceof EObject) {
								if (editor.getMappingModel()
										.getWorkAreaMapping(((EObject) msg.getOldValue()).eClass()) != null) {
									boolean rem = removeObjectTab((EObject) msg.getOldValue());
									if (!rem)
										System.err.println("Warning: The object " + msg.getOldValue()
												+ " could not be found in the work area manager...");
								}
							}
						} else {
							if (msg.getNewValue() instanceof EObject) {
								WorkAreaMapping wamm = editor.getMappingModel()
										.getWorkAreaMapping(((EObject) msg.getNewValue()).eClass());
								if (wamm != null) {
									EObjectWorkArea w = new EObjectWorkArea(EMFWorkAreaManager.this, wamm,
											(EObject) msg.getNewValue());
									addObjectAsTab(w);
								}
							} else {
								System.err.println(
										"Warning: Weird message at " + EMFWorkAreaManager.class.getSimpleName() + ": \""
												+ msg + "\"\n" + "(" + msg.getNewValue() + " instanceof EObject == "
												+ (msg.getNewValue() instanceof EObject) + ")");
							}
						}
					} finally {
						preventRecursion = false;
					}
				}
			}
		};
		editor.getModelInstance().getInstanceResource().eAdapters().add(resourceAdapter);

		// TODO focused tab adapter
	}

	public Node getUI() {
		return split;
	}

	@Override
	public void addTab(FXSplittingTabPane thePane, int tabPaneIndex, ActionEvent buttonEvt) {
		// Called when the add tab button is pressed by the user.
		Button button = (Button) buttonEvt.getSource();
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		for (Entry<EClass, WorkAreaMapping> entry : workAreaTypes.entrySet()) {
			EClass c = entry.getKey();
			WorkAreaMapping wamm = entry.getValue();
			MenuItem item = new MenuItem("Add " + c.getName());
			item.onActionProperty().set((a) -> {
				// The user wants to create an object from the popup menu.
				// The new object needs to be added to the resource.
				EObject obj = c.getEPackage().getEFactoryInstance().create(c);
				assert thePane == split;
				EObjectWorkArea w = new EObjectWorkArea(this, wamm, obj);
				w.getRepresentation().setPreferredTabPaneIndex(tabPaneIndex);

				preventRecursion = true;
				assert !editor.getModelInstance().getInstanceResource().getContents().contains(obj) : editor
						.getModelInstance().getInstanceResource().getContents() + " already contains " + obj + "!";
				editor.getEditingDomain().getCommandStack()
						.execute(new ChangeCommand(editor.getModelInstance().getInstanceResource()) {
							protected void doExecute() {
								editor.getModelInstance().getInstanceResource().getContents().add(obj);
							}
						});
				assert editor.getModelInstance().getInstanceResource().getContents()
						.contains(obj) : editor.getModelInstance().getInstanceResource().getContents()
								+ " did not contain " + obj + " after the add command!";
				preventRecursion = false;
				addObjectAsTab(w);
			});
			items.add(item);
		}
		ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[items.size()]));
		FXUtil.contextMenuCreated(menu);
		menu.show(button, Side.BOTTOM, 0, 0);
	}

	private void addObjectAsTab(EObjectWorkArea w) {
		WorkAreaRepresentation waRepresentation = w.getRepresentation();
		// Note: Only locations 0 and 1 are legal. For now, anyway.
		int tabPaneIndex = (waRepresentation.getPreferredTabPaneIndex() == 1) ? 1 : 0;
		int tabIndex = waRepresentation.getPreferredTabIndex();

		EObject obj = w.getObject();
		if (contains(waRepresentation))
			throw new RuntimeException(this + " already contains the object " + obj + "! \n"
					+ "A duplicate add can happen due to an unusual sequence of operations; \n"
					+ "for example, if another entity moves a child from one index to another, \n"
					+ "records this using a ChangeRecorder, then undoes the operation, then \n"
					+ "the object will be added to the old index before it is removed from the \n"
					+ "new index. However, this will lead to fatal errors in the current \n"
					+ "implementation, and it is not trivial to fix. Note that the solution \n"
					+ "used in EMFSlots is not applicable here because the index in the EList \n"
					+ "cannot be assumed to be the same as the index in the Work Area Manager. \n"
					+ "The work area manager has no inherent order or indexing. \n");
		Tab t = split.addNode(tabPaneIndex, tabIndex, null, w.getDecoratedUI(), true, true);
		Label l = split.getLabel(t);
		l.textProperty().bind(w.labelProvider.labelTextProperty);
		rootObjectsByTab.put(t, obj);
		rootsByObject.put(obj, w);
		w.getRepresentation().eAdapters().add(layoutAdapter);
	}

	private boolean removeObjectTab(EObject obj) {
		// Called when the object has been removed by the undo system.
		assert obj != null;
		EObjectWorkArea w = rootsByObject.remove(obj);
		if (w == null)
			return false;
		w.getRepresentation().eAdapters().remove(layoutAdapter);
		w.dispose();
		Tab t = split.removeNode(w.getDecoratedUI());
		assert t != null;
		EObject rem = rootObjectsByTab.remove(t);
		assert rem == obj;
		return true;
	}

	@Override
	public void deleteTab(FXSplittingTabPane thePane, TabPane containerPane, Tab deletingTab, Event e) {
		// Called from the splitpane when a tab is deleted.
		// This means we need to remove the object from the resource.
		assert thePane == split;
		EObject theObject = rootObjectsByTab.remove(deletingTab);
		assert theObject != null;
		EObjectWorkArea w = rootsByObject.remove(theObject);
		assert w != null;
		assert w.getObject() == theObject;
		w.getRepresentation().eAdapters().remove(layoutAdapter);
		w.dispose();

		preventRecursion = true;
		assert editor.getModelInstance().getInstanceResource().getContents()
				.contains(theObject) : editor.getModelInstance().getInstanceResource().getContents()
						+ " did not contain " + theObject + "!";
		editor.getEditingDomain().getCommandStack()
				.execute(new ChangeCommand(editor.getModelInstance().getInstanceResource()) {
					protected void doExecute() {
						boolean rem = editor.getModelInstance().getInstanceResource().getContents().remove(theObject);
						assert rem;
					}
				});
		assert !editor.getModelInstance().getInstanceResource().getContents().contains(theObject) : editor
				.getModelInstance().getInstanceResource().getContents() + " still contained " + theObject
				+ " after the remove command!";
		preventRecursion = false;
	}

	public void dispose() {
		editor.getModelInstance().getInstanceResource().eAdapters().remove(resourceAdapter);
		orphans.dispose();
		orphans.getRepresentation().eAdapters().remove(layoutAdapter);
		for (EObjectWorkArea w : rootsByObject.values()) {
			w.dispose();
			w.getRepresentation().eAdapters().remove(layoutAdapter);
		}
	}

	public boolean contains(WorkAreaRepresentation rep) {
		return rootsByObject.containsKey(rep.getObject());
	}

	@Override
	public void tabMoved(FXSplittingTabPane thePane, TabPane targetTabPane, int tabIndex, Tab movedTab) {
		preventRecursion = true;
		ArrayList<Command> opList = new ArrayList<>();
		for (int i = 0; i < split.getNumTabPanes(); i++) {
			TabPane tp = split.getTabPane(i);
			for (int j = 0; j < tp.getTabs().size(); j++) {
				Tab t = tp.getTabs().get(j);
				assert t != null;
				EMFWorkArea w;
				if (t == orphansTab)
					w = orphans;
				else {
					EObject obj = rootObjectsByTab.get(t);
					assert obj != null;
					w = rootsByObject.get(obj);
				}
				assert w != null;
				opList.add(SetCommand.create(editor.getEditingDomain(), w.getRepresentation(),
						LayoutPackage.Literals.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX, i));
				opList.add(SetCommand.create(editor.getEditingDomain(), w.getRepresentation(),
						LayoutPackage.Literals.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX, j));
			}
		}

		editor.getEditingDomain().getCommandStack().execute(new FixedOrderCompoundCommand("Move Tab(s)", opList));
		preventRecursion = false;
	}

	public EMFBlockEditor getEditor() {
		return editor;
	}
}
