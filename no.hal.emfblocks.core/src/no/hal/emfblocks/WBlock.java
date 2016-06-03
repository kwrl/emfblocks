package no.hal.emfblocks;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.javafx.ActionSource;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public interface WBlock extends WNode, WContainer<WCell> {
	/**
	 * Copy the block and all child blocks. Note that pointer blocks will not
	 * copy the block that they are referencing.
	 * 
	 * @return A copy of this block. Note that even if this is a palette block,
	 *         then the copy will not be a palette block.
	 */
	public WBlock copy();

	/** Set the location of the block in its current parent. */
	public void setLocation(int x, int y);

	/** Get the x-position of the block in its parent. */
	public int getX();

	/** Get the y-position of the block in its parent. */
	public int getY();

	/**
	 * Whether this block is a palette block. Palette blocks can be copied, but
	 * they are not valid drop targets and they cannot be moved.
	 */
	public boolean isPaletteBlock();

	/**
	 * The block container. This property will be updated when the block is
	 * moved between parents; it may become a {@link Slot}, a {@link WPane}, or
	 * the {@link WRoot}. If the application wants to move the block to a new
	 * parent, it should add the block to the new parent's child list. This
	 * property cannot be modified directly.
	 */
	public PropertyReadOnly<WSingleFieldContainer<? super WBlock>> blockContainer();

	/**
	 * @deprecated Use {@link #blockContainer()}.{@link PropertyReadOnly#get()}
	 *             instead. This method constitutes duplicate functionality and
	 *             should be avoided to simplify code analysis and refactoring.
	 */
	@Deprecated
	@Override
	public default WContainer<?> getParent() {
		return blockContainer().get();
	}

	/**
	 * The block's tab type. The application is allowed to change this property.
	 */
	public Property<Type> tabType();

	/** Whether to show the block's tab. */
	public Property<Boolean> tabVisible();

	/**
	 * The block's color. The application is allowed to change this property.
	 */
	public ObjectProperty<Color> color();

	/** The number of rows in this block. */
	public int getNumRows();

	/**
	 * The number of columns in the row with the given index, in number of
	 * {@link WCell}s. The width of the first row will always be equal to the
	 * width of the block.
	 */
	public int getNumColumnsAt(int row);

	/**
	 * Get the cell at the given position. If the cell does not exist, then this
	 * will trigger its creation. Note that this may create additional cells:
	 * 
	 * <pre>
	 * -At row <b>0</b>, cells will be created up to column <b>x</b>, inclusive
	 * -At row <b>y</b>, cells will be created up to column <b>x</b>, inclusive
	 * </pre>
	 * 
	 * @param x
	 *            The column index
	 * @param y
	 *            The row index
	 * @return The cell at the given position.
	 */
	public WCell getCell(int x, int y);

	/**
	 * Add a listener to be notified of the creation of new cells. (Note that it
	 * is not possible to remove cells once they have been created.) The caller
	 * must remember to call {@link #removeCellListener(CellListener)} when it
	 * no longer wishes to receive events, or it cannot be garbage collected.
	 */
	public void addCellListener(CellListener l);

	/**
	 * Remove a cell listener, so that it no longer recives events when new
	 * cells are created.
	 */
	public boolean removeCellListener(CellListener l);

	/**
	 * Returns the block at the indicated position in local space. If the
	 * position is out of bounds, then this will return null.
	 */
	public WBlock getBlockAt(Point2D localPoint);

	/**
	 * Eject the block from the parent slot, placing it in the pane. If the pane
	 * does not accept the block, then it will disappear.
	 */
	public void eject();

	/**
	 * Create a pointer block with a reference to this block. If the operation
	 * is not supported, null may be returned.
	 */
	public WPointerBlock makePointerBlock();

	/** Returns whether the block has already been disposed. */
	boolean isDestroyed();

	/**
	 * Called when the block will no longer be available to the user, typically
	 * when moved to the trash can.
	 */
	public void dispose();

	@Override
	public FXBlock getUI();

	/**
	 * Returns the concrete type property with the given name.
	 * 
	 * @throws IllegalStateException
	 *             If the block's tab type is a raw type.
	 * @throws IllegalArgumentException
	 *             If the block's tab type does not currently have a type
	 *             parameter with the given name.
	 */
	public Property<Type> concreteType(String pname) throws IllegalStateException, IllegalArgumentException;

	/** Returns the list of destruction listeners */
	public ActionSource getDestructionListeners();

	/**
	 * Return a human readable name for this block. Is not necessarily unique.
	 */
	public String getName();
}
