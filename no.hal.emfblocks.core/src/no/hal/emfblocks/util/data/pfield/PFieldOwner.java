package no.hal.emfblocks.util.data.pfield;
/**Interface for objects that can be added to a {@link PField}. Provides optional methods related to its membership in the field.*/
public interface PFieldOwner<E extends PFieldElement>
{
    /**Called by the {@link PField} before attempting to add this element. If an {@link IllegalArgumentException} is thrown, then the operation is aborted.
     * The owner is the only object with a right to veto the operation, so if it does not throw an exception, 
     * then it is safe to assume that the operation will complete normally.
     * <pre>
     * If newChild is non-null, then it is being added at the given index. If the index is equal to the current size of the field, then the size of the field will grow.
     * If newChild is null, then the element at the given index is being removed, and the size of the field will shrink. 
     * </pre>*/
    public default void pFieldChange(PFieldImmutable<? extends E> field, E newChild, int index) throws IllegalArgumentException
    {
    	
    }
}
