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
 * A Widget to test Events
 * @author Martin Leopold <m@martinleopold.com>
 */
public class Dummy extends Widget<Dummy> {

	public Dummy(PUI pui, int width, int height) {
		super(pui, width, height);
	}

	@Override
	void mouseEntered(float mx, float my) {
		System.out.println("entered: mx:" + mx + " my:" + my);
	}

	@Override
	void mouseExited(float mx, float my) {
		System.out.println("exited: mx:" + mx + " my:" + my);
	}

	@Override
	void mouseMoved(float mx, float my) {
		System.out.println("moved: mx:" + mx + " my:" + my);
	}

	@Override
	void mousePressed(int button, float mx, float my) {
		System.out.println("pressed: button:" + button + " mx:" + mx + " my:" + my);
	}

	@Override
	void mouseReleased(int button, float mx, float my) {
		System.out.println("released: button:" + button + " mx:" + mx + " my:" + my);
	}

	@Override
	void mouseClicked(int button, int count, float mx, float my) {
		System.out.println("clicked: count:" + count + " button:" + button + " mx:" + mx + " my:" + my);
	}

	@Override
	void mouseDragged(int button, float mx, float my, float dx, float dy) {
		System.out.println("dragged: button:" + button + " mx:" + mx + " my:" + my + " dx:" + dx + " dy:" + dy);
	}

	@Override
	void mouseScrolled(int amount) {
		System.out.println("scrolled: amount:" + amount);
	}

	@Override
	void draw(PApplet p) {
		p.pushStyle();
		p.fill(64);
		p.stroke(128);
		p.rect(x, y, width, height);
		p.popStyle();
	}

	@Override
	protected Dummy getThis() {
		return this;
	}
}
