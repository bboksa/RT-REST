package de.boksa.rt.rest.response.parser.processor.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.boksa.rt.model.RTTicket;

public class LongFieldProcessor extends AbstractBeanProcessor {

	private static final LongFieldProcessor SINGLETON = new LongFieldProcessor();
	
	private LongFieldProcessor() { }
	
	public static LongFieldProcessor getInstance() {
		return SINGLETON;
	}

	
	
	private static final Pattern PATTERN_LONG = Pattern.compile("^.*?(-??\\d+).*?$");
	
	@Override
	public void process(RTTicket ticket, String ticketFieldName, String ticketFieldValue) {
		Matcher m = PATTERN_LONG.matcher(ticketFieldValue);
		if (m.matches()) {
			this.copyProperty(ticket, ticketFieldName, m.group(1));
		}
	}
	
	
	

}
