package no.hal.emfblocks.util.data.property;

import no.hal.emfblocks.util.data.Referencer;
/**
 * Interface into a {@link Property} where it is possible to read and add/remove listeners.
 */
public interface PropertyReadOnly<T> extends PropertyImmutable<T>
{	
	/**
	 * Listen to changes to this property.
	 * <br/><br/>	 
	 * <b>The reference to the listener will be weak and the listener is removed as soon as it is garbage collected.</b>
	 * Thus, it is normally not necessary to call {@link #removeListener(Referencer, TypedListener)}.
	 * <br/><br/>
	 * However, if the {@link PropertyListener#valueChanged(PropertyImmutable, Object, Object)} 
	 * event has side effects beyond the listener object, then it is safest if it explicitly removes itself.
	 * <br/><br/>
	 * If "parent" is not null, then the a reference to the listener is injected into the "parent" referencer.
	 * The purpose of this is to prevent garbage collection as long as the parent object is alive.
	 * If any reference to the listener is kept in another location, then "parent" should be null.
	 * <br/><br/>
	 * <font color = #FF0000>Warning: If parent is null and the listener is not referenced by another object, 
	 * then the listener may cease to exist at a nondeterministic point in the future.</font>
	 * @param parent A referencer object used to prevent the listener from being garbage collected as long as the referencer object exists. May be null.
	 * @param l The listener object. A listener may not be null, and it must not be added twice.
	 */
	public void addListener(Referencer parent, PropertyListener<? super T> l);
	
	/**Remove the indicated listener. Returns false if it has not been added yet. 
	 * Note that listeners will be removed automatically if they are garbage collected.
	 * If "parent" is not null, then the listener reference will be removed from it.*/
	public boolean removeListener(Referencer parent, PropertyListener<T> l);
}
