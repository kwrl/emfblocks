package no.hal.emfblocks.editor.ui;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import no.hal.emfblocks.javafx.ActionSource;

import javafx.scene.Node;

public interface AttributeFormFactory {
	public Node[] createForm(EditingDomain editingDomain, EObject obj, EAttribute e,
			ActionSource disposeEventDispatcher);
}
