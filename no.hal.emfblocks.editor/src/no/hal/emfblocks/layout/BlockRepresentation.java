/**
 */
package no.hal.emfblocks.layout;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Representation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredX <em>Preferred X</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredY <em>Preferred Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer <em>Preferred Container</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.layout.LayoutPackage#getBlockRepresentation()
 * @model
 * @generated
 */
public interface BlockRepresentation extends AbstractRepresentation {
	/**
	 * Returns the value of the '<em><b>Preferred X</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred X</em>' attribute.
	 * @see #isSetPreferredX()
	 * @see #unsetPreferredX()
	 * @see #setPreferredX(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getBlockRepresentation_PreferredX()
	 * @model default="0" unsettable="true" required="true"
	 * @generated
	 */
	int getPreferredX();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredX <em>Preferred X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred X</em>' attribute.
	 * @see #isSetPreferredX()
	 * @see #unsetPreferredX()
	 * @see #getPreferredX()
	 * @generated
	 */
	void setPreferredX(int value);

	/**
	 * Unsets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredX <em>Preferred X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPreferredX()
	 * @see #getPreferredX()
	 * @see #setPreferredX(int)
	 * @generated
	 */
	void unsetPreferredX();

	/**
	 * Returns whether the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredX <em>Preferred X</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Preferred X</em>' attribute is set.
	 * @see #unsetPreferredX()
	 * @see #getPreferredX()
	 * @see #setPreferredX(int)
	 * @generated
	 */
	boolean isSetPreferredX();

	/**
	 * Returns the value of the '<em><b>Preferred Y</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred Y</em>' attribute.
	 * @see #isSetPreferredY()
	 * @see #unsetPreferredY()
	 * @see #setPreferredY(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getBlockRepresentation_PreferredY()
	 * @model default="0" unsettable="true" required="true"
	 * @generated
	 */
	int getPreferredY();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredY <em>Preferred Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Y</em>' attribute.
	 * @see #isSetPreferredY()
	 * @see #unsetPreferredY()
	 * @see #getPreferredY()
	 * @generated
	 */
	void setPreferredY(int value);

	/**
	 * Unsets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredY <em>Preferred Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPreferredY()
	 * @see #getPreferredY()
	 * @see #setPreferredY(int)
	 * @generated
	 */
	void unsetPreferredY();

	/**
	 * Returns whether the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredY <em>Preferred Y</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Preferred Y</em>' attribute is set.
	 * @see #unsetPreferredY()
	 * @see #getPreferredY()
	 * @see #setPreferredY(int)
	 * @generated
	 */
	boolean isSetPreferredY();

	/**
	 * Returns the value of the '<em><b>Preferred Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred Container</em>' reference.
	 * @see #isSetPreferredContainer()
	 * @see #unsetPreferredContainer()
	 * @see #setPreferredContainer(WorkAreaRepresentation)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getBlockRepresentation_PreferredContainer()
	 * @model unsettable="true"
	 * @generated
	 */
	WorkAreaRepresentation getPreferredContainer();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer <em>Preferred Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Container</em>' reference.
	 * @see #isSetPreferredContainer()
	 * @see #unsetPreferredContainer()
	 * @see #getPreferredContainer()
	 * @generated
	 */
	void setPreferredContainer(WorkAreaRepresentation value);

	/**
	 * Unsets the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer <em>Preferred Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPreferredContainer()
	 * @see #getPreferredContainer()
	 * @see #setPreferredContainer(WorkAreaRepresentation)
	 * @generated
	 */
	void unsetPreferredContainer();

	/**
	 * Returns whether the value of the '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer <em>Preferred Container</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Preferred Container</em>' reference is set.
	 * @see #unsetPreferredContainer()
	 * @see #getPreferredContainer()
	 * @see #setPreferredContainer(WorkAreaRepresentation)
	 * @generated
	 */
	boolean isSetPreferredContainer();

} // BlockRepresentation
