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
	Rect widgetRect;
	
//	enum LabelPlacement {
//		LEFTTOP, LEFTCENTER, LEFTBOTTOM, 
//		RIGHTTOP, RIGHTCENTER, RIGHTBOTTOM, 
//		TOPLEFT, TOPCENTER, TOPRIGHT,
//		BOTTOMLEFT, BOTTOMCENTER, BOTTOMRIGHT
//	}
//	LabelPlacement labelPlacement;

	
	public WidgetWithLabel(PUI pui, int x, int y, int width, int height) {
		super(pui, x, y, width, height); // init, should 
		widgetRect = new Rect(x, y, width, height); // save dimensions of the widget 
//		labelPlacement = LabelPlacement.BOTTOMLEFT;
		
		label = new Label(pui, x, y+height, 0, 20, false); // place under widget. don't layout  TODO variable height
		setLabel(""); // sets width
		
		this.height = widgetRect.height + label.height; // joint height
		System.out.println("relayouting");
		pui.layout.reLayout(); // need to relayout cause dimensions changed after first layout (in super())
		
		widgetRect.x = this.x;
		widgetRect.y = this.y;
		label.x = this.x;
		label.y = this.y+height;
		
		System.out.println("x:" + this.x + " y:" + this.y + " width:" + this.width + " height:" + this.height);
	}
	
	public WidgetWithLabel setLabel(String text) {
		label.setText(text);
		label.width = (int)label.textWidth()+1; // adjust label size
		// adjust width
		this.width = widgetRect.width > label.width ? widgetRect.width : label.width;
		return this;
	}
	
//	void setLabelPlacement(LabelPlacement placement) {
//		this.labelPlacement = placement;
//		
//		// place widgetRect
//		// place label
//		// place this
//	}
	
	void drawLabel() {
		label.draw(pui.p);
	}
}
