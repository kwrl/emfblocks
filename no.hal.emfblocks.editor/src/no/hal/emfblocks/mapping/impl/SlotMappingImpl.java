/**
 */
package no.hal.emfblocks.mapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.SlotMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Slot Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl#isHorizontal <em>Horizontal</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl#isDominant <em>Dominant</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl#getCellX <em>Cell X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.SlotMappingImpl#getCellY <em>Cell Y</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SlotMappingImpl extends MinimalEObjectImpl.Container implements SlotMapping {
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
	 * The default value of the '{@link #isHorizontal() <em>Horizontal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHorizontal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HORIZONTAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHorizontal() <em>Horizontal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHorizontal()
	 * @generated
	 * @ordered
	 */
	protected boolean horizontal = HORIZONTAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isDominant() <em>Dominant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDominant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DOMINANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDominant() <em>Dominant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDominant()
	 * @generated
	 * @ordered
	 */
	protected boolean dominant = DOMINANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCellX() <em>Cell X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellX()
	 * @generated
	 * @ordered
	 */
	protected static final int CELL_X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCellX() <em>Cell X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellX()
	 * @generated
	 * @ordered
	 */
	protected int cellX = CELL_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getCellY() <em>Cell Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellY()
	 * @generated
	 * @ordered
	 */
	protected static final int CELL_Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCellY() <em>Cell Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellY()
	 * @generated
	 * @ordered
	 */
	protected int cellY = CELL_Y_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlotMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.SLOT_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.SLOT_MAPPING__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHorizontal(boolean newHorizontal) {
		boolean oldHorizontal = horizontal;
		horizontal = newHorizontal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.SLOT_MAPPING__HORIZONTAL, oldHorizontal, horizontal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDominant() {
		return dominant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDominant(boolean newDominant) {
		boolean oldDominant = dominant;
		dominant = newDominant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.SLOT_MAPPING__DOMINANT, oldDominant, dominant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCellX() {
		return cellX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCellX(int newCellX) {
		int oldCellX = cellX;
		cellX = newCellX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.SLOT_MAPPING__CELL_X, oldCellX, cellX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCellY() {
		return cellY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCellY(int newCellY) {
		int oldCellY = cellY;
		cellY = newCellY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.SLOT_MAPPING__CELL_Y, oldCellY, cellY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.SLOT_MAPPING__ENABLED:
				return isEnabled();
			case MappingPackage.SLOT_MAPPING__HORIZONTAL:
				return isHorizontal();
			case MappingPackage.SLOT_MAPPING__DOMINANT:
				return isDominant();
			case MappingPackage.SLOT_MAPPING__CELL_X:
				return getCellX();
			case MappingPackage.SLOT_MAPPING__CELL_Y:
				return getCellY();
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
			case MappingPackage.SLOT_MAPPING__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case MappingPackage.SLOT_MAPPING__HORIZONTAL:
				setHorizontal((Boolean)newValue);
				return;
			case MappingPackage.SLOT_MAPPING__DOMINANT:
				setDominant((Boolean)newValue);
				return;
			case MappingPackage.SLOT_MAPPING__CELL_X:
				setCellX((Integer)newValue);
				return;
			case MappingPackage.SLOT_MAPPING__CELL_Y:
				setCellY((Integer)newValue);
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
			case MappingPackage.SLOT_MAPPING__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case MappingPackage.SLOT_MAPPING__HORIZONTAL:
				setHorizontal(HORIZONTAL_EDEFAULT);
				return;
			case MappingPackage.SLOT_MAPPING__DOMINANT:
				setDominant(DOMINANT_EDEFAULT);
				return;
			case MappingPackage.SLOT_MAPPING__CELL_X:
				setCellX(CELL_X_EDEFAULT);
				return;
			case MappingPackage.SLOT_MAPPING__CELL_Y:
				setCellY(CELL_Y_EDEFAULT);
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
			case MappingPackage.SLOT_MAPPING__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case MappingPackage.SLOT_MAPPING__HORIZONTAL:
				return horizontal != HORIZONTAL_EDEFAULT;
			case MappingPackage.SLOT_MAPPING__DOMINANT:
				return dominant != DOMINANT_EDEFAULT;
			case MappingPackage.SLOT_MAPPING__CELL_X:
				return cellX != CELL_X_EDEFAULT;
			case MappingPackage.SLOT_MAPPING__CELL_Y:
				return cellY != CELL_Y_EDEFAULT;
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
		result.append(", horizontal: ");
		result.append(horizontal);
		result.append(", dominant: ");
		result.append(dominant);
		result.append(", cellX: ");
		result.append(cellX);
		result.append(", cellY: ");
		result.append(cellY);
		result.append(')');
		return result.toString();
	}

} //SlotMappingImpl
