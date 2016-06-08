package no.hal.emfblocks.editor.eclipse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import no.hal.emfblocks.mapping.MappingFactory;
import no.hal.emfblocks.mapping.MappingModel;
import no.hal.emfblocks.mapping.MappingPackage;
import no.hal.emfblocks.palette.Palette;
import no.hal.emfblocks.palette.PaletteFactory;
import no.hal.emfblocks.palette.PalettePackage;

public class SingleResourceConfigLoader implements BlocksConfigLoader {

	private BlocksConfigURIProvider configURIProvider;
	
	public SingleResourceConfigLoader(BlocksConfigURIProvider configURIProvider) {
		this.configURIProvider = configURIProvider;
	}

	@Override
	public boolean loadConfig(EPackage pack, URI resourceURI, ResourceSet resourceSet) {
		URI configURI = configURIProvider.getConfigURI(pack, resourceURI);
		Resource configResource = resourceSet.getResource(configURI, true);
		try {
			configResource.getContents();
		} catch (Exception e) {
		}
		EList<EObject> contents = configResource.getContents();
		MappingModel mapping = (MappingModel) EcoreUtil.getObjectByType(contents, MappingPackage.eINSTANCE.getMappingModel());
		if (mapping == null) {
			mapping = MappingFactory.eINSTANCE.createMappingModel();
			contents.add(mapping);
		}
		Palette palette = (Palette) EcoreUtil.getObjectByType(contents, PalettePackage.eINSTANCE.getPalette());
		if (palette == null) {
			palette = PaletteFactory.eINSTANCE.createPalette();
			contents.add(palette);
		}
		return true;
	}
}
