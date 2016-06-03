package no.hal.emfblocks.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.Property;

public class FXConcreteTypeBox extends Group {
	private static final Paint ACCEPT_COLOR = new Color(0, 1, 0, 1);
	private static final Paint REJECT_COLOR = new Color(1, 0, 0, 1);

	private Rectangle stippleRect;
	private FXColoredImageView typeView;
	private ObjectProperty<Color> bgColor = new ObjectPropertyBase<Color>(Color.BLACK) {
		@Override
		public Object getBean() {
			return FXConcreteTypeBox.this;
		}

		@Override
		public String getName() {
			return "Background color";
		}
	};
	protected final WBlock target;
	protected final String pname;
	protected Referencer referencer = new ReferenceSet();

	public FXConcreteTypeBox(WBlock target, String pname) {
		this.target = target;
		this.pname = pname;
		Property<Type> p = target.concreteType(pname);
		ObjectProperty<Image> imProp = p.get().getRawType().getContour().tabImage();
		p.addListener(referencer, (prop, oldVal, newVal) -> {
			update(newVal.getRawType().getContour().tabImage());
		});
		update(imProp);

		setOnDragOver(event -> {
			if (acceptDrag(event)) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
		setOnDragEntered(e -> {
			stippleRect().fillProperty().unbind();
			stippleRect().fillProperty().set(acceptDrag(e) ? ACCEPT_COLOR : REJECT_COLOR);
		});
		setOnDragExited(e -> {
			stippleRect.fillProperty().bind(bgColor);
		});

		setOnDragDropped(e -> {
			if (acceptDrag(e)) {
				target.concreteType(pname)
						.set(target.getRoot().getTypeHierarchy().getType(target.getRoot().getDragKey()));
			}
		});
	}

	private boolean acceptDrag(DragEvent event) {
		return event.getDragboard().hasString()
				&& event.getDragboard().getString().equals(target.getRoot().getDragKey().toString());
	}

	private void update(ObjectProperty<Image> imProp) {
		stippleRect();

		if (typeView != null)
			getChildren().remove(typeView);
		typeView = new FXColoredImageView(imProp, target.color(), null, true, 1.0);
		getChildren().add(typeView);
		typeView.relocate(2, 2);

		stippleRect.widthProperty().set(typeView.getRealWidth() + 3);
		stippleRect.heightProperty().set(typeView.getRealHeight() + 3);
		// stippleRect.setStrokeWidth(0.5);
		stippleRect.getStrokeDashArray().addAll(1.0, 3.0);
	}

	private Rectangle stippleRect() {
		if (stippleRect == null) {
			stippleRect = new Rectangle();
			getChildren().add(stippleRect);
			stippleRect.relocate(0.5, 0.5);
			stippleRect.setStrokeWidth(1);
			stippleRect.setStroke(new Color(0, 0, 0, 0.5));
			stippleRect.fillProperty().bind(bgColor);
		}
		return stippleRect;
	}
}
