package de.boksa.rt.rest.response.parser.processor;

public interface FieldProcessor {

	public void process(Object object, String fieldName, String fieldValue);
	
}
