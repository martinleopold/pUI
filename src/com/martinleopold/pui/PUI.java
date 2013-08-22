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

import java.util.ArrayList;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * This is a template class and can be used to start a new processing library or tool. Make sure you
 * rename this class as well as the name of the example package 'template' to your own library or
 * tool naming convention.
 *
 * @example Hello
 *
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 * @author Martin Leopold <m@martinleopold.com>
 */
public class PUI {

	public final static String VERSION = "##library.prettyVersion##";
	// myParent is a reference to the parent sketch
	PApplet p;

	ArrayList<AbstractElement> elements = new ArrayList<AbstractElement>();

	/**
	 * A constructor, usually called in the setup() method in your sketch to initialize and start
	 * the library.
	 *
	 * @example Hello
	 * @param p
	 */
	public PUI(PApplet p) {
		welcomeMessage();

		this.p = p;
		ProcessingEventHandler peh = new ProcessingEventHandler();
		p.registerMethod("mouseEvent", peh);
		p.registerMethod("keyEvent", peh);
		p.registerMethod("draw", peh);

	}

	private void welcomeMessage() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}

	/**
	 * Return the version of the library.
	 *
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 * Class used to receive Processing's events. This is used internally as not to expose the
	 * callback functions in the public API. No need to use this class. Needs to be public for
	 * Processsing to be able to access it (via reflection).
	 */
	public class ProcessingEventHandler {

		/**
		 * Callback/handler for processing mouse events. Don't call manually.
		 *
		 * @param e
		 */
		public void mouseEvent(MouseEvent e) {
			switch (e.getAction()) {
				case MouseEvent.ENTER:
					mouseEntered(e);
					System.out.println("ENTER");
					break;
				case MouseEvent.MOVE:
					System.out.println("MOVE");
					mouseMoved(e);
					break;
				case MouseEvent.PRESS:
					System.out.println("PRESS");
					mousePressed(e);
					break;
				case MouseEvent.DRAG:
					System.out.println("DRAG");
					mouseDragged(e);
					break;
				case MouseEvent.RELEASE:
					System.out.println("RELEASE");
					mouseReleased(e);
					break;
				case MouseEvent.CLICK:
					System.out.println("CLICK");
					mouseClicked(e);
					break;
				case MouseEvent.EXIT:
					System.out.println("EXIT");
					mouseExited(e);
					break;
				case MouseEvent.WHEEL:
					System.out.println("WHEEL");
					mouseWheelMoved(e);
					break;
			}

//		System.out.println("button:" + e.getButton() + " count:" + e.getCount() + " x:" + e.getX() + " y:" + e.getY());
			System.out.println("");
		}

		/**
		 * Callback/handler for processing key events. Don't call manually.
		 *
		 */
		public void keyEvent(KeyEvent e) {
			switch (e.getAction()) {
				case KeyEvent.PRESS:
					System.out.println("PRESS");
					break;
				case KeyEvent.RELEASE:
					System.out.println("RELEASE");
					break;
				case KeyEvent.TYPE:
					System.out.println("TYPE");
					break;
			}
			System.out.println("key: " + e.getKey() + " keyCode: " + e.getKeyCode());
		}

		/**
		 * Callback/handler for processing draw events. Don't call manually.
		 *
		 */
		public void draw() {
			//System.out.println("draw " + p.frameCount);
			for (AbstractElement element : elements) {
				if (!element.isActive()) {
					continue;
				}
				element.draw();
			}
		}
	}

	/**
	 * Add an element to the GUI.
	 *
	 * @param e
	 */
	public void add(AbstractElement e) {
		if (!elements.contains(e)) {
			elements.add(e);
		}
	}

	private void mouseEntered(MouseEvent e) {
	}

	private void mouseExited(MouseEvent e) {
	}

	private void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		for (AbstractElement element : elements) {
			// skip inactive elements
			if (!element.isActive()) {
				continue;
			}

			// distribute mouseEntered, mouseExited and mouseMoved
			boolean wasHover = element.hover;
			element.hover = element.isInside(mx, my);
			System.out.println("hover: " + element.hover);
			if (element.hover && !wasHover) {
				element.mouseEntered();
				element.mouseEntered(mx, my);
			} else if (!element.hover && wasHover) {
				element.mouseExited();
				element.mouseExited(mx, my);
			} else {
				element.mouseMoved();
				element.mouseMoved(mx, my);
			}
		}
	}

	private void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		for (AbstractElement element : elements) {
			if (!element.isActive()) {
				continue;
			}

			if (element.hover) {
				element.mousePressedPre(mx, my);
			}
		}
	}

	private void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		for (AbstractElement element : elements) {
			if (!element.isActive()) {
				continue;
			}

			if (element.hover) {
				element.mouseClicked(mx, my);
			}
		}
	}

	private void mouseDragged(MouseEvent evt) {
		int mx = evt.getX();
		int my = evt.getY();

		for (AbstractElement element : elements) {
			if (!element.isActive()) {
				continue;
			}

			if (element.hover) {
				element.mouseDraggedPre(mx, my);
			}
		}
	}

	private void mouseReleased(MouseEvent evt) {
		int mx = evt.getX();
		int my = evt.getY();

		for (AbstractElement element : elements) {
			if (!element.isActive()) {
				continue;
			}

			if (element.hover) {
				element.mouseReleasedPre(mx, my);
				element.mouseReleasedPost(mx, my);
			}
		}
	}

	private void mouseWheelMoved(MouseEvent e) {
		for (AbstractElement element : elements) {
			if (!element.isActive()) {
				continue;
			}

			if (element.hover) {
				element.mouseScrolled(e.getCount());
			}
		}
	}
}
