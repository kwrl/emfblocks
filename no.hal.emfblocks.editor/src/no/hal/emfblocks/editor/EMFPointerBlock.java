package no.hal.emfblocks.editor;

import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;

import no.hal.emfblocks.WBlock;

/**
 * Superclass for blocks that can be used in non-containment reference slots.
 * Has two sublcasses: {@link EObjectPointerBlock} and
 * {@link EcorePointerBlock}.
 */
public interface EMFPointerBlock extends WBlock {
	public EObject dereference();

	public default void recursive(Consumer<WBlock> r) {
		r.accept(this);
	}
}
