package no.hal.emfblocks.editor;

import java.util.function.Consumer;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.util.data.pfield.PField;

public interface EMFSlot {
	public PField<WBlock> getChildren();

	default void recursive(Consumer<WBlock> r) {
		for (WBlock c : getChildren()) {
			if (c instanceof EObjectBlock)
				((EObjectBlock) c).recursive(r);
			else
				((EMFPointerBlock) c).recursive(r);
		}
	}
}
