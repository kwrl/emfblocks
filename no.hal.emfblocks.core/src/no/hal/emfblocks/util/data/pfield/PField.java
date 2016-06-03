package no.hal.emfblocks.util.data.pfield;

import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.PropertyListener;

public interface PField<T extends PFieldElement> extends PFieldImmutable<T>
{	
	/**Adds a new element to the list. 
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default void add(T element) throws IllegalArgumentException
	{
		add(element, null, true);
	}
	/**Adds a new element to the list.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default void add(T element, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		set(size(), element, exclude, fireEvents);
	}
	/**Set the first element in the list.
	 * @throws PropertyVetoException If some veto listener did not accept the change.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default void set(T element) throws IllegalArgumentException
	{
		set(element, null, true);
	}
	/**Set the first element in the list.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default void set(T element, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		set(0, element, exclude, fireEvents);
	}
	/**Replaces the element with the given index in the list. If index == size() then this has the same effect as calling add().
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default void set(int index, T element) throws IllegalArgumentException
	{
		assert element != null : "Null in PFields is not allowed.";
		set(index, element, null, true);
	}
	
	
	/**Replaces the element with the given index in the list. If index == size() then this has the same effect as calling add().
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range, .*/
	public void set(int index, T element, Object exclude, boolean fireEvents) throws IllegalArgumentException;
	
	/**Inserts the element at the given index in the list. If index == size() then this has the same effect as calling add().
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range, .*/
	public default void insert(int index, T element) throws IllegalArgumentException
	{
		assert element != null : "Null in PFields is not allowed.";
		insert(index, element, null, true);
	}
	
	/**Inserts the element at the given index in the list. If index == size() then this has the same effect as calling add().
	 * @param index The index of the new element. Any elements with greater or equal index with be shifted by 1 position.
	 * @param element The element to add. Must be non-null.
	 * @param exclude A listener to be excluded when firing events. Can be used to prevent unwanted recursion. If fireEvents is false, then this is ignored.
	 * @param fireEvents Whether to fire events to {@link PropertyListener}s.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public void insert(int index, T element, Object exclude, boolean fireEvents) throws IllegalArgumentException;
	
	/**Removes the indicated element.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default T remove(int index) throws IllegalArgumentException
	{
		return remove(index, null, true);
	}
	/**Removes the indicated element.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public T remove(int index, Object exclude, boolean fireEvents) throws IllegalArgumentException;
	/**Removes the indicated element.
	 * @return The index of the element.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default int remove(Object element) throws IllegalArgumentException
	{
		return remove(element, null, true);
	}
	/**Removes the indicated element.
	 * @return The index of the element.
	 * @throws IllegalArgumentException If the owner did not accept the change, if the index is out of range.*/
	public default int remove(Object element, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		for(int i = 0; i<size(); i++)
		{
			if(get(i) == element)
			{
				remove(i, exclude, fireEvents);
				return i;
			}
		}
		throw new IllegalArgumentException(element+" was not in "+this);
	}
	/**Removes the last element in the list.
	 * @throws IllegalArgumentException If the owner did not accept the change.*/
	public default void remove() throws IllegalArgumentException
	{
		remove(size()-1, null, true);
	}
	/**Removes the last element in the list.
	 * @throws IllegalArgumentException If the owner did not accept the change.*/
	public default void remove(Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		remove(size()-1, exclude, fireEvents);
	}
	
	
	/**Listen to changes to this property. It is illegal to add a listener twice. It is illegal to add null as a listener.
	 * <b>The reference to the listener will be weak and the listener is removed as soon as it is garbage collected.</b>
	 * Thus, it is normally not necessary to call {@link #removeListener(PFieldListener)}.
	 * If "parent" is not null, then the a reference to the listener is injected into the "parent" referencer.
	 * The purpose of this is to prevent garbage collection as long as the parent object is alive.
	 * If any reference to the listener is kept in another location, then "parent" should be null.*/
	public void addListener(Referencer parent, PFieldListener<? super T> l);
	/**Remove the indicated listener. Returns false if it has not been added yet. 
	 * Note that listeners will be removed automatically if they are garbage collected.
	 * If "parent" is not null, then the listener reference will be removed from it.*/
	public boolean removeListener(Referencer parent, PFieldListener<? super T> l);
	
	public void registerFavorite(Object a);
	public void removeFavorite(Class<?> a);
	
	public void checkVetos(Object exclude, T newValue, int index);
}
