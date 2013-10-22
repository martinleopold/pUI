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
public class ColorPicker extends Widget<ColorPicker> {
	int color;
	
	// helpers
//	int hHeight, sHeight, bHeight; // height of the individual sliders
	
	ColorPicker(PUI pui, int width, int height) {
		super(pui, width, height, true);
		color = pui.p.color(0);
	}
	
	@Override
	protected ColorPicker getThis() {
		return this;
	}
	
	public int color() {
		return color;
	}
	
	public ColorPicker color(int color) {
		this.color = color;
		return getThis();
	}
	
	@Override
	void draw(PApplet p) {
		// draw background
		p.noStroke();
		p.fill(theme.background);
		p.rect(x, y, width, height);
		
		// outline
		if (hovered) p.stroke(theme.outlineHighlight);
		else p.stroke(theme.outline);
		p.noFill();
		p.rect(x, y, width-1, height-1); // stroked rect is bigger
		
		// hue selector
		int innerHeight = height -2;
		int innerWidth = width - 2;
		int hHeight = innerHeight / 3;
		float hue = p.hue(color);
		float saturation = p.saturation(color);
		float brightness = p.brightness(color);
		drawCycle(p, x+1, y+1, innerWidth, hHeight, hue / 255);
		
		// saturation selector
		int sHeight = (innerHeight - hHeight) / 2;
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		drawGradient(p, x+1, y+1+hHeight, innerWidth, sHeight, 
				p.color(hue, 0, brightness), p.color(hue, 255, brightness), saturation / 255 );
		
		// brightness selector
		int bHeight = innerHeight - hHeight - sHeight;
		drawGradient(p, x+1, y+1+hHeight+sHeight, innerWidth, bHeight, 
				p.color(hue, saturation, 0), p.color(hue, saturation, 255), brightness / 255 );
		p.popStyle();
	}
	
	void drawMark(PApplet p, int x, int y, int w, int h, float mark) {
		if (clicked) p.stroke(theme.outlineHighlight); else p.stroke(theme.outline);
		int markX = PApplet.round(x + mark * (w-1));
		p.line(markX, y, markX, y+h-1);
	}
	
	// draw a gradient with a tickmark
	void drawGradient(PApplet p, int x, int y, int w, int h, int from, int to, float mark) {
		// draw w vertical lines (-> gradient)
		for (int i=0; i<w; i++) {
			float a = (float)i / (w-1);
			p.stroke(p.lerpColor(from, to, a));
			p.line(x+i, y, x+i, y+h-1);
		}
		// draw mark
		drawMark(p, x, y, w, h, mark);
	}
	
	void drawCycle(PApplet p, int x, int y, int w, int h, float mark) {
		// draw w vertical lines (-> color cycle)
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		for (int i=0; i<w; i++) {
			float a = (float)i / (w-1);
			p.stroke(a*255, 255, 255);
			p.line(x+i, y, x+i, y+h-1);
		}
		p.popStyle();
		
		// draw mark
		drawMark(p, x, y, w, h, mark);
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
//		sliding = true;
		setValue(mx, my);
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setValue(mx, my);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
//		label.drawHighlight = false;
//		sliding = false;
	}
	
	void setValue(float mx, float my) {
		int innerHeight = height - 2;
		int hHeight = innerHeight / 3;
		int sHeight = (innerHeight - hHeight) / 2;
		int bHeight = innerHeight - hHeight - sHeight;
		PApplet p = pui.p;
		
		// which slider was clicked
		// h: [y+1, y+hHeight
		// s: [y+1+hHeight, y+hHeight+sHeight]
		// b: [y+1+hHeight+sHeight, y+hHeight+sHeight+bHeight]
		mx = PApplet.constrain(mx, x+1, x+width-2);
		float value = PApplet.map(mx, x+1, x+width-2, 0, 255);
		System.out.println("value: " + value);
		
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		if ( my >= y+1 && my <= y+hHeight) {
			color = p.color(value, p.saturation(color), p.brightness(color));
			System.out.println("setting h");
		} else if (my >= y+1+hHeight && my <= y+hHeight+sHeight) {
			color = p.color(p.hue(color), value, p.brightness(color));
			System.out.println("setting s");
		} else if (my >= y+1+hHeight+sHeight && my <= y+hHeight+sHeight+bHeight) {
			color = p.color(p.hue(color), p.saturation(color), value);
			System.out.println("setting b");
		}
		System.out.println(p.hue(color) + " " + p.saturation(color) + " " + p.brightness(color));
		p.popStyle();
	}
}
