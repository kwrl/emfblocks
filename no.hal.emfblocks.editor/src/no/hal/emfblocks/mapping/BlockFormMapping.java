/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Form Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.BlockFormMapping#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellX <em>Cell X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellY <em>Cell Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockFormMapping()
 * @model
 * @generated
 */
public interface BlockFormMapping extends EObject {
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockFormMapping_Enabled()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockFormMapping#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockFormMapping_CellX()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getCellX();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellX <em>Cell X</em>}' attribute.
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockFormMapping_CellY()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getCellY();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellY <em>Cell Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell Y</em>' attribute.
	 * @see #getCellY()
	 * @generated
	 */
	void setCellY(int value);

	/**
	 * Returns the value of the '<em><b>Attribute Form Factory Class</b></em>' attribute.
	 * The default value is <code>"no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Form Factory Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Form Factory Class</em>' attribute.
	 * @see #setAttributeFormFactoryClass(String)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockFormMapping_AttributeFormFactoryClass()
	 * @model default="no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory" required="true"
	 * @generated
	 */
	String getAttributeFormFactoryClass();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Form Factory Class</em>' attribute.
	 * @see #getAttributeFormFactoryClass()
	 * @generated
	 */
	void setAttributeFormFactoryClass(String value);

} // BlockFormMapping
