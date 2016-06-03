package no.hal.emfblocks;

import no.hal.emfblocks.javafx.ActionSource;

public class UndoManager {
	private Runnable[] undos;
	private Runnable[] redos;
	private String[] messages;
	private final int maxSize;
	private int currentSize;
	private int nextIndex;
	private int remainingRedos;
	private int remainingUndos;

	private boolean isPerforming = false;
	private boolean log;

	private final ActionSource modificationListeners;

	/**
	 * Create a new undo manager.
	 * 
	 * @param log
	 *            Whether to print messages to System.out when logging new
	 *            operations and undoing/redoing operations.
	 * @param maxSize
	 *            The maximum number of undo records.
	 */
	public UndoManager(boolean log, int maxSize, ActionSource modificationListeners) {
		this.log = log;
		this.maxSize = maxSize;
		this.modificationListeners = modificationListeners;
		undos = new Runnable[maxSize];
		redos = new Runnable[maxSize];
		messages = new String[maxSize];
		currentSize = 0;
		nextIndex = 0;
		remainingRedos = 0;
		remainingUndos = 0;
	}

	public boolean canUndo() {
		return remainingUndos > 0;
	}

	public boolean canRedo() {
		return remainingUndos > 0;
	}

	public String redo() {
		if (remainingRedos > 0) {
			isPerforming = true;
			String msg = messages[nextIndex];
			redos[nextIndex].run();
			remainingUndos++;
			remainingRedos--;
			nextIndex = cycle(nextIndex + 1, maxSize);
			isPerforming = false;
			if (log)
				System.out.println("Redo: " + msg);
			if (modificationListeners != null)
				modificationListeners.fireEvents();
			return msg;
		} else if (log)
			System.out.println("No redo");
		return null;
	}

	public String undo() {
		if (remainingUndos > 0) {
			isPerforming = true;
			String msg = messages[cycle(nextIndex - 1, maxSize)];
			undos[cycle(nextIndex - 1, maxSize)].run();
			remainingUndos--;
			remainingRedos++;
			nextIndex = cycle(nextIndex - 1, maxSize);
			isPerforming = false;
			if (log)
				System.out.println("Undo: " + msg);
			if (modificationListeners != null)
				modificationListeners.fireEvents();
			return msg;
		} else if (log)
			System.out.println("No undo");
		return null;
	}

	public boolean isPerforming() {
		return isPerforming;
	}

	private int cycle(int value, int max) {
		return ((value % max) + max) % max;
	}

	public void logUndo(Runnable undo, Runnable redo, String message) {
		if (log)
			System.out.println("Operation: " + message);
		if (currentSize < maxSize)
			currentSize++;
		if (remainingUndos < maxSize)
			remainingUndos++;
		remainingRedos = 0;// All redos are killed by an operation
		this.redos[nextIndex] = redo;
		this.undos[nextIndex] = undo;
		this.messages[nextIndex] = message;
		nextIndex = cycle(nextIndex + 1, maxSize);
		if (modificationListeners != null)
			modificationListeners.fireEvents();
	}
}
