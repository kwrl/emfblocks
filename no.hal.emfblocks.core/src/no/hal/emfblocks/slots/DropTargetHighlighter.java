package no.hal.emfblocks.slots;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.javafx.FXSlot;
import no.hal.emfblocks.javafx.FXUtil;

public class DropTargetHighlighter {
	public static class InvalidDropTargetException extends Exception {
		public InvalidDropTargetException(String string) {
			super(string);
		}

		private static final long serialVersionUID = 7578357635734483548L;

	}

	public class Highlight implements ChangeListener<Transform> {
		private final Node node;
		private final Affine transform;
		private final boolean horizontal;
		private final Pane parent;
		private final Rectangle colorizer;
		private final int index;
		private final int xOffset;
		private final int yOffset;

		private Highlight(Node node, Affine transform, boolean horizontal, Pane parent, Rectangle colorizer, int index,
				int xOffset, int yOffset) {
			super();
			this.parent = parent;
			this.colorizer = colorizer;
			this.node = node;
			this.transform = transform;
			this.horizontal = horizontal;
			this.index = index;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}

		public boolean isHorizontal() {
			return mySlot.isHorizontal();
		}

		public Slot getSlot() {
			return mySlot;
		}

		public int getIndex() {
			return index;
		}

		public double getX() {
			Point2D p = root.getUI().sceneToLocal(node.localToScene(0, 0));
			return p.getX();
		}

		public double getY() {
			Point2D p = root.getUI().sceneToLocal(node.localToScene(0, 0));
			return p.getY();
		}

		public void setHover(boolean b) {
			if (b)
				colorizer.setFill(VALID_HIGHLIGHT);
			else
				colorizer.setFill(VALID_TARGET);
			/*
			 * if(b)
			 * colorizer.setFill(isValid?VALID_HIGHLIGHT:INVALID_HIGHLIGHT);
			 * else colorizer.setFill(isValid?VALID_TARGET:INVALID_TARGET);
			 */
		}

		@Override
		public void changed(ObservableValue<? extends Transform> observable, Transform oldValue, Transform newValue) {
			updateTransforms();
		}

		public void updateTransforms() {
			Point2D p = parent.localToScene(xOffset, yOffset);
			p = root.getUI().sceneToLocal(p);
			double s = parent.localToScene(1, 0).getX() - parent.localToScene(0, 0).getX();// why
																							// can't
																							// I
																							// use
																							// homogenous
																							// coordinates
																							// and
																							// avoid
																							// this
																							// shit
			transform.setTx(p.getX());
			transform.setTy(p.getY());
			if (horizontal) {
				transform.setMxx(s);
				transform.setMxy(0);
				transform.setMyx(0);
				transform.setMyy(s);
			} else {
				transform.setMxx(0);
				transform.setMxy(s);
				transform.setMyx(s);
				transform.setMyy(0);
			}
		}
	}

	public static Color VALID_TARGET = FXUtil.rgb(0x408040);
	// public static Color INVALID_TARGET = FXUtil.rgb(0x800000);
	public static Color VALID_HIGHLIGHT = FXUtil.rgb(0x80FF80);
	// public static Color INVALID_HIGHLIGHT = FXUtil.rgb(0xFF0000);

	final WRoot root;
	final Slot mySlot;
	final boolean isValid;
	final Highlight[] highlights;

	public DropTargetHighlighter(WRoot root, Slot s, WBlock candidate) throws InvalidDropTargetException {
		this.root = root;
		this.mySlot = s;
		int xInParent, yInParent;

		FXSlot slotUI = (FXSlot) s.getUI();
		xInParent = slotUI.getLocalRawSlotX();
		yInParent = slotUI.getLocalRawSlotY();

		isValid = s.accepts(candidate) == BlockDropAction.ACCEPT;
		if (!isValid)
			throw new InvalidDropTargetException(s + " is an invalid drop target for " + candidate);
		// final Color c = isValid?VALID_TARGET:INVALID_TARGET;
		final Color c = VALID_TARGET;
		int n = 1;
		if (s.isMultiSlot())
			n += s.getChildren().size();
		highlights = new Highlight[n];

		highlights[0] = mkView(slotUI, 0, s.slotType.get().getRawType().getContour().highlightImage(), c, xInParent,
				yInParent, s.isHorizontal());

		for (int i = 1; i < n; i++) {
			FXBlock ui = (FXBlock) s.getChildren().get(i - 1).getUI();

			FXSlot conn = ui.getListConnectorSlot(s.isHorizontal());
			// List connector slots do not have labels! The offset is different.
			xInParent = conn.getLocalRawSlotX();
			yInParent = conn.getLocalRawSlotY();

			highlights[i] = mkView(conn, i, s.slotType.get().getRawType().getContour().highlightImage(), c, xInParent,
					yInParent, s.isHorizontal());
		}
	}

	/**
	 * Create a Highlight for one drop target in a slot(multi-slots have
	 * multiple Highlights)
	 */
	private Highlight mkView(Pane parent, int index, ObjectProperty<Image> im, Color c, int xInParent, int yInParent,
			boolean horizontal) {
		// Note: Highlights do not update when the slot/tab images change.
		// This can be fixed, but it is difficult to test and not very useful.
		Pane rootUI = root.getUI();
		Group n;
		Affine transform = new Affine();
		int t = 1;
		Rectangle colorizer = new Rectangle(im.get().getWidth() + 2 * t, im.get().getHeight() + 2 * t);
		colorizer.setFill(c);
		colorizer.setClip(dilate(im.get()));
		n = new Group(colorizer);
		// colorizer.setRotate(value); These functions operate about the
		// "center" of the object, which is ill-defined.
		// n.setScaleX(value); This only serves to complicate something that is
		// initially simple, and will continue to confuse and annoy developers
		// until JavaFX finally fades into obscurity.
		n.getTransforms().add(transform);
		rootUI.getChildren().add(1, n);

		Highlight h = new Highlight(n, transform, horizontal, parent, colorizer, index, xInParent, yInParent);
		h.updateTransforms();
		parent.localToSceneTransformProperty().addListener(h);
		return h;
	}

	/**
	 * Create a node displaying 5 images offset by 0 or 1 pixel along each axis,
	 * resulting in dilated alpha.
	 */
	private Group dilate(Image im) {
		Group g = new Group();
		g.setEffect(
				new Blend(BlendMode.SRC_OVER,
						new Blend(BlendMode.SRC_OVER,
								new Blend(BlendMode.SRC_OVER,
										new Blend(BlendMode.SRC_OVER, new ImageInput(im, 1, 1),
												new ImageInput(im, 1, 0)),
										new ImageInput(im, 1, 2)),
								new ImageInput(im, 0, 1)),
						new ImageInput(im, 2, 1)));
		return g;
	}

	/** Called when the drop target highlighter should remove its UI elements */
	public void destroy() {
		Pane rootUI = root.getUI();
		for (Highlight h : highlights) {
			rootUI.getChildren().remove(h.node);
			h.parent.localToSceneTransformProperty().removeListener(h);
		}
	}

	public Highlight getClosestHighlight(double x, double y) {
		if (mySlot.isHorizontal()) {
			int eastIndex = -1;
			for (int i = 0; i < highlights.length; i++) {
				if (highlights[i].getX() > x) {
					eastIndex = i;
					break;
				}
			}
			if (eastIndex == -1)
				return highlights[highlights.length - 1];
			if (eastIndex == 0)
				return highlights[0];
			if (x < (highlights[eastIndex - 1].getX() + highlights[eastIndex].getX()) / 2)
				return highlights[eastIndex - 1];
			return highlights[eastIndex];
		} else {
			int lowIndex = -1;
			for (int i = 0; i < highlights.length; i++) {
				if (highlights[i].getY() > y) {
					lowIndex = i;
					break;
				}
			}
			if (lowIndex == -1)
				return highlights[highlights.length - 1];
			if (lowIndex == 0)
				return highlights[0];
			if (y < (highlights[lowIndex - 1].getY() + highlights[lowIndex].getY()) / 2)
				return highlights[lowIndex - 1];
			return highlights[lowIndex];
		}
	}

	public boolean isHorizontal() {
		return mySlot.isHorizontal();
	}
}
