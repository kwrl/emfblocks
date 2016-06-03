package no.hal.emfblocks;

import java.util.LinkedHashSet;

import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyReadOnly;

public class FutureWBlock {
	private final Property<WBlock> referencedBlockProperty = new Property<WBlock>(WBlock.class, null);
	private final LinkedHashSet<WPointerBlockBase> owners = new LinkedHashSet<>();

	/**
	 * Resolve the reference. Must be done before the pointer block is put into
	 * use. It is legal to call this either before or after the pointer block is
	 * instantiated.
	 */
	public void resolve(WBlock referenceBlock) {
		if (referenceBlock != referencedBlockProperty.get()) {
			referencedBlockProperty.set(referenceBlock);
			for (WPointerBlockBase owner : owners) {
				if (!owner.isResolved() || owner.getReferencedBlock() != referenceBlock)
					owner.referenceResolved(referenceBlock);
			}
		}
	}

	protected void registerOwner(WPointerBlockBase owner) {
		owners.add(owner);
		if (referencedBlockProperty.get() != null
				&& (!owner.isResolved() || owner.getReferencedBlock() != referencedBlockProperty.get()))
			owner.referenceResolved(referencedBlockProperty.get());
	}

	protected void unregisterOwner(WPointerBlockBase owner) {
		owners.remove(owner);
	}

	public PropertyReadOnly<WBlock> getReferencedBlockProperty() {
		return referencedBlockProperty;
	}
}