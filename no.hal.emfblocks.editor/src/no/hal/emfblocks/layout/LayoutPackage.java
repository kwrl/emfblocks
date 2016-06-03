/**
 */
package no.hal.emfblocks.layout;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see no.hal.emfblocks.layout.LayoutFactory
 * @model kind="package"
 * @generated
 */
public interface LayoutPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "layout";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/no.hal.emfblocks.editor/model/layout.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "layout";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LayoutPackage eINSTANCE = no.hal.emfblocks.layout.impl.LayoutPackageImpl.init();

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl <em>Resource Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.impl.ResourceLayoutImpl
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getResourceLayout()
	 * @generated
	 */
	int RESOURCE_LAYOUT = 0;

	/**
	 * The feature id for the '<em><b>Representations By Object</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Mapping Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__MAPPING_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Palette</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__PALETTE = 2;

	/**
	 * The feature id for the '<em><b>Focused Tab Pane Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX = 3;

	/**
	 * The feature id for the '<em><b>Focused Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__FOCUSED_TAB_INDEX = 4;

	/**
	 * The feature id for the '<em><b>Show Pointer Arrows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT__SHOW_POINTER_ARROWS = 5;

	/**
	 * The number of structural features of the '<em>Resource Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Resource Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LAYOUT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.impl.EObjectToJigsawRepresentationImpl <em>EObject To Jigsaw Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.impl.EObjectToJigsawRepresentationImpl
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getEObjectToJigsawRepresentation()
	 * @generated
	 */
	int EOBJECT_TO_JIGSAW_REPRESENTATION = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_TO_JIGSAW_REPRESENTATION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_TO_JIGSAW_REPRESENTATION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EObject To Jigsaw Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_TO_JIGSAW_REPRESENTATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EObject To Jigsaw Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_TO_JIGSAW_REPRESENTATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.impl.AbstractRepresentationImpl <em>Abstract Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.impl.AbstractRepresentationImpl
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getAbstractRepresentation()
	 * @generated
	 */
	int ABSTRACT_REPRESENTATION = 2;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REPRESENTATION__OBJECT = 0;

	/**
	 * The number of structural features of the '<em>Abstract Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REPRESENTATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Abstract Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REPRESENTATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl <em>Work Area Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getWorkAreaRepresentation()
	 * @generated
	 */
	int WORK_AREA_REPRESENTATION = 3;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_REPRESENTATION__OBJECT = ABSTRACT_REPRESENTATION__OBJECT;

	/**
	 * The feature id for the '<em><b>Preferred Tab Pane Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Work Area Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_REPRESENTATION_FEATURE_COUNT = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Work Area Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_REPRESENTATION_OPERATION_COUNT = ABSTRACT_REPRESENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.impl.BlockRepresentationImpl <em>Block Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.impl.BlockRepresentationImpl
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getBlockRepresentation()
	 * @generated
	 */
	int BLOCK_REPRESENTATION = 4;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION__OBJECT = ABSTRACT_REPRESENTATION__OBJECT;

	/**
	 * The feature id for the '<em><b>Preferred X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION__PREFERRED_X = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION__PREFERRED_Y = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Preferred Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION__PREFERRED_CONTAINER = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Block Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION_FEATURE_COUNT = ABSTRACT_REPRESENTATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Block Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_REPRESENTATION_OPERATION_COUNT = ABSTRACT_REPRESENTATION_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link no.hal.emfblocks.layout.PointerArrowMode <em>Pointer Arrow Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.layout.PointerArrowMode
	 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getPointerArrowMode()
	 * @generated
	 */
	int POINTER_ARROW_MODE = 5;


	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.layout.ResourceLayout <em>Resource Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Layout</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout
	 * @generated
	 */
	EClass getResourceLayout();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.layout.ResourceLayout#getRepresentationsByObject <em>Representations By Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Representations By Object</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getRepresentationsByObject()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EReference getResourceLayout_RepresentationsByObject();

	/**
	 * Returns the meta object for the reference '{@link no.hal.emfblocks.layout.ResourceLayout#getMappingModel <em>Mapping Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mapping Model</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getMappingModel()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EReference getResourceLayout_MappingModel();

	/**
	 * Returns the meta object for the reference '{@link no.hal.emfblocks.layout.ResourceLayout#getPalette <em>Palette</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Palette</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getPalette()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EReference getResourceLayout_Palette();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabPaneIndex <em>Focused Tab Pane Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Focused Tab Pane Index</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getFocusedTabPaneIndex()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EAttribute getResourceLayout_FocusedTabPaneIndex();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.ResourceLayout#getFocusedTabIndex <em>Focused Tab Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Focused Tab Index</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getFocusedTabIndex()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EAttribute getResourceLayout_FocusedTabIndex();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.ResourceLayout#getShowPointerArrows <em>Show Pointer Arrows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Pointer Arrows</em>'.
	 * @see no.hal.emfblocks.layout.ResourceLayout#getShowPointerArrows()
	 * @see #getResourceLayout()
	 * @generated
	 */
	EAttribute getResourceLayout_ShowPointerArrows();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EObject To Jigsaw Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject To Jigsaw Representation</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
	 *        valueType="no.hal.emfblocks.layout.AbstractRepresentation" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getEObjectToJigsawRepresentation();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEObjectToJigsawRepresentation()
	 * @generated
	 */
	EReference getEObjectToJigsawRepresentation_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEObjectToJigsawRepresentation()
	 * @generated
	 */
	EReference getEObjectToJigsawRepresentation_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.layout.AbstractRepresentation <em>Abstract Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Representation</em>'.
	 * @see no.hal.emfblocks.layout.AbstractRepresentation
	 * @generated
	 */
	EClass getAbstractRepresentation();

	/**
	 * Returns the meta object for the reference '{@link no.hal.emfblocks.layout.AbstractRepresentation#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object</em>'.
	 * @see no.hal.emfblocks.layout.AbstractRepresentation#getObject()
	 * @see #getAbstractRepresentation()
	 * @generated
	 */
	EReference getAbstractRepresentation_Object();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.layout.WorkAreaRepresentation <em>Work Area Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Area Representation</em>'.
	 * @see no.hal.emfblocks.layout.WorkAreaRepresentation
	 * @generated
	 */
	EClass getWorkAreaRepresentation();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabPaneIndex <em>Preferred Tab Pane Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Tab Pane Index</em>'.
	 * @see no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabPaneIndex()
	 * @see #getWorkAreaRepresentation()
	 * @generated
	 */
	EAttribute getWorkAreaRepresentation_PreferredTabPaneIndex();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabIndex <em>Preferred Tab Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Tab Index</em>'.
	 * @see no.hal.emfblocks.layout.WorkAreaRepresentation#getPreferredTabIndex()
	 * @see #getWorkAreaRepresentation()
	 * @generated
	 */
	EAttribute getWorkAreaRepresentation_PreferredTabIndex();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.layout.BlockRepresentation <em>Block Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Representation</em>'.
	 * @see no.hal.emfblocks.layout.BlockRepresentation
	 * @generated
	 */
	EClass getBlockRepresentation();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredX <em>Preferred X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred X</em>'.
	 * @see no.hal.emfblocks.layout.BlockRepresentation#getPreferredX()
	 * @see #getBlockRepresentation()
	 * @generated
	 */
	EAttribute getBlockRepresentation_PreferredX();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredY <em>Preferred Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Y</em>'.
	 * @see no.hal.emfblocks.layout.BlockRepresentation#getPreferredY()
	 * @see #getBlockRepresentation()
	 * @generated
	 */
	EAttribute getBlockRepresentation_PreferredY();

	/**
	 * Returns the meta object for the reference '{@link no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer <em>Preferred Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Preferred Container</em>'.
	 * @see no.hal.emfblocks.layout.BlockRepresentation#getPreferredContainer()
	 * @see #getBlockRepresentation()
	 * @generated
	 */
	EReference getBlockRepresentation_PreferredContainer();

	/**
	 * Returns the meta object for enum '{@link no.hal.emfblocks.layout.PointerArrowMode <em>Pointer Arrow Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pointer Arrow Mode</em>'.
	 * @see no.hal.emfblocks.layout.PointerArrowMode
	 * @generated
	 */
	EEnum getPointerArrowMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LayoutFactory getLayoutFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl <em>Resource Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.impl.ResourceLayoutImpl
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getResourceLayout()
		 * @generated
		 */
		EClass RESOURCE_LAYOUT = eINSTANCE.getResourceLayout();

		/**
		 * The meta object literal for the '<em><b>Representations By Object</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT = eINSTANCE.getResourceLayout_RepresentationsByObject();

		/**
		 * The meta object literal for the '<em><b>Mapping Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LAYOUT__MAPPING_MODEL = eINSTANCE.getResourceLayout_MappingModel();

		/**
		 * The meta object literal for the '<em><b>Palette</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LAYOUT__PALETTE = eINSTANCE.getResourceLayout_Palette();

		/**
		 * The meta object literal for the '<em><b>Focused Tab Pane Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX = eINSTANCE.getResourceLayout_FocusedTabPaneIndex();

		/**
		 * The meta object literal for the '<em><b>Focused Tab Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_LAYOUT__FOCUSED_TAB_INDEX = eINSTANCE.getResourceLayout_FocusedTabIndex();

		/**
		 * The meta object literal for the '<em><b>Show Pointer Arrows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_LAYOUT__SHOW_POINTER_ARROWS = eINSTANCE.getResourceLayout_ShowPointerArrows();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.impl.EObjectToJigsawRepresentationImpl <em>EObject To Jigsaw Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.impl.EObjectToJigsawRepresentationImpl
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getEObjectToJigsawRepresentation()
		 * @generated
		 */
		EClass EOBJECT_TO_JIGSAW_REPRESENTATION = eINSTANCE.getEObjectToJigsawRepresentation();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_TO_JIGSAW_REPRESENTATION__KEY = eINSTANCE.getEObjectToJigsawRepresentation_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_TO_JIGSAW_REPRESENTATION__VALUE = eINSTANCE.getEObjectToJigsawRepresentation_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.impl.AbstractRepresentationImpl <em>Abstract Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.impl.AbstractRepresentationImpl
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getAbstractRepresentation()
		 * @generated
		 */
		EClass ABSTRACT_REPRESENTATION = eINSTANCE.getAbstractRepresentation();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_REPRESENTATION__OBJECT = eINSTANCE.getAbstractRepresentation_Object();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl <em>Work Area Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getWorkAreaRepresentation()
		 * @generated
		 */
		EClass WORK_AREA_REPRESENTATION = eINSTANCE.getWorkAreaRepresentation();

		/**
		 * The meta object literal for the '<em><b>Preferred Tab Pane Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX = eINSTANCE.getWorkAreaRepresentation_PreferredTabPaneIndex();

		/**
		 * The meta object literal for the '<em><b>Preferred Tab Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX = eINSTANCE.getWorkAreaRepresentation_PreferredTabIndex();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.impl.BlockRepresentationImpl <em>Block Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.impl.BlockRepresentationImpl
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getBlockRepresentation()
		 * @generated
		 */
		EClass BLOCK_REPRESENTATION = eINSTANCE.getBlockRepresentation();

		/**
		 * The meta object literal for the '<em><b>Preferred X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_REPRESENTATION__PREFERRED_X = eINSTANCE.getBlockRepresentation_PreferredX();

		/**
		 * The meta object literal for the '<em><b>Preferred Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_REPRESENTATION__PREFERRED_Y = eINSTANCE.getBlockRepresentation_PreferredY();

		/**
		 * The meta object literal for the '<em><b>Preferred Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_REPRESENTATION__PREFERRED_CONTAINER = eINSTANCE.getBlockRepresentation_PreferredContainer();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.layout.PointerArrowMode <em>Pointer Arrow Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.layout.PointerArrowMode
		 * @see no.hal.emfblocks.layout.impl.LayoutPackageImpl#getPointerArrowMode()
		 * @generated
		 */
		EEnum POINTER_ARROW_MODE = eINSTANCE.getPointerArrowMode();

	}

} //EmfjigsawlayoutPackage
