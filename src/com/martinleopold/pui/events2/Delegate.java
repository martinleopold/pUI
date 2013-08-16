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
package com.martinleopold.pui.events2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Delegate<T> implements Listener<T> {
	Object listenerObject;
	String callbackMethodName;
	
	Delegate(Object listenerObject, String callbackMethodName) {
		this.listenerObject = listenerObject;
		this.callbackMethodName = callbackMethodName;
	}

	@Override
	public void notify(T args) {
		Method method;
		try {
			method = listenerObject.getClass().getMethod(callbackMethodName, args.getClass());
			method.invoke(listenerObject, args);
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(Delegate.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
