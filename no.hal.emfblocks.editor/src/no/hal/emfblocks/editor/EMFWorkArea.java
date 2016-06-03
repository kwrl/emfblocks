package no.hal.emfblocks.editor;

import java.util.EventObject;
import java.util.HashSet;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;

import javafx.application.Platform;
import javafx.scene.Node;
import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WWorkArea;
import no.hal.emfblocks.javafx.ActionSource;
import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.WorkAreaRepresentation;
import no.hal.emfblocks.mapping.BlockMapping;

public abstract class EMFWorkArea extends WWorkArea implements CommandStackListener {
	protected HashSet<EObject> duplicationSet = new HashSet<>();
	protected final ActionSource destructionListeners = new ActionSource();
	protected final EMFWorkAreaManager manager;

	protected boolean preventRecursion = false;
	protected final EMFBlockEditor editor;

	protected final WorkAreaRepresentation representation;

	public EMFWorkArea(EMFWorkAreaManager manager, WorkAreaRepresentation representation) {
		super(manager.getEditor().getRoot());
		assert manager != null;
		this.manager = manager;
		this.editor = manager.getEditor();
		this.representation = representation;

		editor.getEditingDomain().getCommandStack().addCommandStackListener(this);
	}

	/**
	 * Remove the block representing the given object from the work area. This
	 * is done without regard for any EMF containment.
	 */
	protected int removeObject(EObject obj) {
		for (int i = 0; i < getChildren().size(); i++) {
			EObjectBlock eBlock = (EObjectBlock) getChildren().get(i);
			if (obj == eBlock.getObject()) {
				getChildren().remove(i);
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns whether the work area contains an object representing the given
	 * object. This is done without regard for any EMF containment.
	 */
	protected boolean containsObject(EObject obj) {
		for (WBlock c : getChildren()) {
			if (c instanceof EObjectBlock && ((EObjectBlock) c).getObject() == obj)
				return true;
		}
		return false;
	}

	/**
	 * Add a block with the given representation to the work area. This is done
	 * without regard for any EMF containment.
	 */
	protected void add(BlockRepresentation blockRepresentation, BlockMapping bmm) {
		getChildren().add(new EObjectBlock(editor, bmm, blockRepresentation));
	}

	@Override
	public BlockDropAction accepts(WBlock b) {
		// Pointer blocks cannot be contained in this pane!
		if (b instanceof EMFPointerBlock)
			return BlockDropAction.TRASH;
		return super.accepts(b);
	}

	public EObject getObject() {
		return representation == null ? null : representation.getObject();
	}

	public WorkAreaRepresentation getRepresentation() {
		return representation;
	}

	@Override
	protected void dispose2() {
		destructionListeners.fireEvents();
		Platform.runLater(() -> // Avoid ConcurrentModificationException....
		{
			editor.getEditingDomain().getCommandStack().removeCommandStackListener(this);
		});
	}

	@Override
	public void commandStackChanged(EventObject event) {
		assert duplicationSet.isEmpty() : "Duplicates still exist after the operation: " + duplicationSet;
	}

	public Node getDecoratedUI() {
		return getUI();
	}
}
