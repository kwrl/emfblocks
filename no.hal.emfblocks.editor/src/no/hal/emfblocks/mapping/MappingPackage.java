/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see no.hal.emfblocks.mapping.MappingFactory
 * @model kind="package"
 * @generated
 */
public interface MappingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mapping";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/no.hal.emfblocks.editor/model/mapping.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mapping";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MappingPackage eINSTANCE = no.hal.emfblocks.mapping.impl.MappingPackageImpl.init();

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.MappingModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.MappingModelImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getMappingModel()
	 * @generated
	 */
	int MAPPING_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Object Mappings By Type</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.StringToObjectMappingImpl <em>String To Object Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.StringToObjectMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToObjectMapping()
	 * @generated
	 */
	int STRING_TO_OBJECT_MAPPING = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAPPING__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAPPING__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Object Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Object Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.ObjectMappingImpl <em>Object Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.ObjectMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getObjectMapping()
	 * @generated
	 */
	int OBJECT_MAPPING = 2;

	/**
	 * The number of structural features of the '<em>Object Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_MAPPING_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Is Enabled</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_MAPPING___IS_ENABLED = 0;

	/**
	 * The operation id for the '<em>Get Name Info</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_MAPPING___GET_NAME_INFO = 1;

	/**
	 * The number of operations of the '<em>Object Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_MAPPING_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.NoMappingImpl <em>No Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.NoMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getNoMapping()
	 * @generated
	 */
	int NO_MAPPING = 3;

	/**
	 * The number of structural features of the '<em>No Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MAPPING_FEATURE_COUNT = OBJECT_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Is Enabled</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MAPPING___IS_ENABLED = OBJECT_MAPPING___IS_ENABLED;

	/**
	 * The operation id for the '<em>Get Name Info</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MAPPING___GET_NAME_INFO = OBJECT_MAPPING___GET_NAME_INFO;

	/**
	 * The number of operations of the '<em>No Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MAPPING_OPERATION_COUNT = OBJECT_MAPPING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl <em>Block Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.BlockMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getBlockMapping()
	 * @generated
	 */
	int BLOCK_MAPPING = 4;

	/**
	 * The feature id for the '<em><b>Default Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING__DEFAULT_COLOR = OBJECT_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING__ENABLED = OBJECT_MAPPING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING__NAME_INFO = OBJECT_MAPPING_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Forms By Attribute</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING__FORMS_BY_ATTRIBUTE = OBJECT_MAPPING_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Slots By Reference</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING__SLOTS_BY_REFERENCE = OBJECT_MAPPING_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Block Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING_FEATURE_COUNT = OBJECT_MAPPING_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Is Enabled</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING___IS_ENABLED = OBJECT_MAPPING___IS_ENABLED;

	/**
	 * The operation id for the '<em>Get Name Info</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING___GET_NAME_INFO = OBJECT_MAPPING___GET_NAME_INFO;

	/**
	 * The number of operations of the '<em>Block Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_MAPPING_OPERATION_COUNT = OBJECT_MAPPING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.StringToBlockFormMappingImpl <em>String To Block Form Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.StringToBlockFormMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToBlockFormMapping()
	 * @generated
	 */
	int STRING_TO_BLOCK_FORM_MAPPING = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_BLOCK_FORM_MAPPING__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_BLOCK_FORM_MAPPING__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Block Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_BLOCK_FORM_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Block Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_BLOCK_FORM_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl <em>Block Form Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.BlockFormMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getBlockFormMapping()
	 * @generated
	 */
	int BLOCK_FORM_MAPPING = 6;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Cell X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING__CELL_X = 1;

	/**
	 * The feature id for the '<em><b>Cell Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING__CELL_Y = 2;

	/**
	 * The feature id for the '<em><b>Attribute Form Factory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS = 3;

	/**
	 * The number of structural features of the '<em>Block Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Block Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FORM_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.StringToSlotMappingImpl <em>String To Slot Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.StringToSlotMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToSlotMapping()
	 * @generated
	 */
	int STRING_TO_SLOT_MAPPING = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SLOT_MAPPING__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SLOT_MAPPING__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Slot Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SLOT_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Slot Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SLOT_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl <em>Slot Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.SlotMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getSlotMapping()
	 * @generated
	 */
	int SLOT_MAPPING = 8;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Horizontal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING__HORIZONTAL = 1;

	/**
	 * The feature id for the '<em><b>Dominant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING__DOMINANT = 2;

	/**
	 * The feature id for the '<em><b>Cell X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING__CELL_X = 3;

	/**
	 * The feature id for the '<em><b>Cell Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING__CELL_Y = 4;

	/**
	 * The number of structural features of the '<em>Slot Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Slot Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaMappingImpl <em>Work Area Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.WorkAreaMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaMapping()
	 * @generated
	 */
	int WORK_AREA_MAPPING = 9;

	/**
	 * The feature id for the '<em><b>Default Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING__DEFAULT_COLOR = OBJECT_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING__ENABLED = OBJECT_MAPPING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING__NAME_INFO = OBJECT_MAPPING_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Containments By Type</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING__CONTAINMENTS_BY_TYPE = OBJECT_MAPPING_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Forms By Attribute</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING__FORMS_BY_ATTRIBUTE = OBJECT_MAPPING_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Work Area Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING_FEATURE_COUNT = OBJECT_MAPPING_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Is Enabled</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING___IS_ENABLED = OBJECT_MAPPING___IS_ENABLED;

	/**
	 * The operation id for the '<em>Get Name Info</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING___GET_NAME_INFO = OBJECT_MAPPING___GET_NAME_INFO;

	/**
	 * The number of operations of the '<em>Work Area Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_MAPPING_OPERATION_COUNT = OBJECT_MAPPING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.StringToWorkAreaContainmentImpl <em>String To Work Area Containment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.StringToWorkAreaContainmentImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToWorkAreaContainment()
	 * @generated
	 */
	int STRING_TO_WORK_AREA_CONTAINMENT = 10;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_CONTAINMENT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_CONTAINMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Work Area Containment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_CONTAINMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Work Area Containment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_CONTAINMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl <em>Work Area Containment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaContainment()
	 * @generated
	 */
	int WORK_AREA_CONTAINMENT = 11;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_CONTAINMENT__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Containment Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE = 1;

	/**
	 * The number of structural features of the '<em>Work Area Containment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_CONTAINMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Work Area Containment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_CONTAINMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.StringToWorkAreaFormMappingImpl <em>String To Work Area Form Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.StringToWorkAreaFormMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToWorkAreaFormMapping()
	 * @generated
	 */
	int STRING_TO_WORK_AREA_FORM_MAPPING = 12;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_FORM_MAPPING__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_FORM_MAPPING__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Work Area Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_FORM_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Work Area Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_WORK_AREA_FORM_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl <em>Work Area Form Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaFormMapping()
	 * @generated
	 */
	int WORK_AREA_FORM_MAPPING = 13;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Grid X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__GRID_X = 1;

	/**
	 * The feature id for the '<em><b>Grid Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__GRID_Y = 2;

	/**
	 * The feature id for the '<em><b>Grid Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__GRID_WIDTH = 3;

	/**
	 * The feature id for the '<em><b>Grid Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__GRID_HEIGHT = 4;

	/**
	 * The feature id for the '<em><b>Attribute Form Factory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS = 5;

	/**
	 * The number of structural features of the '<em>Work Area Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Work Area Form Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_AREA_FORM_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>EColor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see javafx.scene.paint.Color
	 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getEColor()
	 * @generated
	 */
	int ECOLOR = 14;


	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.MappingModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see no.hal.emfblocks.mapping.MappingModel
	 * @generated
	 */
	EClass getMappingModel();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.mapping.MappingModel#getObjectMappingsByType <em>Object Mappings By Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Object Mappings By Type</em>'.
	 * @see no.hal.emfblocks.mapping.MappingModel#getObjectMappingsByType()
	 * @see #getMappingModel()
	 * @generated
	 */
	EReference getMappingModel_ObjectMappingsByType();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Object Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Object Mapping</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="no.hal.emfblocks.mapping.ObjectMapping" valueContainment="true"
	 * @generated
	 */
	EClass getStringToObjectMapping();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObjectMapping()
	 * @generated
	 */
	EAttribute getStringToObjectMapping_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObjectMapping()
	 * @generated
	 */
	EReference getStringToObjectMapping_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.ObjectMapping <em>Object Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.ObjectMapping
	 * @generated
	 */
	EClass getObjectMapping();

	/**
	 * Returns the meta object for the '{@link no.hal.emfblocks.mapping.ObjectMapping#isEnabled() <em>Is Enabled</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Enabled</em>' operation.
	 * @see no.hal.emfblocks.mapping.ObjectMapping#isEnabled()
	 * @generated
	 */
	EOperation getObjectMapping__IsEnabled();

	/**
	 * Returns the meta object for the '{@link no.hal.emfblocks.mapping.ObjectMapping#getNameInfo() <em>Get Name Info</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Name Info</em>' operation.
	 * @see no.hal.emfblocks.mapping.ObjectMapping#getNameInfo()
	 * @generated
	 */
	EOperation getObjectMapping__GetNameInfo();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.NoMapping <em>No Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.NoMapping
	 * @generated
	 */
	EClass getNoMapping();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.BlockMapping <em>Block Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping
	 * @generated
	 */
	EClass getBlockMapping();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockMapping#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping#isEnabled()
	 * @see #getBlockMapping()
	 * @generated
	 */
	EAttribute getBlockMapping_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockMapping#getNameInfo <em>Name Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name Info</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping#getNameInfo()
	 * @see #getBlockMapping()
	 * @generated
	 */
	EAttribute getBlockMapping_NameInfo();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockMapping#getDefaultColor <em>Default Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Color</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping#getDefaultColor()
	 * @see #getBlockMapping()
	 * @generated
	 */
	EAttribute getBlockMapping_DefaultColor();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.mapping.BlockMapping#getFormsByAttribute <em>Forms By Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Forms By Attribute</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping#getFormsByAttribute()
	 * @see #getBlockMapping()
	 * @generated
	 */
	EReference getBlockMapping_FormsByAttribute();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.mapping.BlockMapping#getSlotsByReference <em>Slots By Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Slots By Reference</em>'.
	 * @see no.hal.emfblocks.mapping.BlockMapping#getSlotsByReference()
	 * @see #getBlockMapping()
	 * @generated
	 */
	EReference getBlockMapping_SlotsByReference();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Block Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Block Form Mapping</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="no.hal.emfblocks.mapping.BlockFormMapping" valueContainment="true"
	 * @generated
	 */
	EClass getStringToBlockFormMapping();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToBlockFormMapping()
	 * @generated
	 */
	EAttribute getStringToBlockFormMapping_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToBlockFormMapping()
	 * @generated
	 */
	EReference getStringToBlockFormMapping_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.BlockFormMapping <em>Block Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Form Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping
	 * @generated
	 */
	EClass getBlockFormMapping();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockFormMapping#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping#isEnabled()
	 * @see #getBlockFormMapping()
	 * @generated
	 */
	EAttribute getBlockFormMapping_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellX <em>Cell X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell X</em>'.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping#getCellX()
	 * @see #getBlockFormMapping()
	 * @generated
	 */
	EAttribute getBlockFormMapping_CellX();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockFormMapping#getCellY <em>Cell Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell Y</em>'.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping#getCellY()
	 * @see #getBlockFormMapping()
	 * @generated
	 */
	EAttribute getBlockFormMapping_CellY();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.BlockFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Form Factory Class</em>'.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping#getAttributeFormFactoryClass()
	 * @see #getBlockFormMapping()
	 * @generated
	 */
	EAttribute getBlockFormMapping_AttributeFormFactoryClass();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Slot Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Slot Mapping</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="no.hal.emfblocks.mapping.SlotMapping" valueContainment="true"
	 * @generated
	 */
	EClass getStringToSlotMapping();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToSlotMapping()
	 * @generated
	 */
	EAttribute getStringToSlotMapping_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToSlotMapping()
	 * @generated
	 */
	EReference getStringToSlotMapping_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.SlotMapping <em>Slot Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Slot Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping
	 * @generated
	 */
	EClass getSlotMapping();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.SlotMapping#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping#isEnabled()
	 * @see #getSlotMapping()
	 * @generated
	 */
	EAttribute getSlotMapping_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.SlotMapping#isHorizontal <em>Horizontal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping#isHorizontal()
	 * @see #getSlotMapping()
	 * @generated
	 */
	EAttribute getSlotMapping_Horizontal();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.SlotMapping#isDominant <em>Dominant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dominant</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping#isDominant()
	 * @see #getSlotMapping()
	 * @generated
	 */
	EAttribute getSlotMapping_Dominant();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.SlotMapping#getCellX <em>Cell X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell X</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping#getCellX()
	 * @see #getSlotMapping()
	 * @generated
	 */
	EAttribute getSlotMapping_CellX();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.SlotMapping#getCellY <em>Cell Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell Y</em>'.
	 * @see no.hal.emfblocks.mapping.SlotMapping#getCellY()
	 * @see #getSlotMapping()
	 * @generated
	 */
	EAttribute getSlotMapping_CellY();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.WorkAreaMapping <em>Work Area Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Area Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping
	 * @generated
	 */
	EClass getWorkAreaMapping();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaMapping#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping#isEnabled()
	 * @see #getWorkAreaMapping()
	 * @generated
	 */
	EAttribute getWorkAreaMapping_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getNameInfo <em>Name Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name Info</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping#getNameInfo()
	 * @see #getWorkAreaMapping()
	 * @generated
	 */
	EAttribute getWorkAreaMapping_NameInfo();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getDefaultColor <em>Default Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Color</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping#getDefaultColor()
	 * @see #getWorkAreaMapping()
	 * @generated
	 */
	EAttribute getWorkAreaMapping_DefaultColor();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getContainmentsByType <em>Containments By Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Containments By Type</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping#getContainmentsByType()
	 * @see #getWorkAreaMapping()
	 * @generated
	 */
	EReference getWorkAreaMapping_ContainmentsByType();

	/**
	 * Returns the meta object for the map '{@link no.hal.emfblocks.mapping.WorkAreaMapping#getFormsByAttribute <em>Forms By Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Forms By Attribute</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping#getFormsByAttribute()
	 * @see #getWorkAreaMapping()
	 * @generated
	 */
	EReference getWorkAreaMapping_FormsByAttribute();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Work Area Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Work Area Containment</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="no.hal.emfblocks.mapping.WorkAreaContainment" valueContainment="true"
	 * @generated
	 */
	EClass getStringToWorkAreaContainment();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToWorkAreaContainment()
	 * @generated
	 */
	EAttribute getStringToWorkAreaContainment_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToWorkAreaContainment()
	 * @generated
	 */
	EReference getStringToWorkAreaContainment_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.WorkAreaContainment <em>Work Area Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Area Containment</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaContainment
	 * @generated
	 */
	EClass getWorkAreaContainment();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaContainment#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaContainment#isEnabled()
	 * @see #getWorkAreaContainment()
	 * @generated
	 */
	EAttribute getWorkAreaContainment_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaContainment#getContainmentReference <em>Containment Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containment Reference</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaContainment#getContainmentReference()
	 * @see #getWorkAreaContainment()
	 * @generated
	 */
	EAttribute getWorkAreaContainment_ContainmentReference();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Work Area Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Work Area Form Mapping</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="no.hal.emfblocks.mapping.WorkAreaFormMapping" valueContainment="true"
	 * @generated
	 */
	EClass getStringToWorkAreaFormMapping();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getStringToWorkAreaFormMapping_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToWorkAreaFormMapping()
	 * @generated
	 */
	EReference getStringToWorkAreaFormMapping_Value();

	/**
	 * Returns the meta object for class '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping <em>Work Area Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Area Form Mapping</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping
	 * @generated
	 */
	EClass getWorkAreaFormMapping();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#isEnabled()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridX <em>Grid X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid X</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridX()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_GridX();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridY <em>Grid Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Y</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridY()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_GridY();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridWidth <em>Grid Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Width</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridWidth()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_GridWidth();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridHeight <em>Grid Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Height</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#getGridHeight()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_GridHeight();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Form Factory Class</em>'.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping#getAttributeFormFactoryClass()
	 * @see #getWorkAreaFormMapping()
	 * @generated
	 */
	EAttribute getWorkAreaFormMapping_AttributeFormFactoryClass();

	/**
	 * Returns the meta object for data type '{@link javafx.scene.paint.Color <em>EColor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EColor</em>'.
	 * @see javafx.scene.paint.Color
	 * @model instanceClass="javafx.scene.paint.Color"
	 * @generated
	 */
	EDataType getEColor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MappingFactory getMappingFactory();

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
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.MappingModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.MappingModelImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getMappingModel()
		 * @generated
		 */
		EClass MAPPING_MODEL = eINSTANCE.getMappingModel();

		/**
		 * The meta object literal for the '<em><b>Object Mappings By Type</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE = eINSTANCE.getMappingModel_ObjectMappingsByType();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.StringToObjectMappingImpl <em>String To Object Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.StringToObjectMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToObjectMapping()
		 * @generated
		 */
		EClass STRING_TO_OBJECT_MAPPING = eINSTANCE.getStringToObjectMapping();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT_MAPPING__KEY = eINSTANCE.getStringToObjectMapping_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_OBJECT_MAPPING__VALUE = eINSTANCE.getStringToObjectMapping_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.ObjectMappingImpl <em>Object Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.ObjectMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getObjectMapping()
		 * @generated
		 */
		EClass OBJECT_MAPPING = eINSTANCE.getObjectMapping();

		/**
		 * The meta object literal for the '<em><b>Is Enabled</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation OBJECT_MAPPING___IS_ENABLED = eINSTANCE.getObjectMapping__IsEnabled();

		/**
		 * The meta object literal for the '<em><b>Get Name Info</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation OBJECT_MAPPING___GET_NAME_INFO = eINSTANCE.getObjectMapping__GetNameInfo();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.NoMappingImpl <em>No Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.NoMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getNoMapping()
		 * @generated
		 */
		EClass NO_MAPPING = eINSTANCE.getNoMapping();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl <em>Block Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.BlockMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getBlockMapping()
		 * @generated
		 */
		EClass BLOCK_MAPPING = eINSTANCE.getBlockMapping();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_MAPPING__ENABLED = eINSTANCE.getBlockMapping_Enabled();

		/**
		 * The meta object literal for the '<em><b>Name Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_MAPPING__NAME_INFO = eINSTANCE.getBlockMapping_NameInfo();

		/**
		 * The meta object literal for the '<em><b>Default Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_MAPPING__DEFAULT_COLOR = eINSTANCE.getBlockMapping_DefaultColor();

		/**
		 * The meta object literal for the '<em><b>Forms By Attribute</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_MAPPING__FORMS_BY_ATTRIBUTE = eINSTANCE.getBlockMapping_FormsByAttribute();

		/**
		 * The meta object literal for the '<em><b>Slots By Reference</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_MAPPING__SLOTS_BY_REFERENCE = eINSTANCE.getBlockMapping_SlotsByReference();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.StringToBlockFormMappingImpl <em>String To Block Form Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.StringToBlockFormMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToBlockFormMapping()
		 * @generated
		 */
		EClass STRING_TO_BLOCK_FORM_MAPPING = eINSTANCE.getStringToBlockFormMapping();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_BLOCK_FORM_MAPPING__KEY = eINSTANCE.getStringToBlockFormMapping_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_BLOCK_FORM_MAPPING__VALUE = eINSTANCE.getStringToBlockFormMapping_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl <em>Block Form Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.BlockFormMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getBlockFormMapping()
		 * @generated
		 */
		EClass BLOCK_FORM_MAPPING = eINSTANCE.getBlockFormMapping();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_FORM_MAPPING__ENABLED = eINSTANCE.getBlockFormMapping_Enabled();

		/**
		 * The meta object literal for the '<em><b>Cell X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_FORM_MAPPING__CELL_X = eINSTANCE.getBlockFormMapping_CellX();

		/**
		 * The meta object literal for the '<em><b>Cell Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_FORM_MAPPING__CELL_Y = eINSTANCE.getBlockFormMapping_CellY();

		/**
		 * The meta object literal for the '<em><b>Attribute Form Factory Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS = eINSTANCE.getBlockFormMapping_AttributeFormFactoryClass();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.StringToSlotMappingImpl <em>String To Slot Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.StringToSlotMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToSlotMapping()
		 * @generated
		 */
		EClass STRING_TO_SLOT_MAPPING = eINSTANCE.getStringToSlotMapping();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_SLOT_MAPPING__KEY = eINSTANCE.getStringToSlotMapping_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_SLOT_MAPPING__VALUE = eINSTANCE.getStringToSlotMapping_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl <em>Slot Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.SlotMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getSlotMapping()
		 * @generated
		 */
		EClass SLOT_MAPPING = eINSTANCE.getSlotMapping();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT_MAPPING__ENABLED = eINSTANCE.getSlotMapping_Enabled();

		/**
		 * The meta object literal for the '<em><b>Horizontal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT_MAPPING__HORIZONTAL = eINSTANCE.getSlotMapping_Horizontal();

		/**
		 * The meta object literal for the '<em><b>Dominant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT_MAPPING__DOMINANT = eINSTANCE.getSlotMapping_Dominant();

		/**
		 * The meta object literal for the '<em><b>Cell X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT_MAPPING__CELL_X = eINSTANCE.getSlotMapping_CellX();

		/**
		 * The meta object literal for the '<em><b>Cell Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT_MAPPING__CELL_Y = eINSTANCE.getSlotMapping_CellY();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaMappingImpl <em>Work Area Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.WorkAreaMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaMapping()
		 * @generated
		 */
		EClass WORK_AREA_MAPPING = eINSTANCE.getWorkAreaMapping();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_MAPPING__ENABLED = eINSTANCE.getWorkAreaMapping_Enabled();

		/**
		 * The meta object literal for the '<em><b>Name Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_MAPPING__NAME_INFO = eINSTANCE.getWorkAreaMapping_NameInfo();

		/**
		 * The meta object literal for the '<em><b>Default Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_MAPPING__DEFAULT_COLOR = eINSTANCE.getWorkAreaMapping_DefaultColor();

		/**
		 * The meta object literal for the '<em><b>Containments By Type</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_AREA_MAPPING__CONTAINMENTS_BY_TYPE = eINSTANCE.getWorkAreaMapping_ContainmentsByType();

		/**
		 * The meta object literal for the '<em><b>Forms By Attribute</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_AREA_MAPPING__FORMS_BY_ATTRIBUTE = eINSTANCE.getWorkAreaMapping_FormsByAttribute();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.StringToWorkAreaContainmentImpl <em>String To Work Area Containment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.StringToWorkAreaContainmentImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToWorkAreaContainment()
		 * @generated
		 */
		EClass STRING_TO_WORK_AREA_CONTAINMENT = eINSTANCE.getStringToWorkAreaContainment();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_WORK_AREA_CONTAINMENT__KEY = eINSTANCE.getStringToWorkAreaContainment_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_WORK_AREA_CONTAINMENT__VALUE = eINSTANCE.getStringToWorkAreaContainment_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl <em>Work Area Containment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaContainment()
		 * @generated
		 */
		EClass WORK_AREA_CONTAINMENT = eINSTANCE.getWorkAreaContainment();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_CONTAINMENT__ENABLED = eINSTANCE.getWorkAreaContainment_Enabled();

		/**
		 * The meta object literal for the '<em><b>Containment Reference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE = eINSTANCE.getWorkAreaContainment_ContainmentReference();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.StringToWorkAreaFormMappingImpl <em>String To Work Area Form Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.StringToWorkAreaFormMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getStringToWorkAreaFormMapping()
		 * @generated
		 */
		EClass STRING_TO_WORK_AREA_FORM_MAPPING = eINSTANCE.getStringToWorkAreaFormMapping();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_WORK_AREA_FORM_MAPPING__KEY = eINSTANCE.getStringToWorkAreaFormMapping_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_WORK_AREA_FORM_MAPPING__VALUE = eINSTANCE.getStringToWorkAreaFormMapping_Value();

		/**
		 * The meta object literal for the '{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl <em>Work Area Form Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getWorkAreaFormMapping()
		 * @generated
		 */
		EClass WORK_AREA_FORM_MAPPING = eINSTANCE.getWorkAreaFormMapping();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__ENABLED = eINSTANCE.getWorkAreaFormMapping_Enabled();

		/**
		 * The meta object literal for the '<em><b>Grid X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__GRID_X = eINSTANCE.getWorkAreaFormMapping_GridX();

		/**
		 * The meta object literal for the '<em><b>Grid Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__GRID_Y = eINSTANCE.getWorkAreaFormMapping_GridY();

		/**
		 * The meta object literal for the '<em><b>Grid Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__GRID_WIDTH = eINSTANCE.getWorkAreaFormMapping_GridWidth();

		/**
		 * The meta object literal for the '<em><b>Grid Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__GRID_HEIGHT = eINSTANCE.getWorkAreaFormMapping_GridHeight();

		/**
		 * The meta object literal for the '<em><b>Attribute Form Factory Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS = eINSTANCE.getWorkAreaFormMapping_AttributeFormFactoryClass();

		/**
		 * The meta object literal for the '<em>EColor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see javafx.scene.paint.Color
		 * @see no.hal.emfblocks.mapping.impl.MappingPackageImpl#getEColor()
		 * @generated
		 */
		EDataType ECOLOR = eINSTANCE.getEColor();

	}

} //EmfjigsawmappingmodelPackage
