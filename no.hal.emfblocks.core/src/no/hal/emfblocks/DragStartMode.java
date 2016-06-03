package no.hal.emfblocks;

import javafx.scene.input.MouseEvent;

public enum DragStartMode
{	
	MOVE_BLOCK, COPY_BLOCK, CREATE_POINTER, USE_TYPE;
	
	public static interface Classifier {
		public DragStartMode getDragMode(MouseEvent event);
	}
}
