/**
 */
package no.hal.emfblocks.palette;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette Item Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.palette.PaletteItemContainer#getObject <em>Object</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.palette.PalettePackage#getPaletteItemContainer()
 * @model
 * @generated
 */
public interface PaletteItemContainer extends PaletteItem {
	/**
	 * Returns the value of the '<em><b>Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' containment reference.
	 * @see #setObject(EObject)
	 * @see no.hal.emfblocks.palette.PalettePackage#getPaletteItemContainer_Object()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EObject getObject();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.palette.PaletteItemContainer#getObject <em>Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' containment reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(EObject value);

} // PaletteItemContainer
