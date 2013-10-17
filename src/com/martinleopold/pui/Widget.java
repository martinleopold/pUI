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
package com.martinleopold.pui;

import com.martinleopold.pui.events.Event;
import com.martinleopold.pui.events.Events;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * 
 * @author Martin Leopold <m@martinleopold.com>
 * @param <T> Concrete Type of the Widget
 */
 public abstract class Widget<T extends Widget<T>> extends Rect {

	PUI pui;

	// state
	// boolean focus; // receives keyboard events
	boolean active = true; // receives events (mouse, keyooard) except draw!
	boolean visible = true; // widget is drawn (onDraw() is called in any case)
	boolean hovered; // mouse over
	boolean clicked; // mouse down
	boolean dragged; // mouse is dragging
	
	// helpers
	float clickedMouseX, clickedMouseY;
	float draggedDistX, draggedDistY;
	
	// last mouse position
	int mx, my;
	
	Theme theme;
	PFont font;
	
	Rect layoutRect;

	protected Widget(PUI pui, int width, int height) {
		this(pui, width, height, true);
	}
	
	/**
	 * Create a Widget. Choose whether to add it to the layout manager or not.
	 * If not put in layout the position can be set via setPosition() or position()
	 * @param pui
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param doLayout 
	 */
	Widget(PUI pui, int width, int height, boolean doLayout) {
		super(0, 0, width, height);
		layoutRect = new Rect(this);
		
		this.pui = pui;
		pui.add(this, doLayout);
		
		theme = pui.theme;
		font = pui.font;
	}
	
	// warning: [unchecked] unchecked cast
	//	required: T
	//  found:    Widget<T>
	//  where T is a type-variable:
	//    T extends Widget<T> declared in class Widget
	@SuppressWarnings("unchecked")
	protected T getThis() {
		return (T)this;
	}

	void onMouseEvent(MouseEvent e) {
		if (!isActive()) {
			return;
		}

		mx = e.getX();
		my = e.getY();
		boolean isInside = isInside(mx, my); // is the mouse inside the element

		switch (e.getAction()) {
			case MouseEvent.MOVE:
				// distribute mouseEntered, mouseExited and mouseMoved
				boolean wasHover = hovered; // previous hover state
				hovered = isInside; // set hover state

				if (hovered && !wasHover) {
					mouseEntered(mx, my);
				} else if (!hovered && wasHover) {
					mouseExited(mx, my);
				}
				if (hovered) {
					mouseMoved(mx, my);
				}
				break;

			case MouseEvent.PRESS:
				clicked = isInside;
				if (isInside) {
					clickedMouseX = mx;
					clickedMouseY = my;
					mousePressed(e.getButton(), mx, my);
				}
				break;

			case MouseEvent.RELEASE:
				if (dragged) {
				draggedDistX = mx - clickedMouseX;
				draggedDistY = my - clickedMouseY;
			}

				hovered = isInside(mx, my); // recalculate hover state, could have dragged outside the element

				// if it was pressed, release it
				if (clicked) {
					mouseReleased(e.getButton(), mx, my);
				}
				clicked = false;
				dragged = false;
				break;

			case MouseEvent.CLICK: // PRESS and RELEASE have to lie inside the widget
				if (hovered) {
				mouseClicked(e.getButton(), e.getCount(), mx, my);
			}
				break;

			case MouseEvent.DRAG:
				dragged = clicked;
				if (dragged) {
					draggedDistX = mx - clickedMouseX;
					draggedDistY = my - clickedMouseY;
					mouseDragged(e.getButton(), mx, my, draggedDistX, draggedDistY);
				}
				break;

			case MouseEvent.WHEEL:
				mouseScrolled(e.getCount());
				break;
		}

		if (isInside) {
			onMouse.fire(e);
		}
	}

	void onKeyEvent(KeyEvent e) {
		if (!isActive()) {
			return;
		}
	}

	void onDraw() {
		PApplet p = pui.p;
		p.pushMatrix();
		p.pushStyle();
		if (isVisible()) {
			draw(p); // only draw if visible
		}
		onDraw.fire(this); // onDraw Event is fired in any case
		p.popMatrix();
		p.popStyle();
	}
	
	void mouseEntered(float mx, float my) {
	}

	void mouseExited(float mx, float my) {
	}

	void mouseMoved(float mx, float my) {
	}

	void mousePressed(int button, float mx, float my) {
	}

	void mouseReleased(int button, float mx, float my) {
	}

	void mouseClicked(int button, int count, float mx, float my) {
	}

	void mouseDragged(int button, float mx, float my, float dx, float dy) {
	}

	void mouseScrolled(int amount) {
	}

	void keyPressed(int key, int keyCode) {
	}

	void keyReleased(int key, int keyCode) {
	}

	void draw(PApplet p) {
	}
	
	/*
	 * Base State
	 */
	
	// in px
	void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		layoutRect.x = x;
		layoutRect.y = y;
	}
	
	// in px
	void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		layoutRect.width = w;
		layoutRect.height = h;
	}
	
	// grid units
	public T position(float x, float y) {
		// TODO. assuming label position below widget
		setPosition(pui.gridX2Px(x), pui.gridY2Px(y));
		// only pin if it is in layout
		if (pui.layout.contains(this)) {
			pui.layout.pin(this);
		}
		return getThis();
	}
	
	// grid units
	public T size(float w, float h) {
		setSize(pui.gridX2Px(w), pui.gridY2Px(h));
		if (pui.layout.contains(this)) {
			pui.layout.reLayout();
		}
		return getThis();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean isHovered() {
		return hovered;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public boolean isDragged() {
		return dragged;
	}
	
	public T activate() {
		active = true;
		return getThis();
	}
	
	public T deactivate() {
		active = false;
		return getThis();
	}
	
	public T show() {
		visible = true;
		return getThis();
	}
	
	public T hide() {
		visible = false;
		return getThis();
	}
	
	// in px
	public int positionX() {
		return layoutRect.x;
	}
	
	// in px	
	public int positionY() {
		return layoutRect.y;
	}
	
	// in px
	public int width() {
		return layoutRect.width;
	}
	
	// in px	
	public int height() {
		return layoutRect.height;
	}
	
	// relative to widget
	public int mouseX() {
		return mx - positionX();
	}
	
	// relative to widget
	public int mouseY() {
		return my - positionY();
	}
	
	/*
	 * Events / Callbacks
	 */

	// TODO: how to convert this to Events.createEvent(Widget<T>.class) ? 
	Event<Widget<T>> onDraw = new Event<Widget<T>>();

	public T onDraw(String methodName) {
		Events.addListener(onDraw, pui.p, methodName);
		visible = false; // disable default rendering. widget needs to be drawn via the onDraw callback now
		return getThis();
	}

	Event<MouseEvent> onMouse = new Event<MouseEvent>();

	public T onMouse(String methodName) {
		Events.addListener(onMouse, pui.p, methodName);
		return getThis();
	}
}
