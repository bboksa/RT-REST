package de.boksa.rt.test.dao;

import org.junit.Test;

import de.boksa.rt.dao.impl.RTTicketRESTDAO;
import de.boksa.rt.rest.RTRESTClient;

public class RTTicketRESTDAOTest {
	
	@Test
	public void test() {
		RTTicketRESTDAO dao = new RTTicketRESTDAO();
		// see http://requesttracker.wikia.com/wiki/Demo
		dao.setClient(new RTRESTClient("http://rt.easter-eggs.org/demos/stable/REST/1.0/", "john.foo", "john.foo"));
		try {
			dao.findByQuery("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.MULTILINE);						
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}