/**
 */
package no.hal.emfblocks.mapping.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import no.hal.emfblocks.mapping.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see no.hal.emfblocks.mapping.MappingPackage
 * @generated
 */
public class MappingAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MappingPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MappingPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingSwitch<Adapter> modelSwitch =
		new MappingSwitch<Adapter>() {
			@Override
			public Adapter caseMappingModel(MappingModel object) {
				return createMappingModelAdapter();
			}
			@Override
			public Adapter caseStringToObjectMapping(Map.Entry<String, ObjectMapping> object) {
				return createStringToObjectMappingAdapter();
			}
			@Override
			public Adapter caseObjectMapping(ObjectMapping object) {
				return createObjectMappingAdapter();
			}
			@Override
			public Adapter caseNoMapping(NoMapping object) {
				return createNoMappingAdapter();
			}
			@Override
			public Adapter caseBlockMapping(BlockMapping object) {
				return createBlockMappingAdapter();
			}
			@Override
			public Adapter caseStringToBlockFormMapping(Map.Entry<String, BlockFormMapping> object) {
				return createStringToBlockFormMappingAdapter();
			}
			@Override
			public Adapter caseBlockFormMapping(BlockFormMapping object) {
				return createBlockFormMappingAdapter();
			}
			@Override
			public Adapter caseStringToSlotMapping(Map.Entry<String, SlotMapping> object) {
				return createStringToSlotMappingAdapter();
			}
			@Override
			public Adapter caseSlotMapping(SlotMapping object) {
				return createSlotMappingAdapter();
			}
			@Override
			public Adapter caseWorkAreaMapping(WorkAreaMapping object) {
				return createWorkAreaMappingAdapter();
			}
			@Override
			public Adapter caseStringToWorkAreaContainment(Map.Entry<String, WorkAreaContainment> object) {
				return createStringToWorkAreaContainmentAdapter();
			}
			@Override
			public Adapter caseWorkAreaContainment(WorkAreaContainment object) {
				return createWorkAreaContainmentAdapter();
			}
			@Override
			public Adapter caseStringToWorkAreaFormMapping(Map.Entry<String, WorkAreaFormMapping> object) {
				return createStringToWorkAreaFormMappingAdapter();
			}
			@Override
			public Adapter caseWorkAreaFormMapping(WorkAreaFormMapping object) {
				return createWorkAreaFormMappingAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.MappingModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.MappingModel
	 * @generated
	 */
	public Adapter createMappingModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Object Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToObjectMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.ObjectMapping <em>Object Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.ObjectMapping
	 * @generated
	 */
	public Adapter createObjectMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.NoMapping <em>No Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.NoMapping
	 * @generated
	 */
	public Adapter createNoMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.BlockMapping <em>Block Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.BlockMapping
	 * @generated
	 */
	public Adapter createBlockMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Block Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToBlockFormMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.BlockFormMapping <em>Block Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.BlockFormMapping
	 * @generated
	 */
	public Adapter createBlockFormMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Slot Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToSlotMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.SlotMapping <em>Slot Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.SlotMapping
	 * @generated
	 */
	public Adapter createSlotMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.WorkAreaMapping <em>Work Area Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.WorkAreaMapping
	 * @generated
	 */
	public Adapter createWorkAreaMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Work Area Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToWorkAreaContainmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.WorkAreaContainment <em>Work Area Containment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.WorkAreaContainment
	 * @generated
	 */
	public Adapter createWorkAreaContainmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Work Area Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToWorkAreaFormMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.emfblocks.mapping.WorkAreaFormMapping <em>Work Area Form Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.emfblocks.mapping.WorkAreaFormMapping
	 * @generated
	 */
	public Adapter createWorkAreaFormMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //EmfjigsawmappingmodelAdapterFactory
