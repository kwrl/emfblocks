package no.hal.emfblocks;

public interface CellListener {
	/**
	 * Called when the given cell has been added to a block. The cell instance
	 * contains a reference to its parent block and the coordinate of the cell,
	 * if this is required.
	 */
	public void cellAdded(WCell c);
}
