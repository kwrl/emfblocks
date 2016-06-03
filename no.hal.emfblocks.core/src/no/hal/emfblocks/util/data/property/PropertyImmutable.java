package no.hal.emfblocks.util.data.property;


/**
 * Interface into a {@link Property} where it is only possible to read the value.
 * (e.g. it is not possible to add or remove listeners.)
 */
public interface PropertyImmutable<T>
{	
	/**
	 * Get the value of the parameter.
	 * <br/><br/>
	 * If the parameter contains a mutable type, then a clone will be returned.
	 * @return The current value of the parameter.
	 */
	public T get();

	/**Type-safe cast of the given object to this parameter's type.*/
	public T cast(Object obj);

	/**Returns the type of the parameter*/
	public Class<T> getType();
}
