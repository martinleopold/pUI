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

import com.martinleopold.pui.PUI;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Listener that calls a method on an object when notified of an event.
 * @author Martin Leopold <m@martinleopold.com>
 * @param <T> Data type of the Event
 */
class DelegateMethod<T> implements Listener<T> {
	
	Object listenerObject;
	String callbackMethodName;
	Method callbackMethod;
	
	// TODO use static factory, so it can return null if no callback is found
	// or: raise an exception in the constructor
	
	DelegateMethod(Object listenerObject, String callbackMethodName) {
		this.listenerObject = listenerObject;
		this.callbackMethodName = callbackMethodName;
	}
	
	// find callback method on instantiation, instead of in notify()
	// need argument types to find the method
	DelegateMethod(Object listenerObject, String callbackMethodName, Class<T> argType) throws NoSuchMethodException {
		this(listenerObject, callbackMethodName);
		if (argType != null) {
			// create explicit array arguments for findMethod
			// Void.class != void.class == Void.TYPE
			Class<?>[] argTypes = (argType != Void.class && argType != void.class) ? new Class<?>[] {argType} : null;
			callbackMethod = findMethod(listenerObject, callbackMethodName, argTypes);
			if (callbackMethod == null) {
				throw new NoSuchMethodException();
			}
		}
	}

	@Override
	public void notify(T args) {
		if (callbackMethod == null) {
			// create explicit array arguments for findMethod varargs parameter
			// this avoids problems e.g. when putting args=null directly into Method.invoke an array containing a null element is passed in instead of a null array
			Class<?>[] argTypes = (args != null) ? new Class<?>[] {args.getClass()} : null;
			callbackMethod = findMethod(listenerObject, callbackMethodName, argTypes);
		}
		
		Object[] argsArray = (args != null ) ? new Object[] {args} : null;
		if (callbackMethod != null) { // don't do anything if there is no valid method to call
			try {
				// create explicit array arguments for Method.invoke
				callbackMethod.invoke(listenerObject, argsArray);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(DelegateMethod.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalArgumentException ex) {
				Logger.getLogger(DelegateMethod.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvocationTargetException ex) {
				Logger.getLogger(DelegateMethod.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	/**
	 * Look for a Method in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @param parameterTypes
	 * @return the Method or null if not found
	 */
	static Method findMethod(Class<?> c, String name, Class... parameterTypes) {
		Method m = null;
		try {
			m = c.getDeclaredMethod(name, parameterTypes);
			m.setAccessible(true);
		} catch (NoSuchMethodException ex) {
			// look in superclass
			Class<?> s = c.getSuperclass();
			if (s == null) return null;
			else return findMethod(s, name, parameterTypes);
		} catch (SecurityException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return m;
	}
	
	/**
	 * Look for a Method in an Object including inherited members.
	 * @param o
	 * @param name
	 * @param parameterTypes
	 * @return the Method or null if not found
	 */
	static Method findMethod(Object o, String name, Class... parameterTypes) {
		return findMethod(o.getClass(), name, parameterTypes);
	}
}
