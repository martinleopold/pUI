package com.martinleopold.pui;

import processing.core.PApplet;

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

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Divider extends Widget<Divider> {

	public Divider(PUI pui, int width, int height) {
		super(pui, width, height);
	}
	
	@Override
	void draw(PApplet p) {
		// draw background
//		p.noStroke();
//		p.fill(theme.background);
//		p.rect(x, y, width, height);
		
		// draw outline
		// draw outline highlight
		p.stroke(theme.fill);
		p.strokeWeight(1.4f);
		if (width >= height) p.line(x, y+height/2, x+width-1, y+height/2);
		else p.line(x+width/2, y, x+width/2, y+height-1);
	}

	@Override
	protected Divider getThis() {
		return this;
	}
}
