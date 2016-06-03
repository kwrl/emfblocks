/**
 */
package no.hal.emfblocks.layout;

import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.palette.Palette;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getRepresentationsByObject <em>Representations By Object</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getMappingModel <em>Mapping Model</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getPalette <em>Palette</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabPaneIndex <em>Focused Tab Pane Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabIndex <em>Focused Tab Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.ResourceLayout#getShowPointerArrows <em>Show Pointer Arrows</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout()
 * @model
 * @generated
 */
public interface ResourceLayout extends EObject {
	/**
	 * Returns the value of the '<em><b>Representations By Object</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EObject},
	 * and the value is of type {@link no.hal.emfblocks.layout.AbstractRepresentation},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representations By Object</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Representations By Object</em>' map.
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_RepresentationsByObject()
	 * @model mapType="no.hal.emfblocks.layout.EObjectToJigsawRepresentation<org.eclipse.emf.ecore.EObject, no.hal.emfblocks.layout.AbstractRepresentation>"
	 * @generated
	 */
	EMap<EObject, AbstractRepresentation> getRepresentationsByObject();

	/**
	 * Returns the value of the '<em><b>Mapping Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapping Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapping Model</em>' reference.
	 * @see #setMappingModel(MappingModel)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_MappingModel()
	 * @model required="true"
	 * @generated
	 */
	MappingModel getMappingModel();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.ResourceLayout#getMappingModel <em>Mapping Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapping Model</em>' reference.
	 * @see #getMappingModel()
	 * @generated
	 */
	void setMappingModel(MappingModel value);

	/**
	 * Returns the value of the '<em><b>Palette</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Palette</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Palette</em>' reference.
	 * @see #setPalette(Palette)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_Palette()
	 * @model required="true"
	 * @generated
	 */
	Palette getPalette();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.ResourceLayout#getPalette <em>Palette</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Palette</em>' reference.
	 * @see #getPalette()
	 * @generated
	 */
	void setPalette(Palette value);

	/**
	 * Returns the value of the '<em><b>Focused Tab Pane Index</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Focused Tab Pane Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Focused Tab Pane Index</em>' attribute.
	 * @see #setFocusedTabPaneIndex(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_FocusedTabPaneIndex()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getFocusedTabPaneIndex();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabPaneIndex <em>Focused Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Focused Tab Pane Index</em>' attribute.
	 * @see #getFocusedTabPaneIndex()
	 * @generated
	 */
	void setFocusedTabPaneIndex(int value);

	/**
	 * Returns the value of the '<em><b>Focused Tab Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Focused Tab Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Focused Tab Index</em>' attribute.
	 * @see #setFocusedTabIndex(int)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_FocusedTabIndex()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getFocusedTabIndex();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabIndex <em>Focused Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Focused Tab Index</em>' attribute.
	 * @see #getFocusedTabIndex()
	 * @generated
	 */
	void setFocusedTabIndex(int value);

	/**
	 * Returns the value of the '<em><b>Show Pointer Arrows</b></em>' attribute.
	 * The default value is <code>"Always"</code>.
	 * The literals are from the enumeration {@link no.hal.emfblocks.layout.PointerArrowMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show Pointer Arrows</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Show Pointer Arrows</em>' attribute.
	 * @see no.hal.emfblocks.layout.PointerArrowMode
	 * @see #setShowPointerArrows(PointerArrowMode)
	 * @see no.hal.emfblocks.layout.LayoutPackage#getResourceLayout_ShowPointerArrows()
	 * @model default="Always"
	 * @generated
	 */
	PointerArrowMode getShowPointerArrows();

	/**
	 * Sets the value of the '{@link no.hal.emfblocks.layout.ResourceLayout#getShowPointerArrows <em>Show Pointer Arrows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Pointer Arrows</em>' attribute.
	 * @see no.hal.emfblocks.layout.PointerArrowMode
	 * @see #getShowPointerArrows()
	 * @generated
	 */
	void setShowPointerArrows(PointerArrowMode value);

} // ResourceLayout
