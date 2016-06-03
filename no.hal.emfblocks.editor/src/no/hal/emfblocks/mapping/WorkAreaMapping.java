/**
 */
package no.hal.emfblocks.mapping;

import javafx.scene.paint.Color;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Area Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaMapping#getDefaultColor <em>Default Color</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaMapping#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaMapping#getNameInfo <em>Name Info</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaMapping#getContainmentsByType <em>Containments By Type</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.WorkAreaMapping#getFormsByAttribute <em>Forms By Attribute</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping()
 * @model
 * @generated
 */
public interface WorkAreaMapping extends ObjectMapping {
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping_Enabled()
	 * @model default="true" required="true" derived="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaMapping#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Name Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Info</em>' attribute.
	 * @see #setNameInfo(String)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping_NameInfo()
	 * @model
	 * @generated
	 */
	String getNameInfo();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getNameInfo <em>Name Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Info</em>' attribute.
	 * @see #getNameInfo()
	 * @generated
	 */
	void setNameInfo(String value);

	/**
	 * Returns the value of the '<em><b>Default Color</b></em>' attribute.
	 * The default value is <code>"C0C0C0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Color</em>' attribute.
	 * @see #setDefaultColor(Color)
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping_DefaultColor()
	 * @model default="C0C0C0" dataType="no.hal.emfblocks.mapping.EColor" required="true"
	 * @generated
	 */
	Color getDefaultColor();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getDefaultColor <em>Default Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Color</em>' attribute.
	 * @see #getDefaultColor()
	 * @generated
	 */
	void setDefaultColor(Color value);

	/**
	 * Returns the value of the '<em><b>Containments By Type</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link no.hal.emfblocks.mapping.WorkAreaContainment},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containments By Type</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containments By Type</em>' map.
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping_ContainmentsByType()
	 * @model mapType="no.hal.emfblocks.mapping.StringToWorkAreaContainment<org.eclipse.emf.ecore.EString, no.hal.emfblocks.mapping.WorkAreaContainment>"
	 * @generated
	 */
	EMap<String, WorkAreaContainment> getContainmentsByType();

	/**
	 * Returns the value of the '<em><b>Forms By Attribute</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link no.hal.emfblocks.mapping.WorkAreaFormMapping},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms By Attribute</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms By Attribute</em>' map.
	 * @see no.hal.emfblocks.mapping.MappingPackage#getWorkAreaMapping_FormsByAttribute()
	 * @model mapType="no.hal.emfblocks.mapping.StringToWorkAreaFormMapping<org.eclipse.emf.ecore.EString, no.hal.emfblocks.mapping.WorkAreaFormMapping>"
	 * @generated
	 */
	EMap<String, WorkAreaFormMapping> getFormsByAttribute();

} // WorkAreaMapping
