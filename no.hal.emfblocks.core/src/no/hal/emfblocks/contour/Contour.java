package no.hal.emfblocks.contour;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import no.hal.emfblocks.hierarchy.TypePrimitive;

public interface Contour {
	public TypePrimitive getTypeNode();

	public short getWidth();

	public short getHeight();

	public ObjectProperty<Image> tabImage();

	public ObjectProperty<Image> slotImage();

	public ObjectProperty<Image> highlightImage();
}
