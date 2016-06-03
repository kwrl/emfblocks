package no.hal.emfblocks;

public enum BlockDropAction {
	/** Indicating that the dragged element can be added. */
	ACCEPT,
	/**
	 * Indicating that the dragged element must be returned to whence it came.
	 * (Note that if the element came from nothingness, then it must return to
	 * nothingness.)
	 */
	CANCEL,
	/** Indicating that the dragged element must be destroyed. */
	TRASH;

}
