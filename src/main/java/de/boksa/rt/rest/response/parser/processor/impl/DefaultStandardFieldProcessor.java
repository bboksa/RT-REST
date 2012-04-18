package de.boksa.rt.rest.response.parser.processor.impl;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.response.parser.processor.api.TicketFieldProcessor;

public final class DefaultStandardFieldProcessor implements TicketFieldProcessor {
	
	private static final DefaultStandardFieldProcessor SINGLETON = new DefaultStandardFieldProcessor();
	
	private DefaultStandardFieldProcessor() { }
	
	public static DefaultStandardFieldProcessor getInstance() {
		return SINGLETON;
	}

	@Override
	public void process(RTTicket ticket, String ticketFieldName, String ticketFieldValue) {
		
	}
	
}
