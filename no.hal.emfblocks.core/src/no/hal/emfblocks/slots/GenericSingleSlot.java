package no.hal.emfblocks.slots;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldSingle;

public class GenericSingleSlot<T extends WBlock> extends GenericSlot<T> {
	public final PFieldSingle<WBlock> child = new PFieldSingle<WBlock>(this, WBlock.class);

	/**
	 * Create a new generic single-slot. A single-slot allows at most one block
	 * to be connected.
	 * 
	 * @param slotType
	 *            The type of this slot. The slot will only accept tabs of this
	 *            type, or tabs that have child types of this type.
	 */
	public GenericSingleSlot(Class<T> blockType, Type slotType) {
		super(blockType, slotType);
	}

	@Override
	public PField<WBlock> getChildren() {
		return child;
	}

	/** Pass-through to {@link PField#size()} on {@link #getChildren()} */
	public int size() {
		return child.size();
	}

	/** Pass-through to {@link PField#isEmpty()} on {@link #getChildren()} */
	public boolean isEmpty() {
		return child.isEmpty();
	}

	@Override
	public boolean isMultiSlot() {
		return false;
	}
}
