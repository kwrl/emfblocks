package no.hal.emfblocks.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

import no.hal.emfblocks.editor.eclipse.BlocksConfigLoader;
import no.hal.emfblocks.layout.AbstractRepresentation;
import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.LayoutFactory;
import no.hal.emfblocks.layout.LayoutPackage;
import no.hal.emfblocks.layout.ResourceLayout;
import no.hal.emfblocks.layout.WorkAreaRepresentation;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.mapping.ObjectMapping;
import no.hal.emfblocks.mapping.WorkAreaMapping;
import no.hal.emfblocks.palette.Palette;
import no.hal.emfblocks.palette.PalettePackage;

public class DecoratedModelInstance {
	protected final HashMap<String, EPackage> packages;
	protected final DecoratedMappingModel mappingModel;
	protected final DecoratedPalette palette;
	protected final Resource instanceResource;
	protected final ResourceLayout layout;
	protected final File cutsDirectory;
	protected final ResourceSet resourceSet;

	public static final String DEFAULT_ECORE_FILE_EXTENSION = "ecore";
	public static final String DEFAULT_INSTANCE_FILE_EXTENSION = "xmi";
	public static final String DEFAULT_LAYOUT_FILE_EXTENSION = "blocks";

	public DecoratedModelInstance(ResourceSet resourceSet, URI resourceURI, BlocksConfigLoader blocksConfigProvider) {
		Resource existing = getResource(resourceURI, resourceSet, true);
		Collection<EPackage> packageList = new ArrayList<>();
		if ("ecore".equals(resourceURI.fileExtension())) {
			resourceURI = resourceURI.trimFileExtension().appendFileExtension(DEFAULT_INSTANCE_FILE_EXTENSION);
			this.instanceResource = getResource(resourceURI, resourceSet, true);
			TreeIterator<EObject> it = EcoreUtil.getAllContents(existing, true);
			EObject eObject;
			while(it.hasNext()) {
				eObject = it.next();
				if (eObject instanceof EPackage && (! packageList.contains(eObject))) {
					packageList.add((EPackage) eObject);
				}
			}
		} else {
			TreeIterator<EObject> it = EcoreUtil.getAllContents(existing, true);
			EPackage pack;
			while(it.hasNext()) {
				pack = it.next().eClass().getEPackage();
				if (! packageList.contains(pack)) {
					packageList.add(pack);
				}
			}
			this.instanceResource = existing;
		}
		this.resourceSet = resourceSet;
		this.packages = new LinkedHashMap<String, EPackage>();
		for (EPackage pack : packageList) {
			packages.put(pack.getNsURI(), pack);
			blocksConfigProvider.loadConfig(pack, resourceURI, resourceSet);
		}

		// Adapt mapping model to this domain
		this.mappingModel = new DecoratedMappingModel((MappingModel) getObjectByType(resourceSet, MappingPackage.eINSTANCE.getMappingModel()));
		this.mappingModel.populateMissingElements(packages.values());
		this.mappingModel.populateMissingElements(EcorePackage.eINSTANCE);
		
		// Adapt palette to this domain
		this.palette = new DecoratedPalette((Palette) getObjectByType(resourceSet, PalettePackage.eINSTANCE.getPalette()));
		this.palette.populateMissingElements(null, packages.values(), mappingModel);
		this.palette.addMissingImmutableInstances("Ecore Literals", EcorePackage.eINSTANCE.eContents());
		
		this.cutsDirectory = null;
		
		Resource layoutResource = getResource(resourceURI.trimFileExtension().appendFileExtension(DEFAULT_LAYOUT_FILE_EXTENSION), resourceSet, true);
		ResourceLayout layout = (ResourceLayout) EcoreUtil.getObjectByType(layoutResource.getContents(), LayoutPackage.eINSTANCE.getResourceLayout());
		if (layout == null) {
			layout = LayoutFactory.eINSTANCE.createResourceLayout();
			layoutResource.getContents().add(layout);
		}
		this.layout = layout;
		adaptLayout();
	}

	private static Resource getResource(URI resourceURI, ResourceSet resourceSet, boolean logEmpty) {
		Resource resource = resourceSet.getResource(resourceURI, true);
		try {
			resource.getContents();
			if (logEmpty && resource.getContents().isEmpty()) {
				System.err.println(resourceURI + " is empty");
			}
		} catch (Exception e) {
			System.err.println("Exception when getting contents of " + resourceURI + ": " + e);
		}
		return resource;
	}

	private EObject getObjectByType(ResourceSet resourceSet, EClass eClass) {
		for (Resource resource : resourceSet.getResources()) {
			Object eObject = EcoreUtil.getObjectByType(resource.getContents(), eClass);
			if (eObject != null) {
				return (EObject) eObject;
			}
		}
		return null;
	}

	private void adaptLayout() {
		if (layout.getMappingModel() == null)
			layout.setMappingModel(mappingModel.getMappingModel());
		else if (layout.getMappingModel() != mappingModel.getMappingModel()) {
			System.out.println("Info: The mapping model of the layout object " + layout + " had to be changed from "
					+ layout.getMappingModel() + " to " + mappingModel.getMappingModel());
			layout.setMappingModel(mappingModel.getMappingModel());
		}

		if (layout.getPalette() == null)
			layout.setPalette(palette.getPaletteInstance());
		else if (layout.getPalette() != palette.getPaletteInstance()) {
			System.out.println("Info: The palette instance of the layout object " + layout + " had to be changed from "
					+ layout.getPalette() + " to " + palette.getPaletteInstance());
			layout.setPalette(palette.getPaletteInstance());
		}

		LinkedHashSet<EObject> coverage = new LinkedHashSet<EObject>(layout.getRepresentationsByObject().keySet());

		TreeIterator<EObject> it = instanceResource.getAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			if (layout.getRepresentationsByObject().containsKey(obj))
				coverage.remove(obj); // Remove all objects that we found in the instance from this set.
			else {
				EClass type = obj.eClass();
				ObjectMapping mm = mappingModel.getObjectMapping(type);
				if (mm == null)
					new Throwable(
							"Warning: Cannot create representation for " + obj + " because it has no mapping model.")
									.printStackTrace();
				else {
					if (mm instanceof WorkAreaMapping)
						createWorkAreaRepresentation(obj, (WorkAreaMapping) mm, true);
					else
						createBlockRepresentation(obj, (BlockMapping) mm, true);
					System.out.println("Info: The object " + obj
							+ " had no layout. A default layout was automatically generated.");
				}
			}
		}

		if (!coverage.isEmpty()) {
			System.out.println("INFO: The following " + coverage.size()
					+ " objects were found in the layout file, but were not in the model instance: " + coverage);
			for (EObject e : coverage)
				unregister(e);
		}
	}

//	public void saveInstance() {
//		save("Instance", instanceResource);
//		// Temporarily purge representations of orphaned objects
//		Collection<java.util.Map.Entry<EObject, AbstractRepresentation>> tmp = new ArrayList<>(layout.getRepresentationsByObject());
//		layout.getRepresentationsByObject().removeIf((e) ->
//			e.getKey() != null && (! MMUtil.isContained(e.getKey()) && !instanceResource.getContents().contains(e.getKey()))
//		);
//		save("Instance", layout.eResource());
//		// Add back
//		layout.getRepresentationsByObject().addAll(tmp);
//	}

	public Resource getInstanceResource() {
		return instanceResource;
	}

	public ResourceLayout getLayoutInstance() {
		return layout;
	}

	public DecoratedPalette getPalette() {
		return palette;
	}

	public DecoratedMappingModel getMappingModel() {
		return mappingModel;
	}

	public WorkAreaRepresentation createWorkAreaRepresentation(EObject obj, WorkAreaMapping wamm, boolean autoAdd) {
		assert wamm != null;
		WorkAreaRepresentation r = LayoutFactory.eINSTANCE.createWorkAreaRepresentation();
		r.setObject(obj);
		if (autoAdd)
			register(r);
		return r;
	}

	private void register(AbstractRepresentation repr) {
		// FIXME this map leaks memory!
		// The reason is that it is difficult to know if an EObject will come back to life or not.
		// However, the layout information needs to be kept until it is guaranteed that the object is lost forever.
		// There are two possible solutions:
		// 1. Using a weak map. This means the representation cannot store a reference to the EObject,
		// because that would prevent garbage collection of the key, which is the same EObject.
		// (See WeakHashMap javadoc). If the backwards reference to the EObject is removed, then one of the
		// lookup algorithms used around the work area manager needs to be rewritten, and some code
		// refactoring is necessary.
		// 2. It seems like EObjects are reparented to a change description object until they are disposed.
		// If this mechanism is robust, then it can be leveraged to determine when it is safe to discard
		// representation objects.
		layout.getRepresentationsByObject().put(repr.getObject(), repr);
	}

	private void unregister(EObject obj) {
		layout.getRepresentationsByObject().remove(obj);
	}

	public BlockRepresentation createBlockRepresentation(EObject obj, BlockMapping mm, boolean autoAdd) {
		assert mm != null;
		BlockRepresentation r = LayoutFactory.eINSTANCE.createBlockRepresentation();
		r.setObject(obj);
		if (autoAdd)
			register(r);
		return r;
	}

	public AbstractRepresentation getRepresentation(EObject obj) {
		return layout.getRepresentationsByObject().get(obj);
	}

	public WorkAreaRepresentation getOrCreateOrphansRepresentation() {
		AbstractRepresentation repr = layout.getRepresentationsByObject().get(null);
		if (repr instanceof WorkAreaRepresentation)
			return (WorkAreaRepresentation) repr;
		repr = LayoutFactory.eINSTANCE.createWorkAreaRepresentation();
		repr.setObject(null);
		layout.getRepresentationsByObject().put(null, repr);
		return (WorkAreaRepresentation) repr;
	}

	public BlockRepresentation getOrCreateAndRegisterBlockRepresentation(EObject obj, BlockMapping bmm) {
		AbstractRepresentation repr = getRepresentation(obj);
		if (repr instanceof BlockRepresentation)
			return (BlockRepresentation) repr;
		else if (repr == null)
			return createBlockRepresentation(obj, bmm, true);
		else
			throw new IllegalArgumentException(obj + " already has a non-block representation " + repr);
		// TODO: recover from this instead of failing?
	}

	public WorkAreaRepresentation getOrCreateAndRegisterWorkAreaRepresentation(EObject obj, WorkAreaMapping wamm) {
		AbstractRepresentation repr = getRepresentation(obj);
		if (repr instanceof WorkAreaRepresentation)
			return (WorkAreaRepresentation) repr;
		else if (repr == null)
			return createWorkAreaRepresentation(obj, wamm, true);
		else
			throw new IllegalArgumentException(obj + " already has a non-work-area representation " + repr);
		// TODO: recover from this instead of failing?
	}

	public File getCutsDirectory() {
		return cutsDirectory;
	}

	public Collection<EPackage> getPackages() {
		return packages.values();
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	//

	static void save(String resourceName, Resource theResource) {
		try {
			HashMap<Object, Object> options = new HashMap<>();
			options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
			// TODO purge unused representations
			theResource.save(options);
		} catch (IOException e) {
			System.err.println("Error in saving " + resourceName + " file:");
			e.printStackTrace();
		}
	}
}
