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

import processing.core.PApplet;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public abstract class AbstractElement extends Rect {

	protected float clickedMouseX, clickedMouseY;
	protected float clickedPositionX, clickedPositionY;
	protected float draggedDistX, draggedDistY;

	public boolean pressed, dragged, hover, activated = true;

	protected boolean debug = false;

	long lastPressed = 0;

	protected PUI pui;
	
	public AbstractElement(float x, float y, float width, float height, PUI pui) {
		super(x, y, width, height);
		this.pui = pui;
		pui.add(this);
		//Interactive.get().addElement( this );
	}

	public void setDebug(boolean tf) {
		debug = tf;
	}

	public void setActive(boolean tf) {
		activated = tf;
	}

	public boolean isActive() {
		return activated;
	}

	abstract public void mouseEntered();

	abstract public void mouseEntered(float mx, float my);

	abstract public void mouseMoved();

	abstract public void mouseMoved(float mx, float my);

	abstract public void mouseExited();

	abstract public void mouseExited(float mx, float my);

	public void mousePressedPre(float mx, float my) {
		if (!isActive()) {
			return;
		}

		pressed = isInside(mx, my);
		if (pressed) {
			clickedPositionX = x;
			clickedPositionY = y;
			clickedMouseX = mx;
			clickedMouseY = my;
			long now = System.currentTimeMillis();
			long lp = lastPressed;
			lastPressed = now;
			if (now - lp < 200) {
				mouseDoubleClicked();
				mouseDoubleClicked(mx, my);
			} else {
				mousePressed();
				mousePressed(mx, my);
			}
		}
	}

	abstract public void mousePressed();

	abstract public void mousePressed(float mx, float my);
	
	abstract public void mouseClicked();

	abstract public void mouseClicked(float mx, float my);

	abstract public void mouseDoubleClicked();

	abstract public void mouseDoubleClicked(float mx, float my);

	public void mouseDraggedPre(float mx, float my) {
		if (!isActive()) {
			return;
		}

		dragged = pressed;
		if (dragged) {
			draggedDistX = clickedMouseX - mx;
			draggedDistY = clickedMouseY - my;
			mouseDragged(mx, my);
			mouseDragged(mx, my, clickedPositionX - draggedDistX, clickedPositionY - draggedDistY);
		}
	}

	abstract public void mouseDragged(float mx, float my);

	abstract public void mouseDragged(float mx, float my, float dx, float dy);

	public void mouseReleasedPre(float mx, float my) {
		if (!isActive()) {
			return;
		}

		if (dragged) {
			draggedDistX = clickedMouseX - mx;
			draggedDistY = clickedMouseY - my;
		}
		
		hover = isInside(mx,my); // recalculate hover state, could have dragged outside the element

		if (pressed) {
			mouseReleased();
			mouseReleased(mx, my);
		}
	}

	abstract public void mouseReleased();

	abstract public void mouseReleased(float mx, float my);

	public void mouseReleasedPost(float mx, float my) {
		pressed = false;
		dragged = false;
	}

	abstract public void mouseScrolled(float step);

	abstract public void draw();
}
