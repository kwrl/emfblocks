/**
 */
package no.hal.emfblocks.mapping.impl;

import java.util.Map;

import javafx.scene.paint.Color;
import no.hal.emfblocks.mapping.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MappingFactoryImpl extends EFactoryImpl implements MappingFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MappingFactory init() {
		try {
			MappingFactory theMappingFactory = (MappingFactory)EPackage.Registry.INSTANCE.getEFactory(MappingPackage.eNS_URI);
			if (theMappingFactory != null) {
				return theMappingFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MappingFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MappingPackage.MAPPING_MODEL: return createMappingModel();
			case MappingPackage.STRING_TO_OBJECT_MAPPING: return (EObject)createStringToObjectMapping();
			case MappingPackage.NO_MAPPING: return createNoMapping();
			case MappingPackage.BLOCK_MAPPING: return createBlockMapping();
			case MappingPackage.STRING_TO_BLOCK_FORM_MAPPING: return (EObject)createStringToBlockFormMapping();
			case MappingPackage.BLOCK_FORM_MAPPING: return createBlockFormMapping();
			case MappingPackage.STRING_TO_SLOT_MAPPING: return (EObject)createStringToSlotMapping();
			case MappingPackage.SLOT_MAPPING: return createSlotMapping();
			case MappingPackage.WORK_AREA_MAPPING: return createWorkAreaMapping();
			case MappingPackage.STRING_TO_WORK_AREA_CONTAINMENT: return (EObject)createStringToWorkAreaContainment();
			case MappingPackage.WORK_AREA_CONTAINMENT: return createWorkAreaContainment();
			case MappingPackage.STRING_TO_WORK_AREA_FORM_MAPPING: return (EObject)createStringToWorkAreaFormMapping();
			case MappingPackage.WORK_AREA_FORM_MAPPING: return createWorkAreaFormMapping();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case MappingPackage.ECOLOR:
				return createEColorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case MappingPackage.ECOLOR:
				return convertEColorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModel createMappingModel() {
		MappingModelImpl mappingModel = new MappingModelImpl();
		return mappingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, ObjectMapping> createStringToObjectMapping() {
		StringToObjectMappingImpl stringToObjectMapping = new StringToObjectMappingImpl();
		return stringToObjectMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoMapping createNoMapping() {
		NoMappingImpl noMapping = new NoMappingImpl();
		return noMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockMapping createBlockMapping() {
		BlockMappingImpl blockMapping = new BlockMappingImpl();
		return blockMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, BlockFormMapping> createStringToBlockFormMapping() {
		StringToBlockFormMappingImpl stringToBlockFormMapping = new StringToBlockFormMappingImpl();
		return stringToBlockFormMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockFormMapping createBlockFormMapping() {
		BlockFormMappingImpl blockFormMapping = new BlockFormMappingImpl();
		return blockFormMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, SlotMapping> createStringToSlotMapping() {
		StringToSlotMappingImpl stringToSlotMapping = new StringToSlotMappingImpl();
		return stringToSlotMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SlotMapping createSlotMapping() {
		SlotMappingImpl slotMapping = new SlotMappingImpl();
		return slotMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkAreaMapping createWorkAreaMapping() {
		WorkAreaMappingImpl workAreaMapping = new WorkAreaMappingImpl();
		return workAreaMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, WorkAreaContainment> createStringToWorkAreaContainment() {
		StringToWorkAreaContainmentImpl stringToWorkAreaContainment = new StringToWorkAreaContainmentImpl();
		return stringToWorkAreaContainment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkAreaContainment createWorkAreaContainment() {
		WorkAreaContainmentImpl workAreaContainment = new WorkAreaContainmentImpl();
		return workAreaContainment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, WorkAreaFormMapping> createStringToWorkAreaFormMapping() {
		StringToWorkAreaFormMappingImpl stringToWorkAreaFormMapping = new StringToWorkAreaFormMappingImpl();
		return stringToWorkAreaFormMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkAreaFormMapping createWorkAreaFormMapping() {
		WorkAreaFormMappingImpl workAreaFormMapping = new WorkAreaFormMappingImpl();
		return workAreaFormMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color createEColorFromString(EDataType eDataType, String initialValue) {
		return (Color)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEColorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingPackage getMappingPackage() {
		return (MappingPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MappingPackage getPackage() {
		return MappingPackage.eINSTANCE;
	}

} //EmfjigsawmappingmodelFactoryImpl
