package no.hal.emfblocks.util.data;

/**
 * A device that holds hard references to prevent objects from being garbage
 * collected. <br/>
 * <br/>
 * In essence, this object provides a solution to the <b>premature garbage
 * collection</b> problem that is introduced by using <b>weak listeners</b>.
 * Together with weak listeners, this object provides a complete solution to the
 * <b>dangling listener problem.</b> <br/>
 * <br/>
 * This object is intended to be used in conjunction with observables that store
 * weak references to their listeners, when the listeners are anonymous classes
 * or functions that aren't naturally referenced elsewhere. <br/>
 * <br/>
 * The Referencer should be stored as a field on the object that the listener is
 * semantically tied to. That object can then spawn listeners, and have
 * references to them artificially injected through the Referencer, giving the
 * listeners the same lifespan as that object.
 * 
 * @see ReferenceSet
 */
public interface Referencer {
	public void addReference(Object o);

	public void removeReference(Object o);
}
