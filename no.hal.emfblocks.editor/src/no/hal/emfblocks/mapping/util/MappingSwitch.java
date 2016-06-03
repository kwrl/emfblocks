/**
 */
package no.hal.emfblocks.mapping.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import no.hal.emfblocks.mapping.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see no.hal.emfblocks.mapping.MappingPackage
 * @generated
 */
public class MappingSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MappingPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingSwitch() {
		if (modelPackage == null) {
			modelPackage = MappingPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MappingPackage.MAPPING_MODEL: {
				MappingModel mappingModel = (MappingModel)theEObject;
				T result = caseMappingModel(mappingModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.STRING_TO_OBJECT_MAPPING: {
				@SuppressWarnings("unchecked") Map.Entry<String, ObjectMapping> stringToObjectMapping = (Map.Entry<String, ObjectMapping>)theEObject;
				T result = caseStringToObjectMapping(stringToObjectMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.OBJECT_MAPPING: {
				ObjectMapping objectMapping = (ObjectMapping)theEObject;
				T result = caseObjectMapping(objectMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.NO_MAPPING: {
				NoMapping noMapping = (NoMapping)theEObject;
				T result = caseNoMapping(noMapping);
				if (result == null) result = caseObjectMapping(noMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.BLOCK_MAPPING: {
				BlockMapping blockMapping = (BlockMapping)theEObject;
				T result = caseBlockMapping(blockMapping);
				if (result == null) result = caseObjectMapping(blockMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.STRING_TO_BLOCK_FORM_MAPPING: {
				@SuppressWarnings("unchecked") Map.Entry<String, BlockFormMapping> stringToBlockFormMapping = (Map.Entry<String, BlockFormMapping>)theEObject;
				T result = caseStringToBlockFormMapping(stringToBlockFormMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.BLOCK_FORM_MAPPING: {
				BlockFormMapping blockFormMapping = (BlockFormMapping)theEObject;
				T result = caseBlockFormMapping(blockFormMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.STRING_TO_SLOT_MAPPING: {
				@SuppressWarnings("unchecked") Map.Entry<String, SlotMapping> stringToSlotMapping = (Map.Entry<String, SlotMapping>)theEObject;
				T result = caseStringToSlotMapping(stringToSlotMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.SLOT_MAPPING: {
				SlotMapping slotMapping = (SlotMapping)theEObject;
				T result = caseSlotMapping(slotMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.WORK_AREA_MAPPING: {
				WorkAreaMapping workAreaMapping = (WorkAreaMapping)theEObject;
				T result = caseWorkAreaMapping(workAreaMapping);
				if (result == null) result = caseObjectMapping(workAreaMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.STRING_TO_WORK_AREA_CONTAINMENT: {
				@SuppressWarnings("unchecked") Map.Entry<String, WorkAreaContainment> stringToWorkAreaContainment = (Map.Entry<String, WorkAreaContainment>)theEObject;
				T result = caseStringToWorkAreaContainment(stringToWorkAreaContainment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.WORK_AREA_CONTAINMENT: {
				WorkAreaContainment workAreaContainment = (WorkAreaContainment)theEObject;
				T result = caseWorkAreaContainment(workAreaContainment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.STRING_TO_WORK_AREA_FORM_MAPPING: {
				@SuppressWarnings("unchecked") Map.Entry<String, WorkAreaFormMapping> stringToWorkAreaFormMapping = (Map.Entry<String, WorkAreaFormMapping>)theEObject;
				T result = caseStringToWorkAreaFormMapping(stringToWorkAreaFormMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MappingPackage.WORK_AREA_FORM_MAPPING: {
				WorkAreaFormMapping workAreaFormMapping = (WorkAreaFormMapping)theEObject;
				T result = caseWorkAreaFormMapping(workAreaFormMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappingModel(MappingModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Object Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Object Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToObjectMapping(Map.Entry<String, ObjectMapping> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectMapping(ObjectMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoMapping(NoMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockMapping(BlockMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Block Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Block Form Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToBlockFormMapping(Map.Entry<String, BlockFormMapping> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block Form Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockFormMapping(BlockFormMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Slot Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Slot Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToSlotMapping(Map.Entry<String, SlotMapping> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Slot Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Slot Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSlotMapping(SlotMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Area Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Area Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkAreaMapping(WorkAreaMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Work Area Containment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Work Area Containment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToWorkAreaContainment(Map.Entry<String, WorkAreaContainment> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Area Containment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Area Containment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkAreaContainment(WorkAreaContainment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Work Area Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Work Area Form Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToWorkAreaFormMapping(Map.Entry<String, WorkAreaFormMapping> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Area Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Area Form Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkAreaFormMapping(WorkAreaFormMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //EmfjigsawmappingmodelSwitch
