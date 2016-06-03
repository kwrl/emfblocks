package no.hal.emfblocks.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.pfield.PField;

public class FXSlot extends Pane {
	public static final int LABEL_SPACING = 2;

	class LayoutInfo {
		protected boolean isLayouting = false;

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
			if (isLayouting)
				new Throwable("Warning: Attempt to recompute while layout is already in progress: ").printStackTrace();
			else {
				isLayouting = true;

				this.assignedW = assignedW;
				this.assignedH = assignedH;

				if (isListConnectorSlot) {
					if (model.isHorizontal()) {
						prefW = FXBlock.TAB_OFFSET;
						prefH = getSubHeight() + FXBlock.TAB_OFFSET;
					} else {
						prefW = getSubWidth() + FXBlock.TAB_OFFSET;
						prefH = FXBlock.TAB_OFFSET;
					}
				} else {
					int[] straightDim = new int[blocks.length];

					prefW = 0;
					prefH = 0;

					int sumWidth = 0;
					int sumHeight = 0;
					int maxWidth = FXBlock.TAB_OFFSET + getSubWidth();
					int maxHeight = FXBlock.TAB_OFFSET + getSubHeight();

					for (int i = 0; i < blocks.length; i++) {
						int w = (int) blocks[i].layout.prefW;
						int h = (int) blocks[i].layout.prefH;
						if (model.isHorizontal())
							straightDim[i] = w;
						else
							straightDim[i] = h;
						sumWidth += w - 1;
						sumHeight += h - 1;
						maxWidth = Math.max(maxWidth, w);
						maxHeight = Math.max(maxHeight, h);
					}
					if (model.isHorizontal()) {
						prefW = getSubWidth() - 1;
						prefW += sumWidth;
						if (sumWidth == 0 && model.isMultiSlot())
							prefW += FXBlock.OPEN_C_BLOCK_SPACING;
						prefH = maxHeight;
					} else {
						prefH = getSubHeight() - 1;
						prefH += sumHeight;
						if (sumHeight == 0 && model.isMultiSlot())
							prefH += FXBlock.OPEN_C_BLOCK_SPACING;
						prefW = maxWidth;
					}
					if (blocks.length > 0) {
						// Free width is the assigned width minus the preferred
						// width, and this will be allocated to blocks.
						int freeDim, freeIncr;
						if (model.isHorizontal()) {
							freeDim = getWidth() - prefW;
							freeIncr = freeDim / blocks.length;
							if (freeDim < 0)
								new Throwable("Warning: Free width is negative: " + freeDim + " : slotWidth="
										+ getSubWidth() + " sumWidth=" + sumWidth).printStackTrace();
						} else {
							freeDim = getHeight() - prefH;
							freeIncr = freeDim / blocks.length;
							if (freeDim < 0)
								new Throwable("Warning: Free height is negative: " + freeDim).printStackTrace();
						}
						for (int i = 0; i < blocks.length; i++) {
							straightDim[i] += freeIncr;
							freeDim -= freeIncr;
						}
						// Rounding error goes to last one
						straightDim[straightDim.length - 1] += freeDim;

						int offset;
						if (model.isHorizontal())
							offset = getSubWidth() - 1;
						else
							offset = getSubHeight() - 1;
						for (int i = 0; i < blocks.length; i++) {
							assert blocks[i].layoutListeners.hasListener(layoutListener) : "ERROR: Block "
									+ blocks[i] + " on " + FXSlot.this + " has never been registered!";
							if (model.isHorizontal()) {
								blocks[i].setBlockLocation(offset, 0);
								blocks[i].setBlockSize(straightDim[i], getBlocksHeight());
								offset += blocks[i].getRealWidth() - 1;
							} else {
								blocks[i].setBlockLocation(0, offset);
								blocks[i].setBlockSize(getBlocksWidth(), straightDim[i]);
								offset += blocks[i].getRealHeight() - 1;
							}
						}
					}
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

		/** Returns the width of the space reserved for blocks */
		public int getBlocksWidth() {
			if (model.isHorizontal())
				return getWidth() - getSubWidth();
			else
				return getWidth();
		}

		/** Returns the height of the space reserved for blocks */
		public int getBlocksHeight() {
			if (model.isHorizontal())
				return getHeight();
			else
				return getHeight() - getSubHeight();
		}
	}

	final ActionSource layoutListeners = new ActionSource();

	protected final Referencer referencer = new ReferenceSet();
	protected final EventHandler<ActionEvent> layoutListener;
	protected LayoutInfo layout;
	protected boolean authorizeLocChange = false;
	/**
	 * The color property, which is associated with the FXBlock that the slot is
	 * added to. Note that in the case of list connector slot, this is different
	 * from the block of the model.
	 */
	protected final ObjectProperty<Color> colorProp;
	/**
	 * Whether this slot exists only for the purpose of connecting one block to
	 * a sibling block in a list. These slots are just fluff.
	 */
	protected final boolean isListConnectorSlot;
	protected final Slot model;
	/** The image of the slot. */
	protected SlackedGroup slot;
	/**
	 * A tab image that appears at the bottom of slot-lists only. Note that list
	 * connector slots never have this feature.
	 */
	protected SlackedGroup tab;

	protected FXBlock[] blocks;

	private short connectorWidthCached;
	private short connectorHeightCached;
	private int labelWidthCached;
	private int labelHeightCached;

	private ImageView myLabelIcon = null;
	private Text myLabelText = null;

	public FXSlot(Slot model, ObjectProperty<Color> color, boolean isListConnectorSlot) {
		super();
		setPickOnBounds(false);
		this.colorProp = color;
		this.model = model;
		this.isListConnectorSlot = isListConnectorSlot;

		rebuildSlotAndTab();
		// Rebuild slot and tab if the slot's type changes(note: weak listener)
		model.slotType.addListener(referencer, (prop, oldVal, newVal) -> {
			rebuildSlotAndTab();
		});
		model.getRoot().getTypeHierarchy().connectorWidth.addListener(referencer, (prop, oldVal, newVal) -> {
			connectorWidthCached = newVal;
			rebuildSlotAndTab();
		});
		model.getRoot().getTypeHierarchy().connectorHeight.addListener(referencer, (prop, oldVal, newVal) -> {
			connectorHeightCached = newVal;
			rebuildSlotAndTab();
		});
		connectorWidthCached = model.getRoot().getTypeHierarchy().connectorWidth.get();
		connectorHeightCached = model.getRoot().getTypeHierarchy().connectorHeight.get();

		layoutXProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException("Unauthorized layout x change for: " + this
						+ " (non-palette block in a layout pane is illegal)");
			assert layout.x == (int) layoutXProperty().get();
		});
		layoutYProperty().addListener(e -> {
			if (!authorizeLocChange)
				throw new IllegalStateException("Unauthorized layout y change for: " + this
						+ " (non-palette block in a layout pane is illegal)");
			assert layout.y == (int) layoutYProperty().get();
		});
		// Create a reusable listener for the layout of slots
		layoutListener = ((e) -> {
			if (layout != null)
				layout.recompute(null, null);
		});
		//
		if (isListConnectorSlot)
			blocks = null;
		else {
			blocks = buildBlocks(model.getChildren());
			model.getChildren().addListener(referencer, (prop, index, oldVal, newVal) -> {
				// Cleanup
				removeBlocks(blocks);
				// Rebuild
				blocks = buildBlocks(model.getChildren());
				if (layout != null)
					layout.recompute(null, null);
			});
		}

		model.labelTextProp.addListener((prop, oldVal, newVal) -> {
			relabel();
		});
		model.labelIconProp.addListener((prop, oldVal, newVal) -> {
			relabel();
		});
		relabel();

		layout = new LayoutInfo();
	}

	private void relabel() {
		if (isListConnectorSlot)
			return;

		boolean ch = false;
		labelWidthCached = 0;
		labelHeightCached = 0;
		Image im = model.labelIconProp.getValue();
		if (im == null) {
			if (myLabelIcon != null) {
				getChildren().remove(myLabelIcon);
				myLabelIcon = null;
				ch = true;
			}
		} else {
			if (myLabelIcon == null) {
				myLabelIcon = new ImageView();
				getChildren().add(myLabelIcon);
			}
			if (myLabelIcon.getImage() != im) {
				myLabelIcon.setImage(im);
				ch = true;
			}
			labelWidthCached += (int) myLabelIcon.prefWidth(-1) + LABEL_SPACING;
			labelHeightCached = (int) Math.max(labelHeightCached, myLabelIcon.prefHeight(-1));
		}
		String text = model.labelTextProp.getValue();
		if (text == null) {
			if (myLabelText != null) {
				getChildren().remove(myLabelText);
				myLabelText = null;
				ch = true;
			}
		} else {
			if (myLabelText == null) {
				myLabelText = new Text();
				myLabelText.setFont(Font.font(10));
				getChildren().add(myLabelText);
			}
			if (text == null ? myLabelText.getText() != null : !text.equals(myLabelText.getText())) {
				myLabelText.setText(text);
				ch = true;
			}
			labelWidthCached += (int) myLabelText.prefWidth(-1) + LABEL_SPACING;
			labelHeightCached = (int) Math.max(labelHeightCached, myLabelText.prefHeight(-1));
		}
		if (layout != null && ch)
			layout.recompute(null, null);
	}

	private void removeBlocks(FXBlock[] blocks) {
		for (FXBlock b : blocks) {
			// Ensure all listeners are removed since the FXBlock instances are
			// usually reused
			b.layoutListeners.removeListener(layoutListener);
			getChildren().remove(b);
		}
	}

	private FXBlock[] buildBlocks(PField<? extends WBlock> pField) {
		FXBlock[] r = new FXBlock[pField.size()];
		int i = 0;
		for (WBlock b : pField) {
			r[i] = (FXBlock) b.getUI();
			getChildren().add(r[i]);
			r[i].layoutListeners.addListener(layoutListener);
			i++;
		}
		/*
		 * reverse order alternative for(int i = pField.size()-1; i >= 0; i--) {
		 * r[i] = (FXBlock) pField.get(i).getUI(); getChildren().add(r[i]);
		 * r[i].layoutListeners.addActionListener(layoutListener); }
		 */
		return r;
	}

	private void rebuildSlotAndTab() {
		if (slot != null) {
			getChildren().remove(slot);
			slot = null;
		}
		if (tab != null) {
			getChildren().remove(tab);
			tab = null;
		}
		SlackedGroup imV;

		imV = model.slotType.get().createNode(false, model.isHorizontal(), colorProp, model.bgColor);
		getChildren().add(imV);
		slot = imV;

		if (model.isMultiSlot() && !isListConnectorSlot) {
			imV = model.slotType.get().getRawType().createNode(true, model.isHorizontal(), colorProp, model.bgColor);
			getChildren().add(imV);
			tab = imV;
		}
		if (layout != null)
			layout.recompute(null, null);
	}

	public void setSlotLocation(int x, int y) {
		authorizeLocChange = true;
		layout.x = x;
		layout.y = y;
		layoutXProperty().set(x);
		layoutYProperty().set(y);
		authorizeLocChange = false;
	}

	public void setSlotX(int x) {
		authorizeLocChange = true;
		layout.x = x;
		layoutXProperty().set(x);
		authorizeLocChange = false;
	}

	public void setSlotY(int y) {
		authorizeLocChange = true;
		layout.y = y;
		layoutYProperty().set(y);
		authorizeLocChange = false;
	}

	public void setSlotSize(int w, int h) {
		if (w < layout.prefW)
			new Throwable("Warning: Assigned width(" + w + ") is less than preferred width(" + layout.prefW + ")!")
					.printStackTrace();
		if (h < layout.prefH)
			new Throwable("Warning: Assigned height(" + h + ") is less than preferred height(" + layout.prefH + ")!")
					.printStackTrace();
		layout.recompute(w, h);
	}

	/**
	 * Returns the x coordinate of the slot pane. Note: To get the exact
	 * location of the connector, use {@link #getRealSlotX()}.
	 */
	public int getRealX() {
		return layout.x;
	}

	/**
	 * Returns the y coordinate of the slot pane. Note: To get the exact
	 * location of the connector, use {@link #getRealSlotY()}.
	 */
	public int getRealY() {
		return layout.y;
	}

	/**
	 * Returns the x coordinate of the slot connector(taking label and offset
	 * into account).
	 */
	public int getRealSlotX() {
		return layout.x + getLocalSlotX();
	}

	/**
	 * Returns the y coordinate of the slot connector(taking label and offset
	 * into account).
	 */
	public int getRealSlotY() {
		return layout.y + getLocalSlotY();
	}

	/**
	 * Returns the x coordinate of the raw slot connector in local space(taking
	 * label and offset into account).
	 */
	public int getLocalRawSlotX() {
		if (model.isHorizontal())
			return getSubWidth() - getRawTypeWidth();
		else
			return FXBlock.TAB_OFFSET;
	}

	/**
	 * Returns the y coordinate of the raw slot connector in local space(taking
	 * label and offset into account).
	 */
	public int getLocalRawSlotY() {
		if (model.isHorizontal())
			return FXBlock.TAB_OFFSET;
		else
			return getSubHeight() - getRawTypeHeight();
	}

	/**
	 * Returns the x coordinate of the slot connector in local space(taking
	 * label and offset into account).
	 */
	public int getLocalSlotX() {
		if (model.isHorizontal())
			return getLabelWidth();
		else
			return FXBlock.TAB_OFFSET;
	}

	/**
	 * Returns the y coordinate of the slot connector in local space(taking
	 * label and offset into account).
	 */
	public int getLocalSlotY() {
		if (model.isHorizontal())
			return FXBlock.TAB_OFFSET;
		else
			return getLabelHeight();
	}

	/** Returns the width of the slot including any labels and child blocks. */
	public int getRealWidth() {
		return layout.getWidth();
	}

	/** Returns the height of the slot including any labels and child blocks. */
	public int getRealHeight() {
		return layout.getHeight();
	}

	/** Returns the width of the slot image plus the label. */
	public int getSubWidth() {
		if (model.isHorizontal())
			return labelWidthCached + getSlotWidth();
		else
			return Math.max(labelWidthCached, connectorHeightCached);
	}

	/** Returns the height of the slot image plus the label. */
	public int getSubHeight() {
		if (model.isHorizontal())
			return Math.max(labelHeightCached, connectorHeightCached);
		else
			return labelHeightCached + getSlotHeight();
	}

	/**
	 * Returns the width of the slot image, which will be the same size as the
	 * tab image.
	 */
	public int getRawTypeWidth() {
		if (model.isHorizontal())
			return connectorWidthCached;
		else
			return connectorHeightCached;
	}

	/**
	 * Returns the height of the slot image, which will be the same size as the
	 * tab image.
	 */
	public int getRawTypeHeight() {
		if (model.isHorizontal())
			return connectorHeightCached;
		else
			return connectorWidthCached;
	}

	/** Returns the width of the slot view */
	public int getSlotWidth() {
		return (int) slot.getRealWidth();
	}

	/** Returns the height of the slot view */
	public int getSlotHeight() {
		return (int) slot.getRealHeight();
	}

	/** Returns the width of the slot label. 0 if none. */
	public int getLabelWidth() {
		return labelWidthCached;
	}

	/** Returns the height of the slot label. 0 if none. */
	public int getLabelHeight() {
		return labelHeightCached;
	}

	@Override
	protected void layoutChildren() {
		int bx;
		if (myLabelText != null) {
			myLabelText.relocate(0, 0);
			bx = (int) (myLabelText.getBoundsInLocal().getWidth() + LABEL_SPACING);
		} else
			bx = 0;
		if (myLabelIcon != null)
			myLabelIcon.relocate(bx, 0);
		if (model.isHorizontal())
			slot.relocate(getSubWidth() - getSlotWidth(), FXBlock.TAB_OFFSET);
		else
			slot.relocate(FXBlock.TAB_OFFSET + getRawTypeWidth(), getSubHeight() - getSlotHeight());
		if (tab != null) {
			if (model.isHorizontal())
				tab.relocate(getRealWidth() + 1 - getRawTypeWidth(), FXBlock.TAB_OFFSET);
			else
				tab.relocate(FXBlock.TAB_OFFSET + getRawTypeWidth(), getRealHeight() + 1 - getRawTypeHeight());
		}
	}

}
