package no.hal.emfblocks.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;

public class FXColoredImageView extends SlackedGroup {
	private final Rectangle colorizer;
	private Rectangle bgColorizer;
	private final ImageView imageView;
	private final ImageView rectClip;
	private final boolean horizontal;
	private final double scale;

	/** Garbage collection prevention */
	private final Referencer referencer = new ReferenceSet();

	public FXColoredImageView(Image im, Color c, Color bgColor, boolean horizontal, double scale) {
		setPickOnBounds(false);
		this.scale = scale;
		this.horizontal = horizontal;
		colorizer = new Rectangle(im.getWidth(), im.getHeight());
		colorizer.setFill(c);
		rectClip = new ImageView(im);
		colorizer.setClip(rectClip);
		getChildren().add(colorizer);
		colorizer.relocate(0, 0);
		imageView = new ImageView(im);
		imageView.setBlendMode(BlendMode.MULTIPLY);
		getChildren().add(imageView);
		imageView.relocate(0, 0);
		setBGColor(bgColor);
		resize(im.getWidth(), im.getHeight());

		if (!horizontal)
			getTransforms().add(new Rotate(90, 0, 0));
		if (scale != 1)
			getTransforms().add(new Scale(scale, scale));
	}

	/** Create a colored image view bound to the indicated image property. */
	public FXColoredImageView(ObjectProperty<Image> imProp, ObjectProperty<Color> c, ObjectProperty<Color> bgColor,
			boolean horizontal, double scale) {
		this(imProp.get(), c.get(), bgColor == null ? null : bgColor.get(), horizontal, scale);
		ChangeListener<Image> imListener = ((prop, oldVal, newVal) -> {
			imageView.setImage(newVal);
			colorizer.setWidth(newVal.getWidth());
			colorizer.setHeight(newVal.getHeight());
			rectClip.setImage(newVal);
		});
		imProp.addListener(new WeakChangeListener<Image>(imListener));
		// Prevent GC
		referencer.addReference(imListener);

		colorProperty().bind(c);

		if (bgColor != null) {
			ChangeListener<Color> bgColorListener = ((prop, oldVal, newVal) -> {
				setBGColor(newVal);
			});
			bgColor.addListener(new WeakChangeListener<Color>(bgColorListener));
			// Prevent GC
			referencer.addReference(bgColorListener);
		}
	}

	private int getWidth() {
		return (int) colorizer.getWidth();
	}

	private int getHeight() {
		return (int) colorizer.getHeight();
	}

	@Override
	public double getRealWidth() {
		if (horizontal)
			return getWidth() * scale;
		else
			return getHeight() * scale;
	}

	@Override
	public double getRealHeight() {
		if (horizontal)
			return getHeight() * scale;
		else
			return getWidth() * scale;
	}

	private void setBGColor(Color bgColor) {
		if (bgColorizer != null)
			getChildren().remove(bgColorizer);

		if (bgColor != null) {
			bgColorizer = new Rectangle(0.5, 0.5, colorizer.getWidth() - 1, colorizer.getHeight() - 1);
			bgColorizer.setStroke(new Color(bgColor.getRed() / 2, bgColor.getGreen() / 2, bgColor.getBlue() / 2, 1.0));
			bgColorizer.widthProperty().bind(colorizer.widthProperty().subtract(1));
			bgColorizer.heightProperty().bind(colorizer.heightProperty().subtract(1));
			bgColorizer.setFill(bgColor);
			getChildren().add(bgColorizer);
			bgColorizer.relocate(0, 0);
			bgColorizer.toBack();
		} else
			bgColorizer = null;
	}

	public Referencer referencer() {
		return referencer;
	}

	public boolean containsWithSlack(Point2D localPoint, int slack) {
		return localPoint.getX() >= -slack && localPoint.getX() < getWidth() + slack && localPoint.getY() >= -slack
				&& localPoint.getY() < getHeight() + slack;
	}

	/**
	 * Returns the image color property. Change this to colorize the image with
	 * a different color.
	 */
	private ObjectProperty<Paint> colorProperty() {
		return colorizer.fillProperty();
	}

	public Referencer getReferencer() {
		return referencer;
	}
}
