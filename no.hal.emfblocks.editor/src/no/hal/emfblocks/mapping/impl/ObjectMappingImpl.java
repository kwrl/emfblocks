/**
 */
package no.hal.emfblocks.mapping.impl;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.ObjectMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jigsaw Object Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ObjectMappingImpl extends MinimalEObjectImpl.Container implements ObjectMapping {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.OBJECT_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract boolean isEnabled();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract String getNameInfo();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case MappingPackage.OBJECT_MAPPING___IS_ENABLED:
				return isEnabled();
			case MappingPackage.OBJECT_MAPPING___GET_NAME_INFO:
				return getNameInfo();
		}
		return super.eInvoke(operationID, arguments);
	}

} //JigsawObjectMappingImpl
