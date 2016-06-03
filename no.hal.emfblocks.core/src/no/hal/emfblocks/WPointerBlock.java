package no.hal.emfblocks;

import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public interface WPointerBlock extends WBlock {
	/** True when the reference has been resolved. */
	public boolean isResolved();

	/**
	 * Returns the property representing the block that is the target of this
	 * pointer block.
	 */
	public PropertyReadOnly<WBlock> referencedBlock();

	/**
	 * Append an action to perform as soon as the referenced block has been
	 * resolved. If it has already been resolved, the action is performed
	 * immediately. The action is performed at most once. <br>
	 * <br>
	 * <font color = RED>It is illegal to append or remove listeners to
	 * {@link #referencedBlock()} from the runnable.</font>
	 */
	public void whenResolved(Runnable r);
}
