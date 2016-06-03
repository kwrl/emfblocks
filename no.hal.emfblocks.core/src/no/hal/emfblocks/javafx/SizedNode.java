package no.hal.emfblocks.javafx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * This class wraps a javaFX Node object, providing predictable layout
 * information to the FXBlock tree.
 */
public class SizedNode extends Pane {
	public interface Size {
		public final Size MIN_SIZE = new Size() {
			@Override
			public int widthOf(Node n) {
				return (int) n.minWidth(-1);
			}

			@Override
			public int heightOf(Node n) {
				return (int) n.minHeight(-1);
			}
		};
		public final Size PREF_SIZE = new Size() {
			@Override
			public int widthOf(Node n) {
				return (int) n.prefWidth(-1);
			}

			@Override
			public int heightOf(Node n) {
				return (int) n.prefHeight(-1);
			}
		};
		public final Size CURRENT_SIZE = new Size() {
			@Override
			public int widthOf(Node n) {
				return (int) n.boundsInLocalProperty().get().getWidth();
			}

			@Override
			public int heightOf(Node n) {
				return (int) n.boundsInLocalProperty().get().getHeight();
			}
		};
		public final Size AUTO_SIZE = new Size() {
			public void prepare(Node n) {
				n.autosize();
			}

			@Override
			public int widthOf(Node n) {
				return (int) n.boundsInLocalProperty().get().getWidth() + 1;
			}

			@Override
			public int heightOf(Node n) {
				return (int) n.boundsInLocalProperty().get().getHeight() + 1;
			}
		};

		/** Returns a fixed size object. */
		public static Size fixedSize(int width, int height) {
			return new Size() {
				@Override
				public int widthOf(Node n) {
					return width;
				}

				@Override
				public int heightOf(Node n) {
					return height;
				}

			};
		}

		/** Decorates the indicated size with a padding. */
		public static Size paddedSize(int xPad, int yPad, Size original) {
			return new Size() {
				@Override
				public void prepare(Node n) {
					original.prepare(n);
				}

				@Override
				public int widthOf(Node n) {
					return xPad + original.widthOf(n);
				}

				@Override
				public int heightOf(Node n) {
					return yPad + original.heightOf(n);
				}
			};
		}

		public default void prepare(Node n) {
			/* Optional method */}

		public int widthOf(Node n);

		public int heightOf(Node n);
	}

	protected class LayoutInfo {
		protected int x;
		protected int y;
		protected int prefW;
		protected int prefH;
		protected Integer assignedW;
		protected Integer assignedH;

		protected int xPad, yPad, padW, padH;
		protected boolean paddingSet;

		public LayoutInfo(int initialWidth, int initialHeight) {
			x = 0;
			y = 0;
			prefW = initialWidth;
			prefH = initialHeight;
			recompute(null, null);
		}

		void recompute(Integer assignedW, Integer assignedH) {
			this.assignedW = assignedW;
			this.assignedH = assignedH;

			// Note: if this happens before other warnings, then it means that
			// someone has altered the preferred size changed without notifying
			// us previously.
			if (getWidth() < prefW || getHeight() < prefH)
				new Throwable("Warning: FXCellContent " + SizedNode.this + " has wrong assigned size: " + getWidth()
						+ ", " + getHeight() + " / " + prefW + ", " + prefH).printStackTrace();

			// The assigned size will be non-null iff the layout call comes from
			// the parent in the layout tree.
			// So, if it was null, notify the parent so it can update the size.
			if (assignedW == null)
				layoutListeners.fireEvents();
			if (!paddingSet)
				node.resizeRelocate(0, 0, getWidth(), getHeight());

			requestLayout();
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
	}

	final ActionSource layoutListeners = new ActionSource();
	protected final LayoutInfo layout;
	protected final Size size;
	protected final Node node;

	private boolean preventRepeat = false;

	public SizedNode(Node n, Size size, boolean autoUpdateSize, boolean includePadding) {
		setPickOnBounds(false);
		size.prepare(n);
		int ix = size.widthOf(n);// 0?
		int iy = size.heightOf(n);// 0?
		this.size = size;
		this.node = n;

		layout = new LayoutInfo(ix, iy);
		layout.paddingSet = !includePadding;

		node.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {
			private int nUpdates = 0;

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				// Doing this in the current thread sometimes fails to update
				// some of the nodes, presumably because
				// all calls to requestLayout() silently fail during a layout
				// pass(and the proper size is only set
				// during layout for some kinds of nodes, like Label). Doing it
				// in a runLater produces a single
				// invalid intermediary frame, but this is something we have to
				// live with.
				if (!preventRepeat) {
					Platform.runLater(() -> {
						forceResize();
						if (!autoUpdateSize && nUpdates == 2)// Stupid stupid
																// stupid (good
																// size is only
																// available
																// second time
																// this is
																// called)
						{
							node.boundsInLocalProperty().removeListener(this);
							nUpdates++;
						}
					});
				}
			}
		});
		getChildren().add(node);
	}

	public void forceResize() {
		if (!preventRepeat)// Recursion prevention
		{
			preventRepeat = true;
			size.prepare(node);
			int npw = size.widthOf(node);
			int nph = size.heightOf(node);
			if (npw != layout.prefW || nph != layout.prefH) {
				layout.prefW = npw;
				layout.prefH = nph;
				layout.recompute(null, null);
			}
			preventRepeat = false;
		}
	}

	public SizedNode(Node n, Size sizeMode, boolean autoUpdateSize) {
		this(n, sizeMode, autoUpdateSize, false);
	}

	public SizedNode(Node n, Size sizeMode) {
		this(n, sizeMode, false);
	}

	public SizedNode(Node n, int width, int height) {
		this(n, Size.fixedSize(width, height));
	}

	void setCellContentBounds(int newX, int newY, int newWidth, int newHeight) {
		layout.x = newX;
		layout.y = newY;
		layout.recompute(newWidth, newHeight);
	}

	public void setPrefSize(int prefWidth, int prefHeight) {
		layout.prefW = prefWidth;
		layout.prefH = prefHeight;
		layout.recompute(null, null);
	}

	public int getRealX() {
		return layout.x;
	}

	public int getRealY() {
		return layout.y;
	}

	public int getRealWidth() {
		return layout.getWidth();
	}

	public int getRealHeight() {
		return layout.getHeight();
	}

	@Override
	protected void layoutChildren() {
		if (node != null) {
			if (!layout.paddingSet) {
				Bounds bds = node.boundsInParentProperty().get();
				layout.xPad = -(int) bds.getMinX();
				layout.yPad = -(int) bds.getMinY();
				layout.padW = layout.getWidth() - (int) bds.getWidth();
				layout.padH = layout.getHeight() - (int) bds.getHeight();
				layout.paddingSet = true;
			}
			preventRepeat = true;
			node.resizeRelocate(layout.x + layout.xPad, layout.y + layout.yPad, layout.getWidth() - layout.padW,
					layout.getHeight() - layout.padH);
			preventRepeat = false;
		}
	}
}
