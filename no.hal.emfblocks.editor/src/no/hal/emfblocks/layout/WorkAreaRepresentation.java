/**
 */
package no.hal.emfblocks.layout;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Area Representation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabPaneIndex <em>Preferred Tab Pane Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabIndex <em>Preferred Tab Index</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.layout.LayoutPackage#getWorkAreaRepresentation()
 * @model
 * @generated
 */
public interface WorkAreaRepresentation extends AbstractRepresentation {
	/**
	 * Returns the value of the '<em><b>Preferred Tab Pane Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred Tab Pane Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred Tab Pane Index</em>' attribute.
	 * @see #setPreferredTabPaneIndex(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getWorkAreaRepresentation_PreferredTabPaneIndex()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getPreferredTabPaneIndex();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabPaneIndex <em>Preferred Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Tab Pane Index</em>' attribute.
	 * @see #getPreferredTabPaneIndex()
	 * @generated
	 */
	void setPreferredTabPaneIndex(int value);

	/**
	 * Returns the value of the '<em><b>Preferred Tab Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred Tab Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred Tab Index</em>' attribute.
	 * @see #setPreferredTabIndex(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getWorkAreaRepresentation_PreferredTabIndex()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getPreferredTabIndex();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabIndex <em>Preferred Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Tab Index</em>' attribute.
	 * @see #getPreferredTabIndex()
	 * @generated
	 */
	void setPreferredTabIndex(int value);

} // WorkAreaRepresentation
