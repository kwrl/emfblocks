package no.hal.emfblocks.javafx;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public abstract class SlackedGroup extends Group {
	public abstract double getRealWidth();

	public abstract double getRealHeight();

	public abstract boolean containsWithSlack(Point2D localPoint, int slack);
}
