/**
 */
package no.hal.emfblocks.mapping;

import javafx.scene.paint.Color;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.BlockMapping#getDefaultColor <em>Default Color</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockMapping#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockMapping#getNameInfo <em>Name Info</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockMapping#getFormsByAttribute <em>Forms By Attribute</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.BlockMapping#getSlotsByReference <em>Slots By Reference</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping()
 * @model
 * @generated
 */
public interface BlockMapping extends ObjectMapping {
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping_Enabled()
	 * @model default="true" required="true" derived="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockMapping#isEnabled <em>Enabled</em>}' attribute.
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping_NameInfo()
	 * @model
	 * @generated
	 */
	String getNameInfo();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockMapping#getNameInfo <em>Name Info</em>}' attribute.
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
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping_DefaultColor()
	 * @model default="C0C0C0" dataType="no.hal.emfblocks.mapping.EColor" required="true"
	 * @generated
	 */
	Color getDefaultColor();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.mapping.BlockMapping#getDefaultColor <em>Default Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Color</em>' attribute.
	 * @see #getDefaultColor()
	 * @generated
	 */
	void setDefaultColor(Color value);

	/**
	 * Returns the value of the '<em><b>Forms By Attribute</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link no.hal.emfblocks.mapping.BlockFormMapping},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms By Attribute</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms By Attribute</em>' map.
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping_FormsByAttribute()
	 * @model mapType="no.hal.emfblocks.mapping.StringToBlockFormMapping<org.eclipse.emf.ecore.EString, no.hal.emfblocks.mapping.BlockFormMapping>"
	 * @generated
	 */
	EMap<String, BlockFormMapping> getFormsByAttribute();

	/**
	 * Returns the value of the '<em><b>Slots By Reference</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link no.hal.emfblocks.mapping.SlotMapping},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slots By Reference</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slots By Reference</em>' map.
	 * @see no.hal.emfblocks.mapping.MappingPackage#getBlockMapping_SlotsByReference()
	 * @model mapType="no.hal.emfblocks.mapping.StringToSlotMapping<org.eclipse.emf.ecore.EString, no.hal.emfblocks.mapping.SlotMapping>"
	 * @generated
	 */
	EMap<String, SlotMapping> getSlotsByReference();

} // BlockMapping
