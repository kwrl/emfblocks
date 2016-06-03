package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.util.EcoreUtil;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.hierarchy.Type;

public class MMUtil {

	static EClassifier[] listClassifiers(Collection<EPackage> packageList) {
		ArrayList<EClassifier> list = new ArrayList<>();
		for (EPackage p : packageList) {
			list.addAll(((EPackage) p).getEClassifiers());
		}
		return list.toArray(new EClassifier[list.size()]);
	}

	static EClassifier[] listClassifiers(EPackage epackage) {
		ArrayList<EClassifier> list = new ArrayList<>();
		list.addAll(epackage.getEClassifiers());
		return list.toArray(new EClassifier[list.size()]);
	}

	static EClassifier[] listClassifiers(WRoot root) {
		ArrayList<EClassifier> list = new ArrayList<>();
		int numTypes = root.getTypeHierarchy().getNumValues();
		for (int i = 0; i < numTypes; i++) {
			list.add((EClassifier) root.getTypeHierarchy().getValue(i).getKey());
		}
		return list.toArray(new EClassifier[list.size()]);
	}

	/** @author leonard.krpanFriend */
	public static String fullyQualifiedName(EPackage pkg) {

		return pkg.getESuperPackage() == null ? pkg.getName()
				: fullyQualifiedName(pkg.getESuperPackage()) + "." + pkg.getName();
	}

	/** @author leonard.krpanFriend */
	public static String fullyQualifiedName(EClassifier cls) {
		EPackage pkg = cls.getEPackage();
		if (pkg == null)
			return cls.getName();
		return fullyQualifiedName(pkg) + "." + cls.getName();
	}

	public static Type typeOf(WRoot root, EObject obj, EReference e) {
		return root.getTypeHierarchy().getType(getReifiedType(obj, e));
	}

	public static EClass getReifiedType(EObject obj, EReference e) {
		return (EClass) EcoreUtil.getReifiedType(obj.eClass(), e.getEGenericType()).getEClassifier();
	}

	public static EDataType getReifiedDataType(EObject obj, EAttribute e) {
		return (EDataType) EcoreUtil.getReifiedType(obj.eClass(), e.getEGenericType()).getEClassifier();
	}

	/**
	 * Returns the {@link EObject} wrapped in the given block, dereferencing
	 * pointer blocks if necessary. If it is null, then null is returned.
	 * 
	 * @throws IllegalStateException
	 *             If the given block is not an {@link EObjectBlock} or a
	 *             pointer thereto
	 */
	public static EObject extractEObject(WBlock block) throws IllegalStateException {
		if (block == null)
			return null;
		else if (block instanceof EMFPointerBlock)
			return ((EMFPointerBlock) block).dereference();
		else {
			if (block instanceof EObjectBlock)
				return ((EObjectBlock) block).getObject();
			else
				throw new IllegalStateException("Workspace contains illegal block " + block + "! All blocks must be "
						+ EObjectBlock.class.getSimpleName() + " or " + EMFPointerBlock.class.getSimpleName());
		}
	}

	public static boolean isContained(EObject object) {
		EObject c = object.eContainer();
		if (c == null || c instanceof ChangeDescription) {
			if (object.eResource() == null)
				return false;
			return object.eResource().getContents().contains(object);
		} else
			return isContained(object.eContainer());
	}
}
