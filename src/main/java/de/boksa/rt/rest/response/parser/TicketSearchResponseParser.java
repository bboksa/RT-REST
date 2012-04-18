package de.boksa.rt.rest.response.parser;

import java.util.List;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTResponse;

public interface TicketSearchResponseParser {

	public List<RTTicket> parseTicketSearchResponse(RTRESTResponse response);

}
