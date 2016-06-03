package no.hal.emfblocks.util.data.pfield;

import java.util.Iterator;

public class PFieldSingle<T extends PFieldElement> extends PFieldAbstract<T>
{	
	private static final long serialVersionUID = 3211940306409009833L;
	
	T value;
	public PFieldSingle(PFieldOwner<? super T> owner, Class<T> childType)
	{
		super(owner, childType);
	}
	
	@Override
	public int indexOf(Object n)
	{
		return value == n ? 0:-1;
	}
	
	@Override
	public int size()
	{
		return value==null?0:1;
	}
	
	@Override
	public void insert(int index, T element, Object exclude, boolean fireEvents) throws IllegalArgumentException {
		if(value != null)
			throw new IllegalStateException(this+": Cannot insert because Single-element PField cannot have more than 1 element! value: "+value+", index: "+index+", element: "+element);
		set(index, element, exclude, fireEvents);
	}

	@Override
	public T get(int index)
	{
		if(index < 0 || index >1)
			throw new IllegalArgumentException(this+" on "+getOwner()+": Index out of bounds: "+index+"/"+size());
		//Note: Optional field returns null if empty.
		return value;
	}

	@Override
	public void set(int index, T child, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		checkVetos(exclude, child, index);
		if(index < 0 || index >= 1)
			throw new IllegalArgumentException("Index out of bounds: "+index+"/1");
		T rem = value;
		this.value = child;
		if(rem != null)
			rem.removeNotify(this);
		if(child != null)
			child.addNotify(this);
		if(fireEvents)
			fireEvents(index, rem, child, exclude);
	}

	@Override
	public T remove(int index, Object exclude, boolean fireEvents) throws IllegalArgumentException
	{
		if(value == null)
			throw new IllegalArgumentException(this+" already does not have any value...");
		if(index != 0)
			throw new IllegalArgumentException(this+" cannot remove element at index "+index+" because there is only one element");
		checkVetos(exclude, null, index);
		T rem = value;
		value = null;
		if(rem != null)
			rem.removeNotify(this);
		if(fireEvents)
			fireEvents(0, rem, null, exclude);
		return rem;
	}	
	
	@Override
	public String toString()
	{
		return "Optional "+type.getSimpleName()+'('+size()+") on "+getOwner();
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			boolean hasCurrent = false;
			boolean hasNext = !isEmpty();
			@Override
			public boolean hasNext()
			{
				return hasNext;
			}

			@Override
			public T next()
			{
				if(hasNext)
				{
					hasNext = false;
					hasCurrent = true;
					return get();
				}
				else
					throw new IllegalStateException("No remaining elements in "+PFieldSingle.this+"...");
			}
			@Override
			public void remove()
			{
				if(!hasCurrent)
					throw new IllegalStateException("Next element in "+PFieldSingle.this+" not yet retrieved: Cannot remove current element!");
				PFieldSingle.this.remove();
				hasCurrent = false;
			}
		};
	}
}
