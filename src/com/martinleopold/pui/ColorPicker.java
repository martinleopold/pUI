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
	float h, s, b; // need to save color components seperately
	int color; // the resulting color
	
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
	
	// color() doesn't currently work with the PDE, since it's treated as a type
	public int getColor() {
		return color;
	}
	
	// color() doesn't currently work with the PDE, since it's treated as a type
	public ColorPicker setColor(int color) {
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
		drawCycle(p, x+1, y+1, innerWidth, hHeight, h / 255);
		
		// saturation selector
		int sHeight = (innerHeight - hHeight) / 2;
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		int toColor = p.color(h, 255, 255);
		p.popStyle();
		drawGradient(p, x+1, y+1+hHeight, innerWidth, sHeight, 
				p.color(255), toColor, s / 255 );
		
		// brightness selector
		int bHeight = innerHeight - hHeight - sHeight;
		drawGradient(p, x+1, y+1+hHeight+sHeight, innerWidth, bHeight, 
				p.color(0), toColor, b / 255 );
	}
	
	void drawMark(PApplet p, int x, int y, int w, int h, float mark, int underlyingColor) {
		int markX = PApplet.round(x + mark * (w-1));
		
		// marker outside
		if (clicked) p.stroke(theme.outlineHighlight);
		else p.stroke(theme.outline);
		p.noFill();
		p.rect(markX-1, y, 2, h-1);
		
		// marker inside
		p.stroke(theme.background);
		p.line(markX, y+1, markX, y+h-2);
				//p.line(markX, y, markX, y+h-1);
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
		drawMark(p, x, y, w, h, mark, p.lerpColor(from, to, mark));
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
		int underlyingColor = p.color(mark*255, 255, 255);
		p.popStyle();
		
		// draw mark
		drawMark(p, x, y, w, h, mark, underlyingColor);
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
//		sliding = true;
		setColor(mx, my);
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setColor(mx, my);
	}
	
//	@Override
//	void mouseReleased(int button, float mx, float my) {
////		label.drawHighlight = false;
////		sliding = false;
//	}
	
	void setColor(float mx, float my) {
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
		

		if ( my >= y+1 && my <= y+hHeight) {
			h = value;
		} else if (my >= y+1+hHeight && my <= y+hHeight+sHeight) {
			s = value;
		} else if (my >= y+1+hHeight+sHeight && my <= y+hHeight+sHeight+bHeight) {
			b = value;
		}
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		color = p.color(h, s, b);
		p.popStyle();
	}
}
