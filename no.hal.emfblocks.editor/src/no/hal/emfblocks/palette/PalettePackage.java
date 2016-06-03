/**
 */
package no.hal.emfblocks.palette;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see no.hal.emfblocks.palette.PaletteFactory
 * @model kind="package"
 * @generated
 */
public interface PalettePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "palette";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/no.hal.emfblocks.editor/model/palette.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "palette";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PalettePackage eINSTANCE = no.hal.emfblocks.palette.impl.PalettePackageImpl.init();

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.palette.impl.PaletteTabImpl <em>Tab</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.palette.impl.PaletteTabImpl
	 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteTab()
	 * @generated
	 */
	int PALETTE_TAB = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_TAB__NAME = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_TAB__CONTENTS = 1;

	/**
	 * The number of structural features of the '<em>Tab</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_TAB_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tab</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_TAB_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.palette.impl.PaletteImpl <em>Palette</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.palette.impl.PaletteImpl
	 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPalette()
	 * @generated
	 */
	int PALETTE = 1;

	/**
	 * The feature id for the '<em><b>Tabs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE__TABS = 0;

	/**
	 * The number of structural features of the '<em>Palette</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Palette</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link no.hal.emfblocks.palette.impl.PaletteItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.palette.impl.PaletteItemImpl
	 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItem()
	 * @generated
	 */
	int PALETTE_ITEM = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM__ENABLED = 1;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Set Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM___SET_OBJECT__EOBJECT = 0;

	/**
	 * The operation id for the '<em>Get Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM___GET_OBJECT = 1;

	/**
	 * The number of operations of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_OPERATION_COUNT = 2;


	/**
	 * The meta object id for the '{@link no.hal.emfblocks.palette.impl.PaletteItemContainerImpl <em>Item Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.palette.impl.PaletteItemContainerImpl
	 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItemContainer()
	 * @generated
	 */
	int PALETTE_ITEM_CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER__NAME = PALETTE_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER__ENABLED = PALETTE_ITEM__ENABLED;

	/**
	 * The feature id for the '<em><b>Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER__OBJECT = PALETTE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Item Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER_FEATURE_COUNT = PALETTE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Set Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER___SET_OBJECT__EOBJECT = PALETTE_ITEM___SET_OBJECT__EOBJECT;

	/**
	 * The operation id for the '<em>Get Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER___GET_OBJECT = PALETTE_ITEM___GET_OBJECT;

	/**
	 * The number of operations of the '<em>Item Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_CONTAINER_OPERATION_COUNT = PALETTE_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.palette.impl.PaletteItemReferenceImpl <em>Item Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.palette.impl.PaletteItemReferenceImpl
	 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItemReference()
	 * @generated
	 */
	int PALETTE_ITEM_REFERENCE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE__NAME = PALETTE_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE__ENABLED = PALETTE_ITEM__ENABLED;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE__OBJECT = PALETTE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Item Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE_FEATURE_COUNT = PALETTE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Set Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE___SET_OBJECT__EOBJECT = PALETTE_ITEM___SET_OBJECT__EOBJECT;

	/**
	 * The operation id for the '<em>Get Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE___GET_OBJECT = PALETTE_ITEM___GET_OBJECT;

	/**
	 * The number of operations of the '<em>Item Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_ITEM_REFERENCE_OPERATION_COUNT = PALETTE_ITEM_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.palette.PaletteTab <em>Tab</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab</em>'.
	 * @see no.hal.emfblocks.palette.PaletteTab
	 * @generated
	 */
	EClass getPaletteTab();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.palette.PaletteTab#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.emfblocks.palette.PaletteTab#getName()
	 * @see #getPaletteTab()
	 * @generated
	 */
	EAttribute getPaletteTab_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.emfblocks.palette.PaletteTab#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see no.hal.emfblocks.palette.PaletteTab#getContents()
	 * @see #getPaletteTab()
	 * @generated
	 */
	EReference getPaletteTab_Contents();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.palette.Palette <em>Palette</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Palette</em>'.
	 * @see no.hal.emfblocks.palette.Palette
	 * @generated
	 */
	EClass getPalette();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.emfblocks.palette.Palette#getTabs <em>Tabs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tabs</em>'.
	 * @see no.hal.emfblocks.palette.Palette#getTabs()
	 * @see #getPalette()
	 * @generated
	 */
	EReference getPalette_Tabs();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.palette.PaletteItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItem
	 * @generated
	 */
	EClass getPaletteItem();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.palette.PaletteItem#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItem#isEnabled()
	 * @see #getPaletteItem()
	 * @generated
	 */
	EAttribute getPaletteItem_Enabled();

	/**
	 * Returns the meta object for the '{@link no.hal.emfblocks.palette.PaletteItem#setObject(org.eclipse.emf.ecore.EObject) <em>Set Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Object</em>' operation.
	 * @see no.hal.emfblocks.palette.PaletteItem#setObject(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	EOperation getPaletteItem__SetObject__EObject();

	/**
	 * Returns the meta object for the '{@link no.hal.emfblocks.palette.PaletteItem#getObject() <em>Get Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Object</em>' operation.
	 * @see no.hal.emfblocks.palette.PaletteItem#getObject()
	 * @generated
	 */
	EOperation getPaletteItem__GetObject();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.palette.PaletteItemContainer <em>Item Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item Container</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItemContainer
	 * @generated
	 */
	EClass getPaletteItemContainer();

	/**
	 * Returns the meta object for the containment reference '{@link no.hal.emfblocks.palette.PaletteItemContainer#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Object</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItemContainer#getObject()
	 * @see #getPaletteItemContainer()
	 * @generated
	 */
	EReference getPaletteItemContainer_Object();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.palette.PaletteItemReference <em>Item Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item Reference</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItemReference
	 * @generated
	 */
	EClass getPaletteItemReference();

	/**
	 * Returns the meta object for the reference '{@link no.hal.emfblocks.palette.PaletteItemReference#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItemReference#getObject()
	 * @see #getPaletteItemReference()
	 * @generated
	 */
	EReference getPaletteItemReference_Object();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PaletteFactory getPaletteFactory();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.palette.PaletteItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.emfblocks.palette.PaletteItem#getName()
	 * @see #getPaletteItem()
	 * @generated
	 */
	EAttribute getPaletteItem_Name();

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
		 * The meta object literal for the '{@link no.hal.emfblocks.palette.impl.PaletteTabImpl <em>Tab</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.palette.impl.PaletteTabImpl
		 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteTab()
		 * @generated
		 */
		EClass PALETTE_TAB = eINSTANCE.getPaletteTab();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_TAB__NAME = eINSTANCE.getPaletteTab_Name();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE_TAB__CONTENTS = eINSTANCE.getPaletteTab_Contents();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.palette.impl.PaletteImpl <em>Palette</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.palette.impl.PaletteImpl
		 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPalette()
		 * @generated
		 */
		EClass PALETTE = eINSTANCE.getPalette();

		/**
		 * The meta object literal for the '<em><b>Tabs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE__TABS = eINSTANCE.getPalette_Tabs();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.palette.impl.PaletteItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.palette.impl.PaletteItemImpl
		 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItem()
		 * @generated
		 */
		EClass PALETTE_ITEM = eINSTANCE.getPaletteItem();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_ITEM__ENABLED = eINSTANCE.getPaletteItem_Enabled();

		/**
		 * The meta object literal for the '<em><b>Set Object</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PALETTE_ITEM___SET_OBJECT__EOBJECT = eINSTANCE.getPaletteItem__SetObject__EObject();

		/**
		 * The meta object literal for the '<em><b>Get Object</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PALETTE_ITEM___GET_OBJECT = eINSTANCE.getPaletteItem__GetObject();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.palette.impl.PaletteItemContainerImpl <em>Item Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.palette.impl.PaletteItemContainerImpl
		 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItemContainer()
		 * @generated
		 */
		EClass PALETTE_ITEM_CONTAINER = eINSTANCE.getPaletteItemContainer();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE_ITEM_CONTAINER__OBJECT = eINSTANCE.getPaletteItemContainer_Object();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.palette.impl.PaletteItemReferenceImpl <em>Item Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.palette.impl.PaletteItemReferenceImpl
		 * @see no.hal.emfblocks.palette.impl.PalettePackageImpl#getPaletteItemReference()
		 * @generated
		 */
		EClass PALETTE_ITEM_REFERENCE = eINSTANCE.getPaletteItemReference();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE_ITEM_REFERENCE__OBJECT = eINSTANCE.getPaletteItemReference_Object();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_ITEM__NAME = eINSTANCE.getPaletteItem_Name();

	}

} //EmfjigsawpalettePackage
