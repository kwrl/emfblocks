package no.hal.emfblocks.hierarchy;

public interface ReparentableTypeNode {
	/**
	 * Sets whether the indicated type node is this node's parent. Note that
	 * this should only be used in the context of the Cut Editor. Reassigning
	 * parents in the block editor will lead to undefined results.
	 */
	public void setIsParent(TypePrimitive n, boolean isParent);
}
