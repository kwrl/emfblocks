package no.hal.emfblocks.util.data.property;

public interface PropertyModifier<X>
{	
	/**
	 * Called when someone attempts to change the value of one of the parameters of this object.
	 * The implementation may return a modified value if the indicated newValue is illegal.
	 * Note that this will be called before the new value has been set to the property.
	 * @param param The parameter which has changed
	 * @param oldValue The old value of the parameter
	 * @param newValue The value which is being assigned to the parameter. 
	 * The implementation may return a different replacement value if this value is not acceptable.
	 * @return newValue The value which should be assigned to the parameter. If no modification is necessary, then newValue should be returned.
	 * If the value cannot be cast to the type of the parameter, then newValue will be used instead of the return value and the program will print a nasty message.
	 */
	public Object parameterModification(PropertyImmutable<?> param, X oldValue, X newValue);
}
