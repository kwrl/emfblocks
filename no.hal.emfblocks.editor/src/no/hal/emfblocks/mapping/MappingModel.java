/**
 */
package no.hal.emfblocks.mapping;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jigsaw Mapping Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.MappingModel#getObjectMappingsByType <em>Object Mappings By Type</em>}</li>
 * </ul>
 *
 * @see no.hal.emfblocks.mapping.MappingPackage#getMappingModel()
 * @model
 * @generated
 */
public interface MappingModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Object Mappings By Type</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link no.hal.emfblocks.mapping.ObjectMapping},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Mappings By Type</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Mappings By Type</em>' map.
	 * @see no.hal.emfblocks.mapping.MappingPackage#getMappingModel_ObjectMappingsByType()
	 * @model mapType="no.hal.emfblocks.mapping.StringToObjectMapping<org.eclipse.emf.ecore.EString, no.hal.emfblocks.mapping.ObjectMapping>"
	 * @generated
	 */
	EMap<String, ObjectMapping> getObjectMappingsByType();

} // JigsawMappingModel
