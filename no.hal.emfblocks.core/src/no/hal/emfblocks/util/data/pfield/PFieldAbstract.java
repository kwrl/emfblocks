package no.hal.emfblocks.util.data.pfield;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

import no.hal.emfblocks.util.data.Referencer;


abstract class PFieldAbstract<T extends PFieldElement> implements PField<T>, Serializable
{
	private static final long serialVersionUID = -7246135460896909184L;

	/**Typed listeners*/
	protected transient WeakSet<PFieldListener<? super T>> childListeners = new WeakSet<>();
	
	protected PFieldOwner<? super T> owner;
	/**The type of the property*/
	protected Class<T> type;
	/**All registered annotations on this instance.*/
	protected HashMap<Class<?>, Object> favorites = new HashMap<>();
	
	/**Whether there are any listeners that have not been notified of a new change.*/
	protected boolean dirty = false;
	
	/**Assigned by {@link PFieldUtil}*/
	transient boolean discovered = false;
	/**Assigned by {@link PFieldUtil}*/
	transient Object discoveredOwner = null;
	/**Assigned by {@link PFieldUtil}*/
	transient Field discoveredField = null;
	
	public PFieldAbstract(PFieldOwner<? super T> owner, Class<T> childType)
	{
		this.owner = owner;
		this.type = childType;
	}
	
	@Override
	public PFieldOwner<? super T> getOwner()
	{
		return owner;
	}
	@Override
	public Class<T> getType()
	{
		return type;
	}
	
	@Override
	public void addListener(Referencer parent, PFieldListener<? super T> l)
	{
		if(parent != null)
			parent.addReference(l);
		if(l == null)
		{
			System.err.println("Cannot add null as listener to "+this);
			return;
		}
		childListeners.add(l);
	}
	
	@Override
	public boolean removeListener(Referencer parent, PFieldListener<? super T> l)
	{
		if(parent != null)
			parent.removeReference(l);
		return childListeners.remove(l);
	}
	@Override
	public void checkVetos(Object exclude, T newValue, int index)
	{
		if(exclude != owner && owner != null)
			owner.pFieldChange(this, newValue, index);
	}
	
	protected void fireEvents(int index, T oldValue, T newValue, Object exclude)
	{
		dirty = true;
		
		for(PFieldListener<? super T> next : childListeners)
		{
			if(next != exclude)
			{
				next.valueChanged(this, index, oldValue, newValue);
			}
			if(!dirty)
				return;
		}
		dirty = false;
	}
	
	@Override
	public void registerFavorite(Object a)
	{
		favorites.put(a.getClass(), a);
	}
	@Override
	public void removeFavorite(Class<?> a)
	{
		favorites.remove(a);
	}
	@Override
	public<X> X getFavorite(Class<X> type)
	{
		@SuppressWarnings("unchecked")
		X r = (X) favorites.get(type);
		return r;
	}
}
