package no.hal.emfblocks.slots;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldSingle;

public class SingleSlot extends Slot {
	public final PFieldSingle<WBlock> child = new PFieldSingle<WBlock>(this, WBlock.class);

	/**
	 * Create a new single-slot. A single-slot allows at most one block to be
	 * connected.
	 * 
	 * @param slotType
	 *            The type of this slot. The slot will only accept tabs of this
	 *            type, or tabs that have child types of this type.
	 */
	public SingleSlot(Type slotType) {
		super(slotType);
	}

	@Override
	public PField<WBlock> getChildren() {
		return child;
	}

	@Override
	public boolean isMultiSlot() {
		return false;
	}
}
