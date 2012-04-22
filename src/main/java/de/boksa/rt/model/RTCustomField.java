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
