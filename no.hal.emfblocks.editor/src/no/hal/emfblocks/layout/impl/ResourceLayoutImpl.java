/**
 */
package no.hal.emfblocks.layout.impl;

import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.AbstractRepresentation;
import no.hal.emfblocks.layout.PointerArrowMode;
import no.hal.emfblocks.layout.ResourceLayout;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.palette.Palette;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getRepresentationsByObject <em>Representations By Object</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getMappingModel <em>Mapping Model</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getPalette <em>Palette</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getFocusedTabPaneIndex <em>Focused Tab Pane Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getFocusedTabIndex <em>Focused Tab Index</em>}</li>
 *   <li>{@link no.hal.emfblocks.layout.impl.ResourceLayoutImpl#getShowPointerArrows <em>Show Pointer Arrows</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceLayoutImpl extends MinimalEObjectImpl.Container implements ResourceLayout {
	/**
	 * The cached value of the '{@link #getRepresentationsByObject() <em>Representations By Object</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentationsByObject()
	 * @generated
	 * @ordered
	 */
	protected EMap<EObject, AbstractRepresentation> representationsByObject;
	/**
	 * The cached value of the '{@link #getMappingModel() <em>Mapping Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappingModel()
	 * @generated
	 * @ordered
	 */
	protected MappingModel mappingModel;
	/**
	 * The cached value of the '{@link #getPalette() <em>Palette</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPalette()
	 * @generated
	 * @ordered
	 */
	protected Palette palette;
	/**
	 * The default value of the '{@link #getFocusedTabPaneIndex() <em>Focused Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFocusedTabPaneIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int FOCUSED_TAB_PANE_INDEX_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getFocusedTabPaneIndex() <em>Focused Tab Pane Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFocusedTabPaneIndex()
	 * @generated
	 * @ordered
	 */
	protected int focusedTabPaneIndex = FOCUSED_TAB_PANE_INDEX_EDEFAULT;
	/**
	 * The default value of the '{@link #getFocusedTabIndex() <em>Focused Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFocusedTabIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int FOCUSED_TAB_INDEX_EDEFAULT = -1;
	/**
	 * The cached value of the '{@link #getFocusedTabIndex() <em>Focused Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFocusedTabIndex()
	 * @generated
	 * @ordered
	 */
	protected int focusedTabIndex = FOCUSED_TAB_INDEX_EDEFAULT;
	/**
	 * The default value of the '{@link #getShowPointerArrows() <em>Show Pointer Arrows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShowPointerArrows()
	 * @generated
	 * @ordered
	 */
	protected static final PointerArrowMode SHOW_POINTER_ARROWS_EDEFAULT = PointerArrowMode.ALWAYS;
	/**
	 * The cached value of the '{@link #getShowPointerArrows() <em>Show Pointer Arrows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShowPointerArrows()
	 * @generated
	 * @ordered
	 */
	protected PointerArrowMode showPointerArrows = SHOW_POINTER_ARROWS_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceLayoutImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LayoutPackage.Literals.RESOURCE_LAYOUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<EObject, AbstractRepresentation> getRepresentationsByObject() {
		if (representationsByObject == null) {
			representationsByObject = new EcoreEMap<EObject,AbstractRepresentation>(LayoutPackage.Literals.EOBJECT_TO_JIGSAW_REPRESENTATION, EObjectToJigsawRepresentationImpl.class, this, LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT);
		}
		return representationsByObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModel getMappingModel() {
		if (mappingModel != null && mappingModel.eIsProxy()) {
			InternalEObject oldMappingModel = (InternalEObject)mappingModel;
			mappingModel = (MappingModel)eResolveProxy(oldMappingModel);
			if (mappingModel != oldMappingModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL, oldMappingModel, mappingModel));
			}
		}
		return mappingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModel basicGetMappingModel() {
		return mappingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappingModel(MappingModel newMappingModel) {
		MappingModel oldMappingModel = mappingModel;
		mappingModel = newMappingModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL, oldMappingModel, mappingModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Palette getPalette() {
		if (palette != null && palette.eIsProxy()) {
			InternalEObject oldPalette = (InternalEObject)palette;
			palette = (Palette)eResolveProxy(oldPalette);
			if (palette != oldPalette) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutPackage.RESOURCE_LAYOUT__PALETTE, oldPalette, palette));
			}
		}
		return palette;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Palette basicGetPalette() {
		return palette;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPalette(Palette newPalette) {
		Palette oldPalette = palette;
		palette = newPalette;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.RESOURCE_LAYOUT__PALETTE, oldPalette, palette));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFocusedTabPaneIndex() {
		return focusedTabPaneIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFocusedTabPaneIndex(int newFocusedTabPaneIndex) {
		int oldFocusedTabPaneIndex = focusedTabPaneIndex;
		focusedTabPaneIndex = newFocusedTabPaneIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX, oldFocusedTabPaneIndex, focusedTabPaneIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFocusedTabIndex() {
		return focusedTabIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFocusedTabIndex(int newFocusedTabIndex) {
		int oldFocusedTabIndex = focusedTabIndex;
		focusedTabIndex = newFocusedTabIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_INDEX, oldFocusedTabIndex, focusedTabIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PointerArrowMode getShowPointerArrows() {
		return showPointerArrows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShowPointerArrows(PointerArrowMode newShowPointerArrows) {
		PointerArrowMode oldShowPointerArrows = showPointerArrows;
		showPointerArrows = newShowPointerArrows == null ? SHOW_POINTER_ARROWS_EDEFAULT : newShowPointerArrows;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LayoutPackage.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS, oldShowPointerArrows, showPointerArrows));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT:
				return ((InternalEList<?>)getRepresentationsByObject()).basicRemove(otherEnd, msgs);
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
			case LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT:
				if (coreType) return getRepresentationsByObject();
				else return getRepresentationsByObject().map();
			case LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL:
				if (resolve) return getMappingModel();
				return basicGetMappingModel();
			case LayoutPackage.RESOURCE_LAYOUT__PALETTE:
				if (resolve) return getPalette();
				return basicGetPalette();
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX:
				return getFocusedTabPaneIndex();
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_INDEX:
				return getFocusedTabIndex();
			case LayoutPackage.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS:
				return getShowPointerArrows();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT:
				((EStructuralFeature.Setting)getRepresentationsByObject()).set(newValue);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL:
				setMappingModel((MappingModel)newValue);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__PALETTE:
				setPalette((Palette)newValue);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX:
				setFocusedTabPaneIndex((Integer)newValue);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_INDEX:
				setFocusedTabIndex((Integer)newValue);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS:
				setShowPointerArrows((PointerArrowMode)newValue);
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
			case LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT:
				getRepresentationsByObject().clear();
				return;
			case LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL:
				setMappingModel((MappingModel)null);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__PALETTE:
				setPalette((Palette)null);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX:
				setFocusedTabPaneIndex(FOCUSED_TAB_PANE_INDEX_EDEFAULT);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_INDEX:
				setFocusedTabIndex(FOCUSED_TAB_INDEX_EDEFAULT);
				return;
			case LayoutPackage.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS:
				setShowPointerArrows(SHOW_POINTER_ARROWS_EDEFAULT);
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
			case LayoutPackage.RESOURCE_LAYOUT__REPRESENTATIONS_BY_OBJECT:
				return representationsByObject != null && !representationsByObject.isEmpty();
			case LayoutPackage.RESOURCE_LAYOUT__MAPPING_MODEL:
				return mappingModel != null;
			case LayoutPackage.RESOURCE_LAYOUT__PALETTE:
				return palette != null;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_PANE_INDEX:
				return focusedTabPaneIndex != FOCUSED_TAB_PANE_INDEX_EDEFAULT;
			case LayoutPackage.RESOURCE_LAYOUT__FOCUSED_TAB_INDEX:
				return focusedTabIndex != FOCUSED_TAB_INDEX_EDEFAULT;
			case LayoutPackage.RESOURCE_LAYOUT__SHOW_POINTER_ARROWS:
				return showPointerArrows != SHOW_POINTER_ARROWS_EDEFAULT;
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
		result.append(" (focusedTabPaneIndex: ");
		result.append(focusedTabPaneIndex);
		result.append(", focusedTabIndex: ");
		result.append(focusedTabIndex);
		result.append(", showPointerArrows: ");
		result.append(showPointerArrows);
		result.append(')');
		return result.toString();
	}

} //ResourceLayoutImpl
