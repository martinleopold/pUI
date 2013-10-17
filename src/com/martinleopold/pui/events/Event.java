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
package com.martinleopold.pui.events;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A registry where listeners can be added to be notified when the event is fired.
 * @author Martin Leopold <m@martinleopold.com>
 * @param <T> Data type of the Event
 */
public class Event<T> {

	ArrayList<Listener<T>> listeners;
	Class<T> dataType;
	
	// TODO remove this constructor
	public Event() {
		listeners = new ArrayList<Listener<T>>();
	}
	
	/**
	 * Extra constructor to explicitly provide the argument data type so it's usable. 
	 * Finding the actual class of a type parameter is nigh impossible, so just provide it explicitly 
	 * for other classes to use.
	 * @param argType 
	 */
	public Event(Class<T> argType) {
		this();
		this.dataType = argType;
	}

	public synchronized void fire(T args) {
		for (Listener<T> l : listeners) {
			l.notify(args);
		}
	}

	public synchronized void addListener(Listener<T> listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(Listener<T> listener) {
		if (listener instanceof DelegateMethod) {
			DelegateMethod<T> delegate = (DelegateMethod<T>) listener;
			for (Iterator<Listener<T>> i = listeners.iterator(); i.hasNext();) {
				Listener<T> l = i.next();
				if (l instanceof DelegateMethod) {
					DelegateMethod<T> d = (DelegateMethod<T>) l;
					if (d.listenerObject == delegate.listenerObject && d.callbackMethodName.equals(delegate.callbackMethodName)) {
						i.remove();
					}
				}
			}
		} else {
			listeners.remove(listener);
		}
	}
}
