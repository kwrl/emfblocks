/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package no.hal.emfblocks.editor;

import java.util.Arrays;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <p>
 * This command uses the Change Model facilities to enable a group of
 * modifications to be executed as one single command. The undo operation
 * reverts all the modifications made when the command was executed
 * </p>
 * <p>
 * In order to use this class, the <tt>org.eclipse.emf.ecore.change</tt> plugin
 * has to be available in your Eclipse configuration.
 * </p>
 * 
 * @since 2.2.0
 */
public class CustomChangeCommand extends AbstractCommand {
	protected ChangeDescription changeDescription;
	protected ChangeRecorder changeRecorder;

	public CustomChangeCommand(Resource... resources) {
		// FIXME this implementation causes bad things tp happen if stuff
		// changes while recording
		changeRecorder = new ChangeRecorder(Arrays.asList(resources));
	}

	public CustomChangeCommand(DecoratedModelInstance instance) {
		this(instance.getInstanceResource(), instance.getLayoutInstance().eResource());
	}

	@Override
	public void dispose() {
		if (changeRecorder != null)
			changeRecorder.dispose();
		changeRecorder = null;
		changeDescription = null;
		super.dispose();
	}

	protected ChangeRecorder createChangeRecorder() {
		return new ChangeRecorder();
	}

	@Override
	protected boolean prepare() {
		return changeDescription == null;
	}

	/** End the current recording. */
	@Override
	public void execute() {
		changeDescription = changeRecorder.endRecording();
		changeRecorder.dispose();
		changeRecorder = null;
	}

	@Override
	public void undo() {
		changeDescription.applyAndReverse();
	}

	public void redo() {
		changeDescription.applyAndReverse();// The order of operations here is
											// wrong, but we've dealt with it in
											// the rest of the code
	}
}
