package no.hal.emfblocks;

import java.util.Queue;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import no.hal.emfblocks.javafx.FXCell;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.javafx.SizedNode;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;
import no.hal.emfblocks.util.data.pfield.PFieldList;
import no.hal.emfblocks.util.data.property.Property;

public class WCell implements WContainer<Slot> {
	public final Property<SizedNode> contentPane = new Property<>(SizedNode.class, null);

	public final PFieldList<Slot> southSlots = new PFieldList<Slot>(this, Slot.class);
	public final PFieldList<Slot> eastSlots = new PFieldList<Slot>(this, Slot.class);

	public final int x, y;
	public final WBlock block;

	private FXCell ui;

	WCell(int x, int y, WBlock block) {
		this.x = x;
		this.y = y;
		this.block = block;
	}

	/**
	 * Use the indicated FXCellContent as this cell's "content pane". <br/>
	 * <br/>
	 * The content pane is displayed in the center of the cell and may contain
	 * objects such as a label or a text field. Note that all JavaFX objects
	 * must be wrapped in FXCellContent because the different JavaFX Nodes
	 * perform layout differently, use layout information in completely
	 * different ways, and supply different layout information. The
	 * FXCellContent remedies this, but implementations must take special care
	 * to update the FXCellContent's layout information when necessary.
	 */
	public void installContentPane(SizedNode n) {
		contentPane.set(n);
	}

	@Override
	public void pFieldChange(PFieldImmutable<? extends Slot> field, Slot newChild, int index) {
		if (newChild != null && newChild.isMultiSlot()) {
			// Slot-lists depend on the cell to the east or south for their
			// visuals.
			// Trigger the creation of the next cell:
			if (field == southSlots)
				block.getCell(x, y + 1);
			else if (field == eastSlots)
				block.getCell(x + 1, y);
		}
	}

	@Override
	public WContainer<?> getParent() {
		return block;
	}

	@Override
	public WRoot getRoot() {
		return block.getRoot();
	}

	@Override
	public Pane getUI() {
		if (ui == null)
			ui = new FXCell(this);
		return ui;
	}

	/**
	 * Get the block that this cell is associated with. This value is final and
	 * will not change.
	 */
	public WBlock getBlock() {
		return block;
	}

	@Override
	public void putTargetSlots(Queue<Slot> sq) {
		for (Slot s : southSlots)
			s.putTargetSlots(sq);
		for (Slot s : eastSlots)
			s.putTargetSlots(sq);
	}

	@Override
	public WBlock getBlockAt(Point2D localPoint) {
		for (Slot s : southSlots) {
			Pane slotUI = s.getUI();
			Point2D slotPoint = FXUtil.fromTo(this.ui, slotUI, localPoint);
			WBlock blk = s.getBlockAt(slotPoint);
			if (blk != null)
				return blk;
		}
		for (Slot s : eastSlots) {
			Pane slotUI = s.getUI();
			Point2D slotPoint = FXUtil.fromTo(this.ui, slotUI, localPoint);
			WBlock blk = s.getBlockAt(slotPoint);
			if (blk != null)
				return blk;
		}
		return null;
	}

	/**
	 * Called when the parent block will no longer be available to the user,
	 * typically when the parent block moved to the trash can.
	 */
	public void dispose() {
		for (Slot s : southSlots) {
			s.dispose();
		}
		for (Slot s : eastSlots) {
			s.dispose();
		}
	}

	public void copyChildrenTo(WCell other) {
		for (int i = 0; i < southSlots.size(); i++) {
			if (i >= other.southSlots.size())
				System.err.println("Warning: Cannot copy children from " + this + " to " + other
						+ " because other does not have a south slot at " + i);
			else {
				Slot s = southSlots.get(i);
				s.copyChildrenTo(other.southSlots.get(i));
			}
		}
		for (int i = 0; i < eastSlots.size(); i++) {
			if (i >= other.eastSlots.size())
				System.err.println("Warning: Cannot copy children from " + this + " to " + other
						+ " because other does not have a south slot at " + i);
			else {
				Slot s = eastSlots.get(i);
				s.copyChildrenTo(other.eastSlots.get(i));
			}
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + x + "," + y + ") on " + block;
	}
}
