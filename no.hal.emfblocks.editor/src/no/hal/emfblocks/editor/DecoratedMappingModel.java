package no.hal.emfblocks.editor;

import java.util.Collection;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.mapping.BlockFormMapping;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.MappingFactory;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.mapping.ObjectMapping;
import no.hal.emfblocks.mapping.SlotMapping;
import no.hal.emfblocks.mapping.WorkAreaContainment;
import no.hal.emfblocks.mapping.WorkAreaFormMapping;
import no.hal.emfblocks.mapping.WorkAreaMapping;

public class DecoratedMappingModel {

	private final MappingModel mappingModel;

	public DecoratedMappingModel(MappingModel mapping) {
		this.mappingModel = mapping;
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(Collection<EPackage> packageList) {
		populateMissingElements(MMUtil.listClassifiers(packageList));
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(EPackage epackage) {
		populateMissingElements(MMUtil.listClassifiers(epackage));
	}

	/** @see #populateMissingElements(EClassifier[]) */
	public void populateMissingElements(WRoot root) {
		populateMissingElements(MMUtil.listClassifiers(root));
	}

	/**
	 * Populates the mapping model with default block mappings for classes
	 * without a mapping model element the given domain. This will also iterate
	 * through already existing mapping model elements and update them if they
	 * have missing elements.
	 * 
	 * @see #populateMissingElements(BlockMapping, EClass)
	 */
	public void populateMissingElements(EClassifier[] classifiers) {
		EMap<String, ObjectMapping> mappingsByType = mappingModel.getObjectMappingsByType();
		int numTypes = classifiers.length;
		for (int i = 0; i < numTypes; i++) {
			EClassifier type = classifiers[i];
			String fqn = MMUtil.fullyQualifiedName(type);
			if (type instanceof EClass) {
				ObjectMapping mapping = mappingsByType.get(fqn);
				if (mapping instanceof BlockMapping)
					populateMissingElements((BlockMapping) mapping, (EClass) type);
				else if (mapping instanceof WorkAreaMapping)
					populateMissingElements((WorkAreaMapping) mapping, (EClass) type);
				else // Update mapping model with new type
					mappingsByType.put(fqn, createDefaultBlockMapping((EClass) type, (float) i / numTypes));
			}
		}
	}

	/**
	 * Populates the work area mapping model with containment/form mapping
	 * models for elements that do not yet have a mapping model.
	 */
	public void populateMissingElements(WorkAreaMapping wamm, EClass type) {
		// Note: The WAMM contains elements for all attributes and fields by
		// default, not just declared ones.
		for (EAttribute attr : type.getEAllAttributes()) {
			if (attr.isChangeable()) {
				String name = attr.getName();
				if (!wamm.getFormsByAttribute().containsKey(name)) {
					WorkAreaFormMapping fmm = MappingFactory.eINSTANCE.createWorkAreaFormMapping();
					// Update mapping model
					wamm.getFormsByAttribute().put(name, fmm);
				}
			}
		}

		for (EReference ref : type.getEAllReferences()) {
			if (ref.isChangeable()) // derived?
			{
				if (ref.isContainment()) {
					String fqn = MMUtil.fullyQualifiedName(ref.getEReferenceType());
					if (!wamm.getContainmentsByType().containsKey(fqn)) {
						WorkAreaContainment existingContainment = getWorkAreaContainmentFor(wamm, type,
								ref.getEReferenceType());
						if (existingContainment != null)
							new Throwable("Warning: " + type.getName() + "." + ref.getName()
									+ "is shadowed because there is already a containment for " + fqn + " at "
									+ type.getName() + "." + existingContainment.getContainmentReference())
											.printStackTrace();

						WorkAreaContainment wac = MappingFactory.eINSTANCE.createWorkAreaContainment();
						wac.setContainmentReference(ref.getName());
						// Update mapping model
						wamm.getContainmentsByType().put(fqn, wac);
					}
				}
			}
		}
	}

	private WorkAreaContainment getWorkAreaContainmentFor(WorkAreaMapping wamm, EClass type, EClass eReferenceType) {
		// TODO inheritance?
		return wamm.getContainmentsByType().get(MMUtil.fullyQualifiedName(eReferenceType));
	}

	/**
	 * Populates the block mapping model with slot/form mapping models for
	 * elements that do not yet have a mapping model.
	 */
	public void populateMissingElements(BlockMapping bmm, EClass type) {
		// Note: Local stuff only
		for (EAttribute attr : type.getEAttributes()) {
			if (attr.isChangeable()) // derived?
			{
				String name = attr.getName();
				if (!bmm.getFormsByAttribute().containsKey(name)) {
					BlockFormMapping fmm = MappingFactory.eINSTANCE.createBlockFormMapping();
					// Update mapping model
					bmm.getFormsByAttribute().put(name, fmm);
				}
			}
		}

		for (EReference ref : type.getEReferences()) {
			if (ref.isChangeable()) // derived?
			{
				String name = ref.getName();
				if (!bmm.getSlotsByReference().containsKey(name)) {
					SlotMapping smm = MappingFactory.eINSTANCE.createSlotMapping();
					smm.setHorizontal(!ref.isMany());
					// Update mapping model
					bmm.getSlotsByReference().put(name, smm);
				}
			}
		}
	}

	/**
	 * Creates a default block mapping model.
	 * 
	 * @param type
	 *            The type for which to create the block mapping model.
	 * @param f
	 *            A value in the range [0,1>, preferably unique for this class.
	 *            Is used to generate arbitrary color values.
	 * @return A block mapping model for the given type, with the default
	 *         settings. The caller must register the object on the jigsaw
	 *         mapping model.
	 */
	private BlockMapping createDefaultBlockMapping(EClass type, float f) {
		BlockMapping bmm = MappingFactory.eINSTANCE.createBlockMapping();
		bmm.setDefaultColor(FXUtil.hsb(f, 0.5f, 1.0f));
		populateMissingElements(bmm, type);
		return bmm;
	}

	public BlockFormMapping getBlockFormMapping(EClass containerType, EAttribute attr) {
		// TODO inheritance?
		ObjectMapping mapping = getObjectMapping(attr.getEContainingClass());
		if (mapping == null || !(mapping instanceof BlockMapping))
			return null;
		BlockFormMapping fmm = ((BlockMapping) mapping).getFormsByAttribute().get(attr.getName());
		if (fmm == null || !fmm.isEnabled())
			return null;
		return fmm;
	}

	public WorkAreaFormMapping getWorkAreaFormMapping(EClass containerType, EAttribute attr) {
		// TODO inheritance?
		ObjectMapping mapping = getObjectMapping(containerType);
		if (mapping == null || !(mapping instanceof WorkAreaMapping))
			return null;
		WorkAreaFormMapping fmm = ((WorkAreaMapping) mapping).getFormsByAttribute().get(attr.getName());
		if (fmm == null || !fmm.isEnabled())
			return null;
		return fmm;
	}

	/**
	 * Returns the SlotMappingModel for the given reference. An
	 * {@link IllegalArgumentException} is thrown if the block should not be
	 * represented as a slot. An {@link IllegalStateException} may be thrown if
	 * the system is in an invalid state that causes the slot to be
	 * unrepresentable.
	 */
	public SlotMapping getSlotMapping(EClass containerType, EReference ref)
			throws IllegalArgumentException, IllegalStateException {
		// TODO inheritance?
		BlockMapping refBMM = getBlockMapping(ref.getEContainingClass());
		if (refBMM == null)
			throw new IllegalArgumentException(
					ref.getEContainingClass().getName() + "." + ref.getName() + " has no block mapping model!");
		SlotMapping refSMM = refBMM.getSlotsByReference().get(ref.getName());
		if (refSMM == null)
			throw new IllegalArgumentException(
					ref.getEContainingClass().getName() + "." + ref.getName() + " has no slot mapping model!");
		if (!refSMM.isEnabled())
			throw new IllegalArgumentException(
					ref.getEContainingClass().getName() + "." + ref.getName() + " has a disabled slot mapping model.");
		boolean refDominant = refSMM.isDominant();

		if (ref.isContainer()) {
			if (refDominant)
				return refSMM;
			else
				throw new IllegalArgumentException(ref.getEContainingClass().getName() + "." + ref.getName()
						+ " is a non-dominant container reference.");
		}

		EReference other = ref.getEOpposite();
		if (other == null)
			return refSMM;

		BlockMapping otherBMM = getBlockMapping(other.getEContainingClass());
		if (otherBMM == null)
			throw new IllegalStateException("Warning: " + ref.getEContainingClass().getName() + "." + ref.getName()
					+ " has an opposite " + MMUtil.fullyQualifiedName(other.getEContainingClass()) + "."
					+ other.getName() + " without a block mapping model.");
		SlotMapping otherSMM = otherBMM.getSlotsByReference().get(other.getName());
		if (otherSMM == null) {
			// Seems to be a normal condition
			// new Throwable("Warning: "+ref.getName()+" has an opposite
			// "+other.getName()+" without a slot mapping
			// model").printStackTrace();
			return refSMM;
		}
		boolean otherDominant = otherSMM.isDominant();

		if (ref.isContainment())
			return refSMM;// Containment must have a slot
		else if (refDominant && !otherDominant)
			return refSMM;
		else if (!refDominant && otherDominant)
			throw new IllegalArgumentException(ref.getEContainingClass().getName() + "." + ref.getName()
					+ " is recessive under " + other.getEContainingClass().getName() + "." + other.getName());
		else if (refDominant && otherDominant)
			return refSMM;
		else {
			String n1 = ref.getEContainingClass().getName() + "." + ref.getName();
			String n2 = other.getEContainingClass().getName() + "." + other.getName();
			int cmp = n1.compareTo(n2);
			if (cmp == 0)
				throw new IllegalStateException(
						"Bidirectional reference appears to reference itself. This is illegal: " + n1 + " -> " + n2);
			else if (cmp > 0)
				return refSMM;
			else
				throw new IllegalArgumentException(
						"Both references in the bidirectional association are recessive. Computed the direction based on comparison: "
								+ n1 + " -> " + n2);
		}
	}

	/** Returns the undecorated mapping model object. */
	public MappingModel getMappingModel() {
		return mappingModel;
	}

	/**
	 * Returns the object mapping for the given type, null if none. May be
	 * either {@link BlockMapping} or {@link WorkAreaMapping}.
	 */
	public ObjectMapping getObjectMapping(EClass eClass) {
		ObjectMapping m = mappingModel.getObjectMappingsByType().get(MMUtil.fullyQualifiedName(eClass));
		if (m == null || !m.isEnabled())
			return null;
		return m;
	}

	/**
	 * Returns the block mapping for the given type, or null if it has no block
	 * mapping. Note that a type cannot have both a work area mapping and a
	 * block mapping.
	 */
	public BlockMapping getBlockMapping(EClass eClass) {
		ObjectMapping mapping = getObjectMapping(eClass);
		if (mapping instanceof BlockMapping)
			return (BlockMapping) mapping;
		return null;
	}

	/**
	 * Returns the work area mapping for the given type, or null if it has no
	 * work area mapping. Note that a type cannot have both a work area mapping
	 * and a block mapping.
	 */
	public WorkAreaMapping getWorkAreaMapping(EClass eClass) {
		ObjectMapping mapping = getObjectMapping(eClass);
		if (mapping instanceof WorkAreaMapping)
			return (WorkAreaMapping) mapping;
		return null;
	}

	/** Save the mapping model resource to disk. */
	public void save() {
		DecoratedModelInstance.save("Mapping", mappingModel.eResource());
	}

	public WorkAreaContainment getWorkAreaContainment(EClass eClass, EReference reference) {

		// TODO inheritance?
		// This is a slow method, but it is likely not performance critical.
		WorkAreaMapping wamm = getWorkAreaMapping(eClass);
		if (wamm != null) {
			for (WorkAreaContainment containment : wamm.getContainmentsByType().values()) {
				if (containment.getContainmentReference().equals(reference.getName())) {
					if (!reference.isContainment()) {
						new Throwable("Warning: The work area mapping for " + MMUtil.fullyQualifiedName(eClass)
								+ " contains a containment mapping for " + containment.getContainmentReference()
								+ ", but this EReference is not containment!").printStackTrace();
						return null;
					}
					return containment;
				}
			}
		}
		return null;
	}

	public WorkAreaContainment getWorkAreaContainment(EClass eClass, EClass type) {
		// TODO inheritance?
		WorkAreaMapping wamm = getWorkAreaMapping(eClass);
		if (wamm != null) {
			WorkAreaContainment containment = findContainment(wamm, type);
			if (containment == null)
				return null;
			EStructuralFeature ref = eClass.getEStructuralFeature(containment.getContainmentReference());
			if (ref == null) {
				new Throwable("Warning: The work area mapping for " + MMUtil.fullyQualifiedName(eClass)
						+ " contains a containment mapping for " + containment.getContainmentReference()
						+ ", but this EReference does not exist!").printStackTrace();
				;
				return null;
			}
			if (!(ref instanceof EReference)) {
				new Throwable("Warning: The work area mapping for " + MMUtil.fullyQualifiedName(eClass)
						+ " contains a containment mapping for " + containment.getContainmentReference()
						+ ", but this feature is an " + ref.eClass().getName() + "!").printStackTrace();
				;
				return null;
			}
			if (!((EReference) ref).isContainment()) {
				new Throwable("Warning: The work area mapping for " + MMUtil.fullyQualifiedName(eClass)
						+ " contains a containment mapping for " + containment.getContainmentReference()
						+ ", but this EReference is not containment!").printStackTrace();
				;
				return null;
			}
			return containment;
		}
		return null;
	}

	/**
	 * Searches for a containment matching the given type or any of its super
	 * types, in the given mapping.
	 */
	private WorkAreaContainment findContainment(WorkAreaMapping wamm, EClass type) {
		WorkAreaContainment containment = wamm.getContainmentsByType().get(MMUtil.fullyQualifiedName(type));
		if (containment != null)
			return containment;

		for (EClass sup : type.getESuperTypes()) {
			containment = findContainment(wamm, sup);
			if (containment != null)
				return containment;
		}
		return null;
	}
}
