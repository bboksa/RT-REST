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
package de.boksa.rt.dao;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.CredentialException;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTClient;
import de.boksa.rt.rest.RTRESTClient.TicketSearchResponseFormat;
import de.boksa.rt.rest.RTRESTResponse;
import de.boksa.rt.rest.response.parser.MultilineTicketSearchResponseParser;
import de.boksa.rt.rest.response.parser.TicketSearchResponseParser;

public class RESTRTTicketDAO implements RTTicketDAO {
	
	private RTRESTClient client;
	
	protected RESTRTTicketDAO() { }
	
	@Override
	public RTTicket findById(Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RTTicket> findByQuery(String query) throws Exception {
		return this.findByQuery(query, null, TicketSearchResponseFormat.MULTILINE);
	}

	@Override
	public List<RTTicket> findByQuery(String query, String orderby) throws Exception {
		return this.findByQuery(query, orderby, TicketSearchResponseFormat.MULTILINE);
	}

	@Override
	public List<RTTicket> findByQuery(String query, TicketSearchResponseFormat format) throws Exception {
		return this.findByQuery(query, null, format);
	}
	
	@Override
	public List<RTTicket> findByQuery(String query, String orderby, TicketSearchResponseFormat format) throws Exception {
		client.login();
		RTRESTResponse response = client.searchTickets(query, orderby, format);
		client.logout();
		TicketSearchResponseParser parser = null;
		
		switch (format) {
			case IDONLY: parser = null; break;
			case IDANDSUBJECT: parser = null; break;
			case MULTILINE: parser = MultilineTicketSearchResponseParser.getInstance(); break;
		}
		
		if (parser != null) {		
			if (response.getStatusCode() == 200l) {
				return parser.parseTicketSearchResponse(response);
			} else if (response.getStatusCode() == 401l) {
				throw new CredentialException(response.getStatusMessage());
			} else {
				throw new IOException(String.format("Server returned REST-response code %s (%s)", response.getStatusCode(), response.getStatusMessage()));
			}
		} else {
			throw new UnsupportedOperationException("Could not create parser for response format.");
		}
	}
	
	// getter and setter methods...
	public RTRESTClient getClient() {
		return client;
	}
	public void setClient(RTRESTClient client) {
		this.client = client;
	}
	
}
