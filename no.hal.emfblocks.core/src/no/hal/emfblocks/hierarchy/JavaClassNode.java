package no.hal.emfblocks.hierarchy;

/**
 * A {@link TypePrimitive} representing a Java class. Since no more than one
 * instance should exist for each class, the constructor is not exposed; please
 * refer to the appropriate {@link TypeHierarchy} to retrieve instances of this
 * class.
 */
public class JavaClassNode extends TypePrimitiveBase {
	public final Class<?> myClass;

	JavaClassNode(Class<?> myClass) {
		super();
		this.myClass = myClass;
	}

	@Override
	public Object getKey() {
		return myClass;
	}

	@Override
	public boolean isChildOf(TypePrimitive n) {
		return n != this && n instanceof JavaClassNode && ((JavaClassNode) n).myClass.isAssignableFrom(myClass);
	}
}
