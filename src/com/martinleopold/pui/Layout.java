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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
final class Layout {
	private int originalWidth, originalHeight;
	private int width, height;
	private int paddingX, paddingY;
	private int columnWidth;
	
	private int nextX, nextY; // where to place the next widget *and* it's padding
	
	private int currentColumnX; // columnX . padding . widget
	private int currentColumnWidth; // width to place widgets and padding in
	private int currentRowHeight; // height of row including widgets and their padding
	
	private int windowPaddingX, windowPaddingY; 
	
	private enum Action { NewRow, NewColumn, AddWidget };
	private List<Action> actions; // sequence of layout actions
	private List<Widget<?>> elements; // list of added widgets
	
	Layout(int width, int height, int paddingX, int paddingY, int columnWidth) {
		this.originalWidth = this.width = width;
		this.originalHeight = this.height = height;
		this.columnWidth = columnWidth;
		
		reset();
		
		setPadding(paddingX, paddingY);
		System.out.println("width:" + width + " height:" + height + " paddingX:" + paddingX + " paddingY:" + paddingY);
	}
	
	void add(Widget<?> w) {
		System.out.println("adding " + elements.size());
		Rect r = w.layoutRect;
		
		int totalWidth = r.width + 2*paddingX; // total widget width (including padding)
		int totalHeight = r.height + 2*paddingY; // total widget height (including padding)
		
		// ceck if we flow out at the bottom
		System.out.println("nextY: " + nextY + " totalHeight:" + totalHeight + " height: " + height);
		if (nextY + totalHeight > height) {
			System.out.println("try new column");
			nextColumn();
		}
		
		// check if it fits in the current line
		if (nextX + totalWidth <= currentColumnX + currentColumnWidth) {
			// place it
			placeNext(w);
//			System.out.println("position: x=" + w.x + " y=" + w.y);
			
			placeAgainstPinned(w); // adjust position to avoid pinned elements
			
			elements.add(w); // add to list of layouted elements
			actions.add(Action.AddWidget);
			System.out.println(elements.indexOf(w) + ": placing in layout x:" + r.x + " y:" + r.y + " tw:" + totalWidth + " th:" + totalHeight);
			// track row height
			if (totalHeight > currentRowHeight) {
				currentRowHeight = totalHeight;
				System.out.println("currentRowHeight: " + currentRowHeight );
			}
			
			// warn if we flow out to the right
			if (nextX + totalWidth > width) {
				System.out.println("Warning: Widget is placed outside of the window.");
			}
			
			// widget was placed
			nextX += totalWidth;
		} else if (nextX == currentColumnX) { // check if we are at the beginning of a line
			System.out.println("make column wider");
			// we are at the beginning of a line and it doesn't fit
			currentColumnWidth = totalWidth; // make this column wider
			add(w); // try again
		} else { // it doesn't fit in the current line
			System.out.println("try new row");
			nextRow();
			add(w);
		}
	}
	
	private void nextRow() {
		nextX = currentColumnX;
		nextY += currentRowHeight;
		currentRowHeight = 0;
		System.out.println("new row x:" + nextX + " y:" + nextY);
	}
	
	private void nextColumn() {
		currentRowHeight = 0;
		currentColumnX += currentColumnWidth;
		currentColumnWidth = columnWidth;
		nextX = currentColumnX;
		nextY = 0;
	}
	
	void newRow() {
		nextRow();
		actions.add(Action.NewRow);
	}
	
	void newColumn() {
		nextColumn();
		actions.add(Action.NewColumn);
	}
	
	// TODO this is recorded as layout action
	void setColumnWidth(int w) {
		columnWidth = w;
		currentColumnWidth = w;
	}
	
	private void remove(Widget<?> e) {
		int idx = elements.indexOf(e); // index of the add action
		if (idx > -1) {
			elements.remove(e);
			int count = 0; // count number of add action
			for (Action a : actions) {
				if (a == Action.AddWidget) {
					if (count++ == idx) {
						actions.remove(a);
						return;
					}
				}
			}
		}
	}
	
	private void reset() {
		nextX = 0;
		nextY = 0;
		currentColumnX = 0; 
		currentColumnWidth = columnWidth; 
		currentRowHeight = 0;
		
		elements = new ArrayList<Widget<?>>();
		actions = new ArrayList<Action>();
		pinned = new ArrayList<Widget<?>>();
	}
	
	void reLayout() {
		System.out.println("reLayout");
		List<Action> oldActions = actions; // save actions
		List<Widget<?>> oldElements = elements; // save elements
		List<Widget<?>> oldPinned = pinned; // save pinned elements
		
		reset(); // reset layout
		pinned = oldPinned; // restore pinned
		
		Iterator<Widget<?>> widgets = oldElements.iterator();
		for (Action a : oldActions) {
			switch (a) {
				case NewRow:
					newRow();
					break;
				case NewColumn:
					newColumn();
					break;
				case AddWidget:
					add(widgets.next());
					break;
			}
		}
	}
	
	private List<Widget<?>> pinned; // list of added widgets
	void pin(Widget<?> w) {
		remove(w); // remove from normal flow (if present)
		if (!pinned.contains(w)) pinned.add(w);
		System.out.println("relayout");
		reLayout();
	}
	
	// assumes w and p are colliding. place w against pinned p
	private void placeAgainstPinned(Widget<?> w, Widget<?> p) {
		Rect r = w.layoutRect;
		int totalWidth = r.width + 2*paddingX; // total widget width (including padding)
		int totalHeight = r.height + 2*paddingY; // total widget height (including padding)
		
		// try to fit w next to (i.e. right of) p in this column
		if (p.layoutRect.x + p.layoutRect.width + paddingX + totalWidth <= currentColumnX + currentColumnWidth) {
			System.out.println("fit next to pin");
			w.setPosition(p.layoutRect.x + p.layoutRect.width + paddingX*2, w.y);
			nextX = w.x - paddingX + totalWidth;
		}
		// try to fit in nextRow (before) p
		else if (currentColumnX + totalWidth < p.layoutRect.x && nextY + totalHeight < height) {
			nextRow();
			placeNext(w);
			nextX+=totalWidth;
		}
		// try to fit w after (i.e. below) p in this column
		else if (p.layoutRect.y + p.layoutRect.height + paddingY + totalHeight <= height) {
			System.out.println("fit below");
			w.setPosition(w.x, p.layoutRect.y + p.layoutRect.height + paddingY*2);
			nextX+=totalWidth;
		}
		// try next column
		else {
			System.out.println("fit in next column");
			newColumn();
			placeAgainstPinned(w, p); // retry
		}
	}
	// place w against all pinned. moves next to pinned, down, or continues in next column until a suitable spot is found
	private void placeAgainstPinned(Widget<?> w) {
		// check against all pinned 
		for (Widget<?> p : pinned) {
			System.out.println("check against pin");
			if (w.isOverapping(p)) { // the widget collides with a previously pinned one (p)
				System.out.println("found overlap");
				placeAgainstPinned(w, p);
			}
		}
	}
	
	// place a widget at next position (adding global and widget padding)
	private void placeNext(Widget<?> w) {
		w.setPosition(windowPaddingX + nextX + paddingX, windowPaddingY + nextY + paddingY);
	}
	
	void setPadding(int px, int py) {
		this.paddingX = px;
		this.paddingY = py;
		// adjust window padding
		if (width - 2*paddingX > 0 && height - 2*paddingY > 0) {
			windowPaddingX = paddingX;
			windowPaddingY = paddingY;
			width = originalWidth - 2*paddingX;
			height = originalHeight - 2*paddingY;
		}
	}
}
