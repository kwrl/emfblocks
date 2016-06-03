package no.hal.emfblocks.javafx.fxml;

import no.hal.emfblocks.WPane;
import no.hal.emfblocks.WRoot;

public interface WPaneUI
{	
	/**Returns the model object of this ui object. If the model object has not yet been assigned, then it is created and added to the indicated root object.*/
	public WPane getModel(WRoot wRoot);
}
