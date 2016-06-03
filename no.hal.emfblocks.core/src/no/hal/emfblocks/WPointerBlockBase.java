package no.hal.emfblocks;

import java.util.LinkedList;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyListener;
import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public class WPointerBlockBase extends WBlockBase implements WPointerBlock, EventHandler<ActionEvent> {
	protected final FutureWBlock futureBlock;
	protected WBlock referencedBlock;
	protected Queue<Runnable> resolutionQueue;
	protected final Property<Boolean> showArrow;
	protected boolean registered;

	private PropertyListener<Type> tabReplicator = (prop, oldVal, newVal) -> {
		if (newVal == null) {
			if (!isDestroyed()) {
				if (blockContainer().get() != null)
					blockContainer().get().getChildren().remove(this);
				dispose();
			}
		} else
			tabType().set(newVal);
	};

	/** Create a pointer block with a reference to the indicated block. */
	public WPointerBlockBase(WBlock referenceBlock) {
		this(true, referenceBlock);
	}

	/** Create a pointer block with a reference to the indicated block. */
	public WPointerBlockBase(boolean showArrow, WBlock referenceBlock) {
		this(showArrow, referenceBlock.getRoot(), new FutureWBlock());
		this.futureBlock.resolve(referenceBlock);
	}

	/**
	 * Constructor only for use when the referenced block is not immediately
	 * known. Note that calling any other methods on the pointer block before
	 * the reference has been resolved will result in undefined behaviour.
	 */
	public WPointerBlockBase(WRoot root, FutureWBlock referenceBlock) {
		this(true, root, referenceBlock);
	}

	/**
	 * Constructor only for use when the referenced block is not immediately
	 * known. Note that calling any other methods on the pointer block before
	 * the reference has been resolved will result in undefined behaviour.
	 */
	public WPointerBlockBase(boolean showArrow, WRoot root, FutureWBlock referenceBlock) {
		// Note: tabType can not be null here, (this must be ensured by the
		// caller).
		super(root, null, Color.WHITE);
		this.resolutionQueue = new LinkedList<>();
		this.futureBlock = referenceBlock;
		registered = false;
		this.showArrow = new Property<Boolean>((p, o, n) -> {
			if (!isDestroyed() && registered) {
				if (!n && o)
					getRoot().removePointerBlockArrow(this);
				else if (n && !o)
					getRoot().addPointerBlockArrow(this);
			}
			return n;
		}, showArrow);
	}

	@Override
	public String getName() {
		if (isResolved())
			return "Pointer to " + getReferencedBlock().getName();
		else
			return "Unresolved pointer";
	}

	@Override
	protected void installContentPanes() {
		// TODO ID?
	}

	@Override
	public WPointerBlock makePointerBlock() {
		throw new IllegalArgumentException("Cannot create pointer block from a pointer block...");
	}

	@Override
	public WBlock shallowCopy() {
		return new WPointerBlockBase(getRoot(), futureBlock);
	}

	@Override
	protected void addNotify2() {
		if (!isDestroyed()) {
			futureBlock.registerOwner(this);
			registered = true;
			if (showArrow.get())
				getRoot().addPointerBlockArrow(this);
		}
	}

	@Override
	protected void removeNotify2() {
		if (!isDestroyed()) {
			futureBlock.unregisterOwner(this);
			registered = false;
			if (showArrow.get())
				getRoot().removePointerBlockArrow(this);
		}
	}

	@Override
	public void dispose2() {
		if (blockContainer().get() != null) {
			futureBlock.unregisterOwner(this);
			registered = false;
			if (showArrow.get())
				getRoot().removePointerBlockArrow(this);
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		if (!isDestroyed() && arg0.getSource() == referencedBlock.getDestructionListeners())// Can
																							// come
																							// from
																							// an
																							// outdated
																							// block
																							// since
																							// the
																							// listener
																							// isn't
																							// removed
		{
			if (blockContainer().get() != null)
				blockContainer().get().getChildren().remove(this);
			dispose();
		}
	}

	public PropertyReadOnly<WBlock> referencedBlock() {
		return futureBlock.getReferencedBlockProperty();
	}

	public boolean isResolved() {
		return referencedBlock != null;
	}

	protected WBlock getReferencedBlock() {
		if (referencedBlock == null)
			throw new IllegalStateException(
					"Cannot call this method while the referenced block is still null! Future value must be resolved first.");
		return referencedBlock;
	}

	/**
	 * Append an action to perform as soon as the referenced block has been
	 * resolved. If it has already been resolved, the action is performed
	 * immediately. The action is performed at most once.
	 */
	public void whenResolved(Runnable r) {
		if (!isResolved())
			resolutionQueue.offer(r);
		else
			r.run();
	}

	/**
	 * Called by {@link FutureWBlock} when the referenced block has been
	 * resolved. Under normal circumstances, this is only called once, when the
	 * {@link FutureWBlock} is resolved.
	 */
	protected void referenceResolved(WBlock newValue) {
		referencedBlock = newValue;
		// Destruction binding
		newValue.getDestructionListeners().addListener(this);
		// Color binding
		color().bind(newValue.color());
		// Tab binding
		tabType().set(newValue.tabType().get());
		newValue.tabType().addListener(null, tabReplicator);
		Runnable evt;
		if (resolutionQueue != null) {
			while ((evt = resolutionQueue.poll()) != null)
				evt.run();
			resolutionQueue = null;
		}
	}
}
