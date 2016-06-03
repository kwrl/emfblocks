package no.hal.emfblocks.editor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.function.Consumer;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.sun.javafx.scene.control.skin.TitledPaneSkin;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import no.hal.emfblocks.FutureWBlock;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WBlockBase;
import no.hal.emfblocks.WCell;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.WWorkArea;
import no.hal.emfblocks.editor.ui.AttributeFormFactory;
import no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.javafx.SizedNode;
import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.mapping.BlockFormMapping;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.SlotMapping;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.pfield.PFieldList;

/**
 * Block that represents an {@link EObject}. Under normal operation, each
 * {@link EObject} has at most one {@link EObjectBlock} representation, however
 * two can exist during an (invalid but momentary) transition state. The current
 * and future canonical representation can be accessed via the
 * {@link ReferenceResolver}.
 */
public class EObjectBlock extends WBlockBase implements CommandStackListener {
	public static boolean DEBUG = true;

	public final BlockLabelProvider labelProvider;
	protected final BlockMapping blockMappingModel;
	protected final BlockRepresentation representation;

	protected boolean preventRecursion = false;

	protected AdapterImpl myAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (!preventRecursion) {
				preventRecursion = true;
				if (msg.getFeature() == LayoutPackage.eINSTANCE.getBlockRepresentation_PreferredX()
						&& blockContainer().get() instanceof WWorkArea && pane != null)
					pane.setBlockLocation(msg.getNewIntValue(), getY());
				else if (msg.getFeature() == LayoutPackage.eINSTANCE.getBlockRepresentation_PreferredY()
						&& blockContainer().get() instanceof WWorkArea && pane != null)
					pane.setBlockLocation(getX(), msg.getNewIntValue());
				// else if(msg.getFeature() ==
				// EmfjigsawlayoutPackage.eINSTANCE.getBlockRepresentation_PreferredContainer())
				preventRecursion = false;
			}
		}
	};

	protected final EMFBlockEditor editor;

	public EObjectBlock(EMFBlockEditor editor, BlockMapping bmm, EObject obj) {
		this(editor, bmm, editor.getModelInstance().getOrCreateAndRegisterBlockRepresentation(obj, bmm));
	}

	public EObjectBlock(EMFBlockEditor editor, BlockMapping bmm, BlockRepresentation representation) {
		super(editor.getRoot(), representation.getObject().eClass(), bmm.getDefaultColor());
		this.editor = editor;
		this.representation = representation;

		this.labelProvider = new BlockLabelProvider(bmm, representation.getObject());

		this.representation.eAdapters().add(myAdapter);
		this.blockMappingModel = bmm;

		for (EReference e : getObject().eClass().getEAllReferences()) {
			createSlot(e);
		}
		editor.getReferenceResolver().register(referencer, this); // <- This was
																	// in
																	// addnotify.
																	// Was there
																	// a reason?
		editor.getEditingDomain().getCommandStack().addCommandStackListener(this);
	}

	@Override
	public FXBlock getUI() {
		boolean fresh = (pane == null);
		FXBlock ui = super.getUI();
		if (fresh) {
			ui.layoutXProperty().addListener((p, o, n) -> {
				if (!preventRecursion && blockContainer().get() instanceof WWorkArea) {
					preventRecursion = true;
					representation.setPreferredX((int) (double) n);
					preventRecursion = false;
				}
			});
			ui.layoutYProperty().addListener((p, o, n) -> {
				if (!preventRecursion && blockContainer().get() instanceof WWorkArea) {
					preventRecursion = true;
					representation.setPreferredY((int) (double) n);
					preventRecursion = false;
				}
			});
		}
		return ui;
	}

	@Override
	protected void addNotify2() {
		if (blockContainer().get() instanceof EObjectWorkArea) // TODO identify
																// if this is
																// valid
																// containment
																// or not,
																// invalid
																// containment
																// should still
																// have its tab
																// and have a
																// red outline
			tabVisible().set(false);
		else
			tabVisible().set(true);

		if (blockContainer().get() instanceof WWorkArea) {
			FXBlock ui = getUI();
			int x = representation.isSetPreferredX() ? representation.getPreferredX() : ui.getRealX();
			int y = representation.isSetPreferredY() ? representation.getPreferredY() : ui.getRealY();
			ui.setBlockLocation(x, y);
		}
	}

	protected void recursive(Consumer<WBlock> r) {
		for (ArrayList<WCell> a : table) {
			for (WCell c : a) {
				for (Slot s : c.eastSlots)
					((EMFSlot) s).recursive(r);
				for (Slot s : c.southSlots)
					((EMFSlot) s).recursive(r);
			}
		}
	}

	private int createEditor(GridPane target, int y, EAttribute e) {
		BlockFormMapping fmm = editor.getMappingModel().getBlockFormMapping(getObject().eClass(), e);
		if (fmm == null)
			return y;
		AttributeFormFactory formFactory = DefaultAttributeFormFactory
				.instanceOrDefault(fmm.getAttributeFormFactoryClass());
		Node[] nodes = formFactory.createForm(editor.getEditingDomain(), getObject(), e, destructionListeners);
		for (int x = 0; x < nodes.length; x++) {
			target.add(nodes[x], x, y);
		}
		return y + 1;
	}

	private void createSlot(EReference e) {
		try {
			SlotMapping smm = editor.getMappingModel().getSlotMapping(getObject().eClass(), e);
			if (smm != null) {
				WCell targetCell = getCell(smm.getCellX(), smm.getCellY());
				PFieldList<Slot> target = smm.isHorizontal() ? targetCell.eastSlots : targetCell.southSlots;
				Slot s;
				if (e.isMany())
					s = new EMFMultiSlot(editor, getObject(), e);
				else
					s = new EMFSingleSlot(editor, getObject(), e);
				target.add(s);
			}
		} catch (IllegalStateException err) {
			err.printStackTrace();
		} catch (IllegalArgumentException err) {
			if (DEBUG)
				System.out.println(err.getMessage());
		}
	}

	public EObject getObject() {
		return representation.getObject();
	}

	@Override
	public String getName() {
		return EMFBlockEditor.CONTAINMENT_SYMBOL + labelProvider.labelTextProperty.get();
	}

	@Override
	protected void installContentPanes() {
		SizedNode[] future = new SizedNode[1];
		Label label = new Label();
		label.textProperty().bind(Bindings.concat(EMFBlockEditor.CONTAINMENT_SYMBOL, labelProvider.labelTextProperty));
		GridPane gridpane = new GridPane();
		int y = 0;
		for (EAttribute e : ((EClass) tabType().get().getRawType().getKey()).getEAllAttributes()) {
			if (e.isChangeable())
				y = createEditor(gridpane, y, e);
		}
		final Node content;
		if (y == 0) {
			content = gridpane;
			gridpane.add(label, 0, 0, 2, 1);
		} else {
			TitledPane titlepane = new TitledPane() {
				@Override
				public void requestLayout() {
					super.requestLayout();
					if (future[0] != null && prefWidth(-1) >= getWidth())// DUMB.
						future[0].forceResize();
				}

				@Override
				protected Skin<?> createDefaultSkin() {
					// We want to redefine the titledpane's default behaviour so
					// that it expands/collapses on a mouse 'click', e.g. not
					// after dragging.
					// Dragging should allow the user to drag the block.
					TitledPaneSkin defaultSkin = new TitledPaneSkin(this);
					try {
						// Unfortunately, this behaviour is defined in the
						// private final field "titleRegion".
						// They've taken all the right steps to prevent us from
						// altering this behaviour
						// without reimplementing the entire skin, so we'll get
						// it using reflection.
						Field f = TitledPaneSkin.class.getDeclaredField("titleRegion");
						f.setAccessible(true);
						Node titleRegion = (Node) f.get(defaultSkin);

						// Move the existing event handler from mouseReleased to
						// mouseClicked.
						EventHandler<? super MouseEvent> expandDong = titleRegion.getOnMouseReleased();
						titleRegion.setOnMouseClicked(expandDong);
						// Redefine the release event to do nothing
						titleRegion.setOnMouseReleased((e) -> {
							// DO NOTHING YOU TWAT
						});
						// Allow root to take control of press, release and
						// drag.
						getRoot().registerListeners(titleRegion, true);
					} catch (Exception e) {
						throw new RuntimeException(
								"TitlePane implementation in this JRE is incompatible. Please revise.");
					}
					return defaultSkin;
				}
			};

			titlepane.setContent(gridpane);
			titlepane.setGraphic(label);
			titlepane.setExpanded(false);
			Pane voidPane = new Pane();
			VBox.setVgrow(titlepane, Priority.NEVER);
			VBox.setVgrow(voidPane, Priority.ALWAYS);
			content = new VBox(titlepane, voidPane);
		}
		future[0] = new SizedNode(content, SizedNode.Size.PREF_SIZE, true);
		getCell(0, 0).installContentPane(future[0]);
	}

	@Override
	public EObjectBlock copy() {
		return new EObjectBlock(editor, blockMappingModel, EcoreUtil.copy(getObject()));
	}

	@Override
	public WBlock shallowCopy() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void dispose2() {
		labelProvider.dispose();
		representation.eAdapters().remove(myAdapter);
		editor.getReferenceResolver().unregister(this);

		Platform.runLater(() -> // Avoid ConcurrentModificationException....
		{
			editor.getEditingDomain().getCommandStack().removeCommandStackListener(this);
		});
	}

	@Override
	public WPointerBlock makePointerBlock() {
		if (tabType().get() == null)
			throw new IllegalArgumentException(
					this + " does not have a tab type. Pointers to a block with no tab type is impossible.");

		FutureWBlock thisReference = editor.getReferenceResolver().getReference(representation.getObject());
		return new EObjectPointerBlock(editor, thisReference);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		if (!isDestroyed())// This can happen between dispose and
							// Platform.runLater.
		{
			if (!isPaletteBlock() && blockContainer().get() == null)// Note:
																	// Only need
																	// to
																	// dispose
																	// the
																	// topmost
																	// block
																	// since
																	// disposal
																	// is
																	// recursive.
			{
				System.out.println("INFO: " + this + " no longer exists due to an action.");
				dispose();
			}
		}
	}

	public BlockRepresentation getRepresentation() {
		return representation;
	}
}
