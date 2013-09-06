/*
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 * 
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
package com.martinleopold.pui;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * This is a template class and can be used to start a new processing library or tool. Make sure you
 * rename this class as well as the name of the example package 'template' to your own library or
 * tool naming convention.
 *
 * @example Hello
 *
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 * @author Martin Leopold <m@martinleopold.com>
 */
public class PUI {

	public final static String VERSION = "##library.prettyVersion##";
	// myParent is a reference to the parent sketch
	PApplet p;

	ArrayList<Widget> widgets = new ArrayList<Widget>();

	/**
	 * A constructor, usually called in the setup() method in your sketch to initialize and start
	 * the library.
	 *
	 * @example Hello
	 * @param p
	 */
	public PUI(PApplet p) {
		welcomeMessage();

		this.p = p;
		ProcessingEventHandler peh = new ProcessingEventHandler();
		p.registerMethod("mouseEvent", peh);
		p.registerMethod("keyEvent", peh);
		p.registerMethod("draw", peh);

	}

	private void welcomeMessage() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}

	/**
	 * Return the version of the library.
	 *
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 * Class used to receive Processing's events. This is used internally as not to expose the
	 * callback functions in the public API. No need to use this class. Needs to be public for
	 * Processsing to be able to access it (via reflection).
	 */
	public class ProcessingEventHandler {

		/**
		 * Callback/handler for processing mouse events. Don't call manually.
		 *
		 * @param e
		 */
		public void mouseEvent(MouseEvent e) {
			// filter ENTER and EXIT, because this has to do with whole sketch window
			if (e.getAction() == MouseEvent.ENTER || e.getAction() == MouseEvent.EXIT) {
				return;
			}

			for (Widget w : widgets) {
				w.onMouseEvent(e);
			}
		}

		/**
		 * Callback/handler for processing key events. Don't call manually.
		 *
		 * @param e
		 */
		public void keyEvent(KeyEvent e) {
			for (Widget w : widgets) {
				w.onKeyEvent(e);
			}
		}

		/**
		 * Callback/handler for processing draw events. Don't call manually.
		 *
		 */
		public void draw() {
			//System.out.println("draw " + p.frameCount);
			for (Widget w : widgets) {
				w.onDraw();
			}
		}
	}

	/**
	 * Add an element to the GUI.
	 *
	 * @param e
	 */
	void add(Widget e) {
		if (!widgets.contains(e)) {
			widgets.add(e);
		}
	}
	
	/**
	 * Generator Button
	 * TODO should it return the element or the PUI?
	 * TODO naming: addButton, newButton, createButton, button -> use a prefix for convention
	 * @param x
	 * @param y
	 * @param width
	 * @param height 
	 * @return  
	 */
	public Button addButton(float x, float y, float width, float height) {
		return new Button(this, x, y, width, height);
	}
	
	public void testReflection() {
		Class c = p.getClass(); // sketch class
		Class s = c.getSuperclass();
		System.out.println(c);
		System.out.println(s);
		System.out.println("isPublic " + Modifier.isPublic(c.getModifiers()));
		
		System.out.println("getDeclaredMethods");
		System.out.println("------------------");
		for (Method m : c.getDeclaredMethods()) {
			System.out.println(m);
		}
		
		System.out.println("getDeclaredFields");
		System.out.println("-----------------");
		for (Field f : c.getDeclaredFields()) {
			System.out.println(f);
		}
		
		System.out.println("getDeclaredClasses");
		System.out.println("------------------");
		for (Class cl : c.getDeclaredClasses()) {
			System.out.println(cl);
		}
		
		System.out.println("getMethods");
		System.out.println("----------");
		for (Method m : c.getMethods()) {
			System.out.println(m);
		}
		
		System.out.println("getFields");
		System.out.println("---------");
		for (Field f : c.getFields()) {
			System.out.println(f);
		}
		
		System.out.println("getClasses");
		System.out.println("----------");
		for (Class cl : c.getClasses()) {
			System.out.println(cl);
		}
	}
	
	public void testField(Object o, String name) {
		Field f = findField(o, name);
		try {
			f.setInt(o,1);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void testField(String name) {
		testField(p, name);
	}
	
	public void testMethod(Object o, String name) {
		Method m = findMethod(o, name);
		try {
			m.invoke(o);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void testMethod(String name) {
		testMethod(p, name);
	}
	
	public void testObject(String name) {
		Class c = p.getClass();
		try {
			Field f = c.getDeclaredField(name);
			System.out.println(f);
			c = f.getType();
			for (Field fx : c.getDeclaredFields()) {
				System.out.println(fx);
			}
			for (Method mx : c.getDeclaredMethods()) {
				System.out.println(mx);
			}
					
		} catch (NoSuchFieldException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Look for a Method in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @param parameterTypes
	 * @return 
	 */
	public static Method findMethod(Class c, String name, Class... parameterTypes) {
		Method m = null;
		try {
			m = c.getDeclaredMethod(name, parameterTypes);
			m.setAccessible(true);
		} catch (NoSuchMethodException ex) {
			// look in superclass
			Class s = c.getSuperclass();
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
	public static Method findMethod(Object o, String name, Class... parameterTypes) {
		return findMethod(o.getClass(), name, parameterTypes);
	}
	
	/**
	 * Look for a Field in a Class and its superclasses.
	 * @param c
	 * @param name
	 * @return 
	 */
	public static Field findField(Class c, String name) {
		Field f = null;
		try {
			f = c.getDeclaredField(name);
			f.setAccessible(true);
		} catch (NoSuchFieldException ex) {
			// look in superclass
			Class s = c.getSuperclass();
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
	public static Field findField(Object o, String name) {
		return findField(o.getClass(), name);
	}
	
}
