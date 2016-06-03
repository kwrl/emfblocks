package no.hal.emfblocks.editor.types;

import org.eclipse.emf.ecore.EDataType;

import no.hal.emfblocks.hierarchy.TypePrimitive;

public class EDataTypePrimitive extends EMFTypePrimitive {

	private final EDataType myClass;

	public EDataTypePrimitive(EDataType c) {
		this.myClass = c;
	}

	@Override
	public EDataType getKey() {
		return myClass;
	}

	@Override
	public boolean isChildOf(TypePrimitive n) {
		return false;
	}
}
