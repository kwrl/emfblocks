package no.hal.emfblocks;

import javafx.scene.layout.Pane;
import javafx.scene.transform.Affine;

public abstract class WTransformablePane extends WPane {
	public WTransformablePane(WRoot root) {
		super(root);
	}

	public abstract Affine getTransform();

	public double scale(double scaleFactor) {
		double zoom = Math.max(0.5, Math.min(2.0, scaleFactor));
		double newScale = Math.max(1.0 / 16.0, Math.min(16.0, getScale() * zoom));

		return setScale(newScale);
	}

	public double getScale() {
		return getTransform().getMxx();
	}

	public double setScale(double newScale) {
		Pane outerUI = getUI();
		Affine transform = getTransform();
		double viewportW = outerUI.getWidth();
		double viewportH = outerUI.getHeight();

		double oldScale = getScale();

		double pCenX = (viewportW / 2 - transform.getTx()) / oldScale;
		double pCenY = (viewportH / 2 - transform.getTy()) / oldScale;

		transform.setTx((int) (-pCenX * newScale + viewportW / 2));
		transform.setTy((int) (-pCenY * newScale + viewportH / 2));

		transform.setMxx(newScale);
		transform.setMyy(newScale);

		return newScale;
	}

	public void translate(double deltaX, double deltaY) {
		Affine transform = getTransform();
		transform.setTx((int) (transform.getTx() + deltaX));
		transform.setTy((int) (transform.getTy() + deltaY));
	}
}