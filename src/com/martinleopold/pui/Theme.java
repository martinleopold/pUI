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

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martinleopold
 */
public class Theme {
	// TODO should these be public?
	public int background;
	public int outline;
	public int outlineHighlight;
	public int fill;
	public int fillHighlight;
	public int overlay;
	
	Theme(int background, int outline, int outlineHighlight, int fill, int fillHighlight, int overlay) {
		this.background = background;
		//this.background = setAlpha(background, 160); // override alpha TODO: fix
		this.outline = outline;
		this.outlineHighlight = outlineHighlight;
		this.fill = fill;
		this.fillHighlight = fillHighlight;
		this.overlay = overlay;
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
	
	static int setAlpha(int c, int a) {
		if (a > 255) a = 255; else if (a < 0) a = 0;
		return (a << 24)  | (c & 0xffffff);
	}
	
	public final static Theme DEFAULT = new Theme(
		color( 0, 0, 0, 160),
		color( 255, 255, 255, 100),
		color( 255, 255, 255, 200),
		color( 255, 255, 255, 200),
		color( 255, 255, 255, 255),
		color( 0, 0, 0, 120 )
	);
	
	public final static Theme HACKER = new Theme(
		color( 0.294118*255.0, 0*255.0, 0.0588235*255.0, 0.196078*255.0 ),
		color( 0.254902*255.0, 0.239216*255.0, 0.239216*255.0, 0.392157*255.0 ),
		color( 0.294118*255.0, 0*255.0, 0.0588235*255.0, 0.784314*255.0 ),
		color( 0.784314*255.0, 1*255.0, 0*255.0, 0.784314*255.0 ),
		color( 0.980392*255.0, 0.00784314*255.0, 0.235294*255.0, 1*255.0 ),
		color( 92, 54, 54, 160 )
	);
	
	public final static Theme HIPSTER = new Theme(
		color( 0.607843*255.0, 0.6*255.0, 0.509804*255.0, 0.196078*255.0 ),
		color( 0.231373*255.0, 0.392157*255.0, 0.501961*255.0, 0.392157*255.0 ),
		color( 0.607843*255.0, 0.6*255.0, 0.509804*255.0, 0.784314*255.0 ),
		color( 1*255.0, 0.52549*255.0, 0.0666667*255.0, 0.784314*255.0 ),
		color( 0.0313725*255.0, 0.101961*255.0, 0.188235*255.0, 1*255.0 ),
		color( 62, 60, 45, 164 )
	);
	
	public final static Theme DIETER = new Theme(
		color( 0.803922*255.0, 0.741176*255.0, 0.682353*255.0, 0.196078*255.0 ),
		color( 0.478431*255.0, 0.356863*255.0, 0.243137*255.0, 0.392157*255.0 ),
		color( 0.803922*255.0, 0.741176*255.0, 0.682353*255.0, 0.784314*255.0 ),
		color( 0.980392*255.0, 0.294118*255.0, 0*255.0, 0.784314*255.0 ), 
		color( 0.980392*255.0, 0.980392*255.0, 0.980392*255.0, 1*255.0 ),
		color( 255, 242, 232, 200 )
	);
	
	public final static Theme BARBIE = new Theme(
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 0.196078*255.0 ),
		color( 0*255.0, 0*255.0, 0*255.0, 0.392157*255.0 ),
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.784314*255.0 ),
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 0.784314*255.0 ),
        color( 1*255.0, 0.94902*255.0, 0*255.0, 1*255.0 ),
		color( 70, 40, 48, 160 )
	);
	
	public final static Theme WINDOWS = new Theme(
		color( 0.0470588*255.0, 0.0588235*255.0, 0.4*255.0, 0.196078*255.0 ),
		color( 0.0431373*255.0, 0.0627451*255.0, 0.54902*255.0, 0.392157*255.0 ),
		color( 0.0470588*255.0, 0.0588235*255.0, 0.4*255.0, 0.784314*255.0 ),
		color( 0.054902*255.0, 0.305882*255.0, 0.678431*255.0, 0.784314*255.0 ),
		color( 0.0627451*255.0, 0.498039*255.0, 0.788235*255.0, 1*255.0 ),
		color( 0, 0, 51, 227 )
	);
	
	public final static Theme OSX = new Theme(
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.196078*255.0 ),
		color( 1*255.0, 0.94902*255.0, 0*255.0, 0.392157*255.0 ),
		color( 0*255.0, 0*255.0, 0*255.0, 0.784314*255.0 ),
		color( 0*255.0, 0.678431*255.0, 0.937255*255.0, 0.784314*255.0 ),
		color( 0.92549*255.0, 0*255.0, 0.54902*255.0, 1*255.0 ),
		color( 0, 0, 45, 221 )
	);
	
	public final static Theme ZOOLANDER = new Theme(
		color( 0.160784*255.0, 0.133333*255.0, 0.121569*255.0, 0.196078*255.0 ),
		color( 0.0745098*255.0, 0.454902*255.0, 0.490196*255.0, 0.392157*255.0 ),
		color( 0.160784*255.0, 0.133333*255.0, 0.121569*255.0, 0.784314*255.0 ),
		color( 0.988235*255.0, 0.207843*255.0, 0.298039*255.0, 0.784314*255.0 ),
		color( 0.988235*255.0, 0.968627*255.0, 0.772549*255.0, 1*255.0 ),
		color( 27, 22, 20, 213 )
	);
	
	public final static Theme VEGAN2 = new Theme(
		color( 0.745098*255.0, 0.94902*255.0, 0.00784314*255.0, 0.196078*255.0 ),
		color( 0.533333*255.0, 0.768627*255.0, 0.145098*255.0, 0.392157*255.0 ),
		color( 0.745098*255.0, 0.94902*255.0, 0.00784314*255.0, 0.784314*255.0 ),
		color( 0.917647*255.0, 0.992157*255.0, 0.901961*255.0, 0.784314*255.0 ),
		color( 0.105882*255.0, 0.403922*255.0, 0.419608*255.0, 1*255.0 ),
		color( 70, 48, 0, 235 )
	);
				
	public final static Theme BERLIN = new Theme(
		color( 0.6*255.0, 0.894118*255.0, 1*255.0, 0.196078*255.0 ),
		color( 0.294118*255.0, 0.34902*255.0, 0.419608*255.0, 0.392157*255.0 ),
		color( 0.6*255.0, 0.894118*255.0, 1*255.0, 0.784314*255.0 ),
		color( 0.968627*255.0, 0.309804*255.0, 0.309804*255.0, 0.784314*255.0 ),
		color( 1*255.0, 0.231373*255.0, 0.231373*255.0, 1*255.0 ),
		color( 232, 249, 255, 223 )
	);
	
	public final static Theme METALGEAR = new Theme(
		color( 51, 44, 44, 75 ),
		color( 25, 26, 36, 100 ),
		color( 51, 44, 44, 200 ),
		color( 250, 101, 87, 200 ),
		color( 255, 255, 255, 255 ),
		color( 86, 74, 74, 239 )
	);

	public final static Theme TEALLIME = new Theme(
		color( 27, 103, 107, 75 ),
		color( 234, 253, 230, 100 ),
		color( 27, 103, 107, 200 ),
		color( 81, 149, 72, 200 ),
		color( 136, 196, 37, 255 ),
		color( 0, 62, 61, 237 )
	);
	
	public final static Theme VEGAN = new Theme(
		color( 81, 149, 72, 75 ),
		color( 27, 103, 107, 100 ),
		color( 81, 149, 72, 200 ),
		color( 136, 196, 37, 200 ),
		color( 190, 242, 2, 255 ),
		color( 45, 64, 43, 202 )
	);
	
	public final static Theme RUSTIC = new Theme(
		color( 196, 182, 109, 75 ),
		color( 247, 109, 60, 100 ),
		color( 196, 182, 109, 200 ),
		color( 213, 39, 5, 200 ),
		color( 240, 211, 119, 255 ),
		color( 70, 42, 42, 227 )
	);
	
	public final static Theme MIDNIGHT = new Theme(
		color( 11, 72, 107, 75 ),
		color( 207, 240, 158, 100 ),
		color( 11, 72, 107, 200 ),
		color( 59, 134, 134, 200 ),
		color( 121, 189, 154, 255 ),
		color( 0, 40, 56, 239 )
	);

	public final static Theme MINBLUE = new Theme(
		color( 254, 249, 240, 75 ),
		color( 176, 248, 255, 100 ),
		color( 254, 249, 240, 200 ),
		color( 0, 188, 209, 200 ),
		color( 118, 211, 222, 255 ),
		color( 219, 217, 214, 245 )
	);
	
	public final static Theme LIMESTONE = new Theme(
		color( 108, 144, 134, 75 ),
		color( 252, 84, 99, 100 ),
		color( 108, 144, 134, 200 ),
		color( 169, 204, 24, 200 ),
		color( 207, 73, 108, 255 ),
		color( 66, 92, 84, 241 )
	);
	
	public final static Theme SPEARMINT = new Theme(
		color( 25, 140, 9, 75 ),
		color( 255, 197, 95, 100 ),
		color( 25, 140, 9, 200 ),
		color( 220, 250, 250, 200 ),
		color( 239, 88, 141, 255 ),
		color( 68, 140, 57, 231 )
	);
	
	public final static Theme MINPINK = new Theme(
		color( 220, 250, 250, 75 ),
		color( 25, 140, 9, 100 ),
		color( 220, 250, 250, 200 ),
		color( 239, 88, 141, 200 ),
		color( 254, 169, 18, 255 ),
		color( 229, 250, 249, 231 )
	);
	
	public final static Theme PEPTOBISMOL = new Theme(
		color( 223, 21, 26, 75 ),
		color( 0, 218, 60, 100 ),
		color( 223, 21, 26, 200 ),
		color( 244, 243, 40, 200 ),
		color( 253, 134, 3, 255 ),
		color( 66, 6, 9, 217 )
	);
	
	public final static Theme BILEBLUE = new Theme(
		color( 253, 134, 3, 75 ),
		color( 244, 243, 40, 100 ),
		color( 253, 134, 3, 200 ),
		color( 0, 203, 231, 200 ),
		color( 0, 218, 60, 255 ),
		color( 143, 75, 2, 237 )
	);
	
	public final static Theme COOLCLAY = new Theme(
		color( 153, 228, 255, 75 ),
		color( 75, 89, 107, 100 ),
		color( 153, 228, 255, 200 ),
		color( 247, 79, 79, 200 ),
		color( 255, 59, 59, 255 ),
		color( 0, 27, 37, 219 )
	);
	
	/////
	public final static Theme BLUEBLUE = new Theme(
		color( 0, 173, 239, 75 ),
		color( 255, 242, 0, 100 ),
		color( 0, 0, 0, 200 ),
		color( 0, 173, 239, 200 ),
		color( 236, 0, 140, 255 ),
		color( 238, 250, 255, 241 )
	);
	
	public final static Theme PINKPANTHER = new Theme(
		color( 236, 0, 140, 75 ),
		color( 0, 0, 0, 100 ),
		color( 0, 173, 239, 200 ),
		color( 236, 0, 140, 200 ),
		color( 255, 242, 0, 255 ),
		color( 49, 0, 29, 213 )
	);
	
	public final static Theme MAROON = new Theme(
		color( 101, 150, 158, 75 ),
		color( 219, 217, 210, 100 ),
		color( 101, 150, 158, 200 ),
		color( 171, 20, 44, 200 ),
		color( 189, 219, 222, 255 ),
		color( 171, 175, 176, 237 )
	);
	
	public final static Theme PINKLATTE = new Theme(
		color( 218, 216, 167, 75 ),
		color( 127, 199, 175, 100 ),
		color( 218, 216, 167, 200 ),
		color( 255, 61, 127, 200 ),
		color( 255, 158, 157, 255 ),
		color( 255, 253, 222, 241 )
	);
	
	public final static Theme MINGREEN = new Theme(
		color( 255, 255, 255, 75 ),
		color( 242, 230, 194, 100 ),
		color( 255, 255, 255, 200 ),
		color( 111, 191, 162, 200 ),
		color( 191, 184, 174, 255 ),
		color( 90, 90, 90, 243 )
	);
	
	public final static Theme HELLOYELLOW = new Theme(
		color( 255, 211, 0, 75 ),
		color( 74, 186, 176, 100 ),
		color( 152, 33, 0, 200 ),
		color( 255, 211, 0, 200 ),
		color( 255, 245, 158, 255 ),
		color( 92, 77, 10, 233 )
	);
	
	public final static Theme TEALTEAL = new Theme(
		color( 74, 186, 176, 75 ),
		color( 255, 211, 0, 100 ),
		color( 255, 245, 158, 200 ),
		color( 74, 186, 176, 200 ),
		color( 152, 33, 0, 255 ),
		color( 15, 21, 20, 221 )
	);
	
	public final static Theme RUSTICORANGE = new Theme(
		color( 107, 85, 48, 75 ),
		color( 49, 48, 66, 100 ),
		color( 107, 85, 48, 200 ),
		color( 255, 109, 36, 200 ),
		color( 255, 235, 107, 255 ),
		color( 49, 39, 22, 237 )
	);

	public final static Theme TEALSALMON = new Theme(
		color( 78, 133, 136, 75 ),
		color( 56, 69, 59, 100 ),
		color( 78, 133, 136, 200 ),
		color( 255, 70, 84, 200 ),
		color( 255, 213, 106, 255 ),
		color( 21, 46, 47, 245 )
	);
	
	public final static Theme CITRUSBLUE = new Theme(
		color( 57, 142, 182, 75 ),
		color( 34, 104, 136, 100 ),
		color( 57, 142, 182, 200 ),
		color( 255, 162, 0, 200 ),
		color( 255, 214, 0, 255 ),
		color( 37, 80, 100, 221 )
	);
	
	public final static Theme LIMEPURPLE = new Theme(
		color( 87, 54, 255, 75 ),
		color( 38, 38, 38, 100 ),
		color( 87, 54, 255, 200 ),
		color( 231, 255, 54, 200 ),
		color( 255, 54, 111, 255 ),
		color( 58, 38, 182, 192 )
	);
	
	public final static Theme LIMESTONE2 = new Theme(
		color( 101, 98, 115, 75 ),
		color( 89, 186, 169, 100 ),
		color( 101, 98, 115, 200 ),
		color( 216, 241, 113, 200 ),
		color( 252, 255, 217, 255 ),
		color( 59, 57, 68, 221 )
	);
	
	public final static Theme COOLPURPLE = new Theme(
		color( 38, 137, 233, 75 ),
		color( 11, 246, 147, 100 ),
		color( 38, 137, 233, 200 ),
		color( 233, 26, 157, 200 ),
		color( 246, 182, 11, 255 ),
		color( 0, 41, 80, 239 )
	);
	
	public final static Theme GRAYRED = new Theme(
		color( 41, 34, 31, 75 ),
		color( 19, 116, 125, 100 ),
		color( 41, 34, 31, 200 ),
		color( 252, 53, 76, 200 ),
		color( 252, 247, 197, 255 ),
		color( 64, 52, 48, 227 )
	);
	
	public final static Theme METALGEAR2 = new Theme(
		color( 205, 189, 174, 75 ),
		color( 122, 91, 62, 100 ),
		color( 205, 189, 174, 200 ),
		color( 250, 75, 0, 200 ),
		color( 250, 250, 250, 255 ),
		color( 231, 212, 196, 239 )
	);
	
	public final static Theme LIGHTPINK = new Theme(
		color( 158, 30, 76, 75 ),
		color( 143, 143, 143, 100 ),
		color( 158, 30, 76, 200 ),
		color( 236, 236, 236, 200 ),
		color( 255, 17, 104, 255 ),
		color( 82, 15, 40, 207 )
	);
	
	public final static Theme MINPINK2 = new Theme(
		color( 236, 236, 236, 75 ),
		color( 158, 30, 76, 100 ),
		color( 236, 236, 236, 200 ),
		color( 255, 17, 104, 200 ),
		color( 37, 2, 15, 255 ),
		color( 236, 236, 236, 225 )
	);
	
	public final static Theme MAXPINK = new Theme(
		color( 255, 20, 87, 75 ),
		color( 10, 10, 10, 100 ),
		color( 227, 246, 255, 200 ),
		color( 255, 20, 87, 200 ),
		color( 255, 216, 125, 255 ),
		color( 70, 5, 25, 227 )
	);
	
	public final static Theme MINYELLOW = new Theme(
		color( 229, 228, 218, 75 ),
		color( 216, 210, 153, 100 ),
		color( 229, 228, 218, 200 ),
		color( 245, 224, 56, 200 ),
		color( 23, 22, 92, 255 ),
		color( 133, 132, 126, 231 )
	);
	
	public final static Theme MINLIME = new Theme(
		color( 245, 225, 226, 75 ),
		color( 225, 183, 237, 100 ),
		color( 245, 225, 226, 200 ),
		color( 185, 222, 81, 200 ),
		color( 209, 227, 137, 255 ),
		color( 98, 90, 91, 239 )
	);
	
	public final static Theme MINORANGE = new Theme(
		color( 204, 204, 204, 75 ),
		color( 111, 111, 111, 100 ),
		color( 204, 204, 204, 200 ),
		color( 255, 100, 0, 200 ),
		color( 255, 255, 255, 255 ),
		color( 74, 73, 73, 237 )
	);
	
	public final static Theme GRAYDAY = new Theme(
		color( 177, 198, 204, 75 ),
		color( 255, 255, 255, 100 ),
		color( 20, 20, 20, 200 ),
		color( 177, 198, 204, 200 ),
		color( 255, 239, 94, 255 ),
		color( 57, 64, 66, 219 )
	);
	
	public final static Theme MINBLACK = new Theme(
		color( 255, 255, 255, 75 ),
		color( 209, 231, 81, 100 ),
		color( 255, 255, 255, 200 ),
		color( 0, 0, 0, 200 ),
		color( 38, 173, 228, 255 ),
		color( 213, 213, 213, 237 )
	);
	
	static enum Preset {
		DEFAULT, HACKER, HIPSTER, DIETER, BARBIE, WINDOWS, OSX, 
		ZOOLANDER, VEGAN2, BERLIN, METALGEAR, TEALLIME, VEGAN, RUSTIC, MIDNIGHT, MINBLUE, LIMESTONE, 
		SPEARMINT, MINPINK, PEPTOBISMOL, BILEBLUE, COOLCLAY, BLUEBLUE, PINKPANTHER, MAROON, 
		PINKLATTE, MINGREEN, HELLOYELLOW, TEALTEAL, RUSTICORANGE, TEALSALMON, CITRUSBLUE,
		LIMEPURPLE, LIMESTONE2, COOLPURPLE, GRAYRED, METALGEAR2, LIGHTPINK, MINPINK2, MAXPINK,
		MINYELLOW, MINLIME, MINORANGE, GRAYDAY, MINBLACK
	}
	
	// get preset by enum
	static Theme preset(Preset preset) {
		try {
			// get preset by reflection
			Field f = Theme.class.getField(preset.name()); // get field with same name as preset
			return (Theme)f.get(null);
		} catch (NoSuchFieldException ex) {
			Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
		}
		return DEFAULT;
	}
	
	// get preset by int
	public static Theme preset(int presetNo) {
		if (presetNo < 0 || presetNo >= Preset.values().length) {
			return preset(Preset.DEFAULT);
		}
		return preset( Preset.values()[presetNo] );
	}
	
	// get preset by name (case insensitive)
	public static Theme preset(String presetName) {
		try {
			return preset( Preset.valueOf(presetName.toUpperCase()) );
		} catch (IllegalArgumentException e) { // preset not found
			return preset(Preset.DEFAULT);
		}
	}
	
	// array containing all preset names
	public static String[] presetNames() {
		String[] names = new String[numPresets()];
		int i=0;
		for (Preset p : Preset.values()) {
			names[i++] = p.name();
		}
		return names;
	}
	
	// get the number of presets
	public static int numPresets() {
		return Preset.values().length;
	}
}
