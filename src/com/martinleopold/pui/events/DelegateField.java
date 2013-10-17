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
 * A Listener that sets a field on an object when notified of an event.
 * @author Martin Leopold <m@martinleopold.com>
 * @param <T> 
 */
public class DelegateField<T> implements Listener<T> {
	
	Object listenerObject;
	String fieldName;
	
	Field field;
	
	DelegateField(Object listenerObject, String fieldName) {
		this.listenerObject = listenerObject;
		this.fieldName = fieldName;
	}
	
	// find the field on instantiation, instead of in notify()
	// also checks for the type of field
	DelegateField(Object listenerObject, String fieldName, Class<T> fieldType) throws NoSuchFieldException {
		this(listenerObject, fieldName);
		if (fieldType != null) {
//			System.out.println("find field of type " + fieldType);
			field = findField(listenerObject, fieldName, fieldType);
			if (field == null) {
//				System.out.println("not found");
				throw new NoSuchFieldException();
			}
		}
	}
	
	
	@Override
	public void notify(T args) {
		if (field == null) {
			field = findField(listenerObject, fieldName, args.getClass());
		}
		if (field != null) { // don't do anything if there is no valid field
			try {
				field.set(listenerObject, args);
			} catch (IllegalArgumentException ex) {
				Logger.getLogger(DelegateField.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(DelegateField.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	/**
	 * Look for a Field in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @param type type of the field
	 * @return the Field or null, if not found.
	 */
	 static Field findField(Class<?> c, String name, Class<?> type) {
		Field f = null;
		try {
			f = c.getDeclaredField(name);
//			System.out.println(f.getType() + " =? " + type);
			if (f.getType() != type) throw new NoSuchFieldException(); // check type
			f.setAccessible(true);
		} catch (NoSuchFieldException ex) {
			// look in superclass
			Class<?> s = c.getSuperclass();
			if (s == null) return null;
			else return findField(s, name, type);
		} catch (SecurityException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return f;
	}
	
	/**
	 * Look for a Field in an Object including inherited members.
	 * @param o
	 * @param name
	 * @param type type of the field
	 * @return the field or null, if not found.
	 */
	static Field findField(Object o, String name, Class<?> type) {
		return findField(o.getClass(), name, type);
	}
	
}
