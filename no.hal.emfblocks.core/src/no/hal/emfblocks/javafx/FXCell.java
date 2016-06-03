package no.hal.emfblocks.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WCell;
import no.hal.emfblocks.WSingleFieldContainer;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.pfield.PFieldList;

public class FXCell extends Pane {
	protected class LayoutInfo {
		protected boolean isLayouting = false;

		protected int x;
		protected int y;
		protected int prefW;
		protected int prefH;
		protected int extraWidth;
		protected int extraHeight;
		protected Integer assignedW;
		protected Integer assignedH;

		public LayoutInfo() {
			x = (int) layoutXProperty().get();
			y = (int) layoutYProperty().get();
			recompute(null, null);
		}

		void recompute(Integer assignedW, Integer assignedH) {
			if (isLayouting)
				new Throwable("Warning: Attempt to recompute while layout is already in progress: ").printStackTrace();
			else {
				final boolean centeredContent = southSlots.length == 1 && southSlots[0].isListConnectorSlot;

				final int oldPrefW = prefW;
				final int oldPrefH = prefH;
				final int oldExtraW = extraWidth;
				final int oldExtraH = extraHeight;

				isLayouting = true;

				this.assignedW = assignedW;
				this.assignedH = assignedH;

				int southSlotsHeight = 0;
				int southBlocksHeight = 0;
				int southBlocksWidth = 0;
				int southSlotsWidth = 0;
				for (FXSlot s : southSlots) {
					assert s.layoutListeners.hasListener(layoutListener) : "ERROR: Slot " + s + " on "
							+ FXCell.this + " has never been registered!";
					// The slot position is placed so that blocks can be added
					// to it without colliding with any previous slots
					// But the slots width is created so that the content area
					// can overlap with the horizontal space of previous blocks.
					southSlotsWidth = southBlocksWidth + FXBlock.TAB_OFFSET + s.getSubWidth();
					southBlocksWidth += s.layout.prefW;
					southSlotsHeight = Math.max(southSlotsHeight, s.getSubHeight());
					southBlocksHeight = Math.max(southBlocksHeight, s.layout.prefH - s.getSubHeight());
				}
				int contentX;
				int contentY;
				int contentW;
				int contentH;
				int totalXSpacing = FXBlock.INDENTATION_OFFSET;// Always leave
																// indentation
																// space on left
																// side
				int totalYSpacingForNonCenteredContent = FXBlock.INDENTATION_OFFSET;
				SizedNode cp = model.contentPane.get();
				if (cp != null) {
					if (southSlots.length > 0 && centeredContent) // Add
																	// horizontal
																	// spacing
																	// between
																	// south
																	// slots and
																	// content
						totalXSpacing += FXBlock.COMPONENT_SPACING;

					if (!centeredContent) {
						if (southSlots.length == 0)
							totalYSpacingForNonCenteredContent += FXBlock.INDENTATION_OFFSET;// Vertical
																								// spacing
																								// towards
																								// south
																								// border
						else if (!centeredContent)
							totalYSpacingForNonCenteredContent += FXBlock.COMPONENT_SPACING;// Vertical
																							// spacing
																							// between
																							// content
																							// and
																							// south
																							// slots
					}

					if (centeredContent)
						contentX = southSlotsWidth + totalXSpacing;
					else
						contentX = FXBlock.INDENTATION_OFFSET;
					contentY = FXBlock.INDENTATION_OFFSET;
					contentW = (int) cp.layout.prefW;
					contentH = (int) cp.layout.prefH;
				} else {
					contentX = 0;
					contentY = 0;
					contentW = 0;
					contentH = 0;
				}

				int eastSlotsWidth = 0;
				int eastBlocksWidth = 0;
				int eastBlocksHeight = 0;
				for (FXSlot s : eastSlots) {
					assert s.layoutListeners.hasListener(layoutListener) : "ERROR: Slot " + s + " on "
							+ FXCell.this + " has never been registered!";
					eastBlocksHeight += s.layout.prefH;
					eastSlotsWidth = Math.max(eastSlotsWidth, s.getSubWidth());
					eastBlocksWidth = Math.max(eastBlocksWidth, s.layout.prefW - s.getSubWidth());
				}

				if (eastSlots.length > 0) {
					if (cp != null || southSlots.length > 0)// Spacing between
															// previous things
															// and eastSlots
						totalXSpacing += FXBlock.COMPONENT_SPACING;
				} else {
					if (cp != null || southSlots.length > 0)// Spacing towards
															// east edge.
						totalXSpacing += FXBlock.INDENTATION_OFFSET;
				}
				// Note: This formula is used to expand the content area. Take
				// special care if it needs to be changed.
				int upperWidth;
				if (centeredContent)
					upperWidth = totalXSpacing + southSlotsWidth + contentW + eastSlotsWidth;
				else
					upperWidth = totalXSpacing + Math.max(southSlotsWidth, contentW) + eastSlotsWidth;
				prefW = Math.max(FXBlock.INDENTATION_OFFSET + southBlocksWidth, upperWidth);
				// ????
				if (centeredContent)
					prefH = FXBlock.INDENTATION_OFFSET + Math.max(southSlotsHeight,
							Math.max(contentH + FXBlock.INDENTATION_OFFSET, eastBlocksHeight));
				else
					prefH = Math.max(contentH + totalYSpacingForNonCenteredContent + southSlotsHeight,
							FXBlock.INDENTATION_OFFSET + eastBlocksHeight);
				// Remember this for expanding content area
				int minSubW = prefW;
				int minSubH = prefH;

				// The upper-left cell must be large enough to hold the tabs,
				// regardless of whether they are visible
				short connectorSize = model.getRoot().getTypeHierarchy().connectorHeight.get();
				if (model.x == 0 && model.y == 0) {
					prefW = Math.max(prefW, connectorSize + 2 * FXBlock.TAB_OFFSET);
					prefH = Math.max(prefH, connectorSize + 2 * FXBlock.TAB_OFFSET);
				}
				// prefW, prefH is not the minimum size of the cell without any
				// attachments.
				extraWidth = eastBlocksWidth;
				extraHeight = southBlocksHeight;

				prefW += extraWidth;
				prefH += extraHeight;

				if (southSlots.length > 0) {
					// Memo: The indentation offset cannot be filled with
					// blocks, do it is not part of free space
					int freeWidth = getSubWidth() - southBlocksWidth - FXBlock.INDENTATION_OFFSET;
					// Also, if free space is allocated to any slots but the
					// rightmost one, then they might overlap with the content
					// area. We do not want this.
					int xOffset = 0;
					if (freeWidth < 0)
						new Throwable("Warning: FXCell Free width is negative: " + freeWidth).printStackTrace();

					for (int sx = 0; sx < southSlots.length; sx++) {

						int w = southSlots[sx].layout.prefW;
						if (sx == 0) {
							if (southSlots[sx].isListConnectorSlot) {
								xOffset = 0;
								w += FXBlock.INDENTATION_OFFSET;
							} else
								xOffset = FXBlock.INDENTATION_OFFSET;
						}

						southSlots[sx].setSlotLocation(xOffset, yOfSouthSlot(sx));
						// Last one receives all space
						if (sx == southSlots.length - 1)
							w += freeWidth;
						xOffset += w;
						southSlots[sx].setSlotSize(w, southSlots[sx].getSubHeight() + southBlocksHeight);
					}
				}
				if (eastSlots.length > 0) {
					// Memo: The indentation offset cannot be filled with
					// blocks, do it is not part of free space
					int freeHeight = getSubHeight() - eastBlocksHeight - FXBlock.INDENTATION_OFFSET;
					// For the east slots, space can be evenly allocated without
					// risk of overlap with the content area:
					int incrHeight = freeHeight / eastSlots.length;
					int yOffset = 0;
					if (freeHeight < 0)
						new Throwable("Warning: FXCell Free height is negative: " + freeHeight).printStackTrace();

					for (int sy = 0; sy < eastSlots.length; sy++) {
						int h = eastSlots[sy].layout.prefH + incrHeight;
						if (sy == 0) {
							if (eastSlots[sy].isListConnectorSlot) {
								yOffset = 0;
								h += FXBlock.INDENTATION_OFFSET;
							} else
								yOffset = FXBlock.INDENTATION_OFFSET;
						}
						eastSlots[sy].setSlotLocation(xOfEastSlot(sy), yOffset);
						freeHeight -= incrHeight;
						// Last one receives rounding error
						if (sy == eastSlots.length - 1)
							h += freeHeight;
						yOffset += h;
						eastSlots[sy].setSlotSize(eastSlots[sy].getSubWidth() + eastBlocksWidth, h);
					}
				}

				if (cp != null) {
					// Note: second indent included in contentspacing for width
					contentW += prefW - minSubW - extraWidth;
					contentH += prefH - minSubH - extraHeight;
					cp.setCellContentBounds(contentX, contentY, contentW, contentH);
				}

				isLayouting = false;

				// Note: if this happens before other warnings, then it means
				// that some sub-element's preferred size changed without
				// notifying us previously.
				if (getWidth() < prefW || getHeight() < prefH)
					new Throwable("Warning: FXCell " + FXCell.this + " has wrong assigned size: " + getWidth() + ", "
							+ getHeight() + " / " + prefW + ", " + prefH + "\n" + "old values: " + oldPrefW + ", "
							+ oldPrefH + ", " + oldExtraW + ", " + oldExtraH + "\n" + "new values: " + prefW + ", "
							+ prefH + ", " + extraWidth + ", " + extraHeight).printStackTrace();

				// The assigned size will be non-null iff the layout call comes
				// from the parent in the layout tree.
				// So, if it was null, notify the parent so it can update the
				// size.

				if (assignedW == null)
					layoutListeners.fireEvents();

				requestLayout();
			}
		}

		private int yOfSouthSlot(int sx) {
			if (hasListConnectorSlot(model.getBlock().blockContainer().get(), false, model) && sx == 0)
				return getHeight() - southSlots[sx].getSubHeight();
			else
				return getSubHeight() - southSlots[sx].getSubHeight();
		}

		private int xOfEastSlot(int sy) {
			if (hasListConnectorSlot(model.getBlock().blockContainer().get(), true, model) && sy == 0)
				return getWidth() - eastSlots[sy].getSubWidth();
			else
				return getSubWidth() - eastSlots[sy].getSubWidth();
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
		 * Returns the assigned subwidth, or the preferred subwidth if none has
		 * been assigned.
		 */
		public int getSubWidth() {
			return getWidth() - extraWidth;
		}

		/**
		 * Returns the assigned subheight, or the preferred subheight if none
		 * has been assigned.
		 */
		public int getSubHeight() {
			return getHeight() - extraHeight;
		}
	}

	final ActionSource layoutListeners = new ActionSource();
	protected LayoutInfo layout;
	protected boolean authorizeLocChange = false;

	public final WCell model;

	FXSlot[] southSlots;
	FXSlot[] eastSlots;
	// FXColoredImageView[] southTabs;
	// FXColoredImageView[] eastTabs;

	private final Referencer referencer = new ReferenceSet();
	protected final EventHandler<ActionEvent> layoutListener;

	public FXCell(WCell model) {
		super();
		setPickOnBounds(false);
		// JavaFX's layout handling is terrible and out of order,
		// meaning we need to manually synchronize nested layout panes.
		// To avoid this as much as possible, avoid nesting of layout panes.
		// Instead, write the entire layout using as few classes as possible.
		this.model = model;

		// Create a reusable listener for the layout of slots
		layoutListener = ((e) -> {
			layout.recompute(null, null);
		});

		// Add the content pane
		SizedNode cp = model.contentPane.get();
		if (cp != null) {
			getChildren().add(cp);
		}
		// Make sure the content pane is properly updated if it changes,
		// 'moving' the listener to the new pane
		model.contentPane.addListener(referencer, (prop, oldVal, newVal) -> {
			if (oldVal != null) {
				oldVal.layoutListeners.removeListener(layoutListener);
				getChildren().remove(oldVal);
			}
			if (newVal != null) {
				getChildren().add(newVal);
				newVal.layoutListeners.addListener(layoutListener);
			}
			layout.recompute(null, null);
		});
		// TODO this doesn't work (Why?)
		if (model.contentPane.get() != null)
			model.contentPane.get().layoutListeners.addListener(layoutListener);

		southSlots = buildSlots(model.southSlots, false);
		model.southSlots.addListener(referencer, (prop, index, oldVal, newVal) -> {
			// Cleanup
			removeSlots(southSlots);
			// Rebuild
			southSlots = buildSlots(model.southSlots, false);
			if (layout != null)
				layout.recompute(null, null);
		});
		eastSlots = buildSlots(model.eastSlots, true);
		model.eastSlots.addListener(referencer, (prop, index, oldVal, newVal) -> {
			// Cleanup
			removeSlots(eastSlots);
			// Rebuild
			eastSlots = buildSlots(model.eastSlots, true);
			if (layout != null)
				layout.recompute(null, null);
		});

		model.getBlock().blockContainer().addListener(referencer, (prop, oldVal, newVal) -> {
			if (hasListConnectorSlot(oldVal, false, model) != hasListConnectorSlot(newVal, false, model)) {
				// Vertical slot changed
				// Cleanup
				removeSlots(southSlots);
				// Rebuild
				southSlots = buildSlots(model.southSlots, false);
				if (layout != null)
					layout.recompute(null, null);
			}
			if (hasListConnectorSlot(oldVal, true, model) != hasListConnectorSlot(newVal, true, model)) {
				// Horizontal slot changed
				// Cleanup
				removeSlots(eastSlots);
				// Rebuild
				eastSlots = buildSlots(model.eastSlots, true);
				if (layout != null)
					layout.recompute(null, null);
			}
		});

		layoutXProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException(
						"Unauthorized layout x change for: " + this + " (FXCell can only be a part of FXBlock)");
			assert layout.x == (int) layoutXProperty().get();
		});
		layoutYProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException(
						"Unauthorized layout y change for: " + this + " (FXCell can only be a part of FXBlock)");
			assert layout.y == (int) layoutYProperty().get();
		});

		layout = new LayoutInfo();
	}

	private void removeSlots(FXSlot[] slots) {
		for (FXSlot s : slots) {
			// Ensure all listeners are removed since the FXSlot instances are
			// usually reused
			s.layoutListeners.removeListener(layoutListener);
			getChildren().remove(s);
		}
	}

	static boolean hasListConnectorSlot(WSingleFieldContainer<? super WBlock> container, boolean horizontalDirection,
			WCell model) {
		if (container == null)
			return false;
		return model.x == (horizontalDirection ? (model.getBlock().getNumColumnsAt(0) - 1) : 0)
				&& model.y == (horizontalDirection ? 0 : (model.getBlock().getNumRows() - 1))
				&& hasListConnectorSlot(container, horizontalDirection);
	}

	static boolean hasListConnectorSlot(WSingleFieldContainer<? super WBlock> container, boolean horizontalDirection) {
		return (container instanceof Slot && // Parent block is docked to a slot
				(((Slot) container).isHorizontal() == horizontalDirection) && // Docked
																				// slot
																				// has
																				// same
																				// orientation
																				// as
																				// this
																				// slot
																				// list
				((Slot) container).isMultiSlot()); // Docked slot is a
													// multi-slot
	}

	private FXSlot[] buildSlots(PFieldList<Slot> slots, boolean horizontalSlots) {
		boolean hasListConnectorSlot = hasListConnectorSlot(model.getBlock().blockContainer().get(), horizontalSlots,
				model);

		FXSlot[] r = new FXSlot[slots.size() + (hasListConnectorSlot ? 1 : 0)];

		int sx = 0;
		if (hasListConnectorSlot) {
			r[sx] = new FXSlot((Slot) model.block.blockContainer().get(), model.block.color(), true);
			r[sx].layoutListeners.addListener(layoutListener);
			getChildren().add(r[sx]);
			sx++;
		}
		for (Slot s : slots) {
			r[sx] = (FXSlot) s.getUI();
			getChildren().add(r[sx]);
			r[sx].layoutListeners.addListener(layoutListener);
			sx++;
		}
		return r;
	}

	/** Returns the x coordinate of the cell. */
	public int getRealX() {
		return layout.x;
	}

	/** Returns the y coordinate of the cell. */
	public int getRealY() {
		return layout.y;
	}

	/** Returns the width of the cell. */
	public int getRealWidth() {
		return layout.getWidth();
	}

	/** Returns the height of the cell. */
	public int getRealHeight() {
		return layout.getHeight();
	}

	/** Returns the width of the cell excluding attached blocks. */
	public int getSubWidth() {
		return layout.getSubWidth();
	}

	/** Returns the height of the cell excluding attached blocks. */
	public int getSubHeight() {
		return layout.getSubHeight();
	}

	public void setCellX(int x) {
		authorizeLocChange = true;
		layout.x = x;
		layoutXProperty().set(x);
		authorizeLocChange = false;
	}

	public void setCellY(int y) {
		authorizeLocChange = true;
		layout.y = y;
		layoutYProperty().set(y);
		authorizeLocChange = false;
	}

	public void setCellLocation(int x, int y) {
		authorizeLocChange = true;
		layout.x = x;
		layout.y = y;
		layoutXProperty().set(x);
		layoutYProperty().set(y);
		authorizeLocChange = false;
	}

	public void setCellSize(int w, int h) {
		if (w < layout.prefW)
			new Throwable(
					this + ": Warning: Assigned width(" + w + ") is less than preferred width(" + layout.prefW + ")!")
							.printStackTrace();
		if (h < layout.prefH)
			new Throwable(
					this + ": Warning: Assigned height(" + h + ") is less than preferred height(" + layout.prefH + ")!")
							.printStackTrace();
		layout.recompute(w, h);
	}

	@Override
	protected void layoutChildren() {
		// The JavaFX layout system is unusable.
		// There's no way to communicate between panes during a layout pass.
		// You have to wait for the next tick, creating graphical glitches,
		// which is obviously unacceptable.
	}

	public WCell getModel() {
		return model;
	}
}
