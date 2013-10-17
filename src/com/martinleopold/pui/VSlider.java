/*
 * Copyright (C) 2013 martinleopold
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
 * @author martinleopold
 */
public class VSlider extends Slider {
	public VSlider(PUI pui, int width, int height) {
		super(pui, width, height);
	}
	
	@Override
	protected VSlider getThis() {
		return this;
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
		// the maximum height of the bar is height-2 (to exclude the outlines)
		float bh = PApplet.map(value, min, max, 0, height-2); // bar height
		bh = PApplet.ceil(bh);
		p.rect(x+1, y+height-1-bh, width-2, bh);
	}
	
	/**
	 * 
	 * @param my 
	 */
	@Override
	protected void setValue(float my) {
		// only the "inside" (i.e. without the outline) of the rect is used to represent the value
		// the point on the lower outline (y+height-1) represents 0/min 
		// the point below the upper outline (y+1) represents 1/max
		value = PApplet.map(my, y+height-1, y+1, min, max);
		value = PApplet.constrain(value, min, max);
		onValueFloat.fire(value);
		onValue.fire(this);
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
		label.drawHighlight = true;
		sliding = true;
		setValue(my);
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setValue(my);
	}
}
