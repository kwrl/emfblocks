package no.hal.emfblocks.hierarchy;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import no.hal.emfblocks.javafx.FXParameterizedType;
import no.hal.emfblocks.javafx.SlackedGroup;
import no.hal.emfblocks.util.data.property.Property;

public class ParameterizedType implements Type {
	public final TypePrimitive rawType;

	protected final LinkedHashMap<String, Property<Type>> params;

	public ParameterizedType(TypePrimitive primaryType) {
		this.rawType = primaryType;
		params = new LinkedHashMap<>();
		for (TypeParameter p : primaryType.getParameters()) {
			params.put(p.name, new Property<Type>(p.boundingType));
		}
	}

	public Property<Type>[] concreteTypes() {
		@SuppressWarnings("unchecked")
		Property<Type>[] types = (Property<Type>[]) Array.newInstance(Property.class, params.size());
		params.values().toArray(types);
		return types;
	}

	public Property<Type> concreteType(String pname) {
		return params.get(pname);
	}

	@Override
	public boolean accepts(Type other) {
		// TODO I don't know how to handle other bounding modes. This uses upper
		// bound for everything.
		if (other instanceof ParameterizedType) {
			ParameterizedType otherT = (ParameterizedType) other;
			// Raw types must be compatible.
			if (rawType.accepts(otherT.rawType)) {
				// All type parameters on the super type must match.
				// The type parameters specific to a subtype of this type are
				// "don't care" conditions.
				for (Entry<String, Property<Type>> p : params.entrySet()) {
					Property<Type> op = otherT.params.get(p.getKey());
					if (op == null)
						throw new IllegalStateException("Other type: " + other + " has the raw type " + otherT.rawType
								+ " which is compatible with " + rawType + ", but it is missing the type parameter \""
								+ p.getKey() + "\"!");
					if (!p.getValue().get().accepts(op.get()))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public TypePrimitive getRawType() {
		return rawType;
	}

	@Override
	public SlackedGroup createNode(boolean tab, boolean horizontal, ObjectProperty<Color> color,
			ObjectProperty<Color> bgColor) {
		return new FXParameterizedType(this, tab, color, bgColor, horizontal, 1);
	}
}
