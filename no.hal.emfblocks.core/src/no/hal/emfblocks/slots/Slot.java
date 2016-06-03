package no.hal.emfblocks.slots;

import java.util.Queue;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WCell;
import no.hal.emfblocks.WContainer;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.WSingleFieldContainer;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.javafx.FXSlot;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldElement;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyImmutable;
import no.hal.emfblocks.util.data.property.PropertyListener;

public abstract class Slot implements PFieldElement, WSingleFieldContainer<WBlock>, PropertyListener<Type> {
	/**
	 * The slot's type. Changing this property may cause connected blocks to be
	 * ejected.
	 */
	public final Property<Type> slotType = new Property<>(Type.class, null);
	/**
	 * The slot's background color. By default, this is null, indicating no
	 * background color.
	 */
	public final ObjectProperty<Color> bgColor = new ObjectPropertyBase<Color>() {
		@Override
		public Object getBean() {
			return Slot.this;
		}

		@Override
		public String getName() {
			return "Background color";
		}

	};

	/** The slot label text property. */
	public final ObjectProperty<String> labelTextProp = new ObjectPropertyBase<String>() {
		@Override
		public Object getBean() {
			return Slot.this;
		}

		@Override
		public String getName() {
			return "Label text";
		}
	};
	/** The slot label text property. */
	public final ObjectProperty<Image> labelIconProp = new ObjectPropertyBase<Image>() {
		@Override
		public Object getBean() {
			return Slot.this;
		}

		@Override
		public String getName() {
			return "Label image";
		}
	};

	private Boolean isHorizontal = null;
	private WCell cell = null;
	private FXSlot ui;

	protected final Referencer referencer = new ReferenceSet();

	private Property<Type> binding = null;

	public Slot(Type slotType) {
		this.slotType.set(slotType);
		// Eject blocks from slot if their type is no longer compatible
		this.slotType.addListener(referencer, (param, oldVal, newVal) -> {
			WBlock[] blocks = getChildren().toArray();
			for (WBlock b : blocks) {
				if (getChildren().contains(b) && accepts(b) != BlockDropAction.ACCEPT)
					b.eject();
			}
		});
	}

	@Override
	public void addNotify(PFieldImmutable<?> newField) {
		assert newField.getOwner() instanceof WCell : getClass().getSimpleName() + " can only be added to "
				+ WCell.class.getSimpleName() + "!";
		cell = (WCell) newField.getOwner();
		isHorizontal = cell.eastSlots == newField;
	}

	/**
	 * Returns whether this slot is on the east side of a cell. If false, it is
	 * on the south side.
	 */
	public boolean isHorizontal() {
		if (isHorizontal == null)
			throw new IllegalStateException(this + " has not been added to a cell!");
		return isHorizontal;
	}

	@Override
	public void pFieldChange(PFieldImmutable<? extends WBlock> field, WBlock newChild, int index) {
		if (newChild != null) {
			if (newChild instanceof WPointerBlock && newChild.tabType().get() == null) {
				((WPointerBlock) newChild).whenResolved(() -> {
					if (!slotType.get().accepts(newChild.tabType().get())) {
						throw new IllegalStateException("The pointer block " + newChild
								+ " was incorrectly added to this slot " + "before its reference was resolved! "
								+ slotType.get() + " is not assignable from " + newChild.tabType().get() + ".");
					}
				});
			} else if (!slotType.get().accepts(newChild.tabType().get())) {
				throw new IllegalArgumentException("The block " + newChild + " cannot be added to this slot because "
						+ "the slot's type(" + slotType.get() + ") is not assignable from the block's type("
						+ newChild.tabType().get() + ")");
			}
		}
	}

	@Override
	public WContainer<?> getParent() {
		return cell;
	}

	@Override
	public WRoot getRoot() {
		if (cell == null)
			throw new IllegalStateException(this + " has not been added to a cell!");
		return cell.getRoot();
	}

	@Override
	public Pane getInternalUI() {
		return getUI();
	}

	@Override
	public Pane getUI() {
		if (ui == null)
			ui = new FXSlot(this, cell.block.color(), false);
		return ui;
	}

	public WBlock getParentBlock() {
		return cell.block;
	}

	public void putTargetSlots(Queue<Slot> sq) {
		sq.offer(this);
		for (WBlock b : getChildren()) {
			b.putTargetSlots(sq);
		}
	}

	@Override
	public BlockDropAction accepts(WBlock dragBlock) {
		if (slotType.get().accepts(dragBlock.tabType().get()))
			return BlockDropAction.ACCEPT;
		else
			return BlockDropAction.CANCEL;
	}

	@Override
	public WBlock getBlockAt(Point2D localPoint) {
		PField<WBlock> children = getChildren();
		for (int i = children.size() - 1; i >= 0; i--) {
			WBlock b = children.get(i);
			Pane blkUI = b.getUI();
			Point2D slotPoint = FXUtil.fromTo(this.ui, blkUI, localPoint);
			WBlock blk = b.getBlockAt(slotPoint);

			if (blk != null)
				return blk;
		}
		return null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " on " + (cell == null ? "null" : getParentBlock().toString());
	}

	/**
	 * Called when the slot will no longer be available to the user. This
	 * happens when the parent block is disposed.
	 */
	public void dispose() {
		for (WBlock b : getChildren()) {
			b.dispose();
		}
	}

	public void copyChildrenTo(Slot other) {
		PField<WBlock> children = getChildren();
		if (children.size() > 1 && !other.isMultiSlot())
			System.err.println("Warning: Cannot copy children from " + this + " to " + other
					+ " because other is not a multi-slot");
		else {
			for (int i = 0; i < children.size(); i++) {
				other.getChildren().add(children.get(i).copy());
			}
		}
	}

	public abstract boolean isMultiSlot();

	public void bindType(Property<Type> concreteType) {
		unbindType();
		slotType.set(concreteType.get());
		concreteType.addListener(null, this);
		binding = concreteType;
	}

	public void unbindType() {
		if (binding != null) {
			binding.removeListener(null, this);
			binding = null;
		}
	}

	/** For binding type to another type property */
	@Override
	public void valueChanged(PropertyImmutable<? extends Type> property, Type oldValue, Type newValue) {
		if (property == binding)
			slotType.set(newValue);
	}
}
