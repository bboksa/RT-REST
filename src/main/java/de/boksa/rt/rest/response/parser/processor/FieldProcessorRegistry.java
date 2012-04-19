package de.boksa.rt.rest.response.parser.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import de.boksa.rt.model.RTCustomField;
import de.boksa.rt.model.RTTicket;


public class FieldProcessorRegistry {

	private static final FieldProcessorRegistry SINGLETON = new FieldProcessorRegistry();

	private FieldProcessorRegistry() {
		this.typeFieldProcessors = new HashMap<Class<?>,FieldProcessor>();
		
		this.typeFieldProcessors.put(Long.class, LongFieldProcessor.getInstance());
		
		this.standardFieldProcessors = new HashMap<String,FieldProcessor>();
		this.customFieldProcessors = new HashMap<String,FieldProcessor>();
	}
	
	public static FieldProcessorRegistry getInstance() {
		return SINGLETON;
	}

	
	
	
	private Map<Class<?>,FieldProcessor> typeFieldProcessors;
	private Map<String,FieldProcessor> standardFieldProcessors;
	private Map<String,FieldProcessor> customFieldProcessors;
	
	
	public FieldProcessor getTicketFieldProcessor(RTTicket ticket, String ticketFieldName) {
		FieldProcessor fieldProcessor = null;
		
		Matcher m = RTCustomField.PATTERN_CUSTOM_FIELD_NAME.matcher(ticketFieldName);
				
		if (m.matches()) {
			fieldProcessor = customFieldProcessors.get(m.group(1));
			if (fieldProcessor == null) {
				fieldProcessor = DefaultCustomFieldProcessor.getInstance();
			}
		} else {
			fieldProcessor = standardFieldProcessors.get(ticketFieldName);
			if (fieldProcessor == null) {
				try {
					fieldProcessor = typeFieldProcessors.get(PropertyUtils.getPropertyType(ticket, StringUtils.uncapitalize(ticketFieldName)));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
		return (fieldProcessor != null) ? fieldProcessor : DefaultFieldProcessor.getInstance();
	}	
	
}