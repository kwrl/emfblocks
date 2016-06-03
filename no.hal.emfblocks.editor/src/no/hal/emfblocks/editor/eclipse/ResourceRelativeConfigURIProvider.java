package no.hal.emfblocks.editor.eclipse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public class ResourceRelativeConfigURIProvider implements BlocksConfigURIProvider {

	private String fileExtension;
	
	public ResourceRelativeConfigURIProvider(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public ResourceRelativeConfigURIProvider() {
		this("blocksConfig");
	}

	@Override
	public URI getConfigURI(EPackage pack, URI resourceURI) {
		URI configURI = resourceURI.trimSegments(1).appendSegment(pack.getNsPrefix()).appendFileExtension(fileExtension);
		return configURI;
	}
}
