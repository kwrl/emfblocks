package no.hal.emfblocks.javafx;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActionSource {

	private Collection<EventHandler<? super ActionEvent>> listeners = new LinkedHashSet<>();

	public void fireEvents() {
		ActionEvent e = new ActionEvent(this, null);
		// Allow concurrent modification
		Collection<EventHandler<? super ActionEvent>> q = new LinkedList<>(listeners);
		for (EventHandler<? super ActionEvent> l : q) {
			if (listeners.contains(l))
				l.handle(e);
		}
	}

	public void addListener(EventHandler<? super ActionEvent> listener) {
		if (! listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public boolean removeListener(EventHandler<? super ActionEvent> listener) {
		return listeners.remove(listener);
	}

	public boolean hasListener(EventHandler<? super ActionEvent> listener) {
		return listeners.contains(listener);
	}
}
