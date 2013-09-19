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
public class Theme {
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
	
//	void setFrom(Theme t) {
//		background = t.background;
//		outline = t.outline;
//		outlineHighlight = t.outlineHighlight;
//		fill = t.fill;
//		fillHighlight = t.fillHighlight;
//	}
	
	static int color(int r, int g, int b, int a) {
      if (a > 255) a = 255; else if (a < 0) a = 0;
      if (r > 255) r = 255; else if (r < 0) r = 0;
      if (g > 255) g = 255; else if (g < 0) g = 0;
      if (b > 255) b = 255; else if (b < 0) b = 0;
      return (a << 24) | (r << 16) | (g << 8) | b;
	}
	
	static int color(double r, double g, double b, double a) {
		return color((int) Math.round(r), (int) Math.round(g), (int) Math.round(b), (int) Math.round(a));
	}
	
	public final static Theme DEFAULT = new Theme(
		color(0,0,0,25),
		color(255,255,255,100),
		color(255,255,255,200),
		color(255,255,255,200),
		color(255,255,255,255)
	);
	
	public final static Theme HACKER = new Theme(
		color( 0.294118*255.0, 0*255.0, 0.0588235*255.0, 0.196078*255.0 ),
		color( 0.254902*255.0, 0.239216*255.0, 0.239216*255.0, 0.392157*255.0 ),
		color( 0.294118*255.0, 0*255.0, 0.0588235*255.0, 0.784314*255.0 ),
		color( 0.784314*255.0, 1*255.0, 0*255.0, 0.784314*255.0 ),
		color( 0.980392*255.0, 0.00784314*255.0, 0.235294*255.0, 1*255.0 )
	);
	
	public final static Theme HIPSTER = new Theme(
		color( 0.607843*255.0, 0.6*255.0, 0.509804*255.0, 0.196078*255.0 ),
		color( 0.231373*255.0, 0.392157*255.0, 0.501961*255.0, 0.392157*255.0 ),
		color( 0.607843*255.0, 0.6*255.0, 0.509804*255.0, 0.784314*255.0 ),
		color( 1*255.0, 0.52549*255.0, 0.0666667*255.0, 0.784314*255.0 ),
		color( 0.0313725*255.0, 0.101961*255.0, 0.188235*255.0, 1*255.0 )
	);
	
	public final static Theme DIETER = new Theme(
		color( 0.803922*255.0, 0.741176*255.0, 0.682353*255.0, 0.196078*255.0 ),
		color( 0.478431*255.0, 0.356863*255.0, 0.243137*255.0, 0.392157*255.0 ),
		color( 0.803922*255.0, 0.741176*255.0, 0.682353*255.0, 0.784314*255.0 ),
		color( 0.980392*255.0, 0.294118*255.0, 0*255.0, 0.784314*255.0 ), 
		color( 0.980392*255.0, 0.980392*255.0, 0.980392*255.0, 1*255.0 )
	);
	
	public final static Theme BARBIE = new Theme(
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 0.196078*255.0 ),
		color( 0*255.0, 0*255.0, 0*255.0, 0.392157*255.0 ),
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.784314*255.0 ),
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 0.784314*255.0 ),
        color( 1*255.0, 0.94902*255.0, 0*255.0, 1*255.0 )
	);
	
	public final static Theme WINDOWS = new Theme(
		color( 0.0470588*255.0, 0.0588235*255.0, 0.4*255.0, 0.196078*255.0 ),
		color( 0.0431373*255.0, 0.0627451*255.0, 0.54902*255.0, 0.392157*255.0 ),
		color( 0.0470588*255.0, 0.0588235*255.0, 0.4*255.0, 0.784314*255.0 ),
		color( 0.054902*255.0, 0.305882*255.0, 0.678431*255.0, 0.784314*255.0 ),
		color( 0.0627451*255.0, 0.498039*255.0, 0.788235*255.0, 1*255.0 )
	);
	
	public final static Theme OSX = new Theme(
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.196078*255.0 ),
		color( 1*255.0, 0.94902*255.0, 0*255.0, 0.392157*255.0 ),
		color( 0*255.0, 0*255.0, 0*255.0, 0.784314*255.0 ),
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.784314*255.0 ),
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 1*255.0 )
	);
	
	public final static Theme ZOOLANDER = new Theme(
		color( 0.160784*255.0, 0.133333*255.0, 0.121569*255.0, 0.196078*255.0 ),
		color( 0.0745098*255.0, 0.454902*255.0, 0.490196*255.0, 0.392157*255.0 ),
		color( 0.160784*255.0, 0.133333*255.0, 0.121569*255.0, 0.784314*255.0 ),
		color( 0.988235*255.0, 0.207843*255.0, 0.298039*255.0, 0.784314*255.0 ),
		color( 0.988235*255.0, 0.968627*255.0, 0.772549*255.0, 1*255.0 )
	);
	
	public final static Theme VEGAN2 = new Theme(
		color( 0.745098*255.0, 0.94902*255.0, 0.00784314*255.0, 0.196078*255.0 ),
		color( 0.533333*255.0, 0.768627*255.0, 0.145098*255.0, 0.392157*255.0 ),
		color( 0.745098*255.0, 0.94902*255.0, 0.00784314*255.0, 0.784314*255.0 ),
		color( 0.917647*255.0, 0.992157*255.0, 0.901961*255.0, 0.784314*255.0 ),
		color( 0.105882*255.0, 0.403922*255.0, 0.419608*255.0, 1*255.0 )
	);
				
	public final static Theme BERLIN = new Theme(
		color( 0.6*255.0, 0.894118*255.0, 1*255.0, 0.196078*255.0 ),
		color( 0.294118*255.0, 0.34902*255.0, 0.419608*255.0, 0.392157*255.0 ),
		color( 0.6*255.0, 0.894118*255.0, 1*255.0, 0.784314*255.0 ),
		color( 0.968627*255.0, 0.309804*255.0, 0.309804*255.0, 0.784314*255.0 ),
		color( 1*255.0, 0.231373*255.0, 0.231373*255.0, 1*255.0 )
	);
	
	public final static Theme METALGEAR = new Theme(
		color( 51, 44, 44, 75 ),
		color( 25, 26, 36, 100 ),
		color( 51, 44, 44, 200 ),
		color( 250, 101, 87, 200 ),
		color( 255, 255, 255, 255 )
	);

	public final static Theme TEALLIME = new Theme(
		color( 27, 103, 107, 75 ),
		color( 234, 253, 230, 100 ),
		color( 27, 103, 107, 200 ),
		color( 81, 149, 72, 200 ),
		color( 136, 196, 37, 255 )
	);
	
	public final static Theme VEGAN = new Theme(
		color( 81, 149, 72, 75 ),
		color( 27, 103, 107, 100 ),
		color( 81, 149, 72, 200 ),
		color( 136, 196, 37, 200 ),
		color( 190, 242, 2, 255 )
	);
	
	public final static Theme RUSTIC = new Theme(
		color( 196, 182, 109, 75 ),
		color( 247, 109, 60, 100 ),
		color( 196, 182, 109, 200 ),
		color( 213, 39, 5, 200 ),
		color( 240, 211, 119, 255 )
	);
	
	public final static Theme MIDNIGHT = new Theme(
		color( 11, 72, 107, 75 ),
		color( 207, 240, 158, 100 ),
		color( 11, 72, 107, 200 ),
		color( 59, 134, 134, 200 ),
		color( 121, 189, 154, 255 )
	);

	public final static Theme MINBLUE = new Theme(
		color( 254, 249, 240, 75 ),
		color( 176, 248, 255, 100 ),
		color( 254, 249, 240, 200 ),
		color( 0, 188, 209, 200 ),
		color( 118, 211, 222, 255 )
	);
	
	public final static Theme LIMESTONE = new Theme(
		color( 108, 144, 134, 75 ),
		color( 252, 84, 99, 100 ),
		color( 108, 144, 134, 200 ),
		color( 169, 204, 24, 200 ),
		color( 207, 73, 108, 255 )
	);
	
	public final static Theme SPEARMINT = new Theme(
		color( 25, 140, 9, 75 ),
		color( 255, 197, 95, 100 ),
		color( 25, 140, 9, 200 ),
		color( 220, 250, 250, 200 ),
		color( 239, 88, 141, 255 )
	);
	
	public final static Theme MINPINK = new Theme(
		color( 220, 250, 250, 75 ),
		color( 25, 140, 9, 100 ),
		color( 220, 250, 250, 200 ),
		color( 239, 88, 141, 200 ),
		color( 254, 169, 18, 255 )
	);
	
	public final static Theme PEPTOBISMOL = new Theme(
		color( 223, 21, 26, 75 ),
		color( 0, 218, 60, 100 ),
		color( 223, 21, 26, 200 ),
		color( 244, 243, 40, 200 ),
		color( 253, 134, 3, 255 )
	);
	
	public final static Theme BILEBLUE = new Theme(
		color( 253, 134, 3, 75 ),
		color( 244, 243, 40, 100 ),
		color( 253, 134, 3, 200 ),
		color( 0, 203, 231, 200 ),
		color( 0, 218, 60, 255 )
	);
	
	public final static Theme COOLCLAY = new Theme(
		color( 153, 228, 255, 75 ),
		color( 75, 89, 107, 100 ),
		color( 153, 228, 255, 200 ),
		color( 247, 79, 79, 200 ),
		color( 255, 59, 59, 255 )
	);
	
	public final static Theme[] ALL = {DEFAULT, HACKER, HIPSTER, DIETER, BARBIE, WINDOWS, OSX, 
		ZOOLANDER, VEGAN2, BERLIN, METALGEAR, TEALLIME, VEGAN, RUSTIC, MIDNIGHT, MINBLUE, LIMESTONE, 
		SPEARMINT, MINPINK, PEPTOBISMOL, BILEBLUE, COOLCLAY};
}
