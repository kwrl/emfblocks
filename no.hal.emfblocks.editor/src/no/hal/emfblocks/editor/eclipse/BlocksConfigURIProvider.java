package no.hal.emfblocks.editor.eclipse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public interface BlocksConfigURIProvider {
	public URI getConfigURI(EPackage pack, URI resourceURI);
}
