package no.hal.emfblocks;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import no.hal.emfblocks.javafx.fxml.FXTrashCan;

public class WTrashCan extends WPane {
	private FXTrashCan ui;

	public WTrashCan(WRoot root, FXTrashCan ui) {
		super(root);
		this.ui = ui;
	}

	public WTrashCan(WRoot root) {
		this(root, null);
	}

	@Override
	protected void setUIImpl(Node ui) {
		if (ui == null || !(ui instanceof FXTrashCan))
			throw new RuntimeException(this + " cannot have this ui: " + ui);
		if (this.ui != null)
			throw new RuntimeException(this + " already has an ui: " + this.ui);

		this.ui = (FXTrashCan) ui;
	}

	@Override
	public Pane getInternalUI() {
		return getUI();
	}

	@Override
	public FXTrashCan getUI() {
		if (ui == null)
			setUI(new FXTrashCan(this));
		return ui;
	}

	@Override
	public BlockDropAction accepts(WBlock b) {
		return BlockDropAction.TRASH;
	}

}
