package no.hal.emfblocks.slots;

import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.hierarchy.Type;

/**
 * A slot which accepts only specific subclasses of {@link WBlock}. Note that
 * these slots will also accept {@link WPointerBlock}s that point to the generic
 * type of block.
 */
public abstract class GenericSlot<T extends WBlock> extends Slot {
	protected Class<T> blockType;

	public GenericSlot(Class<T> blockType, Type slotType) {
		super(slotType);
		this.blockType = blockType;
	}

	@Override
	public BlockDropAction accepts(WBlock dragBlock) {
		if (dragBlock instanceof WPointerBlock)
			return super.accepts(((WPointerBlock) dragBlock).referencedBlock().get());
		else if (blockType.isInstance(dragBlock))
			return super.accepts(dragBlock);
		return BlockDropAction.CANCEL;
	}

	/**
	 * Returns the first block connected to this slot, or null if there is no
	 * such block.
	 */
	public T get() {
		if (getChildren().isEmpty())
			return null;
		WBlock blk = getChildren().get();
		if (blk instanceof WPointerBlock)
			return cast(((WPointerBlock) blk).referencedBlock().get());
		else
			return cast(blk);
	}

	/** Returns the block with the given index connected to this slot */
	public T get(int index) {
		WBlock blk = getChildren().get(index);
		if (blk instanceof WPointerBlock)
			return cast(((WPointerBlock) blk).referencedBlock().get());
		else
			return cast(blk);
	}

	/**
	 * Cast the given object to the {@link #blockType} of the generic slot.
	 * Throws a {@link ClassCastException} if it fails.
	 */
	public T cast(Object element) throws ClassCastException {
		return blockType.cast(element);
	}
}
