package no.hal.emfblocks.util.data.property;

public interface PropertyListener<T> {
	/**Called when the indicated parameter's value has changed from oldValue to newValue.
	 * It is illegal to modify the parameter from this method.*/
    public void valueChanged(PropertyImmutable<? extends T> property, T oldValue, T newValue);
}
