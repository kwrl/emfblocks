package no.hal.emfblocks.util.data;

import java.util.HashSet;

/**
 * The default implementation of the {@link Referencer}. Note that the reference
 * set contents are transient.
 */
public class ReferenceSet implements Referencer {
	private transient HashSet<Object> set = new HashSet<>();

	@Override
	public void addReference(Object o) {
		set.add(o);
	}

	@Override
	public void removeReference(Object o) {
		set.remove(o);
	}

}
