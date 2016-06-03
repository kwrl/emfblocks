package no.hal.emfblocks.util.data.pfield;
/**Interface for objects that can be added to a {@link PField}. Provides optional methods related to its membership in the field.
 * Note that since it is reasonable to expect PFieldElements to be added to multiple types of PFields, 
 * and that these relationships may be defined on different levels in the class hierarchy, this class does not have generic type information.
 * Implementing the interface would then prevent subclasses from implementing it with a different type, which is undesirable.*/
public interface PFieldElement
{	
    /**Called by the {@link PField} after this element is added.*/
    public default void addNotify(PFieldImmutable<?> newField)
    {
	//Nop
    }
    /**Called when the element has been removed from the {@link PField}.*/
    public default void removeNotify(PFieldImmutable<?> fromField)
    {
	//Nop
    }
}
