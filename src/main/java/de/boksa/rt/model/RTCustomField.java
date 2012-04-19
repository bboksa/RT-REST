package de.boksa.rt.model;

import java.util.regex.Pattern;

public class RTCustomField {

	public static final Pattern PATTERN_CUSTOM_FIELD_NAME = Pattern.compile("CF.\\{(.*)\\}");	

	private String name;
	private Object value;
	
	public RTCustomField(String name, String value) {
		this.setName(name);
		this.setValue(value);
	}
	
	// getter and setter methods...
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	// toString...
	@Override
	public String toString() {
		return "RTCustomField [name=" + name + ", value=" + value + "]";
	}
	
}
