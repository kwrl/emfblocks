package no.hal.emfblocks.editor.ui;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import no.hal.emfblocks.editor.MMUtil;
import no.hal.emfblocks.editor.efxclipse.UnregisterableEObjectProperty;
import no.hal.emfblocks.javafx.ActionSource;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class CheckboxAttributeFormFactory implements AttributeFormFactory {
	@Override
	public Node[] createForm(EditingDomain editingDomain, EObject obj, EAttribute e,
			ActionSource disposeEventDispatcher) {
		EDataType realType = MMUtil.getReifiedDataType(obj, e);
		if (!Boolean.class.equals(realType.getInstanceClass()) && !boolean.class.equals(realType.getInstanceClass()))
			throw new IllegalArgumentException(this.getClass().getName() + " is not applicable for the data type "
					+ realType + "(obj=" + obj + ", e=" + e + ")");
		Label label = new Label(e.getName() + " ");
		CheckBox box = new CheckBox();
		UnregisterableEObjectProperty<Boolean> property = new UnregisterableEObjectProperty<>(editingDomain, obj, e);
		box.selectedProperty().bindBidirectional(property);
		disposeEventDispatcher.addListener(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				property.unregister();
				disposeEventDispatcher.removeListener(this);
			}
		});
		return new Node[] { label, box };
	}
}
