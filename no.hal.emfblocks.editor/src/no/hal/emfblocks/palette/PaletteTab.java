/**
 */
package no.hal.emfblocks.palette;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette Tab</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.palette.PaletteTab#getName <em>Name</em>}</li>
 *   <li>{@link no.hal.emfblocks.palette.PaletteTab#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.palette.PalettePackage#getPaletteTab()
 * @model
 * @generated
 */
public interface PaletteTab extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"Misc."</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see no.hal.emfblocks.palette.PalettePackage#getPaletteTab_Name()
	 * @model default="Misc."
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.palette.PaletteTab#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.emfblocks.palette.PaletteItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see no.hal.emfblocks.palette.PalettePackage#getPaletteTab_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<PaletteItem> getContents();

} // PaletteTab
