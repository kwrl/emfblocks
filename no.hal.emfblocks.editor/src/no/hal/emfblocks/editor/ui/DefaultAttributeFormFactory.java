package no.hal.emfblocks.editor.ui;

import java.util.HashMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import no.hal.emfblocks.editor.MMUtil;
import no.hal.emfblocks.javafx.ActionSource;
import javafx.scene.Node;

public class DefaultAttributeFormFactory implements AttributeFormFactory {
	private static HashMap<Class<? extends AttributeFormFactory>, AttributeFormFactory> instances = new HashMap<Class<? extends AttributeFormFactory>, AttributeFormFactory>();

	/**
	 * Returns an instance of the given subclass of
	 * {@link #AttributeFormFactory()}.
	 */
	public static AttributeFormFactory instance(Class<? extends AttributeFormFactory> type)
			throws InstantiationException, IllegalAccessException {
		AttributeFormFactory pr = instances.get(type);
		if (pr == null)
			pr = type.newInstance();
		return pr;
	}

	/** Returns an instance of {@link #DefaultAttributeFormFactory()}. */
	public static DefaultAttributeFormFactory instance() {
		AttributeFormFactory pr = instances.get(DefaultAttributeFormFactory.class);
		if (pr == null)
			pr = new DefaultAttributeFormFactory();
		return (DefaultAttributeFormFactory) pr;
	}

	/**
	 * Returns an instance of the class with the given name, or
	 * {@link #DefaultAttributeFormFactory()} if it was not possible.
	 */
	public static AttributeFormFactory instanceOrDefault(String attributeFormFactoryClass) {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends AttributeFormFactory> type = (Class<? extends AttributeFormFactory>) Class
					.forName(attributeFormFactoryClass);
			AttributeFormFactory i = instance(type);
			assert i != null;
			return i;
		} catch (Exception e) {
			System.err.println("Warning: " + attributeFormFactoryClass + " could not be created as an "
					+ AttributeFormFactory.class.getSimpleName());
			e.printStackTrace();
			return instance();
		}
	}

	@Override
	public Node[] createForm(EditingDomain editingDomain, EObject obj, EAttribute e,
			ActionSource disposeEventDispatcher) {
		try {
			AttributeFormFactory delegate;
			EDataType theType = MMUtil.getReifiedDataType(obj, e);
			if (theType instanceof EEnum)
				delegate = instance(ComboboxAttributeFormFactory.class);
			else if (Boolean.class.equals(theType.getInstanceClass())
					|| boolean.class.equals(theType.getInstanceClass()))
				delegate = instance(CheckboxAttributeFormFactory.class);
			else
				delegate = instance(TextFieldAttributeFormFactory.class);
			return delegate.createForm(editingDomain, obj, e, disposeEventDispatcher);
		} catch (Exception err) {
			throw new RuntimeException(err);
		}
	}
}
