package no.hal.emfblocks.hierarchy;

import no.hal.emfblocks.contour.Contour;

public interface TypePrimitive extends Type {
	/**
	 * Return the key object for this raw type. The key object is used to look
	 * up the raw type instance in the {@link TypeHierarchy}.
	 */
	public Object getKey();

	/**
	 * Whether this raw type is a supertype of the given raw type. Under this
	 * definition, a raw type is never its own parent, and null is nobody's
	 * child.
	 */
	public boolean isParentOf(TypePrimitive n);

	/**
	 * Whether this raw type is a subtype of the given raw type. Under this
	 * definition, a raw type is never its own child, and null is nobody's
	 * parent.
	 */
	public boolean isChildOf(TypePrimitive n);

	/**
	 * Get the raw type's contour object, which can be used to create a visual
	 * representation of a tab or a slot.
	 */
	public Contour getContour();

	/**
	 * Set the raw type's contour. The application should not call this method.
	 */
	public void setContour(Contour c);

	/**
	 * Whether the raw type's contour has been assigned. This will be true after
	 * the raw type has been properly initialized.
	 */
	public boolean hasContour();

	/**
	 * Add a type parameter to this type. The application should not call this
	 * method. Use
	 * {@link TypeHierarchy#addTypeParameter(TypePrimitive, TypePrimitive, TypeParameterBound)}
	 * instead.
	 * 
	 * @return Whether the name was previously not associated with a type
	 *         parameter. In other words, whether the number of type parameters
	 *         increased as a result of this call.
	 */
	public boolean addTypeParameter(String name, TypePrimitive boundingType);

	/**
	 * Return the type parameters. <br/>
	 * <br/>
	 * It is illegal to call
	 * {@link #addTypeParameter(String, TypePrimitive, TypeParameterBound)}
	 * after the first call to this method.
	 */
	public TypeParameter[] getParameters();

	/**
	 * Returns whether the type has a non-zero amount of type parameters.
	 * Functionally equivalent to checking whether the length of
	 * {@link #getParameters()} is greater than 0. <br/>
	 * <br/>
	 * It is illegal to call
	 * {@link #addTypeParameter(String, TypePrimitive, TypeParameterBound)}
	 * after the first call to this method.
	 */
	public boolean hasParameters();

	/**
	 * Returns the type parameter with the given name. <br/>
	 * <br/>
	 * It is illegal to call
	 * {@link #addTypeParameter(String, TypePrimitive, TypeParameterBound)}
	 * after the first call to this method.
	 */
	public TypeParameter getParameter(String pname);
}
