package no.hal.emfblocks.hierarchy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Function;

import javax.imageio.ImageIO;

import no.hal.emfblocks.contour.BitmapContour;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public class TypeHierarchy {
	private static final String CUT_CONFIG_FILE_NAME = "cutConfig.txt";
	private static final boolean LOG = true;

	/** List of all available type nodes */
	protected final ArrayList<TypePrimitive> values;
	/** Map of all available type nodes by their key objects */
	protected final HashMap<Object, TypePrimitive> valueMap;
	/** List of all the hierarchy's roots. Uses lazy initialization. */
	protected LinkedHashSet<TypePrimitive> roots = null;

	/**
	 * The width of slots and tabs. This property must never be changed from the
	 * application.
	 */
	public final PropertyReadOnly<Short> connectorWidth = new Property<Short>(Short.class, null);
	/**
	 * The height of slots and tabs. This property must never be changed from
	 * the application.
	 */
	public final PropertyReadOnly<Short> connectorHeight = new Property<Short>(Short.class, null);

	/** A device for converting type keys to (unique) strings. */
	private Function<Object, String> keyStringifier = null;

	/** The directory used for storing and loading cuts */
	private File cutDir;

	/**
	 * 
	 * Create a new type hierarchy from the given type nodes.
	 * 
	 * @param cutDir
	 *            The directory where we will load cut images from. If null,
	 *            then the default directory "cuts" is used. If cuts cannot be
	 *            loaded for any reason, then default cuts will be generated.
	 * @param vals
	 *            All types that will be used in the hierarchy
	 */
	public TypeHierarchy(File cutDir, Function<Object, String> keyStringifier, TypePrimitive... vals) {
		this.keyStringifier = keyStringifier;
		if (cutDir == null)
			cutDir = new File("cuts");
		this.cutDir = cutDir;

		short w, h;
		// CutConfig config = CutConfig.serializer.read(new File(cutDir,
		// CUT_CONFIG_FILE_NAME));
		Properties config = new Properties();

		File f = new File(cutDir, CUT_CONFIG_FILE_NAME);
		if (f.exists()) {
			try (FileReader fr = new FileReader(f)) {
				config.load(fr);
			} catch (IOException e) {
				System.err.println("Could not read cut config file " + f + "...");
				e.printStackTrace();
			}
		}
		w = -1;
		h = -1;

		try {
			if (config.containsKey("connectorWidth"))
				w = Short.parseShort((String) config.get("connectorWidth"));
			if (config.containsKey("connectorHeight"))
				h = Short.parseShort((String) config.get("connectorHeight"));
		} catch (IllegalArgumentException e) {
			System.err.println("Cut config file " + f + " is corrupt...");
			e.printStackTrace();
		}

		if (w <= 0 || h <= 0) {
			w = BitmapContour.getDefaultSize(vals.length);
			h = w;
		}

		((Property<Short>) connectorWidth).set(w);
		((Property<Short>) connectorHeight).set(h);

		this.values = new ArrayList<>(Arrays.asList(vals));
		this.valueMap = new HashMap<>();
		for (int i = 0; i < values.size(); i++) {
			TypePrimitive t = values.get(i);
			assert !t.isParentOf(t) : t + " cannot be its own parent";
			assert !t.isChildOf(t) : t + " cannot be its own child";

			assert (!t.hasContour()) : t + " has already been initialized! Cannot add it to " + this + "...";

			genContour(cutDir, t, i);

			// Record the value in the hashmap so it can be found via its key
			// later
			this.valueMap.put(t.getKey(), t);
		}
		for (int i = 0; i < values.size(); i++) {
			TypePrimitive t = values.get(i);
			((BitmapContour) t.getContour()).updateImageRaster(this, t, false);
		}
	}

	private void genContour(File cutDir, TypePrimitive t, int index) {
		short w = connectorWidth.get();
		short h = connectorHeight.get();
		byte[] raster = new byte[w * h];

		File imageFile = new File(cutDir, keyToString(t.getKey()) + ".png");
		try {
			if (!imageFile.exists())
				throw new FileNotFoundException("The file " + imageFile + " does not exist.");
			BufferedImage im = ImageIO.read(imageFile);
			if (im.getWidth() != w || im.getHeight() != h)
				throw new IllegalArgumentException("Size of cut image was " + im.getWidth() + " x " + im.getHeight()
						+ ", but the cut configuration requires a size of " + w + " x " + h);
			im = toBinary(imageFile, im, raster);
		} catch (Exception e) {
			if (!(e instanceof FileNotFoundException)) {
				System.err.println("Error in reading cut image file " + imageFile + " for type " + t + ": ");
				e.printStackTrace();
			}
			Arrays.fill(raster, (byte) 1);

			int resolution = (int) Math.ceil(Math.sqrt(getNumValues()));

			int upperEdgeCutThreshold = resolution;
			int lowerEdgeCutThreshold = resolution * 2;

			int baseX = ((index % resolution) * w) / resolution;
			int endX = Math.min(((1 + (index % resolution)) * w) / resolution, w - 1);
			if (index < upperEdgeCutThreshold) {
				for (int x = baseX; x < endX; x++) {
					int slope;
					if (index % 2 == 0)
						slope = Math.min(h, endX - 1 - x);
					else
						slope = Math.min(h, x + 1 - baseX);

					for (int y = 0; y < slope; y++) {
						raster[x + y * w] = 0;
					}
				}
			} else if (index < lowerEdgeCutThreshold) {
				for (int x = baseX; x < endX; x++) {
					int slope;
					if (index % 2 == 0)
						slope = Math.min(h, endX - 1 - x);
					else
						slope = Math.min(h, x + 1 - baseX);

					for (int y = 0; y < slope; y++) {
						raster[x + (h - 1 - y) * w] = 0;
					}
				}
			} else {
				int avgCutSize = w / resolution + 1;
				// Internal cuts
				int line = (index / resolution) - 2;
				int y = avgCutSize + (line * (h - 2 * avgCutSize)) / (resolution - 2);
				for (int x = baseX; x < endX; x++) {
					raster[x + y * w] = 0;
				}
			}
		}

		t.setContour(new BitmapContour(t, raster, w, h));
	}

	private static BufferedImage toBinary(File imageFile, BufferedImage im, byte[] raster) {
		int w = im.getWidth();
		int h = im.getHeight();
		if (im.getRaster().getTransferType() != DataBuffer.TYPE_BYTE || im.getRaster().getNumBands() != 1) {
			BufferedImage oldIm = im;
			im = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D c = im.createGraphics();
			c.drawImage(oldIm, 0, 0, null);
			c.dispose();
		}
		if (im.getType() == BufferedImage.TYPE_BYTE_GRAY || im.getType() == BufferedImage.TYPE_BYTE_INDEXED
				|| im.getType() == BufferedImage.TYPE_BYTE_BINARY)
			im.getRaster().getDataElements(0, 0, w, h, raster);

		if (im.getType() == BufferedImage.TYPE_BYTE_INDEXED) {
			if (!(im.getColorModel() instanceof IndexColorModel))
				throw new IllegalStateException(
						"TYPE_BYTE_INDEXED image's color model was not IndexColorModel! File: " + imageFile);
			IndexColorModel icm = (IndexColorModel) im.getColorModel();
			for (int i = 0; i < raster.length; i++) {
				if (icm.getRed(raster[i]) == 0)
					raster[i] = 0;
				else
					raster[i] = 1;
			}
		}
		return im;
	}

	/** Save all current cuts to the current cuts directory. */
	public void saveCuts() {
		saveCutsTo(cutDir, false);
	}

	/**
	 * Save all current cuts to the indicated directory. The directory may not
	 * be null.
	 */
	public void saveCutsTo(File cutDir, boolean replaceCurrentDirectory) {
		try {
			if (LOG)
				System.out.println("INFO: Saving cuts to directory \"" + cutDir + "\"");
			if (cutDir.exists()) {
				if (!cutDir.isDirectory())
					throw new IOException(cutDir + " exists but it not a directory!");
			} else if (!cutDir.mkdirs())
				throw new IOException(cutDir + " could not be created!");
			this.cutDir = cutDir;

			short w = connectorWidth.get();
			short h = connectorHeight.get();

			// CutConfig.serializer.write(new File(cutDir,
			// CUT_CONFIG_FILE_NAME), new CutConfig(w, h));

			Properties config = new Properties();
			config.put("connectorWidth", String.valueOf(w));
			config.put("connectorHeight", String.valueOf(h));

			File f = new File(cutDir, CUT_CONFIG_FILE_NAME);
			try (FileOutputStream fos = new FileOutputStream(f)) {
				config.store(fos, null);
			} catch (IOException e) {
				System.err.println("Could not store cut config file " + f + "...");
				e.printStackTrace();
			}

			byte[] g = new byte[256];
			Arrays.fill(g, (byte) 255);
			g[0] = 0;
			IndexColorModel icm = new IndexColorModel(8, 256, g, g, g);

			for (TypePrimitive n : values) {
				File output = new File(cutDir, keyToString(n.getKey()) + ".png");
				BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED, icm);
				im.getRaster().setDataElements(0, 0, w, h, ((BitmapContour) n.getContour()).getCutRaster());
				ImageIO.write(im, "PNG", output);
			}
		} catch (IOException e) {
			System.err.println("Error: Could not save cuts: ");
			e.printStackTrace();
		}
	}

	public String keyToString(Object key) {
		if (keyStringifier == null)
			return String.valueOf(key);
		else
			return keyStringifier.apply(key);
	}

	/** Create a TypeHierarchy using the given Java classes. */
	public TypeHierarchy(File cutDir, Class<?>... classes) {
		this(cutDir, new Function<Object, String>() {
			@Override
			public String apply(Object t) {
				return t != null ? ((Class<?>) t).getSimpleName() : String.valueOf(t);
			}
		}, toNodes(classes));
	}

	/** Create an array of TypeNodes for the given Java classes. */
	private static JavaClassNode[] toNodes(Class<?>[] classes) {
		JavaClassNode[] r = new JavaClassNode[classes.length];
		for (int i = 0; i < r.length; i++)
			r[i] = new JavaClassNode(classes[i]);
		return r;
	}

	/**
	 * Create a TypeNode for the given Java class. <br/>
	 * <br/>
	 * This part of the API is for use by the Cut Editor only.
	 */
	public static JavaClassNode toNode(Class<?> type) {
		return new JavaClassNode(type);
	}

	/**
	 * Add a type parameter to an existing type in the hierarchy. This must only
	 * be called during initialization!
	 */
	public void addTypeParameter(String pname, TypePrimitive primaryType, TypePrimitive boundingType) {
		boolean b = primaryType.addTypeParameter(pname, boundingType);
		if (!b)
			throw new IllegalStateException(
					"The type " + primaryType + " already has a type parameter with the name " + pname + "!");
	}

	/**
	 * Add a type parameter to an existing type in the hierarchy. This must only
	 * be called during initialization!
	 */
	public void addTypeParameter(String pname, Class<?> primaryType, Class<?> boundingType) {
		addTypeParameter(pname, getType(primaryType), getType(boundingType));
	}

	/**
	 * Return the type node instance with the given key. The type node must have
	 * been registered in the hierarchy when it was created.
	 */
	public TypePrimitive getType(Object key) {
		TypePrimitive v = valueMap.get(key);
		if (v == null) {
			for (Entry<Object, TypePrimitive> entry : valueMap.entrySet()) {
				if (entry.getKey().equals(key)) {
					throw new RuntimeException(key + " violates hashCode convention!");
				}
			}
			throw new IllegalStateException("Unknown key " + key + " for " + this);
		}
		return v;
	}

	/** Return the number of types in the hierarchy */
	public int getNumValues() {
		return values.size();
	}

	/**
	 * Return all parent types of the given type. The returned array can be
	 * modified without affecting the hierarchy itself.
	 */
	public TypePrimitive[] getParentsOf(TypePrimitive n) {
		ArrayList<TypePrimitive> r = new ArrayList<>();
		for (TypePrimitive p : values) {
			if (p.isParentOf(n))
				r.add(p);
		}
		return r.toArray(new TypePrimitive[r.size()]);
	}

	/**
	 * Return all the hierarchy's roots. The returned array can be modified
	 * without affecting the hierarchy itself.
	 */
	public TypePrimitive[] getRoots() {
		return roots.toArray(new TypePrimitive[roots.size()]);
	}

	/** Return the node with the given index. */
	public TypePrimitive getValue(int index) {
		return values.get(index);
	}

	/** Return a new array containing all type nodes. */
	public TypePrimitive[] getValues() {
		return values.toArray(new TypePrimitive[values.size()]);
	}

	private void findRoots() {
		if (roots == null) {
			roots = new LinkedHashSet<>();
			for (int i = 0; i < values.size(); i++) {
				TypePrimitive t = values.get(i);
				assert !t.isParentOf(t) : t + " cannot be its own parent";
				assert !t.isChildOf(t) : t + " cannot be its own child";

				boolean isTmpRoot = true;

				Iterator<TypePrimitive> iterator = roots.iterator();
				while (iterator.hasNext()) {
					TypePrimitive r = iterator.next();
					if (t.isParentOf(r)) // r has a supertype so it is not a
											// root, remove it
					{
						iterator.remove();
					} else if (t.isChildOf(r)) // t is a subtype so it cannot be
												// root, don't add it
					{
						isTmpRoot = false;
					}
				}
				if (isTmpRoot)// Add t as a tentative root if no parents were
								// found
					roots.add(t);
			}
		}
	}

	/**
	 * Whether the Type Node is a root in the hierarchy. Note that hierarchy can
	 * have multiple roots, but no cycles.
	 */
	public boolean isRoot(TypePrimitive t) {
		findRoots();
		return roots.contains(t);
	}

	/**
	 * Signal that any cached computations may be invalid due to structural
	 * changes to the type hierarchy. This should not be called by the
	 * application.
	 */
	public void invalidate() {
		roots = null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + this.values;
	}

	public File getCutDirectory() {
		return cutDir;
	}
}
