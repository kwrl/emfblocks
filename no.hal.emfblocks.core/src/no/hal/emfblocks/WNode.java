package no.hal.emfblocks;

import java.util.Queue;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.pfield.PFieldElement;

public interface WNode extends PFieldElement {
	public WContainer<?> getParent();

	public WRoot getRoot();

	/**
	 * Returns the UI node of this object. Take special note that
	 * {@link WSingleFieldContainer} defines a separate method
	 * {@link WSingleFieldContainer#getInternalUI()} to retrieve the UI node
	 * where the UIs of the child objects are contained.
	 */
	public Pane getUI();

	/** Enqueue all slots that are potential drop targets in this subtree */
	public void putTargetSlots(Queue<Slot> sq);

	/**
	 * Returns the block at the indicated position in local space. If the
	 * position is out of bounds, then this will return null.
	 */
	public WBlock getBlockAt(Point2D localPoint);

	/** Get the nearest WPane upstream in the hierarchy, null if none. */
	public default WPane getWPane() {
		if (this instanceof WPane)
			return (WPane) this;
		WContainer<?> p = getParent();
		if (p == null)
			return null;
		return p.getWPane();
	}

	/**
	 * Returns whether this node is connected to a root via its parent chain.
	 */
	public default boolean isContained() {
		if (this instanceof WRoot)// Just for clarity.
			return true;

		WContainer<?> p = getParent();
		if (p instanceof WRoot) {
			if (p != getRoot())
				new Throwable(this + " is connected to the root " + p + ", but " + getRoot()
						+ " was returned from getRoot()!").printStackTrace();
			return true;
		} else if (p == null)
			return false;
		else
			return p.isContained();
	}
}
