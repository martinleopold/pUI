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

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
class Layout {
	int width, height;
	int paddingX, paddingY;
	int columnWidth;
	
	int nextX, nextY; // where to place the next widget *and* it's padding
	
	int currentColumnX; // columnX . padding . widget
	int currentColumnWidth; // width to place widgets and padding in
	int currentRowHeight; // height of row including widgets and padding
	
	int windowPaddingX, windowPaddingY; 
	
	Layout(int width, int height, int paddingX, int paddingY, int columnWidth) {
		this.width = width;
		this.height = height;
		this.paddingX = paddingX;
		this.paddingY = paddingY;
		this.columnWidth = columnWidth;
		
		nextX = 0;
		nextY = 0;
		currentColumnX = 0; 
		currentColumnWidth = columnWidth; 
		currentRowHeight = 0;
		
		// window padding
		if (width - 2*paddingX > 0 && height - 2*paddingY > 0) {
			windowPaddingX = paddingX;
			windowPaddingY = paddingY;
			width -= 2*paddingX;
			height -= 2*paddingY;
		}
	}
	
	void add(Rect e) {
		
		int totalWidth = e.width + 2*paddingX; // total widget width (including padding)
		int toalHeight = e.height + 2*paddingY; // total widget height (including padding)
		
		// ceck if we flow out at the bottom
		if (nextY + toalHeight > height) {
//			System.out.println("try new column");
			newColumn();
		}
		
		// check if it fits in the current line
		if (nextX + totalWidth <= currentColumnX + currentColumnWidth) {
			// place it
			e.x = windowPaddingX + nextX + paddingX;
			e.y = windowPaddingY + nextY + paddingY;
			// track row height
			if (toalHeight > currentRowHeight) currentRowHeight = toalHeight;
			// widget was placed
			nextX += totalWidth;
			// warn if we flow out to the right
			if (e.x + totalWidth > width) {
				System.out.println("Warning: Widget is placed outside of the window.");
			}
		} else if (nextX == currentColumnX) { // check if we are at the beginning of a line
//			System.out.println("make column wider");
			// we are at the beginning of a line and it doesn't fit
			currentColumnWidth = totalWidth; // make this column wider
			add(e); // try again
		} else { // it doesn't fit in the current line
//			System.out.println("try new row");
			newRow();
			add(e);
		}
	}
	
	void newRow() {
		nextX = currentColumnX;
		nextY += currentRowHeight;
		currentRowHeight = 0;
	}
	
	void newColumn() {
		currentRowHeight = 0;
		currentColumnX += currentColumnWidth;
		currentColumnWidth = columnWidth;
		nextX = currentColumnX;
		nextY = 0;
	}
	
	void setColumnWidth(int w) {
		columnWidth = w;
		currentColumnWidth = w;
	}
}
