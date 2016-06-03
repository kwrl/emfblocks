package no.hal.emfblocks.hierarchy;

public class TypeParameter {
	public final String name;
	public final TypePrimitive boundingType;

	public TypeParameter(String pname, TypePrimitive boundingType) {
		this.name = pname;
		this.boundingType = boundingType;
	}
}
