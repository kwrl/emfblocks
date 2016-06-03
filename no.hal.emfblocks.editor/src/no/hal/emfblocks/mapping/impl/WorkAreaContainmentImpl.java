/**
 */
package no.hal.emfblocks.mapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.WorkAreaContainment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Area Containment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaContainmentImpl#getContainmentReference <em>Containment Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkAreaContainmentImpl extends MinimalEObjectImpl.Container implements WorkAreaContainment {
	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getContainmentReference() <em>Containment Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainmentReference()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINMENT_REFERENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainmentReference() <em>Containment Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainmentReference()
	 * @generated
	 * @ordered
	 */
	protected String containmentReference = CONTAINMENT_REFERENCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkAreaContainmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.WORK_AREA_CONTAINMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_CONTAINMENT__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContainmentReference() {
		return containmentReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainmentReference(String newContainmentReference) {
		String oldContainmentReference = containmentReference;
		containmentReference = newContainmentReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE, oldContainmentReference, containmentReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.WORK_AREA_CONTAINMENT__ENABLED:
				return isEnabled();
			case MappingPackage.WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE:
				return getContainmentReference();
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
			case MappingPackage.WORK_AREA_CONTAINMENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case MappingPackage.WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE:
				setContainmentReference((String)newValue);
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
			case MappingPackage.WORK_AREA_CONTAINMENT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE:
				setContainmentReference(CONTAINMENT_REFERENCE_EDEFAULT);
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
			case MappingPackage.WORK_AREA_CONTAINMENT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case MappingPackage.WORK_AREA_CONTAINMENT__CONTAINMENT_REFERENCE:
				return CONTAINMENT_REFERENCE_EDEFAULT == null ? containmentReference != null : !CONTAINMENT_REFERENCE_EDEFAULT.equals(containmentReference);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (enabled: ");
		result.append(enabled);
		result.append(", containmentReference: ");
		result.append(containmentReference);
		result.append(')');
		return result.toString();
	}

} //WorkAreaContainmentImpl
