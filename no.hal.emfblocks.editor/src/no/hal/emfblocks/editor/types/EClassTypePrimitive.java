package no.hal.emfblocks.editor.types;

import org.eclipse.emf.ecore.EClass;
import no.hal.emfblocks.hierarchy.TypePrimitive;

public class EClassTypePrimitive extends EMFTypePrimitive {

	private final EClass myClass;

	public EClassTypePrimitive(EClass c) {
		this.myClass = c;
	}

	@Override
	public EClass getKey() {
		return myClass;
	}

	@Override
	public boolean isChildOf(TypePrimitive n) {
		if (!(n instanceof EClassTypePrimitive) || n == this)
			return false;
		else
			return ((EClassTypePrimitive) n).myClass.isSuperTypeOf(myClass);
	}
}
