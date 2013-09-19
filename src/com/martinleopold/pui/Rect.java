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
 * Guido: Basic2DElement
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
class Rect {

	protected int x, y;
	protected int width, height;

	Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	boolean isInside(float tx, float ty) {
		return (tx >= x && tx <= x + width && ty >= y && ty <= y + height);
	}
	
	Rect setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	Rect setSize(int w, int h) {
		this.width = w;
		this.height = h;
		return this;
	}
}
