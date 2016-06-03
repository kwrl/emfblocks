package no.hal.emfblocks.util.data.property;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

import no.hal.emfblocks.util.data.Referencer;

public class Property<T> implements PropertyReadOnly<T>, Serializable
{	
	private static final long serialVersionUID = 962371443635505607L;
	
	/**The owner(may be null)*/
	private final PropertyModifier<? super T> owner;
	/**The type*/
	private final Class<T> type;
	/**The current value*/
	private T value;
	
	/**Assigned by {@link PropertyUtil}*/
	transient boolean discovered = false;
	/**Assigned by {@link PropertyUtil}*/
	transient Object discoveredOwner = null;
	/**Assigned by {@link PropertyUtil}*/
	transient Field discoveredField = null;
	
	/**Listeners*/
	transient private ArrayList<WeakReference<PropertyListener<? super T>>> listeners = new ArrayList<>();
	/**
	 * Create a new property with no owner and the type of the value. <br/><br/>
	 * <font color = #FF0000>Warning: If the value's type is not an exact match for the generic type of this Property, 
	 * then undefined behaviour may occur. Boxed types should be used for number literals. The value may not be null.</font>
	 * @param value The initial value of the property. May not be null, and the value must be a perfect match for the property's generic type.
	 */
	@SuppressWarnings("unchecked")
	public Property(T value)
	{
		this(null, (Class<T>) value.getClass(), value);
	}
	/**
	 * Create a new property with the type of the value. <br/><br/>
	 * <font color = #FF0000>Warning: If the value's type is not an exact match for the generic type of this Property, 
	 * then undefined behaviour may occur. Boxed types should be used for number literals. The value may not be null.</font>
	 * @param owner An "owner" with an exclusive right to apply constraints to the value. May be null.
	 * @param value The initial value of the property. May not be null, and the value must be a perfect match for the property's generic type.
	 */
	@SuppressWarnings("unchecked")
	public Property(PropertyModifier<? super T> owner, T value)
	{
		this(owner, (Class<T>) value.getClass(), value);
	}
	/**
	 * Create a new property with no owner.
	 * @param type The type of the property.
	 * @param value The initial value of the property. May be null.
	 */
	public Property(Class<T> type, T value)
	{
		this(null, type, value);
	}
	/**
	 * Create a new property. 
	 * @param owner An "owner" with an exclusive right to apply constraints to the value. May be null.
	 * @param type The type of the property.
	 * @param value The initial value of the property. May be null.
	 */
	public Property(PropertyModifier<? super T> owner, Class<T> type, T value)
	{
		assert type != null : "Type of parameter cannot be null!";
		this.owner = owner;
		this.type = type;
		this.value = value;
	}
	/**Set the parameter's value to the indicated value.
	 * The owner of this parameter may modify the value before it is assigned and before listeners are notified.
	 * If exception is non-null, then that listener will not receive any event.*/
	public void set(T newValue, PropertyListener<? super T> exception)
	{
		T oldValue = value;
		if(owner == null)
			value = newValue;
		else
		{
			try
			{
				value = cast(owner.parameterModification(this, oldValue, newValue));
			}
			catch(ClassCastException e)
			{
				e.printStackTrace();
				value = newValue;
			}
		}
		
		if(oldValue == null ? (value != null) : (!oldValue.equals(value)))
		{			
			Iterator<WeakReference<PropertyListener<? super T>>> it = listeners.iterator();
			while(it.hasNext())
			{
				PropertyListener<? super T> next = it.next().get();
				if(next != null)
				{
					if(next != exception)
						next.valueChanged(this, oldValue, value);
				}
				else
					it.remove();//Clean up destroyed reference
			}
		}
	}
	/**Set the parameter's value to the indicated value.
	 * The owner of this parameter may modify the value before it is assigned and before listeners are notified.*/
	public void set(T newValue)
	{
		this.set(newValue, null);
	}
	@Override
	public T get()
	{
		return value;
	}
	
	@Override
	public void addListener(Referencer parent, PropertyListener<? super T> l)
	{
		if(parent != null)
			parent.addReference(l);
		if(l == null)
		{
			System.err.println("Warning: Cannot add null as listener to "+this);
			return;
		}
		int oldIndex = PropertyUtil.indexOfWeakReference(listeners, l);
		if(oldIndex != -1)
		{
			System.err.println("Warning: Tried to add "+l+" as listener to "+this+" but it was already added at "+oldIndex);
			return;
		}
		listeners.add(new WeakReference<>(l));
	}
	@Override
	public boolean removeListener(Referencer parent, PropertyListener<T> l)
	{
		if(parent != null)
			parent.removeReference(l);
		int index = PropertyUtil.indexOfWeakReference(listeners, l);
		if(index == -1)
			return false;
		listeners.remove(index);
		return true;
	}
	@Override
	public Class<T> getType()
	{
		return type;
	}
	@Override
	public T cast(Object obj)
	{
		return type.cast(obj);
	}
	@Override
	public String toString() {
		return (discovered?discoveredField.getName():'P')+": "+type.getSimpleName()+"="+(value==null?"null":value.toString());
	}
}
