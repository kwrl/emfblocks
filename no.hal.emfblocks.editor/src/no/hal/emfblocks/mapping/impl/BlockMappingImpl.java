/**
 */
package no.hal.emfblocks.mapping.impl;

import javafx.scene.paint.Color;
import no.hal.emfblocks.mapping.BlockFormMapping;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.MappingFactory;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.SlotMapping;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl#getDefaultColor <em>Default Color</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl#getNameInfo <em>Name Info</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl#getFormsByAttribute <em>Forms By Attribute</em>}</li>
 *   <li>{@link no.hal.emfblocks.mapping.impl.BlockMappingImpl#getSlotsByReference <em>Slots By Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlockMappingImpl extends ObjectMappingImpl implements BlockMapping {
	/**
	 * The default value of the '{@link #getDefaultColor() <em>Default Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color DEFAULT_COLOR_EDEFAULT = (Color)MappingFactory.eINSTANCE.createFromString(MappingPackage.eINSTANCE.getEColor(), "C0C0C0");

	/**
	 * The cached value of the '{@link #getDefaultColor() <em>Default Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultColor()
	 * @generated
	 * @ordered
	 */
	protected Color defaultColor = DEFAULT_COLOR_EDEFAULT;

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
	 * The default value of the '{@link #getNameInfo() <em>Name Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNameInfo() <em>Name Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameInfo()
	 * @generated
	 * @ordered
	 */
	protected String nameInfo = NAME_INFO_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFormsByAttribute() <em>Forms By Attribute</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormsByAttribute()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, BlockFormMapping> formsByAttribute;

	/**
	 * The cached value of the '{@link #getSlotsByReference() <em>Slots By Reference</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlotsByReference()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, SlotMapping> slotsByReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.BLOCK_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_MAPPING__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNameInfo() {
		return nameInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNameInfo(String newNameInfo) {
		String oldNameInfo = nameInfo;
		nameInfo = newNameInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_MAPPING__NAME_INFO, oldNameInfo, nameInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getDefaultColor() {
		return defaultColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultColor(Color newDefaultColor) {
		Color oldDefaultColor = defaultColor;
		defaultColor = newDefaultColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.BLOCK_MAPPING__DEFAULT_COLOR, oldDefaultColor, defaultColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, BlockFormMapping> getFormsByAttribute() {
		if (formsByAttribute == null) {
			formsByAttribute = new EcoreEMap<String,BlockFormMapping>(MappingPackage.Literals.STRING_TO_BLOCK_FORM_MAPPING, StringToBlockFormMappingImpl.class, this, MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE);
		}
		return formsByAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, SlotMapping> getSlotsByReference() {
		if (slotsByReference == null) {
			slotsByReference = new EcoreEMap<String,SlotMapping>(MappingPackage.Literals.STRING_TO_SLOT_MAPPING, StringToSlotMappingImpl.class, this, MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE);
		}
		return slotsByReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE:
				return ((InternalEList<?>)getFormsByAttribute()).basicRemove(otherEnd, msgs);
			case MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE:
				return ((InternalEList<?>)getSlotsByReference()).basicRemove(otherEnd, msgs);
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
			case MappingPackage.BLOCK_MAPPING__DEFAULT_COLOR:
				return getDefaultColor();
			case MappingPackage.BLOCK_MAPPING__ENABLED:
				return isEnabled();
			case MappingPackage.BLOCK_MAPPING__NAME_INFO:
				return getNameInfo();
			case MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE:
				if (coreType) return getFormsByAttribute();
				else return getFormsByAttribute().map();
			case MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE:
				if (coreType) return getSlotsByReference();
				else return getSlotsByReference().map();
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
			case MappingPackage.BLOCK_MAPPING__DEFAULT_COLOR:
				setDefaultColor((Color)newValue);
				return;
			case MappingPackage.BLOCK_MAPPING__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case MappingPackage.BLOCK_MAPPING__NAME_INFO:
				setNameInfo((String)newValue);
				return;
			case MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE:
				((EStructuralFeature.Setting)getFormsByAttribute()).set(newValue);
				return;
			case MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE:
				((EStructuralFeature.Setting)getSlotsByReference()).set(newValue);
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
			case MappingPackage.BLOCK_MAPPING__DEFAULT_COLOR:
				setDefaultColor(DEFAULT_COLOR_EDEFAULT);
				return;
			case MappingPackage.BLOCK_MAPPING__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case MappingPackage.BLOCK_MAPPING__NAME_INFO:
				setNameInfo(NAME_INFO_EDEFAULT);
				return;
			case MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE:
				getFormsByAttribute().clear();
				return;
			case MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE:
				getSlotsByReference().clear();
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
			case MappingPackage.BLOCK_MAPPING__DEFAULT_COLOR:
				return DEFAULT_COLOR_EDEFAULT == null ? defaultColor != null : !DEFAULT_COLOR_EDEFAULT.equals(defaultColor);
			case MappingPackage.BLOCK_MAPPING__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case MappingPackage.BLOCK_MAPPING__NAME_INFO:
				return NAME_INFO_EDEFAULT == null ? nameInfo != null : !NAME_INFO_EDEFAULT.equals(nameInfo);
			case MappingPackage.BLOCK_MAPPING__FORMS_BY_ATTRIBUTE:
				return formsByAttribute != null && !formsByAttribute.isEmpty();
			case MappingPackage.BLOCK_MAPPING__SLOTS_BY_REFERENCE:
				return slotsByReference != null && !slotsByReference.isEmpty();
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
		result.append(" (defaultColor: ");
		result.append(defaultColor);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", nameInfo: ");
		result.append(nameInfo);
		result.append(')');
		return result.toString();
	}

} //BlockMappingImpl
