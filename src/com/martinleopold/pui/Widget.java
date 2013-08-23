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
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Widget extends Rect implements UIEvents {

	PUI pui;

	// state
	boolean active = true;
	boolean hover;
	boolean pressed;
	boolean dragged;
	// helpers
	float clickedMouseX, clickedMouseY;
	float draggedDistX, draggedDistY;

	public Widget(float x, float y, float width, float height, PUI pui) {
		super(x, y, width, height);
		this.pui = pui;
		pui.add(this);
	}

	public void setActive(boolean tf) {
		active = tf;
	}

	public boolean isActive() {
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
				boolean wasHover = hover; // previous hover state
				hover = isInside; // set hover state

				if (hover && !wasHover) {
					mouseEntered(mx, my);
				} else if (!hover && wasHover) {
					mouseExited(mx, my);
				}
				if (hover) {
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

				hover = isInside(mx, my); // recalculate hover state, could have dragged outside the element

				// if it was pressed, release it
				if (pressed) {
					mouseReleased(e.getButton(), mx, my);
				}
				pressed = false;
				dragged = false;
				break;

			case MouseEvent.CLICK: // PRESS and RELEASE have to lie inside the widget
				if (hover) {
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
		draw(pui.p);
		onDraw.fire(this);
	}

	@Override
	public void mouseEntered(float mx, float my) {
	}

	@Override
	public void mouseExited(float mx, float my) {
	}

	@Override
	public void mouseMoved(float mx, float my) {
	}

	@Override
	public void mousePressed(int button, float mx, float my) {
	}

	@Override
	public void mouseReleased(int button, float mx, float my) {
	}

	@Override
	public void mouseClicked(int button, int count, float mx, float my) {
	}

	@Override
	public void mouseDragged(int button, float mx, float my, float dx, float dy) {
	}

	@Override
	public void mouseScrolled(int amount) {
	}

	@Override
	public void keyPressed(int key, int keyCode) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void keyReleased(int key, int keyCode) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void draw(PApplet p) {
	}

	Event<Widget> onDraw = new Event<Widget>();

	public void onDraw(String methodName) {
		Events.addListener(onDraw, pui.p, methodName);
	}

	Event<MouseEvent> onMouse = new Event<MouseEvent>();

	public void onMouse(String methodName) {
		Events.addListener(onMouse, pui.p, methodName);
	}
}
