package de.boksa.rt.test.rest;

import java.io.IOException;

import org.junit.Test;

import de.boksa.rt.rest.RTRESTClient;

public class RTRESTClientTest {

	@Test
	public void test() throws IOException {		
		RTRESTClient client = new RTRESTClient("http://rt.easter-eggs.org/demos/stable/REST/1.0/", "john.foo", "john.foo"); // see http://requesttracker.wikia.com/wiki/Demo
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
		client.login();
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.MULTILINE);
				
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
		client.logout();
		client.searchTickets("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.IDONLY);
	}

}