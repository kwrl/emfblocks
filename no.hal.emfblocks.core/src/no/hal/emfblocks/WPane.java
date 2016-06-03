package no.hal.emfblocks;

import java.util.Queue;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;
import no.hal.emfblocks.util.data.pfield.PFieldList;

public abstract class WPane implements WNode, WSingleFieldContainer<WBlock> {
	protected WRoot root;

	public final PFieldList<WBlock> children = new PFieldList<>(this, WBlock.class);
	protected final Referencer referencer = new ReferenceSet();

	public WPane(WRoot root) {
		this.root = root;
		root.childPaneCreated(this);
	}

	protected final void setUI(Node ui) {
		setUIImpl(ui);
		root.childPaneUICreated(this);
	}

	protected abstract void setUIImpl(Node ui);

	@Override
	public void addNotify(PFieldImmutable<?> newField) {
		assert root == newField.getOwner() : this
				+ " cannot be added to a different root than it was initialized with!";
	}

	@Override
	public void removeNotify(PFieldImmutable<?> fromField) {
		assert root == fromField.getOwner() : this
				+ " cannot be removed from a different root than it was initialized with!";
	}

	@Override
	public PField<WBlock> getChildren() {
		return children;
	}

	@Override
	public WContainer<?> getParent() {
		return root;
	}

	@Override
	public WRoot getRoot() {
		return root;
	}

	public abstract BlockDropAction accepts(WBlock b);

	@Override
	public void putTargetSlots(Queue<Slot> sq) {
		if (this instanceof WPalette)
			return;
		for (WBlock c : children)
			c.putTargetSlots(sq);
	}

	/**
	 * Returns the block at the indicated position in local space. If the
	 * position is out of bounds, then this will return null.
	 */
	public WBlock getBlockAt(Point2D panePoint) {
		int searchIndex = -1;
		WBlock candidate = null;
		for (WBlock c : children) {
			Parent ui = c.getUI();
			Point2D blkPoint = ui.sceneToLocal(getInternalUI().localToScene(panePoint));
			WBlock blk = c.getBlockAt(blkPoint);
			if (blk != null) {
				int i = getInternalUI().getChildrenUnmodifiable().indexOf(ui);
				if (searchIndex == -1 || i > searchIndex) {
					if (i == -1)
						System.err.println("Warning: " + blk + " could not be located on " + this);
					candidate = blk;
					searchIndex = i;
				}
			}
		}
		return candidate;
	}
}
