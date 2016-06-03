package no.hal.emfblocks.editor.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import no.hal.emfblocks.editor.MMUtil;
import no.hal.emfblocks.editor.efxclipse.UnregisterableEObjectProperty;
import no.hal.emfblocks.javafx.ActionSource;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComboboxAttributeFormFactory implements AttributeFormFactory {
	@Override
	public Node[] createForm(EditingDomain editingDomain, EObject obj, EAttribute e,
			ActionSource disposeEventDispatcher) {
		EDataType realType = MMUtil.getReifiedDataType(obj, e);
		if (!(realType instanceof EEnum))
			throw new IllegalArgumentException(this.getClass().getName() + " is not applicable for the data type "
					+ realType + "(obj=" + obj + ", e=" + e + ")");

		Label label = new Label(e.getName());

		EEnum enumType = (EEnum) realType;
		EList<EEnumLiteral> literalWrappers = enumType.getELiterals();
		List<Enumerator> literals = new ArrayList<>();
		for (EEnumLiteral eel : literalWrappers)
			literals.add(eel.getInstance());

		ComboBox<Enumerator> field = new ComboBox<Enumerator>(new ObservableListWrapper<Enumerator>(literals));

		UnregisterableEObjectProperty<Enumerator> property = new UnregisterableEObjectProperty<>(editingDomain, obj, e);
		Object current = obj.eGet(e);
		if (current != null)
			field.getSelectionModel().select((Enumerator) current);

		boolean[] ignoreEvents = { false };
		property.addListener((p, o, n) -> {
			if (!ignoreEvents[0]) {
				ignoreEvents[0] = true;
				field.setValue(n);
				ignoreEvents[0] = false;
			}
		});
		// Mutual binding
		field.onActionProperty().set(a -> {
			if (!ignoreEvents[0]) {
				ignoreEvents[0] = true;
				property.setValue(field.getValue());
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
