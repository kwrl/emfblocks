/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see no.hal.emfblocks.mapping.MappingPackage
 * @generated
 */
public interface MappingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MappingFactory eINSTANCE = no.hal.emfblocks.mapping.impl.MappingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	MappingModel createMappingModel();

	/**
	 * Returns a new object of class '<em>No Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Mapping</em>'.
	 * @generated
	 */
	NoMapping createNoMapping();

	/**
	 * Returns a new object of class '<em>Block Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Mapping</em>'.
	 * @generated
	 */
	BlockMapping createBlockMapping();

	/**
	 * Returns a new object of class '<em>Block Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Form Mapping</em>'.
	 * @generated
	 */
	BlockFormMapping createBlockFormMapping();

	/**
	 * Returns a new object of class '<em>Slot Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Slot Mapping</em>'.
	 * @generated
	 */
	SlotMapping createSlotMapping();

	/**
	 * Returns a new object of class '<em>Work Area Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Area Mapping</em>'.
	 * @generated
	 */
	WorkAreaMapping createWorkAreaMapping();

	/**
	 * Returns a new object of class '<em>Work Area Containment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Area Containment</em>'.
	 * @generated
	 */
	WorkAreaContainment createWorkAreaContainment();

	/**
	 * Returns a new object of class '<em>Work Area Form Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Area Form Mapping</em>'.
	 * @generated
	 */
	WorkAreaFormMapping createWorkAreaFormMapping();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MappingPackage getMappingPackage();

} //EmfjigsawmappingmodelFactory
