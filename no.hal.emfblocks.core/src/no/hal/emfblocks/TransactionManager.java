package no.hal.emfblocks;

public interface TransactionManager {
	/**
	 * Called once, and only once, at the start of every transaction. The
	 * implementation must take appropriate steps to ensure the current state
	 * can be restored via an undo.
	 */
	public void prepareTransaction(WRoot root);

	/**
	 * Called when a transaction has been completed. The implementation should
	 * take appropriate steps to ensure that the current state can be restored
	 * via a redo.
	 * 
	 * @param message
	 *            A message describing in a human readable manner what happened
	 *            during this transaction.
	 */
	public void completeTransaction(WRoot root, String message);

	/**
	 * Called when a transaction was canceled. This closes the current
	 * transaction.
	 * 
	 * @param performUndo
	 *            Whether an undo operation must be performed by the transaction
	 *            manager, in order to restore the previous state. If
	 *            <code>false</code>, it is entirely safe to assume that the
	 *            system is already in the state that it was before the
	 *            transaction was started. This will always be
	 *            <code>false</code> when called from the toolkit itself, so it
	 *            is safe to assume that this is false if the application never
	 *            calls this method explicitly.
	 */
	public void cancelTransaction(WRoot root, boolean performUndo);

	/**
	 * Returns true between a call to {@link #prepareTransaction()} and a
	 * closing transaction call, i.e. {@link #cancelTransaction(boolean)} or
	 * {@link #completeTransaction(Runnable, boolean, String)}
	 */
	public boolean isTransactionInProgress();
}
