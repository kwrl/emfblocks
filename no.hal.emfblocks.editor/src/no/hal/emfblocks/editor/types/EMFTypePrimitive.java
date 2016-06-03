package no.hal.emfblocks.editor.types;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import no.hal.emfblocks.hierarchy.TypePrimitiveBase;

public abstract class EMFTypePrimitive extends TypePrimitiveBase {
	@Override
	public abstract EClassifier getKey();

	public static EMFTypePrimitive createTypePrimitive(EClassifier eType) {
		if (eType instanceof EClass)
			return new EClassTypePrimitive((EClass) eType);
		else if (eType instanceof EDataType)
			return new EDataTypePrimitive((EDataType) eType);
		else
			throw new IllegalArgumentException(eType + " was not a class, data type or enum...");
	}
}
