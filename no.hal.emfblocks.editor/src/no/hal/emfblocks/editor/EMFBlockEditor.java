package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.Collection;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import no.hal.emfblocks.TransactionManager;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.WTrashCan;
import no.hal.emfblocks.editor.types.EMFTypePrimitive;
import no.hal.emfblocks.hierarchy.TypeHierarchy;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.PointerArrowMode;
import no.hal.emfblocks.util.data.property.Property;

public class EMFBlockEditor extends AdapterImpl {
	protected final WRoot root;
	protected final ReferenceResolver referenceResolver;
	protected final EMFWorkAreaManager man;
	protected final EditingDomain editingDomain;
	protected final ComposedAdapterFactory adapterFactory;
	protected final DecoratedModelInstance instance;

	public final Property<PointerArrowMode> showPointerArrows;

	public final ScriptEngine jsEngine;
	public static final String CONTAINMENT_SYMBOL = "";
	public static final String POINTER_SYMBOL = "\u25C7";

	public EMFBlockEditor(EditingDomain editingDomain, ComposedAdapterFactory adapterFactory, DecoratedModelInstance instance) {
		ScriptEngineManager manager = new ScriptEngineManager();
		jsEngine = manager.getEngineByName("js");

		this.editingDomain = editingDomain;
		this.adapterFactory = adapterFactory;
		this.referenceResolver = new ReferenceResolver(this);

		Collection<EMFTypePrimitive> types = new ArrayList<>();

		for (EPackage pack : instance.getPackages()) {
			addClassifierTypes(pack, types);
		}
		// TODO: these should have default graphics and not influence shape generation
		addClassifierTypes(EcorePackage.eINSTANCE, types);

		TypeHierarchy th = new TypeHierarchy(instance.getCutsDirectory(),
				(k) -> (k instanceof EClassifier ? MMUtil.fullyQualifiedName((EClassifier) k) : String.valueOf(k)),
				types.toArray(new EMFTypePrimitive[types.size()])
		);
		// TODO: add default graphics for ecore types
		TransactionManager transactionManager = new EMFTransactionManager(this);

		root = new WRoot(th, transactionManager);

		this.instance = instance;

		showPointerArrows = new Property<>(instance.getLayoutInstance().getShowPointerArrows());
		instance.getLayoutInstance().eAdapters().add(this);

		man = new EMFWorkAreaManager(this);
	}

	protected void addClassifierTypes(EPackage pack, Collection<EMFTypePrimitive> types) {
		for (EClassifier classifier : pack.getEClassifiers()) {
			types.add(EMFTypePrimitive.createTypePrimitive(classifier));
		}
	}

	public void installUI(Parent wRootContent, Pane workAreaParent, Pane paletteParent, Pane trashParent) {
		Node paletteUI = instance.getPalette().createUI(this);
		WTrashCan trash = new WTrashCan(root);		
		workAreaParent.getChildren().setAll(man.getUI());
		paletteParent.getChildren().setAll(paletteUI);
		trashParent.getChildren().setAll(trash.getUI());
		Pane pane = (Pane) wRootContent.getParent();
		pane.getChildren().remove(wRootContent);
		root.installContentPane(wRootContent);
		pane.getChildren().setAll(root.getUI());
	}
	
	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeature() == LayoutPackage.Literals.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS) {
			showPointerArrows.set((PointerArrowMode) msg.getNewValue());
		}
	}

	public WRoot getRoot() {
		return root;
	}

	public DecoratedMappingModel getMappingModel() {
		return instance.getMappingModel();
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	public ReferenceResolver getReferenceResolver() {
		return referenceResolver;
	}
	/*
	 * public EMFWorkAreaManager getWorkAreaManager() { return man; }
	 */

	public DecoratedModelInstance getModelInstance() {
		return instance;
	}

	public void dispose() {
		man.dispose();
		instance.getLayoutInstance().eAdapters().remove(this);
	}
}
