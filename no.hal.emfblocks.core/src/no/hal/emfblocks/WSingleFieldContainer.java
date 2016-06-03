package no.hal.emfblocks;

import javafx.scene.layout.Pane;
import no.hal.emfblocks.util.data.pfield.PField;

public interface WSingleFieldContainer<T extends WNode> extends WContainer<T> {
	/**
	 * Returns the UI node that contains the children. This may be different
	 * from the UI of the object itself(WNode{@link #getUI()}). Furthermore, it
	 * may have a different transformation. This must be taken into
	 * consideration when performing coordinate system computations.
	 */
	public Pane getInternalUI();

	public PField<T> getChildren();

	public BlockDropAction accepts(T dragBlock);
}
