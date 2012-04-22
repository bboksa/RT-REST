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
