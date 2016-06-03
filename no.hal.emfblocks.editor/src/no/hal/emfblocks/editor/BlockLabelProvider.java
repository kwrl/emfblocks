package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import no.hal.emfblocks.mapping.ObjectMapping;

public class BlockLabelProvider extends AdapterImpl {
	private static final String ECLASS_STRING = "eClass";
	protected final EObject myObject;
	protected final String formatString;
	protected final ArrayList<EAttribute> labelAttributes;
	protected final LinkedHashSet<String> labelAttributeNames;
	protected final boolean needsAdapter;

	public final ObjectProperty<String> labelTextProperty;

	public BlockLabelProvider(ObjectMapping mapping, EObject obj) {
		super();
		labelAttributes = new ArrayList<EAttribute>();
		labelAttributeNames = new LinkedHashSet<String>();
		myObject = obj;
		String nameInfo = mapping.getNameInfo();
		String fmtStr;
		String initialText;
		boolean needsAdapter = false;
		try {
			if (nameInfo == null)
				throw new NoSuchElementException();
			if (!nameInfo.startsWith("\""))
				throw new IllegalArgumentException(nameInfo + " does not start with a double quote!");
			int closeIndex = 1;
			while (true) {
				// Starting from current search position, find a double quote.
				closeIndex = nameInfo.indexOf('"', closeIndex);
				// If there was no remaining double quotes, then fail.
				if (closeIndex == -1)
					break;
				// Search for backslashes, backwards from the index of the
				// double quote.
				boolean isEscaped = false;
				escapeCheck: for (int j = closeIndex - 1; j >= 0; j--) {
					if (nameInfo.charAt(j) == '\\')
						isEscaped = !isEscaped;
					else
						break escapeCheck;
				}
				// If the number of backslashes is even, then the double quote
				// is not escaped and we can terminate.
				if (!isEscaped)
					break;
			}
			if (closeIndex == -1)
				throw new IllegalArgumentException(nameInfo + " does not contain a closing double quote!");

			String[] propertyNames = nameInfo.substring(closeIndex + 1).split(",", -1);
			if (!propertyNames[0].trim().isEmpty())
				throw new IllegalArgumentException(nameInfo + " contained a non-whitespace fragment " + propertyNames[0]
						+ " between the closing double quote and the first comma!");
			fmtStr = nameInfo.substring(1, closeIndex);

			for (int i = 1; i < propertyNames.length; i++) {
				String pName = propertyNames[i].trim();
				if (pName.equals(ECLASS_STRING)) {
					labelAttributes.add(null);
				} else {
					needsAdapter = true;
					EStructuralFeature feature = myObject.eClass().getEStructuralFeature(pName);
					if (feature == null)
						throw new IllegalArgumentException(
								"The class " + myObject.eClass() + " has no feature named \"" + pName + "\"");
					if (!(feature instanceof EAttribute))
						throw new IllegalArgumentException(
								"The feature " + pName + " on " + myObject.eClass() + " is not an EAttribute!");
					labelAttributes.add((EAttribute) feature);
				}
				labelAttributeNames.add(pName);
			}
			initialText = computeText(fmtStr);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			if (!(e instanceof NoSuchElementException)) {
				System.err.println("Invalid name info: " + nameInfo + "!");
				e.printStackTrace();
			}
			labelAttributes.clear();
			labelAttributeNames.clear();

			labelAttributes.add(null);
			labelAttributeNames.add(ECLASS_STRING);
			EStructuralFeature nameFeature = myObject.eClass().getEStructuralFeature("name");
			if (nameFeature != null && nameFeature instanceof EAttribute) {
				fmtStr = "%s %s";
				needsAdapter = true;
				labelAttributes.add((EAttribute) nameFeature);
				labelAttributeNames.add(nameFeature.getName());
			} else
				fmtStr = "%s";
			initialText = computeText(fmtStr);
		}
		this.needsAdapter = needsAdapter;
		if (needsAdapter)
			myObject.eAdapters().add(this);
		formatString = fmtStr;
		labelTextProperty = new SimpleObjectProperty<String>(this, "Label Text", initialText);
	}

	public void dispose() {
		if (needsAdapter)
			myObject.eAdapters().remove(this);
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeature() instanceof EAttribute && labelAttributes.contains((EAttribute) msg.getFeature())
				&& msg.getOldValue() != msg.getNewValue()) {
			labelTextProperty.set(computeText(formatString));
		}
	}

	private String computeText(String formatString) throws IllegalArgumentException {
		try {
			Object[] args = new String[labelAttributes.size()];
			int i = 0;
			for (String pname : labelAttributeNames) {
				if (pname.equals(ECLASS_STRING))
					args[i] = myObject.eClass().getName();
				else {
					Object val = myObject.eGet(labelAttributes.get(i));
					if (val == null)
						args[i] = "";
					else
						args[i] = String.valueOf(val);
				}
				i++;
			}
			String str = String.format(formatString, args);
			return str;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
}