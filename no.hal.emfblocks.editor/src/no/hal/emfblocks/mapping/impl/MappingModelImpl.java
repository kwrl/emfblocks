/**
 */
package no.hal.emfblocks.mapping.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.mapping.ObjectMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jigsaw Mapping Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.MappingModelImpl#getObjectMappingsByType <em>Object Mappings By Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MappingModelImpl extends MinimalEObjectImpl.Container implements MappingModel {
	/**
	 * The cached value of the '{@link #getObjectMappingsByType() <em>Object Mappings By Type</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectMappingsByType()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ObjectMapping> objectMappingsByType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.MAPPING_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ObjectMapping> getObjectMappingsByType() {
		if (objectMappingsByType == null) {
			objectMappingsByType = new EcoreEMap<String,ObjectMapping>(MappingPackage.Literals.STRING_TO_OBJECT_MAPPING, StringToObjectMappingImpl.class, this, MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE);
		}
		return objectMappingsByType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE:
				return ((InternalEList<?>)getObjectMappingsByType()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE:
				if (coreType) return getObjectMappingsByType();
				else return getObjectMappingsByType().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE:
				((EStructuralFeature.Setting)getObjectMappingsByType()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE:
				getObjectMappingsByType().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MappingPackage.MAPPING_MODEL__OBJECT_MAPPINGS_BY_TYPE:
				return objectMappingsByType != null && !objectMappingsByType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //JigsawMappingModelImpl
