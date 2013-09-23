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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class DelegateField<T> implements Listener<T> {
	Object listenerObject;
	String fieldName;
	
	DelegateField(Object listenerObject, String fieldName) {
		this.listenerObject = listenerObject;
		this.fieldName = fieldName;
		// try to find field on the object
		Field[] declaredFields = listenerObject.getClass().getDeclaredFields();
//		for ( Field f : declaredFields) {
//			System.out.println(f.getName());
//		}
	}
	@Override
	public void notify(T args) {
		Field field = findField(listenerObject, fieldName);
		try {
			field.set(listenerObject, args);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(DelegateField.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(DelegateField.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Look for a Field in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @return 
	 */
	 static Field findField(Class<?> c, String name) {
		Field f = null;
		try {
			f = c.getDeclaredField(name);
			f.setAccessible(true);
		} catch (NoSuchFieldException ex) {
			// look in superclass
			Class<?> s = c.getSuperclass();
			if (s == null) return null;
			else return findField(s, name);
		} catch (SecurityException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return f;
	}
	
	/**
	 * Look for a Field in an Object including inherited members.
	 * @param o
	 * @param name
	 * @return 
	 */
	static Field findField(Object o, String name) {
		return findField(o.getClass(), name);
	}
	
}
