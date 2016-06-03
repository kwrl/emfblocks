package no.hal.emfblocks.javafx;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WContainer;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.slots.Slot;

public class FXPointerArrow implements ChangeListener<Object>, InvalidationListener {
	private static final double REF_BLK_OFFSET = FXBlock.INDENTATION_OFFSET - 1.5f;

	public final WPointerBlock pointerBlock;
	private WBlock currentReferencedBlock = null;

	private WeakChangeListener<Object> weakChangeListener;
	private WeakInvalidationListener weakInvalidationListener;

	private boolean initd = false;
	private boolean dirty = true;
	private final Circle endPoint, startPoint;
	private final Line line;
	private final Line leftHead;
	private final Line rightHead;

	public FXPointerArrow(WPointerBlock p) {
		super();
		pointerBlock = p;

		startPoint = new Circle(REF_BLK_OFFSET - 2);
		startPoint.fillProperty().set(Color.BLACK);
		endPoint = new Circle(REF_BLK_OFFSET - 2);
		endPoint.fillProperty().set(Color.BLACK);
		line = new Line();
		leftHead = new Line();
		rightHead = new Line();
	}

	public List<Node> getNodes() {
		return Arrays.asList(new Node[] { line, leftHead, rightHead, endPoint, startPoint });
	}

	@Override
	public void invalidated(Observable observable) {
		dirty = true;
		pointerBlock.getRoot().getUI().requestLayout();
	}

	@Override
	public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
		dirty = true;
		pointerBlock.getRoot().getUI().requestLayout();
	}

	public void updateIfDirty() {
		if (!initd) {
			initd = true;
			weakChangeListener = new WeakChangeListener<Object>(this);
			weakInvalidationListener = new WeakInvalidationListener(this);
			FXBlock pBlkUI = pointerBlock.getUI();
			// In case the ui resizes: (only relevant for this end because the
			// other end is size-independent)
			pBlkUI.publicLayoutListeners.addListener((a) -> {
				dirty = true;
				Platform.runLater(() -> {
					pointerBlock.getRoot().getUI().requestLayout();
				});
			});
			pBlkUI.localToSceneTransformProperty().addListener(weakChangeListener);
			FXUtil.addVisibilityListenerTo(pBlkUI, weakInvalidationListener);
		}

		if (pointerBlock.referencedBlock().get() != currentReferencedBlock) {
			if (currentReferencedBlock != null) {
				FXBlock oldRefUI = currentReferencedBlock.getUI();
				oldRefUI.localToSceneTransformProperty().removeListener(weakChangeListener);
				FXUtil.removeVisibilityListenerFrom(oldRefUI, weakInvalidationListener);
			}
			currentReferencedBlock = pointerBlock.referencedBlock().get();
			FXBlock refUI = currentReferencedBlock.getUI();
			refUI.localToSceneTransformProperty().addListener(weakChangeListener);
			FXUtil.addVisibilityListenerTo(refUI, weakInvalidationListener);
		}

		if (dirty) {
			dirty = false;

			final Pane rootUI = pointerBlock.getRoot().getUI();
			FXBlock pBlkUI = pointerBlock.getUI();
			boolean v1 = FXUtil.isVisible(pBlkUI);
			startPoint.setVisible(v1);
			boolean v2;

			WBlock refBlk = pointerBlock.referencedBlock().get();
			FXBlock refUI;
			if (refBlk == null) {
				refUI = null;
				v2 = false;
			} else {
				refUI = refBlk.getUI();
				v2 = FXUtil.isVisible(refUI);
			}

			endPoint.setVisible(v2);
			boolean visible = v1 && v2;
			line.setVisible(visible);
			leftHead.setVisible(visible);
			rightHead.setVisible(visible);

			if (visible) {
				WContainer<?> pbp = pointerBlock.blockContainer().get();
				short hListConnectorWidth = (pbp instanceof Slot && ((Slot) pbp).isMultiSlot()
						&& ((Slot) pbp).isHorizontal())
								? pointerBlock.getRoot().getTypeHierarchy().connectorHeight.get() : 0;

				Point2D startPoint = FXUtil.fromTo(pBlkUI, rootUI,
						new Point2D(pBlkUI.getRealWidth() - REF_BLK_OFFSET - hListConnectorWidth, REF_BLK_OFFSET));
				line.startXProperty().set(startPoint.getX());
				line.startYProperty().set(startPoint.getY());
				this.startPoint.translateXProperty().set(startPoint.getX());
				this.startPoint.translateYProperty().set(startPoint.getY());
				Point2D endPoint = FXUtil.fromTo(refUI, rootUI, new Point2D(REF_BLK_OFFSET, REF_BLK_OFFSET));
				line.endXProperty().set(endPoint.getX());
				line.endYProperty().set(endPoint.getY());
				this.endPoint.translateXProperty().set(endPoint.getX());
				this.endPoint.translateYProperty().set(endPoint.getY());

				Point2D midPoint = endPoint.add(startPoint).multiply(0.5);
				Point2D direction = endPoint.subtract(startPoint).normalize();
				Point2D perp = new Point2D(-direction.getY(), direction.getX());
				final int arrowSize = 8;
				final int arrowWidth = 8;

				leftHead.startXProperty().set(midPoint.getX() + direction.getX() * arrowSize);
				leftHead.startYProperty().set(midPoint.getY() + direction.getY() * arrowSize);
				leftHead.endXProperty().set(midPoint.getX() - direction.getX() * arrowSize + perp.getX() * arrowWidth);
				leftHead.endYProperty().set(midPoint.getY() - direction.getY() * arrowSize + perp.getY() * arrowWidth);

				rightHead.startXProperty().set(midPoint.getX() + direction.getX() * arrowSize);
				rightHead.startYProperty().set(midPoint.getY() + direction.getY() * arrowSize);
				rightHead.endXProperty().set(midPoint.getX() - direction.getX() * arrowSize - perp.getX() * arrowWidth);
				rightHead.endYProperty().set(midPoint.getY() - direction.getY() * arrowSize - perp.getY() * arrowWidth);
			}
		}
	}
}
