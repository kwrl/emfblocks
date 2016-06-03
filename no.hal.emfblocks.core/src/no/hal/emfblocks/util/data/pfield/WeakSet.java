package no.hal.emfblocks.util.data.pfield;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class WeakSet<T> implements Externalizable, Iterable<T>
{
	private transient LinkedHashSet<WeakReference<T>> set = new LinkedHashSet<>();
	
	@Override
	public void readExternal(ObjectInput in) throws IOException,
		ClassNotFoundException
	{
		Object[] array = (Object[]) in.readObject();
		for(Object e : array)
		{
			@SuppressWarnings("unchecked")
			T v = (T) e;
			add(v);
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeObject(toArray());
	}

	public boolean add(T e)
	{
		if(e == null)
			return false;
		return set.add(new WeakReference<T>(e));
	}

	public void clear()
	{
		set.clear();
	}
	
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			private Iterator<WeakReference<T>> internalIt;
			private T next;
			{
				internalIt = set.iterator();
				getNext();
			}
			@Override
			public boolean hasNext()
			{
				return next != null;
			}

			private void getNext()
			{
				next = null;
				while(next == null && internalIt.hasNext())
				{
					T cc = internalIt.next().get();
					if(cc != null)
						next = cc;
					else
						internalIt.remove();
				}
			}

			@Override
			public T next()
			{
				T r = next;
				getNext();
				return r;
			}
		};
	}

	public boolean remove(Object o)
	{
		Iterator<WeakReference<T>> it = set.iterator();
		while(it.hasNext())
		{
			WeakReference<T> w = it.next();
			T e = w.get();
			if(e == null)
				it.remove();
			else if(e == o)
			{
				it.remove();
				return true;
			}
				
		}
		return false;
	}
	
	public Object[] toArray()
	{
		ArrayList<T> list = new ArrayList<>();
		for(T e : this)
			list.add(e);
		return list.toArray();
	}	

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		for(WeakReference<?> l : set)
		{
			Object e = l.get();
			if(idx != 0)
				sb.append(", ");
			if(e == null)
				sb.append("null");
			else
				sb.append(e.toString());
			idx++;
		}
		return sb.toString();
	}
	
}
