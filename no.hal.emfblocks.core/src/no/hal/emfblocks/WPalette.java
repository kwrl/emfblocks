package no.hal.emfblocks;

import java.util.Collection;
import java.util.Iterator;

import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.hal.emfblocks.javafx.fxml.FXPalette;

public class WPalette extends WPane {
	private boolean populated = false;
	private FXPalette ui = null;

	public WPalette(WRoot root, Collection<? extends WBlock> palette, FXPalette ui) {
		super(root);
		if (palette != null)
			setPalette(palette);
		if (ui != null)
			setUI(ui);
	}

	@Override
	protected void setUIImpl(Node ui) {
		if (ui == null || !(ui instanceof FXPalette))
			throw new RuntimeException(this + " cannot have this ui: " + ui);
		if (this.ui != null)
			throw new RuntimeException(this + " already has an ui: " + this.ui);

		this.ui = (FXPalette) ui;
		populateUI();
	}

	/** Sets which blocks should be in this palette. */
	public void setPalette(Collection<? extends WBlock> palette) {
		// Remove all current elements(triggers UI remove if it has been
		// populated)
		Iterator<WBlock> it = children.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		// Add all new elements(triggers UI add if it has been populated)
		for (WBlock b : palette) {
			children.add(b);
		}
	}

	public WPalette(WRoot root, Collection<? extends WBlock> palette) {
		this(root, palette, null);
	}

	@Override
	public Pane getInternalUI() {
		return getUI();
	}

	@Override
	public FXPalette getUI() {
		if (ui == null)
			setUI(new FXPalette(this));
		return ui;
	}

	protected void populateUI() {
		if (populated)
			System.err.println("Warning: " + this + " populated twice...");

		getInternalUI().borderProperty().set(
				new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, new BorderWidths(16))));
		for (WBlock b : children) {
			Pane bui = b.getUI();
			getInternalUI().getChildren().add(bui);
		}
		children.addListener(referencer, (param, index, oldValue, newValue) -> {
			if (oldValue != null)
				getInternalUI().getChildren().remove(oldValue.getUI());
			if (newValue != null) {
				Pane ui = newValue.getUI();
				getInternalUI().getChildren().add(ui);
			}
		});
		populated = true;
	}

	@Override
	public BlockDropAction accepts(WBlock b) {
		return BlockDropAction.CANCEL;
	}
}
