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
public class Slider extends Widget {
	float value = 0.5f;
	float min = 0;
	float max = 1;
	boolean sliding = false;
			
	public Slider(float x, float y, float width, float height, PUI pui) {
		super(x, y, width, height, pui);
	}
	
	@Override
	public void draw(PApplet p) {
		p.pushStyle();
		
		// draw body rect
		p.noStroke();
		p.fill(128);
		p.rect(x, y, width, height);
		
		// draw outline
		p.noFill();
		if (hover) {
			p.stroke(64);
			p.rect(x, y, width, height);
		}
		
		// draw marker
		p.noStroke();
		if (pressed) p.fill(255);
		else p.fill(200);
		
		float markerX = x + (max-min)*value*width;
		p.rect(markerX-1, y, 3, height);
		
		p.popStyle();
	}
	
	private void setValue(float mx) {
		System.out.println("mx: " + mx);
		float dist = mx - x;
		System.out.println("dist: " + dist);
		
		dist = PApplet.constrain(dist/width, 0, max-min);
		System.out.println("dist: " + dist);
		
		value = min + dist;
		System.out.println("value: " + value);
	}
	
	@Override
	public void mousePressed(int button, float mx, float my) {
		sliding = true;
		setValue(mx);
	}
	
	@Override
	public void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setValue(mx);
	}
	
	@Override
	public void mouseReleased(int button, float mx, float my) {
		sliding = false;
	}
}
