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
package de.boksa.rt.rest.response.parser.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import de.boksa.rt.model.RTCustomField;
import de.boksa.rt.model.RTTicket;


public class FieldProcessorRegistry {

	private static final FieldProcessorRegistry SINGLETON = new FieldProcessorRegistry();

	private FieldProcessorRegistry() {
		this.typeFieldProcessors = new HashMap<Class<?>,FieldProcessor>();
		
		this.typeFieldProcessors.put(Long.class, LongFieldProcessor.getInstance());
		this.typeFieldProcessors.put(Date.class, DateFieldProcessor.getInstance());
		
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
