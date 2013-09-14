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

/**
 *
 * @author martinleopold
 */
public class WidgetWithLabel extends Widget {
	Label label;
	
	enum LabelPlacement {
		LEFTTOP, LEFTCENTER, LEFTBOTTOM, 
		RIGHTTOP, RIGHTCENTER, RIGHTBOTTOM, 
		TOPLEFT, TOPCENTER, TOPRIGHT,
		BOTTOMLEFT, BOTTOMCENTER, BOTTOMRIGHT
	}
	LabelPlacement labelPlacement;
	
	public WidgetWithLabel(PUI pui, int x, int y, int width, int height) {
		super(pui, x, y, width, height);
		labelPlacement = LabelPlacement.BOTTOMLEFT;
	}
	
	public WidgetWithLabel setLabel(String text) {
		// TODO adjust label size
		return this;
	}
	
	void setLabelPlacement(LabelPlacement placement) {
		this.labelPlacement = placement;
	}
	
	void drawLabel() {
		
	}
}
