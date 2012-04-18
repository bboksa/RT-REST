package de.boksa.rt.dao.impl;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.CredentialException;

import de.boksa.rt.dao.api.RTTicketDAO;
import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTClient;
import de.boksa.rt.rest.RTRESTClient.TicketSearchResponseFormat;
import de.boksa.rt.rest.RTRESTResponse;
import de.boksa.rt.rest.response.parser.MultilineTicketSearchResponseParser;
import de.boksa.rt.rest.response.parser.TicketSearchResponseParser;

public class RTTicketRESTDAO implements RTTicketDAO {
	
	private RTRESTClient client;
		
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
	
	/* Simple Getter & Setter */
	public RTRESTClient getClient() {
		return client;
	}
	public void setClient(RTRESTClient client) {
		this.client = client;
	}
	
}
