/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Area Containment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaContainment#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaContainment#getContainmentReference <em>Containment Reference</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaContainment()
 * @model
 * @generated
 */
public interface WorkAreaContainment extends EObject {
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaContainment_Enabled()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaContainment#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Containment Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containment Reference</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containment Reference</em>' attribute.
	 * @see #setContainmentReference(String)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaContainment_ContainmentReference()
	 * @model required="true"
	 * @generated
	 */
	String getContainmentReference();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaContainment#getContainmentReference <em>Containment Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containment Reference</em>' attribute.
	 * @see #getContainmentReference()
	 * @generated
	 */
	void setContainmentReference(String value);

} // WorkAreaContainment
