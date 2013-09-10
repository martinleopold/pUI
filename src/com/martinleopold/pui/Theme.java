/*
 * Copyright (C) Error: on line 4, column 33 in Templates/Licenses/license-gpl20.txt
 Expecting a date here, found: 10.09.2013 martinleopold
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
class Theme {
	int background;
	int outline;
	int outlineHighlight;
	int fill;
	int fillHighlight;
	
	Theme(int background, int outline, int outlineHighlight, int fill, int fillHighlight) {
		this.background = background;
		this.outline = outline;
		this.outlineHighlight = outlineHighlight;
		this.fill = fill;
		this.fillHighlight = fillHighlight;
	}
	
	static int color(int r, int g, int b, int a) {
      if (a > 255) a = 255; else if (a < 0) a = 0;
      if (r > 255) r = 255; else if (r < 0) r = 0;
      if (g > 255) g = 255; else if (g < 0) g = 0;
      if (b > 255) b = 255; else if (b < 0) b = 0;
      return (a << 24) | (r << 16) | (g << 8) | b;
	}
	
	final static Theme DEFAULT = new Theme(
			color(0,0,0,25),
			color(255,255,255,100),
			color(255,255,255,200),
			color(255,255,255,200),
			color(255,255,255,255)
	);
}
