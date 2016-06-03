package no.hal.emfblocks.hierarchy;

import java.util.LinkedHashSet;

/**
 * A {@link TypePrimitive} representing a Java class. Since no more than one
 * instance should exist for each class, the constructor is not exposed; please
 * refer to the appropriate {@link TypeHierarchy} to retrieve instances of this
 * class.
 */
public class CustomTypePrimitive extends TypePrimitiveBase implements ReparentableTypeNode {
	private String name;
	private transient final LinkedHashSet<TypePrimitive> parents = new LinkedHashSet<>();

	public CustomTypePrimitive(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object getKey() {
		return name;
	}

	@Override
	public boolean isChildOf(TypePrimitive n) {
		// Type is not child of null nor itself.
		if (n == null || n == this)
			return false;
		// Type is child of its parents
		if (parents.contains(n))
			return true;
		// Type is also child of parents' parents
		for (TypePrimitive p : parents) {
			if (p.isChildOf(n))
				return true;
		}
		return false;
	}

	@Override
	public void setIsParent(TypePrimitive n, boolean isParent) {
		if (isParent)
			parents.add(n);
		else
			parents.remove(n);
	}
}
