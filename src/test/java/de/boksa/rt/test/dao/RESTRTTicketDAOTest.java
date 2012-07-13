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
				LOG.debug("   Found ticket: " + ticket.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
