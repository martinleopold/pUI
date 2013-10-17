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

/**
 * Event system based on ofEvent/poco http://www.openframeworks.cc/documentation/events/ofEvent.html
 * http://pocoproject.org/docs/package-Foundation.Events-index.html
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Events {
	
	// keep in mind:
	// Void.class != void.class == Void.TYPE
	// Boolean.class != boolean.class == Boolean.TYPE
	// for custom class args use MyClass.class
	// for primitive argTypes use void.class, boolean.class, float.class etc.
	// or Void.TYPE, Boolean.TYPE, float.TYPE to make the distinction clearer
	public static <T> Event<T> createEvent(Class<T> argType) {
		return new Event<T>(argType);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param e
	 * @param listener
	 * @param callbackMethodName
	 * @return true, if the method was found (and added), false otherwise
	 */
	public static <T> boolean addListener(Event<T> e, Object listener, String callbackMethodName) {
		try {
			e.addListener(new DelegateMethod<T>(listener, callbackMethodName, e.dataType));
			return true;
		} catch (NoSuchMethodException ex) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param e
	 * @param listener
	 * @param fieldName
	 * @return true if a field was found (and added), false otherwise
	 */
	public static <T> boolean addListenerField(Event<T> e, Object listener, String fieldName) {
		try {
//			System.out.println("trying to add listener field of type " + e.dataType);
			e.addListener(new DelegateField<T>(listener, fieldName, e.dataType));
			return true;
		} catch (NoSuchFieldException ex) {
			return false;
		}
	}

//	public static <T> void removeListener(Event<T> e, Object listener, String callbackMethodName) {
//		e.removeListener(new DelegateMethod<T>(listener, callbackMethodName));
//	}

	public static <T> void fireEvent(Event<T> e, T args) {
		e.fire(args);
	}
}
