package de.boksa.rt.rest.response.parser.processor;

import java.util.regex.Matcher;

import de.boksa.rt.model.RTCustomField;
import de.boksa.rt.model.RTCustomFieldObject;

public final class DefaultCustomFieldProcessor implements FieldProcessor {
	
	private static final DefaultCustomFieldProcessor SINGLETON = new DefaultCustomFieldProcessor();
	
	private DefaultCustomFieldProcessor() { }
	
	public static DefaultCustomFieldProcessor getInstance() {
		return SINGLETON;
	}

	@Override
	public void process(Object object, String fieldName, String fieldValue) {
		Matcher m = RTCustomField.PATTERN_CUSTOM_FIELD_NAME.matcher(fieldName);
		if (m.matches() && (object instanceof RTCustomFieldObject)) {
			RTCustomFieldObject cfObject = (RTCustomFieldObject) object;
			cfObject.getCustomFields().put(m.group(1), new RTCustomField(m.group(1), fieldValue));
		}
	}
	
}
