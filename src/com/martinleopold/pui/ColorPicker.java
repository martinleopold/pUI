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

import com.martinleopold.pui.events.Event;
import com.martinleopold.pui.events.Events;
import processing.core.PApplet;

/**
 *
 * @author martinleopold
 */
public class ColorPicker extends WidgetWithLabel<ColorPicker> {
	float h, s, b, a; // need to save color components seperately
	int color; // the resulting color
	
	enum Slider { NONE, H, S, B, A}
	Slider sliding; // the slider currently in use
	
// helpers
//	int hHeight, sHeight, bHeight; // height of the individual sliders
	
	ColorPicker(PUI pui, int width, int height) {
		super(pui, width, height);
		a = 255;
		color = pui.p.color(h,s,b, a);
		sliding = Slider.NONE;
	}
	
	@Override
	protected ColorPicker getThis() {
		return this;
	}
	
	// color() doesn't currently work with the PDE, since it's treated as a type
	public int col() {
		return color;
	}
	
	// color() doesn't currently work with the PDE, since it's treated as a type
	public ColorPicker col(int color) {
		this.color = color;
		PApplet p = pui.p;
		h = p.hue(color);
		s = p.saturation(color);
		b = p.brightness(color);
		a = p.alpha(color);
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
		int hHeight = innerHeight / 4;
		drawCycle(p, x+1, y+1, innerWidth, hHeight, h / 255);
		
		// saturation selector
		int sHeight = (innerHeight - hHeight) / 3;
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		int toColor = p.color(h, 255, 255);
		int fromAlpha = p.color(h,s,b,0);
//		int toAlpha = p.color(h,s,b,255);
		p.popStyle();
		drawGradient(p, x+1, y+1+hHeight, innerWidth, sHeight, 
				p.color(255), toColor, s / 255 );
		
		// brightness selector
		int bHeight = (innerHeight - hHeight - sHeight) / 2;
		drawGradient(p, x+1, y+1+hHeight+sHeight, innerWidth, bHeight, 
				p.color(0), toColor, b / 255 );
		
		// alpha/opacity selector [transparent, opaque]
		int aHeight = innerHeight - hHeight - sHeight - bHeight;
		
		drawGradient(p, x+1, y+1+hHeight+sHeight+bHeight, innerWidth, aHeight, 
				fromAlpha, toColor, a / 255 );
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
		int innerHeight = height - 2;
		int hHeight = innerHeight / 4;
		int sHeight = (innerHeight - hHeight) / 3;
		int bHeight = (innerHeight - hHeight - sHeight) / 2;
		int aHeight = innerHeight - hHeight - sHeight - bHeight;
		// which slider was clicked
		// h: [y+1, y+hHeight
		// s: [y+1+hHeight, y+hHeight+sHeight]
		// b: [y+1+hHeight+sHeight, y+hHeight+sHeight+bHeight]

		if ( my >= y+1 && my <= y+hHeight) {
			sliding = Slider.H;
		} else if (my >= y+1+hHeight && my <= y+hHeight+sHeight) {
			sliding = Slider.S;
		} else if (my >= y+1+hHeight+sHeight && my <= y+hHeight+sHeight+bHeight) {
			sliding = Slider.B;
		} else if (my >= y+1+hHeight+sHeight+bHeight && my <= y+hHeight+sHeight+bHeight+aHeight) {
			sliding = Slider.A;
		}
				
		setColor(mx);
		label.drawHighlight = true;
	}
	
	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		setColor(mx);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
//		label.drawHighlight = false;
		sliding = Slider.NONE;
	}
	
	void setColor(float mx) {
		mx = PApplet.constrain(mx, x+1, x+width-2);
		float value = PApplet.map(mx, x+1, x+width-2, 0, 255);
		
		switch (sliding) {
			case H: 
				h = value; 
				break;
			case S:
				s = value;
				break;
			case B:
				b = value;
				break;
			case A:
				a = value;
				break;
			default:
				return;
		}
		
		PApplet p = pui.p;
		p.pushStyle();
		p.colorMode(PApplet.HSB);
		color = p.color(h, s, b, a);
		p.popStyle();
		
		onColorInt.fire(color);
		onColor.fire(this);
	}
	
	/*
	 * Callbacks
	 */
	
	Event<ColorPicker> onColor = Events.createEvent(ColorPicker.class);
	Event<Integer> onColorInt = Events.createEvent(int.class);
	public ColorPicker onColor(String methodName) {
		if (Events.addListener(onColorInt, pui.p, methodName)) {
			System.out.println("ColorPicker -> " + methodName + "(int)");
		}
		if (Events.addListener(onColor, pui.p, methodName)) {
			System.out.println("ColorPicker -> " + methodName + "(ColorPicker)");
		}
		return getThis();
	}
	
	public ColorPicker sets(String fieldName) {
		if (Events.addListenerField(onColorInt, pui.p, fieldName)) {
			System.out.println("ColorPicker -> " + fieldName);
		}
		return getThis();
	}
	
	public ColorPicker calls(String methodName) {
		return onColor(methodName);
	}
	
	public ColorPicker connect(String name) {
		sets(name);
		calls(name);
		return getThis();
	}
}
