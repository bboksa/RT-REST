/*
 * Copyright (C) 2012  Benjamin Boksa (http://www.boksa.de/)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package de.boksa.rt.test.rest;

import java.io.IOException;

import org.junit.Test;

import de.boksa.rt.rest.RTRESTClient;

public class RTRESTClientTest {

	@Test
	public void test() throws IOException {		
		// for the credentials used see http://requesttracker.wikia.com/wiki/Demo
		RTRESTClient client = new RTRESTClient("http://rt.easter-eggs.org/demos/stable/REST/1.0/", "john.foo", "john.foo"); 
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
		client.login();
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.MULTILINE);
				
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
		client.logout();
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
	}

}