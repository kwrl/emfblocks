package no.hal.emfblocks.hierarchy;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import no.hal.emfblocks.javafx.SlackedGroup;

public interface Type {
	boolean accepts(Type other);

	TypePrimitive getRawType();

	/**
	 * 
	 * @param tab
	 *            Whether the returned node should represent a tab. If false, a
	 *            representation of a slot will be returned.
	 * @param horizontal
	 *            Whether the returned slot or tab node should be
	 *            horizontally(true) or vertically(false) oriented. For example,
	 *            a horizontal tab protrudes to the left, and a vertical tab
	 *            protrudes upwards.
	 * @param color
	 *            The fill color of the slot or tab. The color will be
	 *            permanently bound to this property.
	 * @param bgColor
	 *            The background color of the slot or tab. The color will be
	 *            permanently bound to this property. If this property is
	 *            null(or if the property's value is null), then the returned
	 *            node will not have a background color.
	 * @return A node representing the tab or slot of this type.
	 */
	SlackedGroup createNode(boolean tab, boolean horizontal, ObjectProperty<Color> color,
			ObjectProperty<Color> bgColor);
}
