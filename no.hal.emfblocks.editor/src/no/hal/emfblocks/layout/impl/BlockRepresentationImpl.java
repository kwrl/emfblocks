/**
 */
package no.hal.emfblocks.layout.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.WorkAreaRepresentation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Representation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.impl.BlockRepresentationImpl#getPreferredX <em>Preferred X</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.BlockRepresentationImpl#getPreferredY <em>Preferred Y</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.BlockRepresentationImpl#getPreferredContainer <em>Preferred Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlockRepresentationImpl extends AbstractRepresentationImpl implements BlockRepresentation {
	/**
	 * The default value of the '{@link #getPreferredX() <em>Preferred X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredX()
	 * @generated
	 * @ordered
	 */
	protected static final int PREFERRED_X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPreferredX() <em>Preferred X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredX()
	 * @generated
	 * @ordered
	 */
	protected int preferredX = PREFERRED_X_EDEFAULT;

	/**
	 * This is true if the Preferred X attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preferredXESet;

	/**
	 * The default value of the '{@link #getPreferredY() <em>Preferred Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredY()
	 * @generated
	 * @ordered
	 */
	protected static final int PREFERRED_Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPreferredY() <em>Preferred Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredY()
	 * @generated
	 * @ordered
	 */
	protected int preferredY = PREFERRED_Y_EDEFAULT;

	/**
	 * This is true if the Preferred Y attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preferredYESet;

	/**
	 * The cached value of the '{@link #getPreferredContainer() <em>Preferred Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredContainer()
	 * @generated
	 * @ordered
	 */
	protected WorkAreaRepresentation preferredContainer;

	/**
	 * This is true if the Preferred Container reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preferredContainerESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockRepresentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LayoutPackage.Literals.BLOCK_REPRESENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPreferredX() {
		return preferredX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredX(int newPreferredX) {
		int oldPreferredX = preferredX;
		preferredX = newPreferredX;
		boolean oldPreferredXESet = preferredXESet;
		preferredXESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X, oldPreferredX, preferredX, !oldPreferredXESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreferredX() {
		int oldPreferredX = preferredX;
		boolean oldPreferredXESet = preferredXESet;
		preferredX = PREFERRED_X_EDEFAULT;
		preferredXESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X, oldPreferredX, PREFERRED_X_EDEFAULT, oldPreferredXESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreferredX() {
		return preferredXESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPreferredY() {
		return preferredY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredY(int newPreferredY) {
		int oldPreferredY = preferredY;
		preferredY = newPreferredY;
		boolean oldPreferredYESet = preferredYESet;
		preferredYESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y, oldPreferredY, preferredY, !oldPreferredYESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreferredY() {
		int oldPreferredY = preferredY;
		boolean oldPreferredYESet = preferredYESet;
		preferredY = PREFERRED_Y_EDEFAULT;
		preferredYESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y, oldPreferredY, PREFERRED_Y_EDEFAULT, oldPreferredYESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreferredY() {
		return preferredYESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkAreaRepresentation getPreferredContainer() {
		if (preferredContainer != null && preferredContainer.eIsProxy()) {
			InternalEObject oldPreferredContainer = (InternalEObject)preferredContainer;
			preferredContainer = (WorkAreaRepresentation)eResolveProxy(oldPreferredContainer);
			if (preferredContainer != oldPreferredContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER, oldPreferredContainer, preferredContainer));
			}
		}
		return preferredContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkAreaRepresentation basicGetPreferredContainer() {
		return preferredContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredContainer(WorkAreaRepresentation newPreferredContainer) {
		WorkAreaRepresentation oldPreferredContainer = preferredContainer;
		preferredContainer = newPreferredContainer;
		boolean oldPreferredContainerESet = preferredContainerESet;
		preferredContainerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER, oldPreferredContainer, preferredContainer, !oldPreferredContainerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreferredContainer() {
		WorkAreaRepresentation oldPreferredContainer = preferredContainer;
		boolean oldPreferredContainerESet = preferredContainerESet;
		preferredContainer = null;
		preferredContainerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER, oldPreferredContainer, null, oldPreferredContainerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreferredContainer() {
		return preferredContainerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X:
				return getPreferredX();
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y:
				return getPreferredY();
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER:
				if (resolve) return getPreferredContainer();
				return basicGetPreferredContainer();
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
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X:
				setPreferredX((Integer)newValue);
				return;
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y:
				setPreferredY((Integer)newValue);
				return;
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER:
				setPreferredContainer((WorkAreaRepresentation)newValue);
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
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X:
				unsetPreferredX();
				return;
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y:
				unsetPreferredY();
				return;
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER:
				unsetPreferredContainer();
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
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_X:
				return isSetPreferredX();
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_Y:
				return isSetPreferredY();
			case LayoutPackage.BLOCK_REPRESENTATION__PREFERRED_CONTAINER:
				return isSetPreferredContainer();
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
		result.append(" (preferredX: ");
		if (preferredXESet) result.append(preferredX); else result.append("<unset>");
		result.append(", preferredY: ");
		if (preferredYESet) result.append(preferredY); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //BlockRepresentationImpl
