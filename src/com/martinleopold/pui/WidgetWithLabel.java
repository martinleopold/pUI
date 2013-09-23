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
 * @param <TWidget> Base Widget Type. Used so methods can return the actual Widget Type
 */
abstract class WidgetWithLabel<TWidget> extends Widget {
	Label label;
	boolean drawLabel;
	
//	enum LabelPlacement {
//		LEFTTOP, LEFTCENTER, LEFTBOTTOM, 
//		RIGHTTOP, RIGHTCENTER, RIGHTBOTTOM, 
//		TOPLEFT, TOPCENTER, TOPRIGHT,
//		BOTTOMLEFT, BOTTOMCENTER, BOTTOMRIGHT
//	}
//	LabelPlacement labelPlacement;

	
	WidgetWithLabel(PUI pui, int x, int y, int width, int height) {
		super(pui, x, y, width, height); // init widget, causes layout (without label)
		
//		labelPlacement = LabelPlacement.BOTTOMLEFT;
		
		//place label
		label = new Label(pui, this.x, this.y+height, 0, pui.gridY2Px(PUI.DEFAULT_FONTSIZE_SMALL), false); // place under widget. don't layout.  TODO variable height
		label.active = false; // no redraw
		
//		System.out.println("x:" + this.x + " y:" + this.y + " width:" + this.width + " height:" + this.height);
	}
	
	public TWidget setLabel(String text) {
		label.setText(text);
		label.width = (int)label.textWidth()+1; // adjust label size
		
		// adjust width
		layoutRect.width = width > label.width ? width : label.width;
		layoutRect.height = height + label.height; // joint height
		
		//		System.out.println("relayouting");
		pui.layout.reLayout(); // need to relayout cause dimensions changed after first layout (in super())
		label.setPosition(this.x, this.y+height);		// label needs to be replaced
		label.active = true; // redraw
		return (TWidget)this;
	}
	
	public TWidget noLabel() {
		layoutRect = new Rect(this);
		label.active = false; // no redraw
		return (TWidget)this;
	}
	
//	void setLabelPlacement(LabelPlacement placement) {
//		this.labelPlacement = placement;
//		
//		// place widgetRect
//		// place label
//		// place this
//	}
	
//	void drawLabel() {
//		if (drawLabel) {
//			label.draw(pui.p);
//		}
//	}
	
	@Override
	Widget setSize(int w, int h) {
		this.width = w;
		this.height = h;
		layoutRect.width = w;
		layoutRect.height = h;
		return this;
	}
}
