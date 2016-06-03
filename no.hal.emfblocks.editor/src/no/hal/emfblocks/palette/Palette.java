/**
 */
package no.hal.emfblocks.palette;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.palette.Palette#getTabs <em>Tabs</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.palette.PalettePackage#getPalette()
 * @model
 * @generated
 */
public interface Palette extends EObject {
	/**
	 * Returns the value of the '<em><b>Tabs</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.emfblocks.palette.PaletteTab}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tabs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tabs</em>' containment reference list.
	 * @see no.hal.emfblocks.palette.PalettePackage#getPalette_Tabs()
	 * @model containment="true"
	 * @generated
	 */
	EList<PaletteTab> getTabs();

} // Palette
