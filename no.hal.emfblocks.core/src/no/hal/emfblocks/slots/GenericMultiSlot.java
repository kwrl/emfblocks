package no.hal.emfblocks.slots;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldList;

public class GenericMultiSlot<T extends WBlock> extends GenericSlot<T> {
	public final PFieldList<WBlock> children;

	/**
	 * Create a new generic multi-slot. A multi-slot allows multiple blocks to
	 * be attacked in a chain.
	 * 
	 * @param slotType
	 *            The type of this slot. The slot will only accept tabs of this
	 *            type, or tabs that have child types of this type.
	 * @param maxSize
	 *            The maximum number of slots in this multi-slot. -1 if
	 *            unlimited.
	 */
	public GenericMultiSlot(Class<T> blockType, Type slotType) {
		super(blockType, slotType);
		children = new PFieldList<>(this, WBlock.class);
	}

	@Override
	public PField<WBlock> getChildren() {
		return children;
	}

	/** Pass-through to {@link PField#size()} on {@link #getChildren()} */
	public int size() {
		return children.size();
	}

	/** Pass-through to {@link PField#isEmpty()} on {@link #getChildren()} */
	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public boolean isMultiSlot() {
		return true;
	}
}
