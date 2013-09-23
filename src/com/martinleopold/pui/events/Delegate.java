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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 * @param <T>
 */
class Delegate<T> implements Listener<T> {

	Object listenerObject;
	String callbackMethodName;

	Delegate(Object listenerObject, String callbackMethodName) {
		this.listenerObject = listenerObject;
		this.callbackMethodName = callbackMethodName;
	}

	@Override
	public void notify(T args) {
		Method method = findMethod(listenerObject, callbackMethodName, args.getClass());
		try {
			method.invoke(listenerObject, args);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
		/**
	 * Look for a Method in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @param parameterTypes
	 * @return 
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
	 * @return 
	 */
	static Method findMethod(Object o, String name, Class... parameterTypes) {
		return findMethod(o.getClass(), name, parameterTypes);
	}
}
