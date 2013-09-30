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

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
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
public final class PUI extends Rect {

	final static String VERSION = "##library.prettyVersion##";
	// myParent is a reference to the parent sketch
	PApplet p;

	ArrayList<Widget<?>> widgets = new ArrayList<Widget<?>>(); // list of widgets managed by this PUI
		
	/**
	 * A constructor, usually called in the setup() method in your sketch to initialize and start
	 * the library.
	 *
	 * @example Hello
	 * @param p
	 */
	public PUI(PApplet p) {
		super(0,0, p.width, p.height);
		welcomeMessage();

		this.p = p;
		ProcessingEventHandler peh = new ProcessingEventHandler();
		p.registerMethod("mouseEvent", peh);
		p.registerMethod("keyEvent", peh);
		p.registerMethod("draw", peh);
		
		theme(Theme.DEFAULT);
		font("SansSerif");
		setLayout(new Layout(width, height, 0, 0, 0));
		grid(DEFAULT_GRID_X, DEFAULT_GRID_Y);
		padding(DEFAULT_PADDING_X, DEFAULT_PADDING_Y); // also sets layout padding
		columnWidth(DEFAULT_COLUMNWIDTH);
		
		visible = true;
	}
	
	// static initializer to avoid new syntax
	public static PUI init(PApplet p) {
		return new PUI(p);
	}

	private void welcomeMessage() {
		System.out.println("##library.name## ##library.prettyVersion## (##library.version##) by ##author##");
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
	 * @exclude
	 */
	public class ProcessingEventHandler {

		/**
		 * Callback/handler for processing mouse events. Don't call manually.
		 *
		 * @param e
		 */
		public void mouseEvent(MouseEvent e) {
			if (!visible) return; // don't process mouse events when GUI is invisible
						
			// filter ENTER and EXIT, because this has to do with whole sketch window
			if (e.getAction() == MouseEvent.ENTER || e.getAction() == MouseEvent.EXIT) {
				return;
			}

			for (Widget<?> w : widgets) {
				w.onMouseEvent(e);
			}
		}

		/**
		 * Callback/handler for processing key events. Don't call manually.
		 *
		 * @param e
		 */
		public void keyEvent(KeyEvent e) {
			for (Widget<?> w : widgets) {
				w.onKeyEvent(e);
			}
		}

		/**
		 * Callback/handler for processing draw events. Don't call manually.
		 *
		 */
		public void draw() {
			if (!visible) return;
			
			p.pushMatrix();
			p.resetMatrix();
			
			p.translate(x,y);
			p.pushStyle();
			if (drawBackground) drawBackground();
			if (drawGrid) drawGrid();
			p.popStyle();
			
			//System.out.println("draw " + p.frameCount);
			for (Widget<?> w : widgets) {
				w.onDraw();
			}

			p.popMatrix();
		}
	}
	
	void drawBackground() {
		p.noStroke();
		p.fill(theme.background);
		p.rect(0, 0, width, height);
	}
	
	void drawGrid() {
		p.stroke(Theme.setAlpha(theme.outline, 64));
		// vertical lines
		for (int x=gridX; x<width; x+=gridX) {
			p.line(x, 0, x, height-1);
		}
		// horizontal lines
		for (int y=gridY; y<height; y+=gridY) {
			p.line(0, y, width-1, y);
		}
	}

	/**
	 * Add an element to the GUI.
	 *
	 * @param e
	 */
	void add(Widget<?> e) {
		add(e, true);
	}
	
	void add(Widget<?> e, boolean doLayout) {
		if (!widgets.contains(e)) {
			widgets.add(e);
			if (doLayout) layout.add(e);
		}
	}
	
	
	static float DEFAULT_BUTTON_W = 5;
	static float DEFAULT_BUTTON_H = 2;
	// return a default size button at the layout without label
	public Button addButton() {
		return new Button(this, 0, 0, gridX2Px(DEFAULT_BUTTON_W), gridY2Px(DEFAULT_BUTTON_H));
	}
	
//	public Button addButton(String name) {
//		return null;
//	}
	
	static float DEFAULT_TOGGLE_W = 2;
	static float DEFAULT_TOGGLE_H = 2;
	public Toggle addToggle() {
		return new Toggle(this, 0, 0, gridX2Px(DEFAULT_TOGGLE_W), gridY2Px(DEFAULT_TOGGLE_H));
	}
	
	static float DEFAULT_SLIDER_W = 11;
	static float DEFAULT_SLIDER_H = 2;
	public Slider addSlider() {
		return new Slider(this, 0, 0, gridX2Px(DEFAULT_SLIDER_W), gridY2Px(DEFAULT_SLIDER_H));
	}
	
	
	static float DEFAULT_DIVIDER_W = 11;
	static float DEFAULT_DIVIDER_H = 1;
	public Divider addDivider() {
		return new Divider(this, 0, 0, gridX2Px(DEFAULT_DIVIDER_W), gridY2Px(DEFAULT_DIVIDER_H));
	}
	
	public Label addLabel(String text) {
		Label l = new Label(this, 0, 0, 0, gridY2Px(DEFAULT_FONTSIZE_MEDIUM));
		l.text(text);
		return l;
	}
	
	// TODO: use name as: label, retrieval handle, default callback (method or variable)
//	public Button addButton(String name) {
//		return null;
//	}
	
	
	
	/*
	 * 
	 * Test Methods TODO: remove / refactor
	 * 
	 */
	
//	void testReflection() {
//		Class<?> c = p.getClass(); // sketch class
//		Class<?> s = c.getSuperclass();
//		System.out.println(c);
//		System.out.println(s);
//		System.out.println("isPublic " + Modifier.isPublic(c.getModifiers()));
//		
//		System.out.println("getDeclaredMethods");
//		System.out.println("------------------");
//		for (Method m : c.getDeclaredMethods()) {
//			System.out.println(m);
//		}
//		
//		System.out.println("getDeclaredFields");
//		System.out.println("-----------------");
//		for (Field f : c.getDeclaredFields()) {
//			System.out.println(f);
//		}
//		
//		System.out.println("getDeclaredClasses");
//		System.out.println("------------------");
//		for (Class<?> cl : c.getDeclaredClasses()) {
//			System.out.println(cl);
//		}
//		
//		System.out.println("getMethods");
//		System.out.println("----------");
//		for (Method m : c.getMethods()) {
//			System.out.println(m);
//		}
//		
//		System.out.println("getFields");
//		System.out.println("---------");
//		for (Field f : c.getFields()) {
//			System.out.println(f);
//		}
//		
//		System.out.println("getClasses");
//		System.out.println("----------");
//		for (Class<?> cl : c.getClasses()) {
//			System.out.println(cl);
//		}
//	}
//	
//	void testField(Object o, String name) {
//		Field f = findField(o, name);
//		try {
//			f.setInt(o,1);
//		} catch (IllegalArgumentException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}
//	
//	void testField(String name) {
//		testField(p, name);
//	}
//	
//	void testMethod(Object o, String name) {
//		Method m = findMethod(o, name);
//		try {
//			m.invoke(o);
//		} catch (IllegalAccessException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (IllegalArgumentException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (InvocationTargetException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}
//	
//	void testMethod(String name) {
//		testMethod(p, name);
//	}
//	
//	void testObject(String name) {
//		Class<?> c = p.getClass();
//		try {
//			Field f = c.getDeclaredField(name);
//			System.out.println(f);
//			c = f.getType();
//			for (Field fx : c.getDeclaredFields()) {
//				System.out.println(fx);
//			}
//			for (Method mx : c.getDeclaredMethods()) {
//				System.out.println(mx);
//			}
//					
//		} catch (NoSuchFieldException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (SecurityException ex) {
//			Logger.getLogger(PUI.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}
	

	
	Theme theme;
	public PUI theme(Theme t) {
		this.theme = t;
		for (Widget<?> w : widgets) {
			w.theme = t;
		}
		return this;
	}
	
	static float DEFAULT_FONTSIZE_SMALL = 1; 
	static float DEFAULT_FONTSIZE_MEDIUM = 1.5f;
	static float DEFAULT_FONTSIZE_LARGE = 2;
	
	PFont font;
	public PUI font(PFont f) {
		this.font = f;
		return this;
	}
	
	public PUI font(String fontName) {
		return font(p.createFont(fontName, DEFAULT_FONTSIZE_LARGE));
	}
	
	
	static final int DEFAULT_GRID_X = 10;
	static final int DEFAULT_GRID_Y = 10;
	
	int gridX;
	int gridY;
	
	public PUI grid(int x, int y) {
		this.gridX = x;
		this.gridY = y;
		// set padding in layout
		layout.setPadding(gridX2Px(paddingX), gridY2Px(paddingY));
		// set column width in layout
		layout.setColumnWidth(gridX2Px(columnWidth));
		return this;
	}
	
	int gridX2Px(float x) {
		return Math.round(gridX * x);
	}
			
	int gridY2Px(float y) {
		return Math.round(gridY * y);
	}
	
	boolean drawGrid;
	
	public PUI showGrid() {
		return showGrid(true);
	}
	
	public PUI showGrid(boolean yes) {
		drawGrid = yes;
		return this;
	}
	
	public PUI toggleGrid() {
		return showGrid(!drawGrid);
	}
	
	boolean drawBackground = true;
	
	public PUI showBackground() {
		return showBackground(true);
	}
	
	public PUI showBackground(boolean tf) {
		drawBackground = tf;
		return this;
	}
	
	
	static float DEFAULT_PADDING_X = 0.5f;
	static float DEFAULT_PADDING_Y = 0.5f;
	
	float paddingX;
	float paddingY;
	
	// set padding in grid units
	public PUI padding(float x, float y) {
		this.paddingX = x;
		this.paddingY = y;
		layout.setPadding(gridX2Px(paddingX), gridY2Px(paddingY));
		return this;
	}

	
	Layout layout;
	void setLayout(Layout l) {
		this.layout = l;
	}
	
	public PUI newRow() {
		layout.newRow();
		return this;
	}
	
	public PUI newColumn() {
		layout.newColumn();
		return this;
	}
	
	static float DEFAULT_COLUMNWIDTH = 13; // in grid X unit
	

	float columnWidth;	// in grid units
	public PUI columnWidth(float w) {
		this.columnWidth = w;
		layout.setColumnWidth(gridX2Px(w));
		return this;
	}
	
	boolean visible;
	public PUI hide() {
		visible = false;
		return this;
	}
	
	public PUI show() {
		visible = true;
		return this;
	}
	
	public PUI toggle() {
		visible = !visible;
		return this;
	}
	
	public PUI size(int w, int h) {
		this.width = w;
		this.height = h;
		layout.setSize(w, h);
		layout.reLayout();
		return this;
	}
}
