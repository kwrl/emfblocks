package no.hal.emfblocks.util.data.property;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import no.hal.emfblocks.util.data.Hidden;
import no.hal.emfblocks.util.data.ReflectionUtil;

public class PropertyUtil
{
	/**Returns all non-{@link Hidden} Property fields on the given object.*/
	public static Property<?>[] getProperties(Object p)
	{
		ArrayList<Property<?>> list = new ArrayList<>();
		for (Field f : ReflectionUtil.getFields(p))
		{
			if(Property.class.isAssignableFrom(f.getType()))
			{
				if(f.getAnnotation(Hidden.class) == null)
				{
					if(!Modifier.isFinal(f.getModifiers()))
						System.err.println("Warning: "+p+" has a non-final, non-hidden "+Property.class.getSimpleName()+" field: "+f);
					f.setAccessible(true);
					try
					{
						Property<?> prop = (Property<?>)f.get(p);
						if(prop.discovered)
						{
							if(prop.discoveredOwner != p)
								System.err.println("Warning: "+p+"."+f.getName()+" is a non-hidden "+Property.class.getSimpleName()+
									" field which is shared with "+prop.discoveredOwner+"."+prop.discoveredField.getName());
						}
						else
						{
							prop.discoveredField = f;
							prop.discoveredOwner = p;
							prop.discovered = true;
						}
						list.add(prop);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		Property<?>[] props = (Property<?>[]) Array.newInstance(Property.class, list.size());
		list.toArray(props);
		return props;
	}
	public static String nameOf(Property<?> prop, Object owner)
	{
		if(!prop.discovered)
			getProperties(owner);//Triggers discovery
		if(!prop.discovered)
			throw new RuntimeException(prop+" is not a field on "+owner+"!");
		return prop.discoveredField.getName();
	}
	public static Annotation[] getAnnotations(Property<?> prop, Object owner)
	{
		if(!prop.discovered)
			getProperties(owner);//Triggers discovery
		if(!prop.discovered)
			throw new RuntimeException(prop+" is not a field on "+owner+"!");
		return prop.discoveredField.getAnnotations();
	}
	
	/**
	 * Warning: untested.
	 */
	@SuppressWarnings("unchecked")
	public static<X> Property<X> getEquivalentProperty(Property<X> prop, Object owner, Object onObject)
	{
		if(!prop.discovered)
			getProperties(owner);//Triggers discovery
		if(!prop.discovered)
			throw new RuntimeException(prop+" is not a field on "+owner+"!");
		//onObject must be instanceof prop's owner's class
		assert prop.discoveredOwner.getClass().isInstance(onObject);
		
		try
		{
			return (Property<X>) prop.discoveredField.get(onObject);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Cannot locate equivalent of "+prop+" from "+owner+" on "+onObject, e);
		}
	}
	/**Returns the first index of the item in the list of weak references, or -1 if it was not found.
	 * @see ArrayList#indexOf(Object)*/
	public static<T> int indexOfWeakReference(ArrayList<WeakReference<T>> list, Object item)
	{
		for(int i = 0; i<list.size(); i++)
		{
			if(		(item == null)?
				(list.get(i) == null || list.get(i).get() == null)
				:
					item.equals(list.get(i).get()))
				return i;
		}
		return -1;
	}
}
