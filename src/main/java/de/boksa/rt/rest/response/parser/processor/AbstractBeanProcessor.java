package de.boksa.rt.rest.response.parser.processor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

public abstract class AbstractBeanProcessor implements FieldProcessor {

	protected void copyProperty(Object object, String name, Object value) {
		try {
			BeanUtils.copyProperty(object, StringUtils.uncapitalize(name), value);
		} catch (Exception ex) { }		
	}
	
}
