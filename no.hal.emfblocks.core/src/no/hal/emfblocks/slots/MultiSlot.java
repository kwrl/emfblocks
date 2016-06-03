package no.hal.emfblocks.slots;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldList;

public class MultiSlot extends Slot {
	public final PFieldList<WBlock> children;

	/**
	 * Create a new multi-slot. A multi-slot allows multiple blocks to be
	 * attacked in a chain.
	 * 
	 * @param slotType
	 *            The type of this slot. The slot will only accept tabs of this
	 *            type, or tabs that have child types of this type.
	 * @param maxSize
	 *            The maximum number of slots in this multi-slot. -1 if
	 *            unlimited.
	 */
	public MultiSlot(Type slotType) {
		super(slotType);
		children = new PFieldList<>(this, WBlock.class);
	}

	@Override
	public PField<WBlock> getChildren() {
		return children;
	}

	@Override
	public boolean isMultiSlot() {
		return true;
	}
}
