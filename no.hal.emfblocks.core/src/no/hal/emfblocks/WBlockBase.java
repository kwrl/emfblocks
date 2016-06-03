package no.hal.emfblocks;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Queue;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.hal.emfblocks.hierarchy.ParameterizedType;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.hierarchy.TypeParameter;
import no.hal.emfblocks.hierarchy.TypePrimitive;
import no.hal.emfblocks.javafx.ActionSource;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;
import no.hal.emfblocks.util.data.pfield.PFieldOwner;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public abstract class WBlockBase implements WBlock {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final Property<WSingleFieldContainer<? super WBlock>> container = new Property(WContainer.class, null);
	// Private to prevent two separate access mechanisms(simplifies debugging)
	private final Property<Type> tabType = new Property<Type>(Type.class, null);
	private final Property<Boolean> tabVisible = new Property<>(true);
	private final ObjectProperty<Color> color = new ObjectPropertyBase<Color>(Color.GRAY) {
		@Override
		public Object getBean() {
			return WBlockBase.this;
		}

		@Override
		public String getName() {
			return "Block Color";
		}
	};
	protected final ActionSource destructionListeners = new ActionSource();
	protected boolean destroyed = false;
	/**
	 * This is a cached result, assigned in {@link #isPaletteBlock()}. True if
	 * there is a {@link WPalette} in the parent chain, false if the parent
	 * chain connects to the {@link WRoot} with no {@link WPalette}, and null
	 * otherwise. The cached result is reset to null on
	 * {@link #addNotify(PFieldImmutable)} or
	 * {@link #removeNotify(PFieldImmutable)}
	 */
	protected Boolean isInPalette = null;

	protected WRoot root;
	protected FXBlock pane;
	protected final LinkedHashSet<CellListener> cellListeners = new LinkedHashSet<>();
	protected final Referencer referencer = new ReferenceSet();

	protected final ArrayList<ArrayList<WCell>> table = new ArrayList<>();

	public WBlockBase(WBlockBase src) {
		this(src.getRoot(), src.tabType.get().getRawType(), src.color.get());

	}

	public WBlockBase(WRoot root, TypePrimitive type, Color defaultColor) {
		this.root = root;

		this.tabType.addListener(referencer, (param, oldVal, newVal) -> {
			WSingleFieldContainer<? super WBlock> parent = blockContainer().get();
			if (parent != null && parent.accepts(this) != BlockDropAction.ACCEPT) {
				System.err.println(this + " is ejecting due to a type change! type=" + newVal);
				eject();
			}
		});

		if (type != null) {
			if (type.hasParameters())
				this.tabType.set(new ParameterizedType(type));
			else
				this.tabType.set(type);
		}
		color.set(defaultColor);
		getCell(0, 0);
	}

	public WBlockBase(WRoot root, Object typeKey, Color defaultColor) {
		// Note: getTypeNode() is fail-fast.
		this(root, typeKey == null ? null : root.getTypeHierarchy().getType(typeKey), defaultColor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void addNotify(PFieldImmutable<?> newField) {
		isInPalette = null;
		if (container.get() != null)
			System.err.println("Warning: " + this + " was added to: " + newField.getOwner()
					+ " without being removed from previous container: " + container.get());
		container.set((WSingleFieldContainer<? super WBlock>) newField.getOwner());
		addNotify2();
	}

	protected void addNotify2() {
	}

	@Override
	public final void removeNotify(PFieldImmutable<?> fromField) {
		isInPalette = false;
		// System.out.println(this+": removeNotify from "+fromField.getOwner());
		if (fromField.getOwner() == container.get())
			container.set(null);
		else
			System.err.println("Warning: " + this + " was removed from unknown container: " + fromField.getOwner()
					+ "(current container=" + container.get() + ")");
		removeNotify2();
	}

	protected void removeNotify2() {
	}

	@Override
	public FXBlock getUI() {
		if (pane == null) {
			installContentPanes();
			pane = new FXBlock(this);
		}
		return pane;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	/**
	 * Called when the JavaFX GUI is being constructed. The implementation
	 * should make any calls to
	 * {@link WCell#installContentPane(no.hal.emfblocks.javafx.SizedNode)} in
	 * this method.
	 */
	protected abstract void installContentPanes();

	public boolean isPaletteBlock() {
		if (isInPalette == null) {
			PFieldOwner<?> p = blockContainer().get();
			while (p != null) {
				if (p instanceof WPalette) {
					isInPalette = true;
					break;
				}
				p = ((WNode) p).getParent();
				if (p instanceof WRoot)
					isInPalette = false;
			}
		}
		return isInPalette == Boolean.TRUE;// (Both null and false => false)
	}

	@Override
	public void putTargetSlots(Queue<Slot> sq) {
		if (isPaletteBlock())
			return;

		for (ArrayList<WCell> a : table) {
			for (WCell c : a) {
				c.putTargetSlots(sq);
			}
		}
	}

	@Override
	public void setLocation(int x, int y) {
		getUI().setBlockLocation(x, y);
	}

	@Override
	public int getX() {
		return pane.getRealX();
	}

	@Override
	public int getY() {
		return pane.getRealY();
	}

	@Override
	public PropertyReadOnly<WSingleFieldContainer<? super WBlock>> blockContainer() {
		return container;
	}

	@Override
	public ObjectProperty<Color> color() {
		return color;
	}

	public int getNumRows() {
		return table.size();
	}

	public int getNumColumnsAt(int row) {
		return table.get(row).size();
	}

	@Override
	public WCell getCell(int x, int y) {
		// Need to add new rows until we have the indicated row(y)
		while (y >= table.size()) {
			table.add(new ArrayList<WCell>());
		}
		// The uppermost row must always have as many or more elements as the
		// rest
		ArrayList<WCell> upperRow = table.get(0);
		int tx = upperRow.size();
		while (x > tx) {
			WCell c = new WCell(tx, 0, this);
			upperRow.add(c);
			tx++;
			for (CellListener l : cellListeners)
				l.cellAdded(c);
		}
		ArrayList<WCell> row = table.get(y);
		int cx = row.size();
		while (x >= cx) {
			WCell c = new WCell(cx, y, this);
			row.add(c);
			cx++;
			for (CellListener l : cellListeners)
				l.cellAdded(c);
		}
		return row.get(x);
	}

	@Override
	public void addCellListener(CellListener l) {
		cellListeners.add(l);
	}

	@Override
	public boolean removeCellListener(CellListener l) {
		return cellListeners.remove(l);
	}

	@Override
	public WRoot getRoot() {
		return root;
	}

	@Override
	public Property<Type> tabType() {
		return tabType;
	}

	@Override
	public Property<Boolean> tabVisible() {
		return tabVisible;
	}

	@Override
	public WBlock getBlockAt(Point2D localPoint) {
		if (((FXBlock) getUI()).blockContains(localPoint)) {
			for (ArrayList<WCell> a : table) {
				for (WCell c : a) {
					Pane ui = c.getUI();
					Point2D cellPoint = FXUtil.fromTo(pane, ui, localPoint);
					WBlock blk = c.getBlockAt(cellPoint);
					if (blk != null)
						return blk;
				}
			}
			return this;
		}
		return null;
	}

	@Override
	public void eject() {
		WPane p = getWPane();
		FXBlock oldUI = (FXBlock) getUI();
		Point2D pt = FXUtil.fromTo(oldUI, p.getInternalUI(), new Point2D(50, 50));

		blockContainer().get().getChildren().remove(this);
		if (p.accepts(this) == BlockDropAction.ACCEPT) {
			// TODO unhandled exceptions
			p.getChildren().add(this);
			setLocation((int) pt.getX(), (int) pt.getY());
			oldUI.toFront();
		} else {
			dispose();
		}
	}

	@Override
	public WPointerBlock makePointerBlock() {
		if (tabType().get() == null)
			throw new IllegalArgumentException(
					this + " does not have a tab type. Pointers to a block with no tab type is impossible.");
		return new WPointerBlockBase(this);
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public final void dispose() {
		if (isDestroyed())
			new Throwable("Warning: Block destroyed twice: " + this).printStackTrace();
		else {
			destroyed = true;
			if (getUI().isFocused()) {
				// Prevent JavaFX from handing over the focus to something
				// random
				root.defaultFocus();
			}
			for (ArrayList<WCell> a : table) {
				for (WCell c : a) {
					c.dispose();
				}
			}
			destructionListeners.fireEvents();
			dispose2();
		}
	}

	/**
	 * Optional method. Override this method instead of dispose(), to prevent
	 * accidentally overriding mandatory functionality.
	 */
	protected void dispose2() {
	}

	@Override
	public WBlock copy() {
		WBlock copy = shallowCopy();
		copyChildrenTo(copy);
		return copy;
	}

	/**
	 * Helper function for the default {@link #copy()} mechanism. Copies the
	 * children of this block to another block.
	 */
	protected void copyChildrenTo(WBlock copy) {
		for (int y = 0; y < table.size(); y++) {
			ArrayList<WCell> a = table.get(y);
			for (int x = 0; x < a.size(); x++) {
				WCell c = a.get(x);
				c.copyChildrenTo(copy.getCell(x, y));
			}
		}
	}

	/**
	 * Helper function for the default {@link #copy()} mechanism. Creates a
	 * shallow copy of this object. {@link #copyChildrenTo(WBlock)} will be
	 * called later. If the default {@link #copy()} mechanism is overridden,
	 * this may throw an UnsupportedOperationException.
	 */
	public abstract WBlock shallowCopy() throws UnsupportedOperationException;

	@Override
	public String toString() {
		return getClass().getSimpleName() + hashCode();
	}

	@Override
	public Property<Type> concreteType(String pname) {
		Type tab = tabType().get();
		if (!(tab instanceof ParameterizedType))
			throw new IllegalStateException("The tab type of " + this + " is a raw type: " + tab);
		Property<Type> p = ((ParameterizedType) tab).concreteType(pname);
		if (p == null)
			throw new IllegalArgumentException(
					"The tab type of " + this + " (" + tab + ") does not have a type parameter called " + pname);
		return p;
	}

	protected TypePrimitive defaultType(String pname) {
		TypePrimitive tab = tabType().get().getRawType();
		TypeParameter p = tab.getParameter(pname);
		if (p == null)
			throw new IllegalArgumentException(
					"The tab type of " + this + " (" + tab + ") does not have a type parameter called " + pname);
		return p.boundingType;
	}

	@Override
	public ActionSource getDestructionListeners() {
		return destructionListeners;
	}
}
