package de.boksa.rt.rest.response.parser.processor.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import de.boksa.rt.model.RTCustomField;
import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.response.parser.processor.impl.DefaultCustomFieldProcessor;
import de.boksa.rt.rest.response.parser.processor.impl.DefaultStandardFieldProcessor;
import de.boksa.rt.rest.response.parser.processor.impl.LongFieldProcessor;
import de.boksa.rt.rest.response.parser.processor.impl.StringFieldProcessor;


public class TicketFieldProcessorRegistry {

	private static final TicketFieldProcessorRegistry SINGLETON = new TicketFieldProcessorRegistry();

	private TicketFieldProcessorRegistry() {
		this.typeFieldProcessors = new HashMap<Class<?>,TicketFieldProcessor>();
		
		this.typeFieldProcessors.put(Long.class, LongFieldProcessor.getInstance());
		this.typeFieldProcessors.put(String.class, StringFieldProcessor.getInstance());
		
		this.standardFieldProcessors = new HashMap<String,TicketFieldProcessor>();
		this.customFieldProcessors = new HashMap<String,TicketFieldProcessor>();
	}
	
	public static TicketFieldProcessorRegistry getInstance() {
		return SINGLETON;
	}

	
	
	
	private Map<Class<?>,TicketFieldProcessor> typeFieldProcessors;
	private Map<String,TicketFieldProcessor> standardFieldProcessors;
	private Map<String,TicketFieldProcessor> customFieldProcessors;
	
	
	public TicketFieldProcessor getTicketFieldProcessor(RTTicket ticket, String ticketFieldName) {
		TicketFieldProcessor ticketFieldProcessor = null;
		
		Matcher m = RTCustomField.PATTERN_CUSTOM_FIELD_NAME.matcher(ticketFieldName);
				
		if (m.matches()) {
			ticketFieldProcessor = customFieldProcessors.get(m.group(1));
			if (ticketFieldProcessor == null) {
				ticketFieldProcessor = DefaultCustomFieldProcessor.getInstance();
			}
		} else {
			ticketFieldProcessor = standardFieldProcessors.get(ticketFieldName);
			if (ticketFieldProcessor == null) {
				try {
					ticketFieldProcessor = typeFieldProcessors.get(PropertyUtils.getPropertyType(ticket, StringUtils.uncapitalize(ticketFieldName)));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
		return (ticketFieldProcessor != null) ? ticketFieldProcessor : DefaultStandardFieldProcessor.getInstance();
	}	
	
}