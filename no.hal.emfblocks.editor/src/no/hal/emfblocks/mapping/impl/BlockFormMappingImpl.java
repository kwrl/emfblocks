/**
 */
package no.hal.emfblocks.mapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import no.hal.emfblocks.mapping.BlockFormMapping;
import no.hal.emfblocks.mapping.MappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Form Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl#getCellX <em>Cell X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl#getCellY <em>Cell Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockFormMappingImpl#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlockFormMappingImpl extends MinimalEObjectImpl.Container implements BlockFormMapping {
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
	 * The default value of the '{@link #getAttributeFormFactoryClass() <em>Attribute Form Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeFormFactoryClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTE_FORM_FACTORY_CLASS_EDEFAULT = "no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory";

	/**
	 * The cached value of the '{@link #getAttributeFormFactoryClass() <em>Attribute Form Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeFormFactoryClass()
	 * @generated
	 * @ordered
	 */
	protected String attributeFormFactoryClass = ATTRIBUTE_FORM_FACTORY_CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockFormMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.BLOCK_FORM_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_FORM_MAPPING__ENABLED, oldEnabled, enabled));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_FORM_MAPPING__CELL_X, oldCellX, cellX));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_FORM_MAPPING__CELL_Y, oldCellY, cellY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttributeFormFactoryClass() {
		return attributeFormFactoryClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeFormFactoryClass(String newAttributeFormFactoryClass) {
		String oldAttributeFormFactoryClass = attributeFormFactoryClass;
		attributeFormFactoryClass = newAttributeFormFactoryClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS, oldAttributeFormFactoryClass, attributeFormFactoryClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.BLOCK_FORM_MAPPING__ENABLED:
				return isEnabled();
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_X:
				return getCellX();
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_Y:
				return getCellY();
			case MappingPackage.BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
				return getAttributeFormFactoryClass();
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
			case MappingPackage.BLOCK_FORM_MAPPING__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_X:
				setCellX((Integer)newValue);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_Y:
				setCellY((Integer)newValue);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
				setAttributeFormFactoryClass((String)newValue);
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
			case MappingPackage.BLOCK_FORM_MAPPING__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_X:
				setCellX(CELL_X_EDEFAULT);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_Y:
				setCellY(CELL_Y_EDEFAULT);
				return;
			case MappingPackage.BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
				setAttributeFormFactoryClass(ATTRIBUTE_FORM_FACTORY_CLASS_EDEFAULT);
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
			case MappingPackage.BLOCK_FORM_MAPPING__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_X:
				return cellX != CELL_X_EDEFAULT;
			case MappingPackage.BLOCK_FORM_MAPPING__CELL_Y:
				return cellY != CELL_Y_EDEFAULT;
			case MappingPackage.BLOCK_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
				return ATTRIBUTE_FORM_FACTORY_CLASS_EDEFAULT == null ? attributeFormFactoryClass != null : !ATTRIBUTE_FORM_FACTORY_CLASS_EDEFAULT.equals(attributeFormFactoryClass);
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
		result.append(", cellX: ");
		result.append(cellX);
		result.append(", cellY: ");
		result.append(cellY);
		result.append(", attributeFormFactoryClass: ");
		result.append(attributeFormFactoryClass);
		result.append(')');
		return result.toString();
	}

} //BlockFormMappingImpl
