package no.hal.emfblocks.javafx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.WeakHashMap;

import javax.imageio.ImageIO;

import javafx.beans.InvalidationListener;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class FXUtil {
	/**
	 * JavaFX thinks it's a good idea to allow multiple simultaneous popup
	 * menus. Obviously this is dangerous and never useful, so use this field to
	 * track the currently active one and destroy it when creating new popup
	 * menus.
	 */
	private static WeakReference<ContextMenu> currentContextMenu = null;

	public enum ImageFormat {
		PNG("png"), BMP("bmp"), JPEG("jpeg");

		private String imageIOName;

		private ImageFormat(String imageIOName) {
			this.imageIOName = imageIOName;
		}
	}

	private static PixelFormat<ByteBuffer> grayScale, binary;

	public static Point2D fromTo(Node from, Node to, Point2D p) {
		return to.sceneToLocal(from.localToScene(p));
	}

	public static Bounds fromTo(Node from, Node to, Bounds b) {
		return to.sceneToLocal(from.localToScene(b));
	}

	public static void forceRepaint(Node p) {
		// ░░░░░░░░▄▄██▀▀▀▀▀▀▀████▄▄▄▄░░░░░░░░░░░░░
		// ░░░░░▄██▀░░░░░░░░░░░░░░░░░▀▀██▄▄░░░░░░░░
		// ░░░░██░░░░░░░░░░░░░░░░░░░░░░░░▀▀█▄▄░░░░░
		// ░░▄█▀░░░░░░░░░░░░░░░░░░░░░░░░░░░░▀▀█▄░░░
		// ░▄█▀░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█▄░░
		// ░█▀░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▀█░
		// ▄█░░░░░░░░░░░░░░░░░░▄░░░░░░░░░░░░░░░░░██
		// █▀░░░░░░██▄▄▄▄▄░░░░▄█░░░░░░░░░░░░░░░░░░█
		// █░░░░░░░█▄░░▀██████▀░░░▄▄░░░░░░░░░░██░░█
		// █░░░░░░░░▀█▄▄▄█▀░░░░░░░██▀▀██▄▄▄▄▄▄█░░▄█
		// █░░░░░░░░░░░░░░░░░░░░░░░▀▄▄▄▀▀▀██▀░░░░█▀
		// █▄░░░░░▄▄░░░░░░░░░░░░░░░░░░▀▀▀▀▀░░░░▄█▀░
		// ░█▄░░░░█░░░░▄▄▄▄░░░░░░░░░░░░░░░░░░░▄█░░░
		// ░░█▄░░▀█▄░░▀▀▀███████▄▄▄░░░▄░░░░░▄█▀░░░░
		// ░░░█▄░░░░░░░░░░░░░▀▀▀░░█░░░█░░░░██░░░░░░
		// ░░░░▀█▄▄░░░░░░░░░░░░░░░░░██░░░▄█▀░░░░░░░
		// ░░░░░░▀▀█▄▄▄░░░░░░░░░░░░░▄▄▄█▀▀░░░░░░░░░
		// ░░░░░░░░░░▀▀█▀▀███▄▄▄███▀▀▀░░░░░░░░░░░░░
		// ░░░░░░░░░░░█▀░░░░░░░░░░░░░░░░░░░░░░░░░░░

		// Are you fucking kidding me
		p.setLayoutX(Double.longBitsToDouble(flipLastBit(Double.doubleToLongBits(p.getLayoutX()))));
	}

	/** Incur the smallest possible change to a long integer. */
	private static long flipLastBit(long bits) {
		return bits ^ 0x0000000000000000000000000000000000000000000000000000000000000001L;
	}

	/** Returns an indexed color opaque binary format. */
	public static PixelFormat<ByteBuffer> getBinaryFormat() {
		if (binary == null) {
			int[] gs = new int[0xFF];
			for (int i = 1; i < gs.length; i++) {
				gs[i] = 0xFFFFFFFF;
			}
			binary = PixelFormat.createByteIndexedInstance(gs);
		}
		return binary;
	}

	/** Returns an indexed color opaque grayscale format. */
	public static PixelFormat<ByteBuffer> getGrayScaleFormat() {
		if (grayScale == null) {
			int[] gs = new int[0xFF];
			for (int i = 0; i < gs.length; i++) {
				int c = (i) | (i << 8) | (i << 16) | (0xFF000000);
				gs[i] = c;
			}
			grayScale = PixelFormat.createByteIndexedInstance(gs);
		}
		return grayScale;
	}

	/**
	 * Creates a JavaFX color from a 24 bit int color without alpha
	 * component(alpha defaults to 100% opaque).
	 */
	public static Color rgb(int i) {
		int b = i & 0xFF;
		int g = (i >>> 8) & 0xFF;
		int r = (i >>> 16) & 0xFF;
		return new Color((double) r / 0xFF, (double) g / 0xFF, (double) b / 0xFF, 1.0);
	}

	/** Creates a JavaFX color from a 32 bit int color with alpha component. */
	public static Color argb(int i) {
		int b = i & 0xFF;
		int g = (i >>> 8) & 0xFF;
		int r = (i >>> 16) & 0xFF;
		int a = (i >>> 24) & 0xFF;
		return new Color((double) r / 0xFF, (double) g / 0xFF, (double) b / 0xFF, (double) a / 0xFF);
	}

	/** Creates a JavaFX color from a 16 bit int color with alpha component. */
	public static Color halfByteARGB(int i) {
		int b = i & 0xF;
		int g = (i >>> 4) & 0xF;
		int r = (i >>> 8) & 0xF;
		int a = (i >>> 12) & 0xF;
		return new Color((double) r / 0xF, (double) g / 0xF, (double) b / 0xF, (double) a / 0xF);
	}

	/**
	 * Creates a JavaFX color from a 12 bit int color without alpha
	 * component(alpha defaults to 100% opaque).
	 */
	public static Color halfByteRGB(int i) {
		int b = i & 0xF;
		int g = (i >>> 4) & 0xF;
		int r = (i >>> 8) & 0xF;
		return new Color((double) r / 0xF, (double) g / 0xF, (double) b / 0xF, 1.0);
	}

	/**
	 * Converts from the hue/saturation/brightness color space to the
	 * red/green/blue color space, returning a JavaFX {@link Color} object.
	 * 
	 * @param h
	 *            The hue, in the range 0-1.
	 * @param s
	 *            The saturation, in the range 0-1.
	 * @param b
	 *            The brightness, in the range 0-1.
	 * @return The color represented in RGB color space.
	 * @see java.awt.Color#HSBtoRGB(float, float, float)
	 */
	public static Color hsb(float h, float s, float b) {
		return rgb(java.awt.Color.HSBtoRGB(h, s, b));
	}

	/**
	 * Converts the given JavaFX Color to a 32 bit integer, where the most
	 * significant byte is the alpha component and the least significant byte is
	 * the blue component. <br>
	 * <br>
	 * Caution: Calling {@link Integer#toHexString(int)} on the return value
	 * will not yield a comparable result to {@link Color#toString()} because
	 * JavaFX uses the RGBA byte order. The ARGB byte order has the advantage
	 * that the most significant byte can be clipped (e.g. &0xFFFFFF) to obtain
	 * the opaque RGB value, which is (relatively) human readable and can be fed
	 * into the {@link #rgb(int)} function.
	 */
	public static int toARGB(Color color) {
		int a = (int) (0xFF * color.getOpacity());
		int r = (int) (0xFF * color.getRed());
		int g = (int) (0xFF * color.getGreen());
		int b = (int) (0xFF * color.getBlue());
		return (b) | (g << 8) | (r << 16) | (a << 24);
	}

	/**
	 * Returns whether the indicated Node is visible. This factors in the
	 * visibility of all parent nodes.
	 */
	public static boolean isVisible(Node node) {
		if (node == null)
			return true;
		return node.isVisible() && isVisible(node.getParent());
	}

	/** Sets the background color of a pane without the fluff. */
	public static void setBackground(Region p, Color bgColor) {
		p.setBackground(new Background(new BackgroundFill(bgColor, new CornerRadii(0), new Insets(0))));
	}

	/**
	 * A map from visibility listeners to hierarchy listeners: Each visibility
	 * listener is associated with a hierarchy listener which is responsible for
	 * adding/removing it to the visibility properties in the parent chain. This
	 * is a weak hash map because, if the visibility listener is explicitly
	 * removed from all nodes, or those nodes simply cease to exist, then there
	 * is no other mechanism to remove the references to the hierarchy
	 * listeners. On a related but different note, if the visibility listeners
	 * themselves are weak, then a separate mechanism is used to remove the
	 * related hierarchy listeners from this map.
	 * 
	 * @see #addVisibilityListenerTo(Node, InvalidationListener)
	 */
	private static WeakHashMap<InvalidationListener, ChangeListener<Node>> visibilityHierarchyListeners = new WeakHashMap<>();

	/**
	 * Adds a visibility listener to the given node. This will also listen to
	 * changes the the parent chain and the visibility of all parents.
	 * 
	 * @see #removeVisibilityListenerFrom(Node, InvalidationListener,
	 *      ChangeListener)
	 */
	public static void addVisibilityListenerTo(Node n, final InvalidationListener listener) {
		ChangeListener<Node> h = visibilityHierarchyListeners.get(listener);
		if (h == null) {
			// This will only happen once per visibility listener.
			h = new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
					// If the listener was weak and died
					if (listener instanceof WeakInvalidationListener
							&& ((WeakInvalidationListener) listener).wasGarbageCollected()) {
						// Remove this listener from the parent property.
						// Note that it is possible to reuse the listener(s)
						// for multiple leaf nodes, and that information is
						// not known here, so the remove must be done on
						// a case-by-case basis.
						observable.removeListener(this);
						// Forget that it existed so that it cannot be used
						// again
						// (It may already have been removed. This is expected.)
						visibilityHierarchyListeners.remove(listener);
					} else {
						// Remove visibility listener from old parent
						internalRemoveVisibilityListenerFrom(oldValue, listener, this);
						// Add visibility listener to new parent
						internalAddVisibilityListenerTo(newValue, listener, this);
						// Signal that the new visibility is unknown.
						// Since newValue may be null, the parent property
						// is the most relevant argument to use here...
						listener.invalidated(observable);
					}
				}
			};
			visibilityHierarchyListeners.put(listener, h);
		}
		internalAddVisibilityListenerTo(n, listener, h);
	}

	private static void internalAddVisibilityListenerTo(Node n, InvalidationListener visibilityListener,
			ChangeListener<Node> hierarchyListener) {
		if (n == null)
			return;
		n.visibleProperty().addListener(visibilityListener);
		n.parentProperty().addListener(hierarchyListener);
		internalAddVisibilityListenerTo(n.getParent(), visibilityListener, hierarchyListener);
	}

	/**
	 * Remove visibility listener from the node and all of its parents.
	 * 
	 * @see #addVisibilityListenerTo(Node, InvalidationListener, ChangeListener)
	 */
	public static void removeVisibilityListenerFrom(Node n, InvalidationListener listener) {
		ChangeListener<Node> h = visibilityHierarchyListeners.get(listener);
		if (h == null) {
			new Throwable("Warning: Cannot remove visibility listener from: " + n
					+ " because there is no hierarchy listener registered for the listener: " + listener
					+ " (most likely the visibility listener was already removed or never added)").printStackTrace();
			return;
		}
		internalRemoveVisibilityListenerFrom(n, listener, h);
	}

	private static void internalRemoveVisibilityListenerFrom(Node n, InvalidationListener listener,
			ChangeListener<Node> hierarchyListener) {
		if (n == null)
			return;
		n.visibleProperty().removeListener(listener);
		n.parentProperty().removeListener(hierarchyListener);
		internalRemoveVisibilityListenerFrom(n.getParent(), listener, hierarchyListener);
	}

	/**
	 * Save the indicated JavaFX to the given file using the given image file
	 * format.
	 */
	public static void save(Image image, ImageFormat format, File output) throws IOException {
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ImageIO.write(bImage, format.imageIOName, output);
	}

	/**
	 * Save the indicated JavaFX to the given output stream using the given
	 * image file format.<br>
	 * <br>
	 * This method does not close the provided OutputStream after the write
	 * operation has completed; it is the responsibility of the caller to close
	 * the stream.
	 */
	public static void save(Image image, ImageFormat format, OutputStream output) throws IOException {
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ImageIO.write(bImage, format.imageIOName, output);
	}

	/**
	 * This method should be called when it is certain that no context menu
	 * should exist. Note that this is called from
	 * {@link #contextMenuCreated(ContextMenu)}, so it is not necessary to call
	 * it before showing a new menu.
	 */
	public static void destroyCurrentContextMenu() {
		if (currentContextMenu != null) {
			ContextMenu oldMenu = currentContextMenu.get();
			if (oldMenu != null)
				oldMenu.hide();
		}
	}

	/**
	 * This method should be called consistently throughout the application when
	 * a context menu is created, to ensure that only one context menu can exist
	 * at the same time. This method uses a weak reference and as such it does
	 * not constitute a risk of a memory leak.
	 */
	public static void contextMenuCreated(ContextMenu m) {
		destroyCurrentContextMenu();
		currentContextMenu = new WeakReference<ContextMenu>(m);
	}

	public static Color derive(Color c, float alpha) {
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}
}
