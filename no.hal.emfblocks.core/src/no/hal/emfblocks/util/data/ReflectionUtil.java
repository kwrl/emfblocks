package no.hal.emfblocks.util.data;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;

public class ReflectionUtil {

	/**
	 * Returns all fields from the object(not just those declared in its class)
	 */
	public static Field[] getFields(Object p) {
		int totalSize = 0;
		Queue<Field[]> allFields = new LinkedList<>();
		Class<?> c = p.getClass();
		while (c != null) {
			Field[] ffs = c.getDeclaredFields();
			allFields.offer(ffs);
			totalSize += ffs.length;
			c = c.getSuperclass();
		}
		Field[] fields = new Field[totalSize];
		int pos = 0;
		Field[] next;
		while ((next = allFields.poll()) != null) {
			System.arraycopy(next, 0, fields, pos, next.length);
			pos += next.length;
		}
		assert pos == totalSize;
		return fields;
	}

	/**
	 * Returns the field with the given name from the object(not limited to
	 * public fields or those declared in its class). null will be returned if
	 * the field does not exist.
	 */
	public static Field getField(Object p, String srcFieldName) {
		Class<?> c = p.getClass();
		while (c != null) {
			try {
				Field f = c.getField(srcFieldName);
				if (f != null)
					return f;
			} catch (Exception e) {
			}
			c = c.getSuperclass();
		}
		return null;
	}

}
