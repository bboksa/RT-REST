package de.boksa.rt.rest.response.parser.processor.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.response.parser.processor.api.TicketFieldProcessor;

public abstract class AbstractBeanProcessor implements TicketFieldProcessor {

	protected void copyProperty(RTTicket ticket, String name, Object value) {
		try {
			BeanUtils.copyProperty(ticket, StringUtils.uncapitalize(name), value);
		} catch (Exception ex) { }		
	}
	
}
