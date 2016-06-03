package no.hal.emfblocks;

import no.hal.emfblocks.DragStartMode;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DefaultDragModeHelper implements DragStartMode.Classifier {

	@Override
	public DragStartMode getDragMode(MouseEvent event) {
		DragStartMode mode = DragStartMode.MOVE_BLOCK;
		if (event.isShiftDown() || event.getButton() == MouseButton.SECONDARY) {
			mode = DragStartMode.CREATE_POINTER;
		} else if (event.isAltDown() || event.getButton() == MouseButton.MIDDLE) {
			mode = DragStartMode.USE_TYPE;							
		} else if (event.isControlDown()) {
			mode = DragStartMode.COPY_BLOCK; 
		}
		return mode;
	}

}
