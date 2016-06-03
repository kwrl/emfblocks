package no.hal.emfblocks.javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

/**
 * A modified {@link TextField} which fires an ActionEvent when it loses
 * keyboard focus. This prevents unexpected and confusing situations where users
 * have edited the text field but the old value is still used by the
 * application, even after the user has moved on to work on another component.
 * <br/>
 * <br/>
 * This is provided as an alternative to updating the value after editing each
 * character, which can be highly problematic in many cases. <br/>
 * <br/>
 * However, caution must be advised because for some reason, performing a menu
 * bar action does not generate a focus lost event. A solution to this has yet
 * to be found.
 */
public class FXTextField extends TextField {
	public FXTextField() {
		super();
		register();
	}

	public FXTextField(String title) {
		super(title);
		/**
		 * final TextField tbox = new TextField();
		 * tbox.textProperty().addListener(new InvalidationListener() {
		 * 
		 * @Override public void invalidated(Observable observable) { String
		 *           value = ((TextProperty)observable).get(); try{
		 *           Integer.parseInt(value);
		 *           tbox.textProperty().setValue(value); }catch (Exception e) {
		 *           tbox.textProperty().setValue(""); } } });
		 */
		register();
	}

	private void register() {
		focusedProperty().addListener((ov, oldb, newb) -> {
			if (!newb) {
				fireEvent(new ActionEvent());
			}
		});
	}
}
