package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WPalette;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.palette.Palette;
import no.hal.emfblocks.palette.PaletteFactory;
import no.hal.emfblocks.palette.PaletteItem;
import no.hal.emfblocks.palette.PaletteItemContainer;
import no.hal.emfblocks.palette.PaletteItemReference;
import no.hal.emfblocks.palette.PalettePackage;
import no.hal.emfblocks.palette.PaletteTab;

public class DecoratedPalette {
	private final Palette palette;

	/**
	 * @param palette
	 *            The resource containing the palette object to wrap. If none
	 *            exists, one will be created and added to the resource.
	 */
	public DecoratedPalette(Palette palette) {
		this.palette = palette;
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(String defaultTabName, EPackage ePackage, DecoratedMappingModel mappingModel) {
		populateMissingElements(defaultTabName, MMUtil.listClassifiers(ePackage), mappingModel);
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(String defaultTabName, Collection<EPackage> packageList,
			DecoratedMappingModel mappingModel) {
		populateMissingElements(defaultTabName, MMUtil.listClassifiers(packageList), mappingModel);
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(String defaultTabName, WRoot root, DecoratedMappingModel mappingModel) {
		populateMissingElements(defaultTabName, MMUtil.listClassifiers(root), mappingModel);
	}

	/**
	 * For any instantiable EClass that has no element in the palette, an
	 * element will be automatically spawned in the first tab with the default
	 * name(creating such a tab if necessary).
	 */
	public void populateMissingElements(String defaultTabName, EClassifier[] classifiers,
			DecoratedMappingModel mappingModel) {
		if (defaultTabName == null)
			defaultTabName = (String) PalettePackage.eINSTANCE.getPaletteTab_Name().getDefaultValue();
		HashSet<EClass> classesInPalette = new HashSet<EClass>();
		PaletteTab defaultTab = null;
		for (PaletteTab paletteTab : palette.getTabs()) {
			String tabName = paletteTab.getName();
			// See if this is the default tab
			if (defaultTabName.equals(tabName))
				defaultTab = paletteTab;
			// Find out which classes are already present
			Iterator<PaletteItem> it = paletteTab.getContents().iterator();
			while (it.hasNext()) {
				PaletteItem item = it.next();
				EObject element = item.getObject();
				if (element == null) {
					System.err.println("Warning: Palette contained a null item...");
					it.remove();
				} else if (item instanceof PaletteItemContainer) {
					// Add the class of this element to the set
					classesInPalette.add(element.eClass());
				}
			}
		}

		ArrayList<PaletteItem> newElements = new ArrayList<PaletteItem>();
		for (EClassifier e : classifiers) {
			if (e instanceof EClass && !((EClass) e).isAbstract() && !classesInPalette.contains(e)
					&& mappingModel.getBlockMapping((EClass) e) != null) {
				PaletteItem item = PaletteFactory.eINSTANCE.createPaletteItemContainer();
				item.setObject(EcoreUtil.create((EClass) e));
				newElements.add(item);
			}
		}
		if (newElements.size() > 0) {
			if (defaultTab == null) {
				defaultTab = PaletteFactory.eINSTANCE.createPaletteTab();
				defaultTab.setName(defaultTabName);
				EList<PaletteTab> tabsList = palette.getTabs();
				tabsList.add(defaultTab);
			}
			EList<PaletteItem> defaultTabContents = defaultTab.getContents();
			defaultTabContents.addAll(newElements);
		}
	}

	/**
	 * Adds a , //TODO in the first tab with the default name(creating such a
	 * tab if necessary).
	 */
	public void addMissingImmutableInstances(String defaultTabName, Collection<EObject> objects) {
		if (defaultTabName == null)
			defaultTabName = (String) PalettePackage.eINSTANCE.getPaletteTab_Name().getDefaultValue();
		HashSet<EObject> literalsInPalette = new HashSet<EObject>();
		PaletteTab defaultTab = null;
		for (PaletteTab paletteTab : palette.getTabs()) {
			String tabName = paletteTab.getName();
			// See if this is the default tab
			if (defaultTabName.equals(tabName))
				defaultTab = paletteTab;
			// Find out which classes are already present
			Iterator<PaletteItem> it = paletteTab.getContents().iterator();
			while (it.hasNext()) {
				PaletteItem item = it.next();
				EObject element = item.getObject();
				if (element == null) {
					System.err.println("Warning: Palette contained a null item...");
					it.remove();
				} else if (item instanceof PaletteItemReference) {
					// Add the element to the set
					literalsInPalette.add(element);
				}
			}
		}

		ArrayList<PaletteItem> newElements = new ArrayList<PaletteItem>();
		for (EObject e : objects) {
			if (!literalsInPalette.contains(e)) {
				PaletteItem item = PaletteFactory.eINSTANCE.createPaletteItemReference();
				item.setObject(e);
				newElements.add(item);
			}
		}
		if (newElements.size() > 0) {
			if (defaultTab == null) {
				defaultTab = PaletteFactory.eINSTANCE.createPaletteTab();
				defaultTab.setName(defaultTabName);
				EList<PaletteTab> tabsList = palette.getTabs();
				tabsList.add(defaultTab);
			}
			EList<PaletteItem> defaultTabContents = defaultTab.getContents();
			defaultTabContents.addAll(newElements);
		}
	}

	public Palette getPaletteInstance() {
		return palette;
	}

	public Node createUI(EMFBlockEditor editor) {
		EList<PaletteTab> tabsList = palette.getTabs();

		TabPane tp = new TabPane();// TODO handle single tab mode

		for (PaletteTab paletteTab : tabsList) {
			String tabName = paletteTab.getName();

			ArrayList<WBlock> paletteBlocks = new ArrayList<>();
			for (PaletteItem item : paletteTab.getContents()) {
				if (item.isEnabled()) {
					if (item instanceof PaletteItemReference)
						paletteBlocks.add(new EcorePointerBlock(editor, item.getObject()));
					else {
						EObject element = item.getObject();
						BlockMapping bmm = editor.getMappingModel().getBlockMapping(element.eClass());
						if (bmm != null) {
							paletteBlocks.add(new EObjectBlock(editor, bmm,
									editor.getModelInstance().createBlockRepresentation(element, bmm, false)));
						} else {
							System.err.println("Warning: Object " + element
									+ " was found in the palette, but it has no block mapping model.");
						}
					}
				}
			}

			WPalette pal = new WPalette(editor.getRoot(), paletteBlocks);
			ScrollPane scroller = new ScrollPane(pal.getUI());
			scroller.setFitToWidth(true);
			scroller.setFitToHeight(true);

			// TODO handle single tab mode
			Tab tab = new Tab(tabName, scroller);
			tab.setClosable(false);
			tp.getTabs().add(tab);
		}

		return tp;
	}

	public void save() {
		DecoratedModelInstance.save("Palette", palette.eResource());
	}

	public boolean containsLiteral(EObject target) {
		for (PaletteTab t : palette.getTabs()) {
			for (PaletteItem i : t.getContents()) {
				if (i instanceof PaletteItemReference && i.getObject() == target)
					return true;
			}
		}
		return false;
	}
}
