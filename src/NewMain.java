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
import com.martinleopold.pui.events.Event;
import com.martinleopold.pui.events.Events;
import com.martinleopold.pui.events.GenericEventHandler;

/**
 *
 * @author Martin Leopold <m@martinleopold.com>
 */
public class NewMain implements GenericEventHandler {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Running NewMain");
		// TODO code application logic here
		Events events = new Events();
	}

	@Override
	public void handleEvent(Event<GenericEventHandler> event) {
	}

}
