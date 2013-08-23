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

import processing.core.PApplet;

/**
 * User Interface Event Callbacks
 * @author Martin Leopold <m@martinleopold.com>
 */
interface UIEvents {
	void mouseEntered(float mx, float my);
	void mouseExited(float mx, float my);
	void mouseMoved(float mx, float my);
	void mousePressed(int button, float mx, float my);
	void mouseReleased(int button, float mx, float my);
	void mouseClicked(int button, int count, float mx, float my);
	void mouseDragged(int button, float mx, float my, float dx, float dy);
	void mouseScrolled(int amount);
	void keyPressed(int key, int keyCode);
	void keyReleased(int key, int keyCode);
	void draw(PApplet p);
}
