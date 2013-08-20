/*
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 * 
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

import processing.core.*;
import processing.event.MouseEvent;

/**
 * This is a template class and can be used to start a new processing library or tool. Make sure you rename this class
 * as well as the name of the example package 'template' to your own library or tool naming convention.
 *
 * @example Hello
 *
 * (the tag @example followed by the name of an example included in folder 'examples' will automatically include the
 * example in the javadoc.)
 * @author Martin Leopold <m@martinleopold.com>
 */
public class PUI {

	public final static String VERSION = "##library.prettyVersion##";
	// myParent is a reference to the parent sketch
	PApplet p;

	/**
	 * a Constructor, usually called in the setup() method in your sketch to initialize and start the library.
	 *
	 * @example Hello
	 * @param p
	 */
	public PUI(PApplet p) {
		welcomeMessage();

		this.p = p;
		p.registerMethod("mouseEvent", this);
		p.registerMethod("draw", this);

	}

	private void welcomeMessage() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}

	/**
	 * return the version of the library.
	 *
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	public void mouseEvent(MouseEvent e) {
		System.out.println(e);
	}

	public void draw() {
		System.out.println("draw " + p.frameCount);
	}

}
