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
public class Bang extends AbstractElement {

	public Bang(float x, float y, float width, float height, PUI pui) {
		super(x, y, width, height, pui);
	}
	
	@Override
	public void mouseEntered() {
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseEntered(float mx, float my) {
		System.out.println("mouseEntered: " + mx + " " + my);
	}

	@Override
	public void mouseMoved() {
		System.out.println("mouseMoved");
	}

	@Override
	public void mouseMoved(float mx, float my) {
		System.out.println("mouseMoved: " + mx + " " + my);
	}

	@Override
	public void mouseExited() {
		System.out.println("mouseMoved");
	}

	@Override
	public void mouseExited(float mx, float my) {
		System.out.println("mouseExited: " + mx + " " + my);
	}

	@Override
	public void mousePressed() {
		System.out.println("mousePressed");
	}

	@Override
	public void mousePressed(float mx, float my) {
		System.out.println("mousePressed: " + mx + " " + my);
	}

	@Override
	public void mouseDoubleClicked() {
		System.out.println("mouseDoubleClicked");
	}

	@Override
	public void mouseDoubleClicked(float mx, float my) {
		System.out.println("mouseDoubleClicked: " + mx + " " + my);
	}

	@Override
	public void mouseDragged(float mx, float my) {
		System.out.println("mouseDragged: " + mx + " " + my);
	}

	@Override
	public void mouseDragged(float mx, float my, float dx, float dy) {
		System.out.println("mouseReleased: " + mx + " " + my + " " + dx + " " + dy);
	}

	@Override
	public void mouseReleased() {
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseReleased(float mx, float my) {
		System.out.println("mouseReleased: " + mx + " " + my);
	}

	@Override
	public void mouseScrolled(float step) {
		System.out.println("mouseScrolled: " + step);
	}

	@Override
	public void draw() {
		pui.p.rect(x, y, width, height);
	}
}
