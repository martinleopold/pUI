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
 */
abstract class Widget<TWidget> extends Rect {

	PUI pui;

	// state
	// boolean focus; // receives keyboard events
	boolean active = true; // receives events (mouse, draw, ...)
	public boolean hovered; // mouse over
	public boolean pressed; // mouse down
	public boolean dragged; // mouse is dragging
	
	// helpers
	float clickedMouseX, clickedMouseY;
	float draggedDistX, draggedDistY;
	
	Theme theme;
	PFont font;
	
	Rect layoutRect;

	// TODO having x, y here doesn't make sense since it's put in the layout immediately
	Widget(PUI pui, int x, int y, int width, int height) {
		this(pui, x, y, width, height, true);
	}
	
	Widget(PUI pui, int x, int y, int width, int height, boolean doLayout) {
		super(x, y, width, height);
		layoutRect = new Rect(this);
		
		this.pui = pui;
		pui.add(this, doLayout);
		
		theme = pui.theme;
		font = pui.font;
	}

//	public void setActive(boolean tf) {
//		active = tf;
//	}

	boolean isActive() {
		return active;
	}

	void onMouseEvent(MouseEvent e) {
		if (!isActive()) {
			return;
		}

		int mx = e.getX(), my = e.getY();
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
				pressed = isInside;
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
				if (pressed) {
					mouseReleased(e.getButton(), mx, my);
				}
				pressed = false;
				dragged = false;
				break;

			case MouseEvent.CLICK: // PRESS and RELEASE have to lie inside the widget
				if (hovered) {
				mouseClicked(e.getButton(), e.getCount(), mx, my);
			}
				break;

			case MouseEvent.DRAG:
				dragged = pressed;
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
		if (!isActive()) {
			return;
		}
		PApplet p = pui.p;
		p.pushMatrix();
		p.pushStyle();
		draw(p);
		onDraw.fire(this);
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

	Event<Widget> onDraw = new Event<Widget>();

	public TWidget onDraw(String methodName) {
		Events.addListener(onDraw, pui.p, methodName);
		return (TWidget)this;
	}

	Event<MouseEvent> onMouse = new Event<MouseEvent>();

	public TWidget onMouse(String methodName) {
		Events.addListener(onMouse, pui.p, methodName);
		return (TWidget)this;
	}
	
	void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		layoutRect.x = x;
		layoutRect.y = y;
	}
	
	void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		layoutRect.width = w;
		layoutRect.height = h;
	}
	
	public TWidget position(int x, int y) {
		// TODO. assuming label position below widget
		setPosition(x,y);
		pui.layout.pin(this);
		pui.layout.reLayout();
		return (TWidget)this;
	}
	
	public TWidget size(int w, int h) {
		setSize(w,h);
		pui.layout.reLayout();
		return (TWidget)this;
	}
}
