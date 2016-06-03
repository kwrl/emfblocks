package no.hal.emfblocks.util.data.pfield;

import java.lang.reflect.Array;

public interface PFieldImmutable<T extends PFieldElement> extends Iterable<T>
{	
	/**Returns the object upon which this is a field.*/
	public PFieldOwner<? super T> getOwner();
	/**The type of the element field.*/
	public Class<T> getType();
	
	/**The current number of elementren in this field*/
	public int size();
	/**Returns whether the size is 0.*/
	public default boolean isEmpty()
	{
		return size()==0;
	}
	/**Returns the index of the indicated node, or -1 if it does not exist.*/
	public int indexOf(Object n);

	/**Returns whether the indicated object is in the PField.*/
	public default boolean contains(Object n)
	{
		return indexOf(n) != -1;
	}
	/**Returns the first element in this field, or null if there are no elements.*/
	public default T get()
	{
		if(isEmpty())
			return null;
		return get(0);
	}
	/**Returns the element with the given index in this field*/
	public T get(int index);
	/**Returns all the elementren in this field*/
	public default T[] toArray()
	{
		@SuppressWarnings("unchecked")
		T[] r = (T[]) Array.newInstance(getType(), size());
		for(int i = 0; i<size(); i++)
		{
			r[i] = get(i);
		}
		return r;
	}
	/**Cast the given object to the type of the field. Throws a {@link ClassCastException} if it fails.*/
	public default T cast(Object element) throws ClassCastException
	{
		return getType().cast(element);
	}
	public<X> X getFavorite(Class<X> type);
}
