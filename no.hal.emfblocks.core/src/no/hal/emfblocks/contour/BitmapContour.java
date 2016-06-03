package no.hal.emfblocks.contour;

import java.nio.ByteBuffer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import no.hal.emfblocks.hierarchy.TypeHierarchy;
import no.hal.emfblocks.hierarchy.TypePrimitive;

public class BitmapContour implements Contour {
	/** Color value for transparent, external pixels on the image */
	public static int EXTERNAL_PIXEL = 0x00000000;
	/** Color value for opaque, internal pixels on the image */
	public static int BORDER_PIXEL = 0xFF808080;
	/** Color value for border pixels on the image */
	public static int INTERNAL_PIXEL = 0xFFFFFFFF;
	/**
	 * Pixel formats for the contour bitmaps.
	 * 
	 * <pre>
	 * The internal values uses the values 0x00, 0x01 and 0x02.
	 * <b>tabFormat</b> uses the following logical mapping: 
	 * 0x00 -> {@value #EXTERNAL_PIXEL}
	 * 0x01 -> {@value #BORDER_PIXEL} 
	 * 0x02 -> {@value #INTERNAL_PIXEL}
	 * <b>slotFormat</b> uses the following logical mapping: 
	 * 0x00 -> {@value #INTERNAL_PIXEL}
	 * 0x01 -> {@value #BORDER_PIXEL} 
	 * 0x02 -> {@value #EXTERNAL_PIXEL}
	 * <b>highlightFormat</b> uses the following logical mapping: 
	 * 0x00 -> {@value #EXTERNAL_PIXEL}
	 * 0x01 -> {@value #INTERNAL_PIXEL} 
	 * 0x02 -> {@value #EXTERNAL_PIXEL}
	 * </pre>
	 */
	public static PixelFormat<ByteBuffer> tabFormat;
	public static PixelFormat<ByteBuffer> slotFormat;
	public static PixelFormat<ByteBuffer> highlightFormat;

	private TypePrimitive type;
	/** The cut raster. This defines the cut that is unique to this type. */
	private byte[] cutRaster;
	/**
	 * The raster used to construct the different images. Is constructed from
	 * the cut raster of this type and parent types.
	 * 
	 * @see #tabImage
	 * @see #slotImage
	 * @see #highlightImage
	 */
	private byte[] internalRaster;
	/** Objects may listen to changes to the contour's cut */
	// public final ActionSource rasterListeners = new ActionSource();
	/** The size of the {@link #raster} */
	private short width, height;
	/**
	 * Images for different representations of the contour.
	 * 
	 * @see #tabFormat
	 * @see #slotFormat
	 * @see #highlightFormat
	 */
	private ObjectProperty<Image> tabImageProperty;
	private ObjectProperty<Image> slotImageProperty;
	private ObjectProperty<Image> highlightImageProperty;

	/**
	 * Create a bitmap contour for the given {@link TypePrimitive}.
	 * {@link #updateImageRaster(TypeHierarchy, TypePrimitive, boolean)} must be
	 * called at least once to make the BitmapContour usable.
	 */
	public BitmapContour(TypePrimitive type, byte[] raster, short rasterWidth, short rasterHeight) {
		this.type = type;
		setCutRaster(raster, rasterWidth, rasterHeight);
	}

	/**
	 * Return the value at the given position in the raster(with 1 Bpp). Since
	 * this is used to generate differently colored border pixels, the following
	 * border cases are used:
	 * 
	 * <pre>
	 * <b>0x02</b> is returned on the <b>eastern</b> exterior 
	 * <b>0x00</b> is returned on the <b>northern, western and southern</b> exteriors
	 * </pre>
	 * 
	 * If there is a conflict, 0 takes precedence. As a result, pixels the east
	 * side of the image(positive X) will not be treated as border pixels,
	 * unless they are neighbour to a pixel with the value 0 on the interior of
	 * the image.
	 */
	private static int val(byte[] raster, int x, int y, int width, int height) {
		if (x < 0)
			return 0;
		if (y < 0)
			return 0;
		if (x >= width)
			return 2;
		if (y >= height)
			return 0;
		return raster[width * y + x];
	}

	@Override
	public short getWidth() {
		return width;
	}

	@Override
	public short getHeight() {
		return height;
	}

	@Override
	public TypePrimitive getTypeNode() {
		return type;
	}

	/**
	 * Create an image from the contour's raster with the given format.
	 * 
	 * @see #tabFormat
	 * @see #slotFormat
	 * @see #highlightFormat
	 */
	private Image makeImage(PixelFormat<ByteBuffer> format) {
		WritableImage im = new WritableImage(width, height);
		PixelWriter writer = im.getPixelWriter();
		writer.setPixels(0, 0, width, height, format, internalRaster, 0, width);
		return im;
	}

	/**
	 * Return the contour's cut raster. The caller must call
	 * {@link #setCutRaster(byte[], short, short)} after any eventual edit.
	 * <br/>
	 * <br/>
	 * This part of the API is for use by the Cut Editor only.
	 */
	public byte[] getCutRaster() {
		return cutRaster;
	}

	/**
	 * Return the contour's internal image raster. The caller should not edit
	 * the returned array. <br/>
	 * <br/>
	 * This part of the API is for use by the Cut Editor only.
	 */
	public byte[] getInternalRaster() {
		return internalRaster;
	}

	/**
	 * Create the contour's internal raster from the given cut raster. This
	 * method must be called at least once before the contour is usable. The
	 * given raster should be a binary image, where 0 is a transparent pixel and
	 * any other value is a non-transparent pixel. <br/>
	 * <br/>
	 * {@link #createImageRaster(TypePrimitive[])} must be called after this
	 * method in order to update the images. <br/>
	 * <br/>
	 * Note that the passed array will be kept by the contour object. This means
	 * that {@link #updateImageRaster(TypeHierarchy, TypePrimitive, boolean)}
	 * needs to be called again after each edit to the array. However, this
	 * method does not need to be called again unless the dimension changes.
	 * <br/>
	 * <br/>
	 * This part of the API is only for use during initialization or by the Cut
	 * Editor.
	 */
	public void setCutRaster(byte[] cutRaster, short w, short h) {
		this.width = w;
		this.height = h;
		this.cutRaster = cutRaster;
		this.internalRaster = null;
	}

	/**
	 * Create the image raster from the current cut raster and parent types. If
	 * updateChildren is true, then the contour images of child types will also
	 * be updated.
	 * 
	 * This must be called after {@link #setCutRaster(byte[], short, short)}.
	 * This will cause the image properties to be updated. <br/>
	 * <br/>
	 * This part of the API is only for use during initialization or by the Cut
	 * Editor.
	 */
	public void updateImageRaster(TypeHierarchy hierarchy, TypePrimitive myType, boolean updateChildren) {
		this.internalRaster = new byte[width * height];
		for (int i = 0; i < width * height; i++) {
			if (cutRaster[i] != 0 || i % width == width - 1)
				internalRaster[i] = 2;
		}

		for (TypePrimitive type : hierarchy.getValues()) {
			if (type.isParentOf(myType)) {
				int i = 0;
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						if (((BitmapContour) type.getContour()).cutRaster[i] == 0)
							internalRaster[i] = 0;
						i++;
					}
				}
			} else if (type.isChildOf(myType) && updateChildren) {
				/*
				 * Don't force update child nodes of child nodes because 1. The
				 * child nodes' cuts are unchanged. 2. They will be updated
				 * anyway
				 */
				((BitmapContour) type.getContour()).updateImageRaster(hierarchy, type, false);
			}
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (internalRaster[y * width + x] == 2 && (val(internalRaster, x - 1, y, width, height) == 0
						|| val(internalRaster, x + 1, y, width, height) == 0 || // Open
																				// end
						val(internalRaster, x, y - 1, width, height) == 0
						|| val(internalRaster, x, y + 1, width, height) == 0))
					internalRaster[y * width + x] = 1;
			}
		}
		if (tabImageProperty != null)
			tabImageProperty.set(makeImage(tabFormat));
		if (slotImageProperty != null)
			slotImageProperty.set(makeImage(slotFormat));
		if (highlightImageProperty != null)
			highlightImageProperty.set(makeImage(highlightFormat));
	}

	@Override
	public ObjectProperty<Image> tabImage() {
		if (tabFormat == null)
			tabFormat = PixelFormat
					.createByteIndexedInstance(new int[] { EXTERNAL_PIXEL, BORDER_PIXEL, INTERNAL_PIXEL });
		if (tabImageProperty == null)
			tabImageProperty = new ObjectPropertyBase<Image>(makeImage(tabFormat)) {
				@Override
				public Object getBean() {
					return BitmapContour.this;
				}

				@Override
				public String getName() {
					return "Tab Image";
				}
			};
		return tabImageProperty;
	}

	@Override
	public ObjectProperty<Image> slotImage() {
		if (slotFormat == null)
			slotFormat = PixelFormat
					.createByteIndexedInstance(new int[] { INTERNAL_PIXEL, BORDER_PIXEL, EXTERNAL_PIXEL });
		if (slotImageProperty == null)
			slotImageProperty = new ObjectPropertyBase<Image>(makeImage(slotFormat)) {
				@Override
				public Object getBean() {
					return BitmapContour.this;
				}

				@Override
				public String getName() {
					return "Slot Image";
				}
			};
		return slotImageProperty;
	}

	@Override
	public ObjectProperty<Image> highlightImage() {
		if (highlightFormat == null)
			highlightFormat = PixelFormat
					.createByteIndexedInstance(new int[] { EXTERNAL_PIXEL, INTERNAL_PIXEL, EXTERNAL_PIXEL });
		if (highlightImageProperty == null)
			highlightImageProperty = new ObjectPropertyBase<Image>(makeImage(highlightFormat)) {
				@Override
				public Object getBean() {
					return BitmapContour.this;
				}

				@Override
				public String getName() {
					return "Highlight Image";
				}
			};
		return highlightImageProperty;
	}

	public static short getDefaultSize(int numNodes) {
		int resolution = Math.max(2, (int) Math.ceil(Math.sqrt(numNodes)));
		int cutWidth = 8 - Math.min(4, resolution * 2);
		return (short) (1 + cutWidth * resolution);
	}
}
