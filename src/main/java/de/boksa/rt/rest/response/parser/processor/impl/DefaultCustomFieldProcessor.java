package de.boksa.rt.rest.response.parser.processor.impl;

import java.util.regex.Matcher;

import de.boksa.rt.model.RTCustomField;
import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.response.parser.processor.api.TicketFieldProcessor;

public final class DefaultCustomFieldProcessor implements TicketFieldProcessor {
	
	private static final DefaultCustomFieldProcessor SINGLETON = new DefaultCustomFieldProcessor();
	
	private DefaultCustomFieldProcessor() { }
	
	public static DefaultCustomFieldProcessor getInstance() {
		return SINGLETON;
	}

	@Override
	public void process(RTTicket ticket, String ticketFieldName, String ticketFieldValue) {
		Matcher m = RTCustomField.PATTERN_CUSTOM_FIELD_NAME.matcher(ticketFieldName);
		if (m.matches()) {
			ticket.getCustomFields().put(m.group(1), new RTCustomField(m.group(1), ticketFieldValue));
		}
	}
	
}
