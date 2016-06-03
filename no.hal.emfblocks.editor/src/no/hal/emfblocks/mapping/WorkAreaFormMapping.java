/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Area Form Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridX <em>Grid X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridY <em>Grid Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridWidth <em>Grid Width</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridHeight <em>Grid Height</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping()
 * @model
 * @generated
 */
public interface WorkAreaFormMapping extends EObject {
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_Enabled()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Grid X</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid X</em>' attribute.
	 * @see #setGridX(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_GridX()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getGridX();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridX <em>Grid X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid X</em>' attribute.
	 * @see #getGridX()
	 * @generated
	 */
	void setGridX(int value);

	/**
	 * Returns the value of the '<em><b>Grid Y</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Y</em>' attribute.
	 * @see #setGridY(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_GridY()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getGridY();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridY <em>Grid Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Y</em>' attribute.
	 * @see #getGridY()
	 * @generated
	 */
	void setGridY(int value);

	/**
	 * Returns the value of the '<em><b>Grid Width</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Width</em>' attribute.
	 * @see #setGridWidth(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_GridWidth()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getGridWidth();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridWidth <em>Grid Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Width</em>' attribute.
	 * @see #getGridWidth()
	 * @generated
	 */
	void setGridWidth(int value);

	/**
	 * Returns the value of the '<em><b>Grid Height</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Height</em>' attribute.
	 * @see #setGridHeight(int)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_GridHeight()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getGridHeight();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridHeight <em>Grid Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Height</em>' attribute.
	 * @see #getGridHeight()
	 * @generated
	 */
	void setGridHeight(int value);

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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaFormMapping_AttributeFormFactoryClass()
	 * @model default="no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory" required="true"
	 * @generated
	 */
	String getAttributeFormFactoryClass();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Form Factory Class</em>' attribute.
	 * @see #getAttributeFormFactoryClass()
	 * @generated
	 */
	void setAttributeFormFactoryClass(String value);

} // WorkAreaFormMapping
