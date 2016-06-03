package no.hal.emfblocks.cuteditor;

import java.util.Stack;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.hal.emfblocks.contour.BitmapContour;
import no.hal.emfblocks.hierarchy.TypePrimitive;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.Property;

public class TypeNodeEditor implements InvalidationListener {
	public static final Color COLOR_SOLID = Color.WHITE;
	public static final Color COLOR_CUT = new Color(0.0, 0.0, 0.0, 1);
	public static final Color COLOR_INHERITED_CUT = new Color(0.5, 0.5, 0.5, 1);

	public final TypePrimitive myType;

	private AnchorPane pane;
	private Canvas canvas;
	private GraphicsContext gc;
	private Referencer referencer = new ReferenceSet();

	private boolean selected = false;

	private byte[] undoRaster;
	private boolean imageChanged = false;
	private boolean erase = false;
	private Point2D lastDrag = null;
	private boolean outsideImage = true;

	public final Property<Color> labelcolor = new Property<Color>(Color.class, null);
	public final CutEditor editor;

	public TypeNodeEditor(CutEditor editor, TypePrimitive myType) {
		this.myType = myType;
		this.editor = editor;

		myType.getContour().tabImage().addListener(new WeakInvalidationListener(this));

		short w = editor.hierarchy.connectorWidth.get();
		short h = editor.hierarchy.connectorHeight.get();
		short zoom = editor.tabZoom.get();

		canvas = new Canvas(w * zoom, h * zoom);
		canvas.addEventHandler(MouseEvent.ANY, (e) -> {
			Point2D imgPos = toImage(e);
			if (e.getEventType() == MouseEvent.MOUSE_EXITED) {
				editor.positionText.set(null);
				outsideImage = true;
			} else if (e.getEventType() == MouseEvent.MOUSE_ENTERED)
				outsideImage = false;
			if (!outsideImage)
				editor.positionText.set(positionText(imgPos));

			if (lastDrag == null && e.getEventType() == MouseEvent.MOUSE_PRESSED
					&& (e.getButton() == MouseButton.PRIMARY || e.getButton() == MouseButton.SECONDARY)) {
				if (editor.currentTool.get() == CutEditor.Tool.BRUSH) {
					undoRaster = getPixels();
					lastDrag = imgPos;
					erase = e.getButton() == MouseButton.SECONDARY;
					imageChanged = setPixel(imgPos, erase);
					if (imageChanged) {
						// Need to update child nodes as well because
						// the cut changes on this node only.
						updateRaster(true);
					}
				} else if (editor.currentTool.get() == CutEditor.Tool.BUCKET) {
					undoRaster = getPixels();
					erase = e.getButton() == MouseButton.SECONDARY;
					imageChanged = false;
					Stack<Short> nextX = new Stack<>();
					Stack<Short> nextY = new Stack<>();
					nextX.push((short) imgPos.getX());
					nextY.push((short) imgPos.getY());

					while (!nextX.isEmpty()) {
						short x = nextX.pop();
						short y = nextY.pop();

						if (setPixel(x, y, editor.hierarchy.connectorWidth.get(),
								editor.hierarchy.connectorHeight.get(), erase)) {
							nextX.push((short) (x - 1));
							nextY.push((short) (y));
							nextX.push((short) (x + 1));
							nextY.push((short) (y));
							nextX.push((short) (x));
							nextY.push((short) (y - 1));
							nextX.push((short) (x));
							nextY.push((short) (y + 1));
							imageChanged = true;
						}
					}
					if (imageChanged) {
						// Need to update child nodes as well because
						// the cut changes on this node only.
						updateRaster(true);

						final byte[] oldRaster = undoRaster;
						final byte[] newRaster = getPixels();
						editor.undoManager.logUndo(() -> {
							setPixels(oldRaster);
							updateRaster(true);
						}, () -> {
							setPixels(newRaster);
							updateRaster(true);
						}, (erase ? "Eraser" : "Paint") + " Stroke on " + getDisplayName());
					}
					undoRaster = null;
					lastDrag = null;
				}
			} else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED && lastDrag != null) {
				boolean ch = false;
				short x = (short) lastDrag.getX();
				short y = (short) lastDrag.getY();
				short dx = (short) (imgPos.getX() - lastDrag.getX());
				short dy = (short) (imgPos.getY() - lastDrag.getY());
				if (Math.abs(dy) > Math.abs(dx))// High slope, must
												// iterate over Y
				{
					short incr = (short) Math.signum(dy);
					float f = x;
					float fIncr = (float) dx / Math.abs(dy);
					for (int i = Math.abs(dy); i >= 0; i--) {
						ch |= setPixel((short) Math.round(f), y, erase);
						f += fIncr;
						y += incr;
					}
				} else {
					short incr = (short) Math.signum(dx);
					float f = y;
					float fIncr = (float) dy / Math.abs(dx);
					for (int i = Math.abs(dx); i >= 0; i--) {
						ch |= setPixel(x, (short) Math.round(f), erase);
						f += fIncr;
						x += incr;
					}
				}
				imageChanged |= ch;
				if (ch) {
					// Need to update child nodes as well because the
					// cut changes on this node only.
					updateRaster(true);
				}
				lastDrag = imgPos;
			} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED && lastDrag != null
					&& ((e.getButton() == MouseButton.PRIMARY && !erase)
							|| (e.getButton() == MouseButton.SECONDARY && erase))) {
				if (imageChanged) {
					final byte[] oldRaster = undoRaster;
					final byte[] newRaster = getPixels();
					// Need to update child nodes the cut changes on
					// this node only.
					editor.undoManager.logUndo(() -> {
						setPixels(oldRaster);
						updateRaster(true);
					}, () -> {
						setPixels(newRaster);
						updateRaster(true);
					}, (erase ? "Eraser" : "Paint") + " Stroke on " + getDisplayName());
				}
				undoRaster = null;
				lastDrag = null;
			}

		});
		canvas.setOnScroll((e) -> {
			editor.zoom((int) e.getDeltaY() / 32);
		});
		gc = canvas.getGraphicsContext2D();
		editor.hierarchy.connectorWidth.addListener(referencer, (prop, oldVal, newVal) -> {
			updateRasterSize(oldVal, editor.hierarchy.connectorHeight.get());
		});
		editor.hierarchy.connectorHeight.addListener(referencer, (prop, oldVal, newVal) -> {
			updateRasterSize(editor.hierarchy.connectorWidth.get(), oldVal);
		});
		editor.tabZoom.addListener(referencer, (prop, oldVal, newVal) -> {
			updateZoomAndRepaint();
		});
		BorderPane scrollCenter = new BorderPane(canvas);
		FXUtil.setBackground(scrollCenter, Color.DARKGRAY);
		ScrollPane scp = new ScrollPane(scrollCenter);
		AnchorPane.setBottomAnchor(scp, 0.0);
		AnchorPane.setTopAnchor(scp, 0.0);
		AnchorPane.setLeftAnchor(scp, 0.0);
		AnchorPane.setRightAnchor(scp, 0.0);
		scp.setFitToWidth(true);
		scp.setFitToHeight(true);
		scp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		scp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane = new AnchorPane(scp);

		updateRaster(true);
	}

	/**
	 * Copy the pixels from the src array into the raster. This will not trigger
	 * any updates.
	 */
	public void setPixels(byte[] src) {
		if (src.length != raster().length)
			new Throwable(this + ": Size does not match: " + src.length + " vs " + raster().length).printStackTrace();
		else {
			System.arraycopy(src, 0, raster(), 0, src.length);
		}
	}

	/** Return a copy of the internal raster(e.g. for undo record storage) */
	public byte[] getPixels() {
		return raster().clone();
	}

	/**
	 * If updateOtherNodes is true, then child nodes will have their images
	 * updated automatically. This should be true if: 1. This node's cut has
	 * actually changed 2. We cannot guarantee that the other rasters will be
	 * updated in time by some other mechanism
	 */
	void updateRaster(boolean updateOtherNodes) {
		((BitmapContour) myType.getContour()).updateImageRaster(editor.hierarchy, myType, updateOtherNodes);
	}

	private boolean setPixel(Point2D imgPos, boolean erase) {
		return setPixel((short) imgPos.getX(), (short) imgPos.getY(), erase);
	}

	private boolean setPixel(short x, short y, boolean erase) {
		short w = editor.hierarchy.connectorWidth.get();
		short h = editor.hierarchy.connectorHeight.get();
		return setPixel(x, y, w, h, erase);
	}

	private boolean setPixel(short x, short y, short w, short h, boolean erase) {
		byte val = (byte) (erase ? 0 : 1);
		if (x >= 0 && y >= 0 && x < w - 1 && y < h && raster()[x + y * w] != val) {
			raster()[x + y * w] = val;
			return true;
		}
		return false;
	}

	private byte[] raster() {
		return ((BitmapContour) myType.getContour()).getCutRaster();
	}

	private Point2D toImage(MouseEvent e) {
		short zoom = editor.tabZoom.get();
		short x = (short) (e.getX() / zoom);
		short y = (short) (e.getY() / zoom);
		return new Point2D(x, y);
	}

	private String positionText(Point2D e) {
		return (short) e.getX() + ", " + (short) e.getY();
	}

	public String getDisplayName() {
		return editor.hierarchy.keyToString(myType.getKey());
	}

	public Pane getUI() {
		return pane;
	}

	private void repaint() {
		if (selected) {
			short w = editor.hierarchy.connectorWidth.get();
			short h = editor.hierarchy.connectorHeight.get();
			short zoom = editor.tabZoom.get();
			gc.setFill(COLOR_SOLID);
			gc.fillRect(0, 0, w * zoom, h * zoom);

			int i = 0;
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					if (((BitmapContour) myType.getContour()).getInternalRaster()[i] == 0) {
						gc.setFill(raster()[i] == 0 ? COLOR_CUT : COLOR_INHERITED_CUT);
						gc.fillRect(x * zoom, y * zoom, zoom, zoom);
					}
					i++;
				}
			}
		}
	}

	/** Called when the size of the image is changed. */
	private void updateRasterSize(short oldW, short oldH) {
		short w = editor.hierarchy.connectorWidth.get();
		short h = editor.hierarchy.connectorHeight.get();

		byte[] oldRaster = raster();
		byte[] newRaster = new byte[w * h];

		((BitmapContour) myType.getContour()).setCutRaster(newRaster, w, h);

		if (!editor.undoManager.isPerforming())// If undoing/redoing, then
												// raster will be explicitly
												// updated using setPixels().
		{
			for (int y = 0; y < h; y++) {
				newRaster[y * w + w - 1] = 1;
			}
			int xOffs = w - oldW;
			int yOffs = (h - oldH) / 2;

			int dstY = yOffs;
			int srcPos = 0;
			for (int srcY = 0; srcY < oldH; srcY++) {
				int dstX = xOffs;
				int dstPos = dstX + w * dstY;
				for (int srcX = 0; srcX < oldW; srcX++) {
					if (dstX >= 0 && dstY >= 0 && srcX >= 0 && srcY >= 0 && dstX < w && dstY < h && srcX < oldW
							&& srcY < oldH) {
						newRaster[dstPos] = oldRaster[srcPos];
					}
					dstX++;
					dstPos++;
					srcPos++;
				}
				dstY++;
			}
			/*
			 * Don't force update child nodes because: 1. The edit is identical
			 * across all nodes 2. All nodes will be updated anyway
			 */
			// Note: Raster must be updated after all nodes have changed their
			// sizes...
			Platform.runLater(() -> {
				updateRaster(false);
			});
		}
		short zoom = editor.tabZoom.get();
		canvas.setWidth(w * zoom);
		canvas.setHeight(h * zoom);
	}

	private void updateZoomAndRepaint() {
		if (selected) {
			short w = editor.hierarchy.connectorWidth.get();
			short h = editor.hierarchy.connectorHeight.get();
			short zoom = editor.tabZoom.get();

			canvas.setWidth(w * zoom);
			canvas.setHeight(h * zoom);
			repaint();
		}
	}

	public void select() {
		selected = true;
		updateZoomAndRepaint();
	}

	public void deselect() {
		selected = false;
	}

	void updateLabelColor(TypeNodeEditor selectedNode) {
		if (selectedNode == null)
			labelcolor.set(null);
		else if (myType.isChildOf(selectedNode.myType))
			labelcolor.set(new Color(0.75, 1, 0.75, 1));
		else if (myType.isParentOf(selectedNode.myType))
			labelcolor.set(new Color(0.8, 0.9, 1, 1));
		else
			labelcolor.set(null);
	}

	@Override
	public void invalidated(Observable e) {
		if (e == myType.getContour().tabImage())
			repaint();
	}
}
