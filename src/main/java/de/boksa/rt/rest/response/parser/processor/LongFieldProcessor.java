package de.boksa.rt.rest.response.parser.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LongFieldProcessor extends AbstractBeanProcessor {

	private static final LongFieldProcessor SINGLETON = new LongFieldProcessor();
	
	private LongFieldProcessor() { }
	
	public static LongFieldProcessor getInstance() {
		return SINGLETON;
	}

	
	
	private static final Pattern PATTERN_LONG = Pattern.compile("^.*?(-??\\d+).*?$");
	
	@Override
	public void process(Object ticket, String fieldName, String fieldValue) {
		Matcher m = PATTERN_LONG.matcher(fieldValue);
		if (m.matches()) {
			this.copyProperty(ticket, fieldName, m.group(1));
		}
	}
	
	
	

}
