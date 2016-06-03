package no.hal.emfblocks.editor;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import no.hal.emfblocks.FutureWBlock;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.util.data.Referencer;

/**
 * A registry of the canonical {@link EObjectBlock} representation of each
 * {@link EObject}. The key feature of this class is retrieval of
 * {@link FutureWBlock} objects, allowing {@link EObjectPointerBlock}s to exist
 * before the block that they reference, and enabling them to redirect their
 * pointer arrows if the current canonical block representation changes.
 */
public class ReferenceResolver {
	protected final HashMap<EObject, FutureWBlock> pointers;
	protected final EMFBlockEditor editor;

	public ReferenceResolver(EMFBlockEditor editor) {
		this.pointers = new HashMap<>();
		this.editor = editor;
	}

	/**
	 * Returns a reference to a block that may or may not exist yet. Pointer
	 * blocks should call {@link FutureWBlock#addOwner(WPointerBlock)} to ensure
	 * that they are properly set up.
	 */
	public FutureWBlock getReference(EObject key) {
		FutureWBlock fb = pointers.get(key);
		if (fb == null) {
			fb = new FutureWBlock();
			pointers.put(key, fb);
		}
		return fb;
	}

	/**
	 * Called when an {@link EObjectBlock} is added, to notify current and
	 * future pointer blocks that their references have been resolved.
	 * 
	 * @param ref
	 *            A referencer belonging to the block, preventing garbage
	 *            collection of the reference until the block falls out of use.
	 *            The reference to the block may be garbage collected when both
	 *            the block and any pointer blocks are eligible for garbage
	 *            collection.
	 * @param The
	 *            block that is currently the canonical representation of its
	 *            EObject.
	 */
	public void register(Referencer ref, EObjectBlock block) {
		final EObject key = block.getObject();
		FutureWBlock fb = pointers.get(key);
		if (fb == null) {
			fb = new FutureWBlock();
			pointers.put(key, fb);
		}
		fb.resolve(block);
	}

	/** Ensures the given block will never be used again (via this class). */
	public void unregister(EObjectBlock block) {
		final EObject key = block.getObject();
		if (pointers.get(key).getReferencedBlockProperty().get() == block)
			pointers.remove(key);
		else {
			System.out.println(
					"INFO: " + block + " was unregistered after being replaced by another block. (key=" + key + ")");
		}
	}

	/**
	 * Returns an EMFPointerBlock pointing to the given object. If the object is
	 * represented as a literal in the palette, then an
	 * {@link EcorePointerBlock} representing that object is returned.
	 * Otherwise, an {@link EObjectPointerBlock} pointing to an
	 * {@link EObjectBlock} representing the object is returned. The caller
	 * should know that such a block exists, or will exist in the imminent
	 * future.
	 */
	public EMFPointerBlock createPointerBlock(EObject target) {
		if (target.eIsProxy())// Target is sometimes a proxy...
			target = EcoreUtil.resolve(target, editor.getModelInstance().getResourceSet());
		if (editor.getModelInstance().getPalette().containsLiteral(target))
			return new EcorePointerBlock(editor, target);
		FutureWBlock referenceBlock = editor.getReferenceResolver().getReference(target);
		return new EObjectPointerBlock(editor, referenceBlock);
	}
}
