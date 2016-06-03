package no.hal.emfblocks.javafx;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import no.hal.emfblocks.CellListener;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WCell;
import no.hal.emfblocks.WContainer;
import no.hal.emfblocks.WSingleFieldContainer;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;

public class FXBlock extends Pane {
	private static final byte H = 0b01;
	private static final byte V = 0b10;
	private static final byte H_AND_V = 0b11;

	// Nested layouts have no chain of command,
	// there are way too many layout properties,
	// and it is impossible to tell which of
	// them will be meaningful at a given time,
	// and their meaning changes depending on
	// the implementation class.
	//
	// If queried at the wrong time, each of
	// these properties will contain arbitrary
	// values which cannot be used. The
	// preferred size mechanism acts in an
	// unpredictable way and is sometimes
	// ignored.
	//
	// To make something that works, it's best
	// to make our own layout properties from
	// the ground up and ignore the existing
	// mechanisms as much as possible.
	protected class LayoutInfo {
		protected boolean isLayouting = false;
		protected boolean ignoreRecursiveLayout = false;

		protected int x;
		protected int y;

		protected Integer assignedW;
		protected Integer assignedH;
		protected int prefW;
		protected int prefH;

		public LayoutInfo() {
			x = (int) layoutXProperty().get();
			y = (int) layoutYProperty().get();
			recompute(null, null);
		}

		protected void recompute(Integer assignedW, Integer assignedH) {
			if (ignoreRecursiveLayout)
				return;
			if (isLayouting)
				new Throwable("Warning: Attempt to recompute while layout is already in progress: ").printStackTrace();
			else {
				isLayouting = true;

				this.assignedW = assignedW;
				this.assignedH = assignedH;

				prefW = 0;
				prefH = 0;

				int[] colWidths;
				int[] rowHeights;
				int numRows = cells.size();
				rowHeights = new int[numRows];
				colWidths = new int[0];
				int numCols = 0;
				for (int y = 0; y < numRows; y++) {
					int rowHeight = 0;
					ArrayList<FXCell> row = cells.get(y);
					numCols = Math.max(numCols, row.size());
					if (colWidths.length < numCols)
						colWidths = Arrays.copyOf(colWidths, numCols);
					for (int x = 0; x < row.size(); x++) {
						FXCell cell = row.get(x);
						assert cell.layoutListeners.hasListener(layoutListener) : "ERROR: Cell " + cell + " on "
								+ FXBlock.this + " has never been registered!";
						colWidths[x] = Math.max(colWidths[x], cell.layout.prefW);
						rowHeight = Math.max(rowHeight, cell.layout.prefH);
					}
					rowHeights[y] = rowHeight;
					prefH += rowHeight;
				}
				for (int x = 0; x < numCols; x++)
					prefW += colWidths[x];

				if (assignedW != null) {
					int freeW = assignedW - prefW;
					int incrW = freeW / numCols;
					if (freeW < 0)
						new Throwable("Warning: Free width is negative: " + freeW + " with " + numCols)
								.printStackTrace();

					for (int x = 0; x < numCols; x++) {
						colWidths[x] += incrW;
						freeW -= incrW;
					}
					assert freeW >= 0 || freeW < 0 : "Arithmetic error: " + freeW + "%" + numCols
							+ " gave a negative result!";
					// Last one receives rounding error
					colWidths[numCols - 1] += freeW;

					int freeH = assignedH - prefH;
					int incrH = freeH / numRows;
					if (freeH < 0)
						new Throwable("Warning: Free height is negative: " + freeH + " with " + numRows)
								.printStackTrace();
					for (int y = 0; y < numRows; y++) {
						rowHeights[y] += incrH;
						freeH -= incrH;
					}
					// Last one receives rounding error
					rowHeights[numRows - 1] += freeH;
				}
				//
				int compoundY = 0;
				for (int y = 0; y < cells.size(); y++) {
					int compoundX = 0;
					ArrayList<FXCell> row = cells.get(y);
					for (int x = 0; x < row.size(); x++) {
						FXCell c = row.get(x);
						c.setCellLocation(compoundX, compoundY);
						// Important: we must assign a size to all children:
						c.setCellSize(colWidths[x], rowHeights[y]);
						// c.setCellSize(c.layout.prefW, c.layout.prefH);
						compoundX += colWidths[x];
					}
					compoundY += rowHeights[y];
				}

				isLayouting = false;
				// The assigned size will be non-null iff the layout call comes
				// from the parent in the layout tree.
				// So, if it was null, notify the parent so it can update the
				// size.
				if (assignedW == null)
					layoutListeners.fireEvents();

				requestLayout();
			}
		}

		/**
		 * Returns the assigned height, or the preferred height if none has been
		 * assigned.
		 */
		public int getHeight() {
			if (assignedH == null)
				return prefH;
			return assignedH;
		}

		/**
		 * Returns the assigned width, or the preferred width if none has been
		 * assigned.
		 */
		public int getWidth() {
			if (assignedW == null)
				return prefW;
			return assignedW;
		}
	}

	public static final int CONTAINS_SLACK = 6;

	public static final int INDENTATION_OFFSET = 6;
	public static final int COMPONENT_SPACING = 4;
	public static final int TAB_OFFSET = 2;
	public static final int OPEN_C_BLOCK_SPACING = 13;

	protected boolean authorizeLocChange;
	protected LayoutInfo layout;
	protected final ActionSource layoutListeners = new ActionSource();
	public final ActionSource publicLayoutListeners = new ActionSource();
	protected Referencer referencer = new ReferenceSet();
	protected final EventHandler<ActionEvent> layoutListener;

	/**
	 * Tab images. Warning: These may become null or be exchanged at any time,
	 * and their location and size are not memorized. No computations should
	 * depend on them.
	 */
	protected SlackedGroup hTab, vTab;

	protected ArrayList<ArrayList<FXCell>> cells;
	/**
	 * Polygon outline. Warning: This may be exchanged at any time, and its
	 * location and size are not memorized. No computations should depend on it.
	 */
	protected Polygon outline;
	protected WBlock model;

	public FXBlock(WBlock model) {
		this.model = model;
		setPickOnBounds(false);
		setFocusTraversable(true);
		authorizeLocChange = model.isPaletteBlock();
		// Note: If WBlock outlives FXBlock then we need to remove a lot of
		// listeners, but we are assuming it doesn't.
		model.color().addListener((e) -> {
			if (outline != null) {
				outline.setFill(model.color().get());
				outline.setStroke(new Color(model.color().get().getRed() / 2, model.color().get().getGreen() / 2,
						model.color().get().getBlue() / 2, 1));
			}
		});
		// Note:
		// JavaFX flips its shit if the pressed component is removed,
		// such as when changing the tabs when dragging the component.
		// This makes it important to modify as few components as
		// possible, hence the bitmask.
		rebuildTabs(H_AND_V);
		model.tabType().addListener(referencer, (prop, oldVal, newVal) -> {
			if (oldVal == null ? newVal != null : (!oldVal.equals(newVal)))
				rebuildTabs(H_AND_V);
		});
		model.tabVisible().addListener(referencer, (prop, oldVal, newVal) -> {
			if (!oldVal.equals(newVal))
				rebuildTabs(H_AND_V);
		});
		// Note: tabs are layout-irrelevant.
		model.getRoot().getTypeHierarchy().connectorWidth.addListener(referencer, (prop, oldVal, newVal) -> {
			rebuildTabs(H_AND_V);
		});
		model.getRoot().getTypeHierarchy().connectorHeight.addListener(referencer, (prop, oldVal, newVal) -> {
			rebuildTabs(H_AND_V);
		});
		model.blockContainer().addListener(referencer, (prop, oldVal, newVal) -> {
			if (isSlot(oldVal, false) != isSlot(newVal, false))
				rebuildTabs(H);
			else
				updateTabPattern(hTab, newVal);
			if (isSlot(oldVal, true) != isSlot(newVal, true))
				rebuildTabs(V);
			else
				updateTabPattern(vTab, newVal);

			// -Do not recompute layout if we were just removed.
			// -Do not recompute layout if we were added to a slot, because then
			// the slot will
			// query our preferred size, which is still correct, and assign a
			// new width.
			// -If we were added to something that is not a slot, then we need
			// to reset our
			// size to the preferred size(do not keep the assigned size).
			if (newVal != null && !(newVal instanceof Slot)) {
				layout.recompute(null, null);
			}
		});

		layoutXProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException("Unauthorized layout x change for: " + this
						+ " (non-palette block in a layout pane is illegal)");
			// Note: Uncontrolled changes can happen to palette blocks.
			layout.x = (int) layoutXProperty().get();
		});
		layoutYProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException("Unauthorized layout y change for: " + this
						+ " (non-palette block in a layout pane is illegal)");
			// Note: Uncontrolled changes can happen to palette blocks.
			layout.y = (int) layoutYProperty().get();
		});

		// Create a reusable listener for the layout of cells
		layoutListener = ((e) -> {
			if (layout != null)
				layout.recompute(null, null);
		});
		cells = new ArrayList<>();
		for (int y = 0; y < model.getNumRows(); y++) {
			ArrayList<FXCell> row = new ArrayList<>();
			for (int x = 0; x < model.getNumColumnsAt(y); x++) {
				FXCell cell = (FXCell) model.getCell(x, y).getUI();
				cell.layoutListeners.addListener(layoutListener);
				row.add(cell);
				getChildren().add(cell);
			}
			cells.add(row);
		}
		model.addCellListener(new CellListener() {
			@Override
			public void cellAdded(WCell c) {
				while (c.y >= cells.size())
					cells.add(new ArrayList<>());
				ArrayList<FXCell> row = cells.get(c.y);
				assert c.x == row.size() : "Internal error: " + FXBlock.this + " was not notified of new columns; "
						+ "view-row width at " + c.y + " was " + row.size() + " but should be " + c.x;
				FXCell cell = (FXCell) c.getUI();
				cell.layoutListeners.addListener(layoutListener);
				row.add(cell);
				getChildren().add(cell);
				layout.recompute(null, null);
			}
		});
		model.getRoot().dragging.addListener(referencer, (prop, index, oldVal, newVal) -> {
			updateBorderEffect();
		});
		focusedProperty().addListener((b, o, n) -> {
			updateBorderEffect();
		});
		updateBorderEffect();
		layout = new LayoutInfo();
	}

	private void updateBorderEffect() {
		if (model.getRoot().dragging.get() == model)
			setEffect(new DropShadow());
		else if (isFocused())
			setEffect(new DropShadow(2.0, Color.BLACK));
		else
			setEffect(null);
	}

	/** Returns the x of the block */
	public int getRealX() {
		return layout.x;
	}

	/** Returns the y of the block */
	public int getRealY() {
		return layout.y;
	}

	/**
	 * Returns the width of the block, excluding tabs or exterior attached
	 * blocks.
	 */
	public int getRealWidth() {
		return layout.getWidth();
	}

	/**
	 * Returns the height of the block, excluding tabs or exterior attached
	 * blocks.
	 */
	public int getRealHeight() {
		return layout.getHeight();
	}

	public void setBlockLocation(int x, int y) {
		authorizeLocChange = true;
		layout.x = x;
		layout.y = y;
		layoutXProperty().set(x);
		layoutYProperty().set(y);
		authorizeLocChange = model.isPaletteBlock();
	}

	public void setBlockSize(int w, int h) {
		if (w < layout.prefW)
			new Throwable("Warning: Assigned width(" + w + ") is less than preferred width(" + layout.prefW + ")!")
					.printStackTrace();
		if (h < layout.prefH)
			new Throwable("Warning: Assigned height(" + h + ") is less than preferred height(" + layout.prefH + ")!")
					.printStackTrace();
		layout.recompute(w, h);
	}

	/**
	 * Updates the tab rendering pattern to the indicated container. If the
	 * container is a slot, then the rendering pattern is bound to the slot's
	 * type. Otherwise, it is set to the type of the tab itself(meaning all
	 * levels of the tab will be rendered).
	 * 
	 * @see FXParameterizedType#setPattern(Type)
	 */
	private void updateTabPattern(SlackedGroup tab, WSingleFieldContainer<? super WBlock> blockContainer) {
		if (tab != null && tab instanceof FXParameterizedType) {
			FXParameterizedType pTab = (FXParameterizedType) tab;
			if (blockContainer instanceof Slot)
				pTab.setPattern(((Slot) blockContainer).slotType);
			else
				pTab.setPattern(pTab.getPrimaryType());
		}
	}

	/**
	 * Rebuild the tab(s) according to the mask with the given type, for the
	 * given container.
	 * 
	 * @param tabMask
	 *            {@link #H}, {@link #V} or {@link #H_AND_V}, depending on what
	 *            tabs need to be rebuilt.
	 */
	private void rebuildTabs(byte tabMask) {
		// Type The (new) type of the tab(s).
		Type tabType = model.tabType().get();

		// If the container is a slot and the tab type is a parameterized type,
		// then rendering will be restricted to the number of levels in the
		// slot's type.
		WSingleFieldContainer<? super WBlock> blockContainer = model.blockContainer().get();

		if ((tabMask & H) != 0) {

			if (hTab != null) {
				getChildren().remove(hTab);
				hTab = null;
			}
			if (tabType != null && model.tabVisible().get() && !isSlot(model.blockContainer().get(), false)) {
				hTab = tabType.createNode(true, true, model.color(), null);
				updateTabPattern(hTab, blockContainer);
				getChildren().add(hTab);
			}
		}
		if ((tabMask & V) != 0) {
			if (vTab != null) {
				getChildren().remove(vTab);
				vTab = null;
			}
			if (tabType != null && model.tabVisible().get() && !isSlot(model.blockContainer().get(), true)) {
				vTab = tabType.createNode(true, false, model.color(), null);
				updateTabPattern(vTab, blockContainer);
				getChildren().add(vTab);
			}
		}
	}

	private static boolean isSlot(WContainer<?> container, boolean horizontal) {
		if (container == null)
			return false;
		if (!(container instanceof Slot))
			return false;
		return ((Slot) container).isHorizontal() == horizontal;
	}

	protected void layoutChildren() {
		if (hTab != null)
			hTab.relocate(-hTab.getRealWidth() + 1, TAB_OFFSET);
		if (vTab != null) {
			vTab.relocate(TAB_OFFSET + vTab.getRealWidth(), -vTab.getRealHeight() + 1);
		}

		if (outline == null) {
			outline = new Polygon();
			outline.setFill(model.color().get());
			outline.setStroke(new Color(model.color().get().getRed() / 2, model.color().get().getGreen() / 2,
					model.color().get().getBlue() / 2, 1));
			// outline.setMouseTransparent(true);
			getChildren().add(outline);
		}
		ObservableList<Double> pts = outline.getPoints();
		pts.clear();
		// ---commence construction---
		// northwest
		pts.addAll(0.5, 0.5);

		// Traverse cells in a zig-zaggy pattern.
		for (int y = cells.size() - 1; y >= 0; y--) {
			ArrayList<FXCell> row = cells.get(y);
			for (int x = 0; x < row.size(); x++) {
				FXCell c = row.get(x);

				if (x == 0 && y == cells.size() - 1) {
					if (FXCell.hasListConnectorSlot(model.blockContainer().get(), false)) {
						// Case: this is the corner for the vertical list
						// connector slot.
						// It all the way at the bottom, so use the cell's full
						// height.
						// Lower-Left
						pts.addAll(0.5, c.getRealY() + c.getRealHeight() - 0.5);
					} else {
						// Case: this is the lower-left corner, but there is no
						// vertical list connector slot.
						// It is at the normal cell height.
						// Lower-left
						pts.addAll(0.5, c.getRealY() + c.getSubHeight() - 0.5);
					}
				} else if (x != 0) {
					// Case: This is the right side of a horizontal "bridge".
					// Mid-left indented
					pts.addAll(c.getRealX() + 0.5, c.getRealY() + INDENTATION_OFFSET + 0.5);
					// Lower-left
					pts.addAll(c.getRealX() + 0.5, c.getRealY() + c.getSubHeight() - 0.5);
				}

				// Go around all south slots
				for (FXSlot slot : c.southSlots) {
					pts.addAll(c.getRealX() + slot.getRealX() + 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - 0.5, // cell.getSubHeight?
							c.getRealX() + slot.getRealX() + TAB_OFFSET + 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - 0.5,
							c.getRealX() + slot.getRealX() + TAB_OFFSET + 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - slot.getRawTypeHeight() + 0.5,
							c.getRealX() + slot.getRealX() + TAB_OFFSET + slot.getRawTypeWidth() - 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - slot.getRawTypeHeight() + 0.5,
							c.getRealX() + slot.getRealX() + TAB_OFFSET + slot.getRawTypeWidth() - 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - 0.5,
							c.getRealX() + slot.getRealX() + slot.getRealWidth() - 0.5,
							c.getRealY() + slot.getRealY() + slot.getSubHeight() - 0.5);
				}
				// southeast
				if (x == row.size() - 1 && y == 0 && FXCell.hasListConnectorSlot(model.blockContainer().get(), true))
					pts.addAll(c.getRealX() + c.getRealWidth() - 0.5, c.getRealY() + c.getSubHeight() - 0.5);
				else
					pts.addAll(c.getRealX() + c.getSubWidth() - 0.5, c.getRealY() + c.getSubHeight() - 0.5);

				FXSlot[] eastSlots = c.eastSlots;
				for (int i = eastSlots.length - 1; i >= 0; i--) {
					FXSlot slot = eastSlots[i];
					pts.addAll(c.getRealX() + slot.getRealX() + slot.getSubWidth() - 0.5,
							c.getRealY() + slot.getRealY() + slot.getRealHeight() - 0.5,
							c.getRealX() + slot.getRealX() + slot.getSubWidth() - 0.5,
							c.getRealY() + slot.getRealY() + TAB_OFFSET + slot.getRawTypeHeight() - 0.5,
							c.getRealX() + slot.getRealX() + slot.getSubWidth() - slot.getRawTypeWidth() + 0.5,
							c.getRealY() + slot.getRealY() + TAB_OFFSET + slot.getRawTypeHeight() - 0.5,
							c.getRealX() + slot.getRealX() + slot.getSubWidth() - slot.getRawTypeWidth() + 0.5,
							c.getRealY() + slot.getRealY() + TAB_OFFSET + 0.5,
							c.getRealX() + slot.getRealX() + slot.getSubWidth() - 0.5,
							c.getRealY() + slot.getRealY() + TAB_OFFSET + 0.5,
							c.getRealX() + slot.getRealX() + slot.getSubWidth() - 0.5,
							c.getRealY() + slot.getRealY() + 0.5);
				}

				if (x == row.size() - 1) {
					// northeast
					if (FXCell.hasListConnectorSlot(model.blockContainer().get(), true) && y == 0)
						pts.addAll(c.getRealX() + c.getRealWidth() - 0.5, c.getRealY() + 0.5);
					else
						pts.addAll(c.getRealX() + c.getSubWidth() - 0.5, c.getRealY() + 0.5);
					// northwest
					FXCell first = row.get(0);
					pts.addAll(first.getRealX() + INDENTATION_OFFSET + 0.5, first.getRealY() + 0.5);
				} else // mid-east
					pts.addAll(c.getRealX() + c.getSubWidth() - 0.5, c.getRealY() + INDENTATION_OFFSET + 0.5);

				// pts.addAll(0.5, 0.5);
			}

		}
		// ---the duty is consummated---
		outline.toBack();

		publicLayoutListeners.fireEvents();
	}

	public FXCell getFXCell(int x, int y) {
		return cells.get(y).get(x);
	}

	/**
	 * Returns the UI element for the list connector slot. Must only be called
	 * if the block is known to have one!
	 */
	public FXSlot getListConnectorSlot(boolean horizontal) {
		if (horizontal)
			return cells.get(0).get(cells.get(0).size() - 1).eastSlots[0];
		else
			return cells.get(cells.size() - 1).get(0).southSlots[0];
	}

	public boolean blockContains(Point2D localPoint) {
		if (hTab != null && hTab.containsWithSlack(FXUtil.fromTo(this, hTab, localPoint), CONTAINS_SLACK))
			return true;
		if (vTab != null && vTab.containsWithSlack(FXUtil.fromTo(this, vTab, localPoint), CONTAINS_SLACK))
			return true;
		if (localPoint.getX() >= -CONTAINS_SLACK && localPoint.getX() < getRealWidth() + CONTAINS_SLACK
				&& localPoint.getY() >= -CONTAINS_SLACK && localPoint.getY() < getRealHeight() + CONTAINS_SLACK)
			return true;
		return false;
	}
}
