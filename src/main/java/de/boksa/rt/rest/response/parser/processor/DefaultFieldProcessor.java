package de.boksa.rt.rest.response.parser.processor;


public final class DefaultFieldProcessor extends AbstractBeanProcessor {
	
	private static final DefaultFieldProcessor SINGLETON = new DefaultFieldProcessor();
	
	private DefaultFieldProcessor() { }
	
	public static DefaultFieldProcessor getInstance() {
		return SINGLETON;
	}

	@Override
	public void process(Object object, String fieldName, String fieldValue) {
		this.copyProperty(object, fieldName, fieldValue);		
	}
	
}
