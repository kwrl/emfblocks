/**
 *  Copyright (c) 2012 TESIS DYNAware GmbH and others. 
 *  All rights reserved. This program and the accompanying materials 
 *  are made available under the terms of the Eclipse Public License v1.0 
 *  which accompanies this distribution, and is available at 
 *  http://www.eclipse.org/legal/epl-v10.html 
 *  
 *  Contributors: 
 *      Torsten Sommer <torsten.sommer@tesis.de> - initial API and implementation
 */
package no.hal.emfblocks.editor.efxclipse;

import javafx.beans.property.ObjectPropertyBase;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public class UnregisterableEObjectProperty<T> extends ObjectPropertyBase<T> {

	EObject eObject;
	AdapterImpl myAdapter;
	EStructuralFeature feature;
	EditingDomain editingDomain;

	public UnregisterableEObjectProperty(EditingDomain editingDomain, EObject eObject, EStructuralFeature feature) {
		super();
		this.eObject = eObject;
		this.feature = feature;
		this.editingDomain = editingDomain;

		eObject.eAdapters().add(myAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				fireValueChangedEvent();
			}
		});
	}

	@Override
	public void setValue(T newValue) {
		Command command = SetCommand.create(editingDomain, eObject, feature, newValue);
		if (command.canExecute())
			editingDomain.getCommandStack().execute(command);
	}

	public void unregister() {
		eObject.eAdapters().remove(myAdapter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getValue() {
		return (T) eObject.eGet(feature);
	}

	public Object getBean() {
		return eObject;
	}

	public String getName() {
		return feature.getName();
	}

}