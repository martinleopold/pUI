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

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Button extends WidgetWithLabel<Button> {
	// state
	boolean pressed;
	
	public Button(PUI pui, int width, int height) {
		super(pui, width, height);
	}
	
	@Override
	void draw(PApplet p) {
		// draw background
		p.noStroke();
		p.fill(theme.background);
		p.rect(x, y, width, height);
		
		// draw outline
		// draw outline highlight
		if (hovered) p.stroke(theme.outlineHighlight);
		else p.stroke(theme.outline);
		
		// draw fill
		// draw fill highlight
		if (clicked) p.fill(theme.fillHighlight);
		else p.fill(theme.fill);
		
		p.rect(x,y, width-1, height-1); // stroked rect is bigger
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
		label.drawHighlight = true;
		clicked = true;
		
		onClickBoolean.fire(clicked);
		onClickVoid.fire(null);
		onClick.fire(this);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
		label.drawHighlight = false;
		clicked = false;
		onClickBoolean.fire(clicked);
	}
	
	/*
	 * State
	 */
	
	public boolean isPressed() {
		return pressed;
	}
	
	public Button isPressed(boolean pressed) {
		this.pressed = pressed;
		// TODO fire callback
		return getThis();
	}
	
	/*
	 * Callbacks
	 */
	
	Event<Void> onClickVoid = Events.createEvent(void.class);
	Event<Button> onClick = Events.createEvent(Button.class);
	Event<Boolean> onClickBoolean = Events.createEvent(boolean.class);
		
	public Button onClick(String methodName) {
		if (Events.addListener(onClickVoid, pui.p, methodName)) {
			System.out.println("Button -> " + methodName + "()");
		}
		if (Events.addListener(onClick, pui.p, methodName)) {
			System.out.println("Button -> " + methodName + "(Button)");
		}
		return getThis();
	}
	
	// connect to a field
	public Button sets(String fieldName) {
		if (Events.addListenerField(onClickBoolean, pui.p, fieldName)) {
			System.out.println("Button -> " + fieldName);
		}
		return getThis();
	}
	
	// connect to a method
	public Button calls(String methodName) {
		return onClick(methodName);
	}
	
	// auto connect to fields and methods
	public Button connect(String name) {
		sets(name);
		calls(name);
		return getThis();
	}
	
	@Override
	protected Button getThis() {
		return this;
	}
}
