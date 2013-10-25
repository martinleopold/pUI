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
package com.martinleopold.pui.events;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class EventsTest implements Listener<String> {
	
	public EventsTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		messageReceived = "";
	}
	
	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void viaListener() {
		// create event
		Event<String> message = new Event<String>();
		
		// add listeners
		message.addListener(this);
		
		// fire event
		message.fire("hello");
		
		assertEquals("hello", messageReceived);
		
		setUp();
		message.removeListener(this);
		message.fire("hello");
		assertEquals("", messageReceived);
	}
	
	public String messageReceived;

	@Override
	public void notify(String args) {
		messageReceived = args;
	}

	@Test
	public void viaEventsObject() {
		// create an event
		Event message = Events.createEvent(String.class);
		
		// add listener
		Events.addListener(message, this, "notify");
		
		// fire event
		Events.fireEvent(message, "hello2");
		
		assertEquals("hello2", messageReceived);
		
//		setUp();
//		Events.removeListener(message, this, "notify");
//		message.fire("hello2");
//		assertEquals("", messageReceived);
	}

}
