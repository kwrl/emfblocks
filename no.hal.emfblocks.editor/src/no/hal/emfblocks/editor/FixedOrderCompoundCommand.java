package no.hal.emfblocks.editor;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.WrappedException;

/**
 * Exactly the same as {@link CompoundCommand}, except that the order of undo is
 * the same as redo.
 */
public class FixedOrderCompoundCommand extends CompoundCommand {
	/**
	 * Creates instance with the given label and list.
	 * 
	 * @param label
	 *            the label.
	 * @param commandList
	 *            the list of commands.
	 */
	public FixedOrderCompoundCommand(String label, List<Command> commandList) {
		super(label);
		this.commandList = commandList;
	}

	/**
	 * Creates an instance with the given label, description, and list.
	 * 
	 * @param label
	 *            the label.
	 * @param description
	 *            the description.
	 * @param commandList
	 *            the list of commands.
	 */
	public FixedOrderCompoundCommand(String label, String description, List<Command> commandList) {
		super(label, description);
		this.commandList = commandList;
	}

	/**
	 * Calls {@link Command#undo} for each command in the list, in forward
	 * order.
	 */
	@Override
	public void undo() {
		for (ListIterator<Command> commands = commandList.listIterator(); commands.hasNext();) {
			try {
				Command command = commands.next();
				command.undo();
			} catch (RuntimeException exception) {
				// Skip over the command that threw the exception.
				//
				commands.previous();

				try {
					// Iterate backward over the undone commands to redo them.
					//
					while (commands.hasPrevious()) {
						Command command = commands.previous();
						command.redo();
					}
				} catch (RuntimeException nestedException) {
					CommonPlugin.INSTANCE
							.log(new WrappedException(CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"),
									nestedException).fillInStackTrace());
				}

				throw exception;
			}
		}
	}
}
