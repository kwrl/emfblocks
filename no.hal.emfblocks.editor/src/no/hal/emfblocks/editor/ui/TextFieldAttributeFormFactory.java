package no.hal.emfblocks.editor.ui;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;

import no.hal.emfblocks.editor.MMUtil;
import no.hal.emfblocks.editor.efxclipse.UnregisterableEObjectProperty;
import no.hal.emfblocks.javafx.ActionSource;
import no.hal.emfblocks.javafx.FXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class TextFieldAttributeFormFactory implements AttributeFormFactory {
	@Override
	public Node[] createForm(EditingDomain editingDomain, EObject obj, EAttribute e,
			ActionSource disposeEventDispatcher) {
		Label label = new Label(e.getName());
		EDataType dt = MMUtil.getReifiedDataType(obj, e);
		if (!dt.isSerializable())
			return new Node[] { label, new Label("NOT_SERIALIZABLE") };
		if (dt.getEPackage() == null)
			return new Node[] { label, new Label("NO_PACKAGE") };

		FXTextField field = new FXTextField(EcoreUtil.convertToString(dt, obj.eGet(e)));

		UnregisterableEObjectProperty<Object> property = new UnregisterableEObjectProperty<>(editingDomain, obj, e);

		boolean[] ignoreEvents = { false };
		property.addListener((p, o, n) -> {
			if (!ignoreEvents[0]) {
				ignoreEvents[0] = true;
				field.setText(EcoreUtil.convertToString(MMUtil.getReifiedDataType(obj, e), obj.eGet(e)));
				ignoreEvents[0] = false;
			}
		});
		// Mutual binding with fallback
		field.onActionProperty().set(a -> {
			if (!ignoreEvents[0]) {
				ignoreEvents[0] = true;
				try {
					Object val = EcoreUtil.createFromString(MMUtil.getReifiedDataType(obj, e), field.getText());
					property.setValue(val);
				} catch (IllegalArgumentException err) {
					System.out.println(field.getText() + " could not be parsed as " + e.getEAttributeType());
				}
				int oldCaret = field.getCaretPosition();
				field.setText(EcoreUtil.convertToString(MMUtil.getReifiedDataType(obj, e), obj.eGet(e)));
				field.positionCaret(oldCaret);
				ignoreEvents[0] = false;
			}
		});
		disposeEventDispatcher.addListener(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				property.unregister();
				disposeEventDispatcher.removeListener(this);
			}
		});
		return new Node[] { label, field };
	}
}
