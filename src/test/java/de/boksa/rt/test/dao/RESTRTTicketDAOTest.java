package de.boksa.rt.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import de.boksa.rt.dao.RESTRTDAOFactory;
import de.boksa.rt.dao.RTTicketDAO;
import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTClient;

public class RESTRTTicketDAOTest {
	
	// use Commons Logging
	private static final Log LOG = LogFactory.getLog(RESTRTTicketDAOTest.class);
	
	// this is a RT Query Builder query
	private static final String RTQBQ_ALL_FROM_CUSTOMER_SERVICE = "Queue = 'Customer Service'";
	
	// this is rather a demo than a test ;-)
	@Test
	public void demo() {
		// we use a Map to hold the factory parameters to have a common signature for the factory method
		Map<String,Object> factoryParameters = new HashMap<String,Object>();
		
		// for the credentials used see http://requesttracker.wikia.com/wiki/Demo
		LOG.debug("Setting credentials to access the RT demo installation");		
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_BASE_URL, "http://rt.easter-eggs.org/demos/stable/REST/1.0/");
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_USERNAME, "john.foo");
		factoryParameters.put(RESTRTDAOFactory.REST_INTERFACE_PASSWORD, "john.foo");
		
		LOG.debug("Creating the RTTicketDAO");
		RTTicketDAO dao = RESTRTDAOFactory.getInstance().getRTTicketDAO(factoryParameters);
		
		// Strictly following the J2EE DAO Pattern that would be:
		// RTTicketDAO dao = RTDAOFactory.getRTDAOFactory(RTDAOFactoryType.REST).getRTTicketDAO(factoryParameters);
		
		try {
			LOG.debug("Running the RT Query Builder query and parsing the results");
			List<RTTicket> result = dao.findByQuery(RTQBQ_ALL_FROM_CUSTOMER_SERVICE, RTRESTClient.TicketSearchResponseFormat.MULTILINE);
			
			LOG.debug("Iterating over the resulting POJOs");
			for (RTTicket ticket : result) {
				LOG.debug(String.format("   Found ticket: #%s / %s", ticket.getId(), ticket.getSubject()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}