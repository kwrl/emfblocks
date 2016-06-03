/**
 */
package no.hal.emfblocks.mapping.impl;

import no.hal.emfblocks.mapping.BlockFormMapping;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.MappingFactory;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.mapping.ObjectMapping;
import no.hal.emfblocks.mapping.NoMapping;
import no.hal.emfblocks.mapping.SlotMapping;
import no.hal.emfblocks.mapping.WorkAreaContainment;
import no.hal.emfblocks.mapping.WorkAreaFormMapping;
import no.hal.emfblocks.mapping.WorkAreaMapping;
import java.util.Map;

import javafx.scene.paint.Color;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MappingPackageImpl extends EPackageImpl implements MappingPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToObjectMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToBlockFormMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockFormMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToSlotMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass slotMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workAreaMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToWorkAreaContainmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workAreaContainmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToWorkAreaFormMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workAreaFormMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eColorEDataType = null;

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
	 * @see no.hal.emfblocks.mapping.MappingPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MappingPackageImpl() {
		super(eNS_URI, MappingFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link MappingPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MappingPackage init() {
		if (isInited) return (MappingPackage)EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI);

		// Obtain or create and register package
		MappingPackageImpl theMappingPackage = (MappingPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MappingPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MappingPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theMappingPackage.createPackageContents();

		// Initialize created meta-data
		theMappingPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMappingPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MappingPackage.eNS_URI, theMappingPackage);
		return theMappingPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMappingModel() {
		return mappingModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMappingModel_ObjectMappingsByType() {
		return (EReference)mappingModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToObjectMapping() {
		return stringToObjectMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToObjectMapping_Key() {
		return (EAttribute)stringToObjectMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToObjectMapping_Value() {
		return (EReference)stringToObjectMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectMapping() {
		return objectMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getObjectMapping__IsEnabled() {
		return objectMappingEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getObjectMapping__GetNameInfo() {
		return objectMappingEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNoMapping() {
		return noMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockMapping() {
		return blockMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockMapping_Enabled() {
		return (EAttribute)blockMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockMapping_NameInfo() {
		return (EAttribute)blockMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockMapping_DefaultColor() {
		return (EAttribute)blockMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlockMapping_FormsByAttribute() {
		return (EReference)blockMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlockMapping_SlotsByReference() {
		return (EReference)blockMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToBlockFormMapping() {
		return stringToBlockFormMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToBlockFormMapping_Key() {
		return (EAttribute)stringToBlockFormMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToBlockFormMapping_Value() {
		return (EReference)stringToBlockFormMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockFormMapping() {
		return blockFormMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockFormMapping_Enabled() {
		return (EAttribute)blockFormMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockFormMapping_CellX() {
		return (EAttribute)blockFormMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockFormMapping_CellY() {
		return (EAttribute)blockFormMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockFormMapping_AttributeFormFactoryClass() {
		return (EAttribute)blockFormMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToSlotMapping() {
		return stringToSlotMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToSlotMapping_Key() {
		return (EAttribute)stringToSlotMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToSlotMapping_Value() {
		return (EReference)stringToSlotMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSlotMapping() {
		return slotMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSlotMapping_Enabled() {
		return (EAttribute)slotMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSlotMapping_Horizontal() {
		return (EAttribute)slotMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSlotMapping_Dominant() {
		return (EAttribute)slotMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSlotMapping_CellX() {
		return (EAttribute)slotMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSlotMapping_CellY() {
		return (EAttribute)slotMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkAreaMapping() {
		return workAreaMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaMapping_Enabled() {
		return (EAttribute)workAreaMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaMapping_NameInfo() {
		return (EAttribute)workAreaMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaMapping_DefaultColor() {
		return (EAttribute)workAreaMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkAreaMapping_ContainmentsByType() {
		return (EReference)workAreaMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkAreaMapping_FormsByAttribute() {
		return (EReference)workAreaMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToWorkAreaContainment() {
		return stringToWorkAreaContainmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToWorkAreaContainment_Key() {
		return (EAttribute)stringToWorkAreaContainmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToWorkAreaContainment_Value() {
		return (EReference)stringToWorkAreaContainmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkAreaContainment() {
		return workAreaContainmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaContainment_Enabled() {
		return (EAttribute)workAreaContainmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaContainment_ContainmentReference() {
		return (EAttribute)workAreaContainmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToWorkAreaFormMapping() {
		return stringToWorkAreaFormMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToWorkAreaFormMapping_Key() {
		return (EAttribute)stringToWorkAreaFormMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToWorkAreaFormMapping_Value() {
		return (EReference)stringToWorkAreaFormMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkAreaFormMapping() {
		return workAreaFormMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_Enabled() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_GridX() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_GridY() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_GridWidth() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_GridHeight() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkAreaFormMapping_AttributeFormFactoryClass() {
		return (EAttribute)workAreaFormMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEColor() {
		return eColorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingFactory getMappingFactory() {
		return (MappingFactory)getEFactoryInstance();
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
		mappingModelEClass = createEClass(MAPPING_MODEL);
		createEReference(mappingModelEClass, MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE);

		stringToObjectMappingEClass = createEClass(STRING_TO_OBJECT_MAPPING);
		createEAttribute(stringToObjectMappingEClass, STRING_TO_OBJECT_MAPPING__KEY);
		createEReference(stringToObjectMappingEClass, STRING_TO_OBJECT_MAPPING__VALUE);

		objectMappingEClass = createEClass(OBJECT_MAPPING);
		createEOperation(objectMappingEClass, OBJECT_MAPPING___IS_ENABLED);
		createEOperation(objectMappingEClass, OBJECT_MAPPING___GET_NAME_INFO);

		noMappingEClass = createEClass(NO_MAPPING);

		blockMappingEClass = createEClass(BLOCK_MAPPING);
		createEAttribute(blockMappingEClass, BLOCK_MAPPING__DEFAULT_COLOR);
		createEAttribute(blockMappingEClass, BLOCK_MAPPING__ENABLED);
		createEAttribute(blockMappingEClass, BLOCK_MAPPING__NAME_INFO);
		createEReference(blockMappingEClass, BLOCK_MAPPING__FORMS_BY_ATTRIBUTE);
		createEReference(blockMappingEClass, BLOCK_MAPPING__SLOTS_BY_REFERENCE);

		stringToBlockFormMappingEClass = createEClass(STRING_TO_BLOCK_FORM_MAPPING);
		createEAttribute(stringToBlockFormMappingEClass, STRING_TO_BLOCK_FORM_MAPPING__KEY);
		createEReference(stringToBlockFormMappingEClass, STRING_TO_BLOCK_FORM_MAPPING__VALUE);

		blockFormMappingEClass = createEClass(BLOCK_FORM_MAPPING);
		createEAttribute(blockFormMappingEClass, BLOCK_FORM_MAPPING__ENABLED);
		createEAttribute(blockFormMappingEClass, BLOCK_FORM_MAPPING__CELL_X);
		createEAttribute(blockFormMappingEClass, BLOCK_FORM_MAPPING__CELL_Y);
		createEAttribute(blockFormMappingEClass, BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS);

		stringToSlotMappingEClass = createEClass(STRING_TO_SLOT_MAPPING);
		createEAttribute(stringToSlotMappingEClass, STRING_TO_SLOT_MAPPING__KEY);
		createEReference(stringToSlotMappingEClass, STRING_TO_SLOT_MAPPING__VALUE);

		slotMappingEClass = createEClass(SLOT_MAPPING);
		createEAttribute(slotMappingEClass, SLOT_MAPPING__ENABLED);
		createEAttribute(slotMappingEClass, SLOT_MAPPING__HORIZONTAL);
		createEAttribute(slotMappingEClass, SLOT_MAPPING__DOMINANT);
		createEAttribute(slotMappingEClass, SLOT_MAPPING__CELL_X);
		createEAttribute(slotMappingEClass, SLOT_MAPPING__CELL_Y);

		workAreaMappingEClass = createEClass(WORK_AREA_MAPPING);
		createEAttribute(workAreaMappingEClass, WORK_AREA_MAPPING__DEFAULT_COLOR);
		createEAttribute(workAreaMappingEClass, WORK_AREA_MAPPING__ENABLED);
		createEAttribute(workAreaMappingEClass, WORK_AREA_MAPPING__NAME_INFO);
		createEReference(workAreaMappingEClass, WORK_AREA_MAPPING__CONTAINMENTS_BY_TYPE);
		createEReference(workAreaMappingEClass, WORK_AREA_MAPPING__FORMS_BY_ATTRIBUTE);

		stringToWorkAreaContainmentEClass = createEClass(STRING_TO_WORK_AREA_CONTAINMENT);
		createEAttribute(stringToWorkAreaContainmentEClass, STRING_TO_WORK_AREA_CONTAINMENT__KEY);
		createEReference(stringToWorkAreaContainmentEClass, STRING_TO_WORK_AREA_CONTAINMENT__VALUE);

		workAreaContainmentEClass = createEClass(WORK_AREA_CONTAINMENT);
		createEAttribute(workAreaContainmentEClass, WORK_AREA_CONTAINMENT__ENABLED);
		createEAttribute(workAreaContainmentEClass, WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE);

		stringToWorkAreaFormMappingEClass = createEClass(STRING_TO_WORK_AREA_FORM_MAPPING);
		createEAttribute(stringToWorkAreaFormMappingEClass, STRING_TO_WORK_AREA_FORM_MAPPING__KEY);
		createEReference(stringToWorkAreaFormMappingEClass, STRING_TO_WORK_AREA_FORM_MAPPING__VALUE);

		workAreaFormMappingEClass = createEClass(WORK_AREA_FORM_MAPPING);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__ENABLED);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__GRID_X);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__GRID_Y);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__GRID_WIDTH);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__GRID_HEIGHT);
		createEAttribute(workAreaFormMappingEClass, WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS);

		// Create data types
		eColorEDataType = createEDataType(ECOLOR);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		noMappingEClass.getESuperTypes().add(this.getObjectMapping());
		blockMappingEClass.getESuperTypes().add(this.getObjectMapping());
		workAreaMappingEClass.getESuperTypes().add(this.getObjectMapping());

		// Initialize classes, features, and operations; add parameters
		initEClass(mappingModelEClass, MappingModel.class, "MappingModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMappingModel_ObjectMappingsByType(), this.getStringToObjectMapping(), null, "objectMappingsByType", null, 0, -1, MappingModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToObjectMappingEClass, Map.Entry.class, "StringToObjectMapping", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToObjectMapping_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToObjectMapping_Value(), this.getObjectMapping(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectMappingEClass, ObjectMapping.class, "ObjectMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getObjectMapping__IsEnabled(), ecorePackage.getEBoolean(), "isEnabled", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getObjectMapping__GetNameInfo(), ecorePackage.getEString(), "getNameInfo", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(noMappingEClass, NoMapping.class, "NoMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blockMappingEClass, BlockMapping.class, "BlockMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlockMapping_DefaultColor(), this.getEColor(), "defaultColor", "C0C0C0", 1, 1, BlockMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockMapping_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, BlockMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockMapping_NameInfo(), ecorePackage.getEString(), "nameInfo", null, 0, 1, BlockMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockMapping_FormsByAttribute(), this.getStringToBlockFormMapping(), null, "formsByAttribute", null, 0, -1, BlockMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockMapping_SlotsByReference(), this.getStringToSlotMapping(), null, "slotsByReference", null, 0, -1, BlockMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToBlockFormMappingEClass, Map.Entry.class, "StringToBlockFormMapping", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToBlockFormMapping_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToBlockFormMapping_Value(), this.getBlockFormMapping(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockFormMappingEClass, BlockFormMapping.class, "BlockFormMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlockFormMapping_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, BlockFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockFormMapping_CellX(), ecorePackage.getEInt(), "cellX", "0", 1, 1, BlockFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockFormMapping_CellY(), ecorePackage.getEInt(), "cellY", "0", 1, 1, BlockFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockFormMapping_AttributeFormFactoryClass(), ecorePackage.getEString(), "attributeFormFactoryClass", "no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory", 1, 1, BlockFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToSlotMappingEClass, Map.Entry.class, "StringToSlotMapping", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToSlotMapping_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToSlotMapping_Value(), this.getSlotMapping(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(slotMappingEClass, SlotMapping.class, "SlotMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSlotMapping_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, SlotMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSlotMapping_Horizontal(), ecorePackage.getEBoolean(), "horizontal", "false", 1, 1, SlotMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSlotMapping_Dominant(), ecorePackage.getEBoolean(), "dominant", "false", 1, 1, SlotMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSlotMapping_CellX(), ecorePackage.getEInt(), "cellX", "0", 1, 1, SlotMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSlotMapping_CellY(), ecorePackage.getEInt(), "cellY", "0", 1, 1, SlotMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workAreaMappingEClass, WorkAreaMapping.class, "WorkAreaMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkAreaMapping_DefaultColor(), this.getEColor(), "defaultColor", "C0C0C0", 1, 1, WorkAreaMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaMapping_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, WorkAreaMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaMapping_NameInfo(), ecorePackage.getEString(), "nameInfo", null, 0, 1, WorkAreaMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkAreaMapping_ContainmentsByType(), this.getStringToWorkAreaContainment(), null, "containmentsByType", null, 0, -1, WorkAreaMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkAreaMapping_FormsByAttribute(), this.getStringToWorkAreaFormMapping(), null, "formsByAttribute", null, 0, -1, WorkAreaMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToWorkAreaContainmentEClass, Map.Entry.class, "StringToWorkAreaContainment", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToWorkAreaContainment_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToWorkAreaContainment_Value(), this.getWorkAreaContainment(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workAreaContainmentEClass, WorkAreaContainment.class, "WorkAreaContainment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkAreaContainment_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, WorkAreaContainment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaContainment_ContainmentReference(), ecorePackage.getEString(), "containmentReference", null, 1, 1, WorkAreaContainment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToWorkAreaFormMappingEClass, Map.Entry.class, "StringToWorkAreaFormMapping", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToWorkAreaFormMapping_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToWorkAreaFormMapping_Value(), this.getWorkAreaFormMapping(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workAreaFormMappingEClass, WorkAreaFormMapping.class, "WorkAreaFormMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkAreaFormMapping_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaFormMapping_GridX(), ecorePackage.getEInt(), "gridX", "-1", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaFormMapping_GridY(), ecorePackage.getEInt(), "gridY", "0", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaFormMapping_GridWidth(), ecorePackage.getEInt(), "gridWidth", "1", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaFormMapping_GridHeight(), ecorePackage.getEInt(), "gridHeight", "1", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkAreaFormMapping_AttributeFormFactoryClass(), ecorePackage.getEString(), "attributeFormFactoryClass", "no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory", 1, 1, WorkAreaFormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(eColorEDataType, Color.class, "EColor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //EmfjigsawmappingmodelPackageImpl
