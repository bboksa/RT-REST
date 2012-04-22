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
