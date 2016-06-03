package no.hal.emfblocks.editor.eclipse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface BlocksConfigLoader {
	public boolean loadConfig(EPackage pack, URI resourceURI, ResourceSet resourceSet);
}
