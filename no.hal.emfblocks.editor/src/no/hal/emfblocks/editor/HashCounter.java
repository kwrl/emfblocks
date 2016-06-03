package no.hal.emfblocks.editor;

import java.util.HashMap;

public class HashCounter<T> {
	private HashMap<T, Integer> counter = new HashMap<>();

	/**
	 * Increase the counter with the given key by 1, deleting it if it reaches
	 * 0(allowing garbage collection of the key). Returns the new value of the
	 * counter.
	 */
	public int incrementAndGet(T key) {
		return modifyAndGet(key, 1);
	}

	/**
	 * Decrease the counter with the given key by 1, deleting it if it reaches
	 * 0(allowing garbage collection of the key). Returns the new value of the
	 * counter.
	 */
	public int decrementAndGet(T key) {
		return modifyAndGet(key, -1);
	}

	/**
	 * Add the given value to the indicated counter, deleting it if it reaches
	 * 0(allowing garbage collection of the key). Returns the new value of the
	 * counter.
	 */
	private int modifyAndGet(T key, int changeAmount) {
		Integer i = counter.get(key);
		if (i == null)
			i = 0;
		i = i + changeAmount;
		if (i == 0)
			counter.remove(key);
		else
			counter.put(key, i);
		return i;
	}
}
