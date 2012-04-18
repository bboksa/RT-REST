package de.boksa.rt.rest.response.parser.processor.impl;

import de.boksa.rt.model.RTTicket;

public class StringFieldProcessor extends AbstractBeanProcessor {

	private static final StringFieldProcessor SINGLETON = new StringFieldProcessor();
	
	private StringFieldProcessor() { }
	
	public static StringFieldProcessor getInstance() {
		return SINGLETON;
	}

	
		
	@Override
	public void process(RTTicket ticket, String ticketFieldName, String ticketFieldValue) {
		this.copyProperty(ticket, ticketFieldName, ticketFieldValue);
	}
	
	
	

}
