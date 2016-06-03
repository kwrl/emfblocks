package no.hal.emfblocks.util.data.pfield;

import java.util.ArrayList;
import java.util.Iterator;

public class PFieldList<T extends PFieldElement> extends PFieldAbstract<T> implements Iterable<T>
{
	private static final long serialVersionUID = -1783504137851551677L;
	
	private ArrayList<T> list;
	
	public PFieldList(PFieldOwner<? super T> owner, Class<T> childType)
	{
		super(owner, childType);
		list = new ArrayList<>();
	}
	
	@Override
	public int indexOf(Object n)
	{
		return list.indexOf(n);
	}
	
	@Override
	public int size()
	{
		return list.size();
	}
	
	@Override
	public T get(int index)
	{
		return list.get(index);
	}
	

	@Override
	public void insert(int index, T element, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		assert element != null : "Inserting null in PFields is not allowed.";
		checkVetos(exclude, element, index);
		for(int i = size(); i > index; i--)
		{
			T rem = clr(i-1);
			unVetoableSet(i, rem, exclude, false);
		}
		unVetoableSet(index, element, exclude, fireEvents);
	}
	/**Replaces the element at the given index with null, with a removeNotify(). Used internally.*/
	public T clr(int index) 
	{
		T rem = list.get(index);
		if(rem == null)
			throw new IllegalStateException(this+": it is illegal to clr a null element! Index: "+index);
		rem.removeNotify(this);
		list.set(index, null);
		return rem;
	}
	@Override
	public void set(int index, T child, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		checkVetos(exclude, child, index);
		unVetoableSet(index, child, exclude, fireEvents);
	}
	
	private void unVetoableSet(int index, T child, Object exclude, boolean fireEvents) {
		final T oldValue;
		if(index == list.size())
		{
			list.add(child);
			oldValue = null;
		}
		else
		{
			T rem = list.get(index);
			list.set(index, child);
			//Note if we are doing the insert operation, then the element may have been shifted and clr'd:
			//Then it should not receive the remove message.
			if(rem != null)
				rem.removeNotify(this);
			oldValue = rem;
		}
		child.addNotify(this);
		if(fireEvents)
			fireEvents(index, oldValue, child, exclude);
	}

	@Override
	public T remove(int index, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		checkVetos(exclude, null, index);
		T rem = list.remove(index);
		assert rem != null : "Tried to remove a nonexisting element at index "+index+" from "+this+" with size "+size();
		rem.removeNotify(this);
		if(fireEvents)
			fireEvents(list.size(), rem, null, exclude);
		return rem;
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			int currentPosition = -1;
			int nextPosition = 0;
			@Override
			public boolean hasNext()
			{
				return nextPosition < size();
			}

			@Override
			public T next()
			{
				currentPosition = nextPosition;
				nextPosition++;
				return get(currentPosition);
			}
			@Override
			public void remove()
			{
				if(currentPosition == -1)
					throw new IllegalStateException("Next element not yet retrieved: Cannot remove current element!");
				PFieldList.this.remove(currentPosition);
				currentPosition = -1;
				nextPosition --;
			}
		};
	}	
	@Override
	public String toString()
	{
		return "List of "+type.getSimpleName()+'('+size()+") on "+getOwner();
	}

	public void clear()
	{
		for(int i = size()-1; i >= 0; i--)
			remove(i);
	}
}
