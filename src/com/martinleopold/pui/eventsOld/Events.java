/*
 * Copyright (C) 2013 Martin Leopold <m@martinleopold.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.martinleopold.pui.eventsOld;

import java.util.*;

/**
 * Event System based on http://stackoverflow.com/questions/937302/simple-java-message-dispatching-system
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Events {

	/**
	 * mapping of class events to active listeners *
	 */
	private final HashMap<Class, ArrayList> map = new HashMap<Class, ArrayList>(10);

	/**
	 * Add a listener to an event class *
	 */
	public <L> void listen(Class<? extends Event<L>> evtClass, L listener) {
		final ArrayList<L> listeners = listenersOf(evtClass);
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}

	/**
	 * Stop sending an event class to a given listener *
	 */
	public <L> void mute(Class<? extends Event<L>> evtClass, L listener) {
		final ArrayList<L> listeners = listenersOf(evtClass);
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	/**
	 * Gets listeners for a given event class *
	 */
	private <L> ArrayList<L> listenersOf(Class<? extends Event<L>> evtClass) {
		synchronized (map) {
			@SuppressWarnings("unchecked")
			final ArrayList<L> existing = map.get(evtClass);
			if (existing != null) {
				return existing;
			}
			final ArrayList<L> emptyList = new ArrayList<L>(5);
			map.put(evtClass, emptyList);
			return emptyList;
		}
	}

	/**
	 * Notify a new event to registered listeners of this event class *
	 */
	public <L> void notify(final Event<L> evt) {
		@SuppressWarnings("unchecked")
		Class<Event<L>> evtClass = (Class<Event<L>>) evt.getClass();

		for (L listener : listenersOf(evtClass)) {
			evt.notify(listener);
		}
	}
}
