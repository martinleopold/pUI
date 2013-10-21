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
public class Slider extends WidgetWithLabel<Slider> {
	// state
	float value = 0.5f;
	float min = 0;
	float max = 1;
	
	// helpers
	boolean sliding = false;
			
	public Slider(PUI pui, int width, int height) {
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
		p.noFill();
		p.rect(x, y, width-1, height-1); // stroked rect is bigger
		
		// draw fill
		// draw fill highlight
		p.noStroke();
		if (clicked) p.fill(theme.fillHighlight);
		else p.fill(theme.fill);
		
		// only the "inside" (i.e. without the outline) of the rect is used to represent the value
		// the maximum width of the bar is width-2 (to exclude the outlines)
		float bw = PApplet.map(value, min, max, 0, width-2); // bar width
		bw = PApplet.ceil(bw); // round up to get hard pixels
		p.rect(x+1, y+1, bw, height-2);
	}
	
	void setValue(float mx) {
		// only the "inside" (i.e. without the outline) of the rect is used to represent the value
		// the point on the left outline (x) represents 0/min 
		// the point left of the right outline (x+width-2) represents 1/max
		value = PApplet.map(mx, x, x+width-2, min, max);
		value = PApplet.constrain(value, min, max);
		onValueFloat.fire(value);
		onValue.fire(this);
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
		label.drawHighlight = true;
		sliding = true;
		setValue(mx);
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setValue(mx);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
		label.drawHighlight = false;
		sliding = false;
	}
	
	/*
	 * State
	 */
	
	public Slider min(float m) {
		//value = PApplet.map(value, min, max, m, max); // map value to new range
		min = m;
		return getThis();
	}
	
	public Slider max(float m) {
		//value = PApplet.map(value, min, max, min, m); // map value to new range
		max = m;
		return getThis();
	}
	
	public float value() {
		return value;
	}
	
	public Slider value(float value) {
		this.value = value;
		return getThis();
	}
	
	/*
	 * Callbacks
	 */
	
	Event<Slider> onValue = Events.createEvent(Slider.class);
	Event<Float> onValueFloat = Events.createEvent(float.class);
	public Slider onValue(String methodName) {
		if (Events.addListener(onValueFloat, pui.p, methodName)) {
			System.out.println("Slider -> " + methodName + "(float)");
		}
		if (Events.addListener(onValue, pui.p, methodName)) {
			System.out.println("Slider -> " + methodName + "(Slider)");
		}
		return getThis();
	}
	
	public Slider sets(String fieldName) {
		if (Events.addListenerField(onValueFloat, pui.p, fieldName)) {
			System.out.println("Slider -> " + fieldName);
		}
		return getThis();
	}
	
	public Slider calls(String methodName) {
		return onValue(methodName);
	}
	
	public Slider connect(String name) {
		sets(name);
		calls(name);
		return getThis();
	}

	@Override
	protected Slider getThis() {
		return this;
	}
}
