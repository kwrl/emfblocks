/**
 */
package no.hal.emfblocks.mapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.WorkAreaFormMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Area Form Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#getGridX <em>Grid X</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#getGridY <em>Grid Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#getGridWidth <em>Grid Width</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#getGridHeight <em>Grid Height</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.WorkAreaFormMappingImpl#getAttributeFormFactoryClass <em>Attribute Form Factory Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkAreaFormMappingImpl extends MinimalEObjectImpl.Container implements WorkAreaFormMapping {
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
	 * The default value of the '{@link #getGridX() <em>Grid X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridX()
	 * @generated
	 * @ordered
	 */
	protected static final int GRID_X_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getGridX() <em>Grid X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridX()
	 * @generated
	 * @ordered
	 */
	protected int gridX = GRID_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridY() <em>Grid Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridY()
	 * @generated
	 * @ordered
	 */
	protected static final int GRID_Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getGridY() <em>Grid Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridY()
	 * @generated
	 * @ordered
	 */
	protected int gridY = GRID_Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridWidth() <em>Grid Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int GRID_WIDTH_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getGridWidth() <em>Grid Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridWidth()
	 * @generated
	 * @ordered
	 */
	protected int gridWidth = GRID_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridHeight() <em>Grid Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int GRID_HEIGHT_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getGridHeight() <em>Grid Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridHeight()
	 * @generated
	 * @ordered
	 */
	protected int gridHeight = GRID_HEIGHT_EDEFAULT;

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
	protected WorkAreaFormMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.WORK_AREA_FORM_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getGridX() {
		return gridX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGridX(int newGridX) {
		int oldGridX = gridX;
		gridX = newGridX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__GRID_X, oldGridX, gridX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getGridY() {
		return gridY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGridY(int newGridY) {
		int oldGridY = gridY;
		gridY = newGridY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__GRID_Y, oldGridY, gridY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getGridWidth() {
		return gridWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGridWidth(int newGridWidth) {
		int oldGridWidth = gridWidth;
		gridWidth = newGridWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__GRID_WIDTH, oldGridWidth, gridWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getGridHeight() {
		return gridHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGridHeight(int newGridHeight) {
		int oldGridHeight = gridHeight;
		gridHeight = newGridHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__GRID_HEIGHT, oldGridHeight, gridHeight));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS, oldAttributeFormFactoryClass, attributeFormFactoryClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.WORK_AREA_FORM_MAPPING__ENABLED:
				return isEnabled();
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_X:
				return getGridX();
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_Y:
				return getGridY();
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_WIDTH:
				return getGridWidth();
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_HEIGHT:
				return getGridHeight();
			case MappingPackage.WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
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
			case MappingPackage.WORK_AREA_FORM_MAPPING__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_X:
				setGridX((Integer)newValue);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_Y:
				setGridY((Integer)newValue);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_WIDTH:
				setGridWidth((Integer)newValue);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_HEIGHT:
				setGridHeight((Integer)newValue);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
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
			case MappingPackage.WORK_AREA_FORM_MAPPING__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_X:
				setGridX(GRID_X_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_Y:
				setGridY(GRID_Y_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_WIDTH:
				setGridWidth(GRID_WIDTH_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_HEIGHT:
				setGridHeight(GRID_HEIGHT_EDEFAULT);
				return;
			case MappingPackage.WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
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
			case MappingPackage.WORK_AREA_FORM_MAPPING__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_X:
				return gridX != GRID_X_EDEFAULT;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_Y:
				return gridY != GRID_Y_EDEFAULT;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_WIDTH:
				return gridWidth != GRID_WIDTH_EDEFAULT;
			case MappingPackage.WORK_AREA_FORM_MAPPING__GRID_HEIGHT:
				return gridHeight != GRID_HEIGHT_EDEFAULT;
			case MappingPackage.WORK_AREA_FORM_MAPPING__ATTRIBUTE_FORM_FACTORY_CLASS:
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
		result.append(", gridX: ");
		result.append(gridX);
		result.append(", gridY: ");
		result.append(gridY);
		result.append(", gridWidth: ");
		result.append(gridWidth);
		result.append(", gridHeight: ");
		result.append(gridHeight);
		result.append(", attributeFormFactoryClass: ");
		result.append(attributeFormFactoryClass);
		result.append(')');
		return result.toString();
	}

} //WorkAreaFormMappingImpl
