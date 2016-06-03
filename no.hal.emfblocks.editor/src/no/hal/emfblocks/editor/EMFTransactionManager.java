package no.hal.emfblocks.editor;

import no.hal.emfblocks.TransactionManager;
import no.hal.emfblocks.WRoot;

public class EMFTransactionManager implements TransactionManager {
	private EMFBlockEditor editor;
	private CustomChangeCommand command;

	public EMFTransactionManager(EMFBlockEditor editor) {
		this.editor = editor;
	}

	@Override
	public void prepareTransaction(WRoot root) {
		command = new CustomChangeCommand(editor.getModelInstance());
	}

	@Override
	public void completeTransaction(WRoot root, String message) {
		command.setDescription(message);
		editor.getEditingDomain().getCommandStack().execute(command);
		command = null;
	}

	@Override
	public void cancelTransaction(WRoot root, boolean performUndo) {
		command.dispose();
		command = null;
	}

	@Override
	public boolean isTransactionInProgress() {
		return command != null;
	}

}
