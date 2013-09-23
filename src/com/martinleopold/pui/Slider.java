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
	public float value = 0.5f;
	float min = 0;
	float max = 1;
	boolean sliding = false;
			
	public Slider(PUI pui, int x, int y, int width, int height) {
		super(pui, x, y, width, height);
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
		if (pressed) p.fill(theme.fillHighlight);
		else p.fill(theme.fill);
		// use full width for value but render inside only (preserving outline)
		float widthX = ( (max-min)*value * width ) - 1;
		if (widthX < 0) widthX = 0;
		if (widthX > width-2) widthX = width-2;
		p.rect(x+1,y+1, widthX, height-2);
	}
	
	private void setValue(float mx) {
		float dist = mx - x;
		dist = PApplet.constrain(dist/width, 0, max-min);
		value = min + dist;
		onValueChanged.fire(this);
		newValue.fire(value);
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
		sliding = true;
		setValue(mx);
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setValue(mx);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
		sliding = false;
	}
	
	Event<Slider> onValueChanged = new Event<Slider>();
	Event<Float> newValue = new Event<Float>();
	
	public void onValue(String methodName) {
		Events.addListener(onValueChanged, pui.p, methodName);
	}
	
	public void connect(String fieldName) {
		Events.addListenerField(newValue, pui.p, fieldName);
	}
}
