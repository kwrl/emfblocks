package no.hal.emfblocks.hierarchy;

import java.util.LinkedHashMap;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import no.hal.emfblocks.contour.Contour;
import no.hal.emfblocks.javafx.FXColoredImageView;

public abstract class TypePrimitiveBase implements TypePrimitive {
	protected transient Contour contour;

	private transient LinkedHashMap<String, TypeParameter> typeParameters = null;
	private transient boolean parametersFinalized = false;

	@Override
	public boolean accepts(Type other) {
		return other != null && (other.getRawType().equals(this) || other.getRawType().isChildOf(this));
	}

	@Override
	public boolean isParentOf(TypePrimitive n) {
		return n != this && n != null && n.isChildOf(this);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + getKey() + ")";
	}

	@Override
	public void setContour(Contour c) {
		this.contour = c;
	}

	@Override
	public Contour getContour() {
		if (contour == null) {
			throw new IllegalStateException(this
					+ " has no contour! The type must be added to a type hierarchy before being used in the application.");
		}
		return contour;
	}

	@Override
	public boolean hasContour() {
		return contour != null;
	}
	
	@Override
	public boolean addTypeParameter(String pname, TypePrimitive boundingType) {
		checkParametersFinalized();
		if (typeParameters == null) {
			typeParameters = new LinkedHashMap<>();
		}
		return typeParameters.put(pname, new TypeParameter(pname, boundingType)) == null;
	}

	private void checkParametersFinalized() {
		if (parametersFinalized) {
			throw new IllegalStateException("This method cannot be called after the first call to getParameters!");
		}
	}

	public boolean removeTypeParameter(String pname) {
		checkParametersFinalized();
		return (typeParameters != null && typeParameters.remove(pname) != null);
	}

	@Override
	public TypeParameter[] getParameters() {
		finalizeTypeParameters();
		return typeParameters == null ? new TypeParameter[0] : typeParameters.values().toArray(new TypeParameter[typeParameters.size()]);
	}

	private void finalizeTypeParameters() {
		parametersFinalized = true;
	}

	@Override
	public boolean hasParameters() {
		finalizeTypeParameters();
		return typeParameters != null && (! typeParameters.isEmpty());
	}

	@Override
	public TypeParameter getParameter(String pname) {
		finalizeTypeParameters();
		return typeParameters == null ? null : typeParameters.get(pname);
	}

	@Override
	public TypePrimitive getRawType() {
		return this;
	}

	@Override
	public FXColoredImageView createNode(boolean tab, boolean horizontal, ObjectProperty<Color> color,
			ObjectProperty<Color> bgColor) {
		return new FXColoredImageView(tab ? getContour().tabImage() : getContour().slotImage(), color, bgColor,
				horizontal, 1);
	}
}
