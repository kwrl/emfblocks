/**
 */
package no.hal.emfblocks.layout.impl;

import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.LayoutFactory;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.AbstractRepresentation;
import no.hal.emfblocks.layout.PointerArrowMode;
import no.hal.emfblocks.layout.ResourceLayout;
import no.hal.emfblocks.layout.WorkAreaRepresentation;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.palette.PalettePackage;
import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutPackageImpl extends EPackageImpl implements LayoutPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eObjectToJigsawRepresentationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractRepresentationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workAreaRepresentationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockRepresentationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pointerArrowModeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see no.hal.emfblocks.layout.LayoutPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LayoutPackageImpl() {
		super(eNS_URI, LayoutFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link LayoutPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LayoutPackage init() {
		if (isInited) return (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);

		// Obtain or create and register package
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LayoutPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MappingPackage.eINSTANCE.eClass();
		PalettePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theLayoutPackage.createPackageContents();

		// Initialize created meta-data
		theLayoutPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLayoutPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LayoutPackage.eNS_URI, theLayoutPackage);
		return theLayoutPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceLayout() {
		return resourceLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceLayout_RepresentationsByObject() {
		return (EReference)resourceLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceLayout_MappingModel() {
		return (EReference)resourceLayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceLayout_Palette() {
		return (EReference)resourceLayoutEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceLayout_FocusedTabPaneIndex() {
		return (EAttribute)resourceLayoutEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceLayout_FocusedTabIndex() {
		return (EAttribute)resourceLayoutEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceLayout_ShowPointerArrows() {
		return (EAttribute)resourceLayoutEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEObjectToJigsawRepresentation() {
		return eObjectToJigsawRepresentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEObjectToJigsawRepresentation_Key() {
		return (EReference)eObjectToJigsawRepresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEObjectToJigsawRepresentation_Value() {
		return (EReference)eObjectToJigsawRepresentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractRepresentation() {
		return abstractRepresentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractRepresentation_Object() {
		return (EReference)abstractRepresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkAreaRepresentation() {
		return workAreaRepresentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaRepresentation_PreferredTabPaneIndex() {
		return (EAttribute)workAreaRepresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaRepresentation_PreferredTabIndex() {
		return (EAttribute)workAreaRepresentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockRepresentation() {
		return blockRepresentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockRepresentation_PreferredX() {
		return (EAttribute)blockRepresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockRepresentation_PreferredY() {
		return (EAttribute)blockRepresentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlockRepresentation_PreferredContainer() {
		return (EReference)blockRepresentationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPointerArrowMode() {
		return pointerArrowModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutFactory getLayoutFactory() {
		return (LayoutFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		resourceLayoutEClass = createEClass(RESOURCE_LAYOUT);
		createEReference(resourceLayoutEClass, RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT);
		createEReference(resourceLayoutEClass, RESOURCE_LAYOUT__MAPPING_MODEL);
		createEReference(resourceLayoutEClass, RESOURCE_LAYOUT__PALETTE);
		createEAttribute(resourceLayoutEClass, RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX);
		createEAttribute(resourceLayoutEClass, RESOURCE_LAYOUT__FOCUSED_TAB_INDEX);
		createEAttribute(resourceLayoutEClass, RESOURCE_LAYOUT__SHOW_POINTER_ARROWS);

		eObjectToJigsawRepresentationEClass = createEClass(EOBJECT_TO_JIGSAW_REPRESENTATION);
		createEReference(eObjectToJigsawRepresentationEClass, EOBJECT_TO_JIGSAW_REPRESENTATION__KEY);
		createEReference(eObjectToJigsawRepresentationEClass, EOBJECT_TO_JIGSAW_REPRESENTATION__VALUE);

		abstractRepresentationEClass = createEClass(ABSTRACT_REPRESENTATION);
		createEReference(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__OBJECT);

		workAreaRepresentationEClass = createEClass(WORK_AREA_REPRESENTATION);
		createEAttribute(workAreaRepresentationEClass, WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX);
		createEAttribute(workAreaRepresentationEClass, WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX);

		blockRepresentationEClass = createEClass(BLOCK_REPRESENTATION);
		createEAttribute(blockRepresentationEClass, BLOCK_REPRESENTATION__PREFERRED_X);
		createEAttribute(blockRepresentationEClass, BLOCK_REPRESENTATION__PREFERRED_Y);
		createEReference(blockRepresentationEClass, BLOCK_REPRESENTATION__PREFERRED_CONTAINER);

		// Create enums
		pointerArrowModeEEnum = createEEnum(POINTER_ARROW_MODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		MappingPackage theMappingPackage = (MappingPackage)EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI);
		PalettePackage thePalettePackage = (PalettePackage)EPackage.Registry.INSTANCE.getEPackage(PalettePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		workAreaRepresentationEClass.getESuperTypes().add(this.getAbstractRepresentation());
		blockRepresentationEClass.getESuperTypes().add(this.getAbstractRepresentation());

		// Initialize classes, features, and operations; add parameters
		initEClass(resourceLayoutEClass, ResourceLayout.class, "ResourceLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceLayout_RepresentationsByObject(), this.getEObjectToJigsawRepresentation(), null, "representationsByObject", null, 0, -1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceLayout_MappingModel(), theMappingPackage.getMappingModel(), null, "mappingModel", null, 1, 1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceLayout_Palette(), thePalettePackage.getPalette(), null, "palette", null, 1, 1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceLayout_FocusedTabPaneIndex(), ecorePackage.getEInt(), "focusedTabPaneIndex", "0", 1, 1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceLayout_FocusedTabIndex(), ecorePackage.getEInt(), "focusedTabIndex", "-1", 1, 1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceLayout_ShowPointerArrows(), this.getPointerArrowMode(), "showPointerArrows", "Always", 0, 1, ResourceLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eObjectToJigsawRepresentationEClass, Map.Entry.class, "EObjectToJigsawRepresentation", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEObjectToJigsawRepresentation_Key(), ecorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEObjectToJigsawRepresentation_Value(), this.getAbstractRepresentation(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractRepresentationEClass, AbstractRepresentation.class, "AbstractRepresentation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractRepresentation_Object(), ecorePackage.getEObject(), null, "object", null, 1, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workAreaRepresentationEClass, WorkAreaRepresentation.class, "WorkAreaRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkAreaRepresentation_PreferredTabPaneIndex(), ecorePackage.getEInt(), "preferredTabPaneIndex", "-1", 1, 1, WorkAreaRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaRepresentation_PreferredTabIndex(), ecorePackage.getEInt(), "preferredTabIndex", "-1", 1, 1, WorkAreaRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockRepresentationEClass, BlockRepresentation.class, "BlockRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlockRepresentation_PreferredX(), ecorePackage.getEInt(), "preferredX", "0", 1, 1, BlockRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockRepresentation_PreferredY(), ecorePackage.getEInt(), "preferredY", "0", 1, 1, BlockRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockRepresentation_PreferredContainer(), this.getWorkAreaRepresentation(), null, "preferredContainer", null, 0, 1, BlockRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(pointerArrowModeEEnum, PointerArrowMode.class, "PointerArrowMode");
		addEEnumLiteral(pointerArrowModeEEnum, PointerArrowMode.NEVER);
		addEEnumLiteral(pointerArrowModeEEnum, PointerArrowMode.WHEN_FOCUSED);
		addEEnumLiteral(pointerArrowModeEEnum, PointerArrowMode.ALWAYS);

		// Create resource
		createResource(eNS_URI);
	}

} //EmfjigsawlayoutPackageImpl
