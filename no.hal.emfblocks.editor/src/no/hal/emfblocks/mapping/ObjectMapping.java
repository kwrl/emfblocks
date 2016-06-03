/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jigsaw Object Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getObjectMapping()
 * @model abstract="true"
 * @generated
 */
public interface ObjectMapping extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getNameInfo();
} // JigsawObjectMapping
