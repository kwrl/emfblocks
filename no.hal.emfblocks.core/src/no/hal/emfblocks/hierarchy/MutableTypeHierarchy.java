package no.hal.emfblocks.hierarchy;

import java.io.File;
import java.util.function.Function;

public class MutableTypeHierarchy extends TypeHierarchy {
	public MutableTypeHierarchy(File cutDir, Function<Object, String> keyStringifier, TypePrimitive... vals) {
		super(cutDir, keyStringifier, vals);
	}

	/** Create a TypeHierarchy using the given Java classes. */
	public MutableTypeHierarchy(File cutDir, Class<?>... classes) {
		super(cutDir, classes);
	}

	/** Cut editor api */
	public void add(TypePrimitive t) {
		if (valueMap.containsKey(t.getKey()))
			throw new IllegalArgumentException(
					t + " cannot be added to " + this + " because it has already been added!");
		values.add(t);
		valueMap.put(t.getKey(), t);
		invalidate();
	}

	/** Cut editor api */
	public void remove(TypePrimitive t) {
		if (!valueMap.containsKey(t.getKey()))
			throw new IllegalArgumentException(t + " cannot be removed from " + this + " because it does not exist!");
		values.remove(t);
		valueMap.remove(t.getKey());
		invalidate();
	}
}
