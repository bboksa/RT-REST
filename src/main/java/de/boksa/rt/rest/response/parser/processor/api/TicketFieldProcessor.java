package de.boksa.rt.rest.response.parser.processor.api;

import de.boksa.rt.model.RTTicket;

public interface TicketFieldProcessor {

	public void process(RTTicket ticket, String ticketFieldName, String ticketFieldValue);
	
}
