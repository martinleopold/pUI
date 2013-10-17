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
public class Label extends Widget<Label> {
	//state
	String text = "";
	
	// some (internal) switches
	boolean drawBackground; // defaults to false
	boolean drawHighlight; // defaults to false

	public Label(PUI pui, int width, int height) {
		super(pui, width, height);
	}
	
	Label(PUI pui, int width, int height, boolean doLayout) {
		super(pui, width, height, doLayout);
	}
	
	@Override
	void draw(PApplet p) {
		// draw background
		if (drawBackground) {
			p.noStroke();
			p.fill(theme.background);
			p.rect(x, y, width, height);
		}
		
		// draw outline
		// draw outline highlight
//		if (hover) p.stroke(theme.outlineHighlight);
//		else p.stroke(theme.outline);
//		p.noFill();
//		p.rect(x, y, width-1, height-1);
		
		// draw fill
		// draw fill highlight
//		if (hover) p.fill(theme.fillHighlight);
//		else p.fill(theme.fill);
		if (drawHighlight) p .fill(theme.fillHighlight);
		else p.fill(theme.fill);
		p.textFont(font, height);
		p.textAlign(PApplet.LEFT, PApplet.TOP);
		p.text(text, x, y);
	}
	
	// get width of the current text
	float textWidth() {
		return textWidth(height);
	}
	
	float textWidth(float atHeight) {
		PApplet p = pui.p;
		p.pushStyle();
		p.textFont(font, atHeight);
		float w = p.textWidth(text);
		p.popStyle();
		return w;
	}
	
	Label setFontSize(int sizePx) {
		setSize((int)(textWidth(sizePx)+1), sizePx);
		pui.layout.reLayout();
		return this;
	}
	
	@Override
	protected Label getThis() {
		return this;
	}
	
	/*
	 * State
	 */
	public Label text(String text) {
		this.text = text;
		setSize((int)(textWidth()+1), height);
		pui.layout.reLayout();
		return this;
	}
	
	public String text() {
		return text;
	}
	
	public Label small() {
		return setFontSize(pui.gridY2Px(PUI.DEFAULT_FONTSIZE_SMALL));
	}
	
	public Label medium() {
		return setFontSize(pui.gridY2Px(PUI.DEFAULT_FONTSIZE_MEDIUM));
	}
	
	public Label large() {
		return setFontSize(pui.gridY2Px(PUI.DEFAULT_FONTSIZE_LARGE));
	}
}
