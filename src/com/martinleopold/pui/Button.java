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
	public boolean clicked; // pressed is already used in superclass
	
	public Button(PUI pui, int x, int y, int width, int height) {
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
		
		// draw fill
		// draw fill highlight
		if (pressed) p.fill(theme.fillHighlight);
		else p.fill(theme.fill);
		
		p.rect(x,y, width-1, height-1); // stroked rect is bigger
	}
	
	@Override
	void mousePressed(int button, float mx, float my) {
		clicked = true;
		onClick.fire(this);
	}
	
	@Override
	void mouseReleased(int button, float mx, float my) {
		clicked = false;
	}
	
	Event<Button> onClick = new Event<Button>();
	
	public void onClick(String methodName) {
		Events.addListener(onClick, pui.p, methodName);
	}
}
