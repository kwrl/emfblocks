package no.hal.emfblocks;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Affine;
import no.hal.emfblocks.javafx.fxml.FXWorkArea;

public class WWorkArea extends WTransformablePane {
	protected boolean destroyed = false;
	private FXWorkArea ui;

	public WWorkArea(WRoot root, FXWorkArea ui) {
		super(root);
		this.ui = ui;
		if (ui != null)
			populateUI();
	}

	public WWorkArea(WRoot root) {
		this(root, null);
	}

	@Override
	protected void setUIImpl(Node ui) {
		if (ui == null || !(ui instanceof FXWorkArea))
			throw new RuntimeException(this + " cannot have this ui: " + ui);
		if (this.ui != null)
			throw new RuntimeException(this + " already has an ui: " + this.ui);

		this.ui = (FXWorkArea) ui;
		populateUI();
	}

	@Override
	public Affine getTransform() {
		return getUI().getBlockContainerTransform();
	}

	@Override
	public Pane getInternalUI() {
		return getUI().getBlockContainerPane();
	}

	@Override
	public FXWorkArea getUI() {
		if (ui == null)
			setUI(createDefaultUI());
		return ui;
	}

	protected FXWorkArea createDefaultUI() {
		return new FXWorkArea(this);
	}

	protected void populateUI() {
		for (WBlock b : children) {
			getInternalUI().getChildren().add(b.getUI());
		}
		children.addListener(referencer, (param, index, oldValue, newValue) -> {
			if (oldValue != null)
				getInternalUI().getChildren().remove(oldValue.getUI());
			if (newValue != null)
				getInternalUI().getChildren().add(newValue.getUI());
		});
	}

	/**
	 * Should be called when the work area is no longer available to the user,
	 * in order to properly dispose all the blocks.
	 */
	public final void dispose() {
		if (isDestroyed())
			new Throwable("Warning: Block destroyed twice: " + this).printStackTrace();
		else {
			destroyed = true;
			for (WBlock b : children) {
				b.dispose();
			}
			dispose2();
		}
	}

	protected void dispose2() {
		// Optional method
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public BlockDropAction accepts(WBlock b) {
		return BlockDropAction.ACCEPT;
	}
}
