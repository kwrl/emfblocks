/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slot Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.SlotMapping#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.SlotMapping#isHorizontal <em>Horizontal</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.SlotMapping#isDominant <em>Dominant</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.SlotMapping#getCellX <em>Cell X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.SlotMapping#getCellY <em>Cell Y</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping()
 * @model
 * @generated
 */
public interface SlotMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping_Enabled()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.SlotMapping#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Horizontal</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Horizontal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Horizontal</em>' attribute.
	 * @see #setHorizontal(boolean)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping_Horizontal()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isHorizontal();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.SlotMapping#isHorizontal <em>Horizontal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Horizontal</em>' attribute.
	 * @see #isHorizontal()
	 * @generated
	 */
	void setHorizontal(boolean value);

	/**
	 * Returns the value of the '<em><b>Dominant</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dominant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dominant</em>' attribute.
	 * @see #setDominant(boolean)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping_Dominant()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isDominant();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.SlotMapping#isDominant <em>Dominant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dominant</em>' attribute.
	 * @see #isDominant()
	 * @generated
	 */
	void setDominant(boolean value);

	/**
	 * Returns the value of the '<em><b>Cell X</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cell X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell X</em>' attribute.
	 * @see #setCellX(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping_CellX()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getCellX();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.SlotMapping#getCellX <em>Cell X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell X</em>' attribute.
	 * @see #getCellX()
	 * @generated
	 */
	void setCellX(int value);

	/**
	 * Returns the value of the '<em><b>Cell Y</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cell Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell Y</em>' attribute.
	 * @see #setCellY(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getSlotMapping_CellY()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getCellY();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.SlotMapping#getCellY <em>Cell Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell Y</em>' attribute.
	 * @see #getCellY()
	 * @generated
	 */
	void setCellY(int value);

} // SlotMapping
