/**
 */
package no.hal.emfblocks.layout.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.WorkAreaRepresentation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Area Representation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl#getPreferredTabPaneIndex <em>Preferred Tab Pane Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.WorkAreaRepresentationImpl#getPreferredTabIndex <em>Preferred Tab Index</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkAreaRepresentationImpl extends AbstractRepresentationImpl implements WorkAreaRepresentation {
	/**
	 * The default value of the '{@link #getPreferredTabPaneIndex() <em>Preferred Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredTabPaneIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int PREFERRED_TAB_PANE_INDEX_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getPreferredTabPaneIndex() <em>Preferred Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredTabPaneIndex()
	 * @generated
	 * @ordered
	 */
	protected int preferredTabPaneIndex = PREFERRED_TAB_PANE_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredTabIndex() <em>Preferred Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredTabIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int PREFERRED_TAB_INDEX_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getPreferredTabIndex() <em>Preferred Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredTabIndex()
	 * @generated
	 * @ordered
	 */
	protected int preferredTabIndex = PREFERRED_TAB_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkAreaRepresentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LayoutPackage.Literals.WORK_AREA_REPRESENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPreferredTabPaneIndex() {
		return preferredTabPaneIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredTabPaneIndex(int newPreferredTabPaneIndex) {
		int oldPreferredTabPaneIndex = preferredTabPaneIndex;
		preferredTabPaneIndex = newPreferredTabPaneIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX, oldPreferredTabPaneIndex, preferredTabPaneIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPreferredTabIndex() {
		return preferredTabIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredTabIndex(int newPreferredTabIndex) {
		int oldPreferredTabIndex = preferredTabIndex;
		preferredTabIndex = newPreferredTabIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX, oldPreferredTabIndex, preferredTabIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX:
				return getPreferredTabPaneIndex();
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX:
				return getPreferredTabIndex();
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
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX:
				setPreferredTabPaneIndex((Integer)newValue);
				return;
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX:
				setPreferredTabIndex((Integer)newValue);
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
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX:
				setPreferredTabPaneIndex(PREFERRED_TAB_PANE_INDEX_EDEFAULT);
				return;
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX:
				setPreferredTabIndex(PREFERRED_TAB_INDEX_EDEFAULT);
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
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_PANE_INDEX:
				return preferredTabPaneIndex != PREFERRED_TAB_PANE_INDEX_EDEFAULT;
			case LayoutPackage.WORK_AREA_REPRESENTATION__PREFERRED_TAB_INDEX:
				return preferredTabIndex != PREFERRED_TAB_INDEX_EDEFAULT;
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
		result.append(" (preferredTabPaneIndex: ");
		result.append(preferredTabPaneIndex);
		result.append(", preferredTabIndex: ");
		result.append(preferredTabIndex);
		result.append(')');
		return result.toString();
	}

} //WorkAreaRepresentationImpl
