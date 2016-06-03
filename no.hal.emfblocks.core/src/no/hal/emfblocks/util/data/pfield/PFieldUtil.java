package no.hal.emfblocks.util.data.pfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import no.hal.emfblocks.util.data.Hidden;
import no.hal.emfblocks.util.data.ReflectionUtil;

public class PFieldUtil
{
	/**Returns all non-{@link Hidden} PFieldAbstract fields on the given object.*/
	public static PField<?>[] getPFields(Object p)
	{
		ArrayList<PFieldAbstract<?>> list = new ArrayList<>();
		for (Field f : ReflectionUtil.getFields(p))
		{
			if(PFieldAbstract.class.isAssignableFrom(f.getType()))
			{
				if(f.getAnnotation(Hidden.class) == null)
				{
					if(!Modifier.isFinal(f.getModifiers()))
						System.err.println("Warning: "+p+" has a non-final, non-hidden "+PFieldAbstract.class.getSimpleName()+" field: "+f);
					f.setAccessible(true);
					try
					{
						PFieldAbstract<?> prop = (PFieldAbstract<?>)f.get(p);
						if(prop.discovered)
						{
							if(prop.discoveredOwner != p)
								System.err.println("Warning: "+p+"."+f.getName()+" is a non-hidden "+PFieldAbstract.class.getSimpleName()+
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
		PField<?>[] props = (PField<?>[]) Array.newInstance(PField.class, list.size());
		list.toArray(props);
		return props;
	}
	public static String nameOf(PField<?> field, Object owner)
	{
		PFieldAbstract<?> prop = (PFieldAbstract<?>) field;
		if(!prop.discovered)
			getPFields(owner);//Triggers discovery
		if(!prop.discovered)
			throw new RuntimeException(prop+" is not a field on "+owner+"!");
		return prop.discoveredField.getName();
	}
	public static Annotation[] getAnnotations(PField<?> field, Object owner)
	{
		PFieldAbstract<?> prop = (PFieldAbstract<?>) field;
		if(!prop.discovered)
			getPFields(owner);//Triggers discovery
		if(!prop.discovered)
			throw new RuntimeException(prop+" is not a field on "+owner+"!");
		return prop.discoveredField.getAnnotations();
	}
}
