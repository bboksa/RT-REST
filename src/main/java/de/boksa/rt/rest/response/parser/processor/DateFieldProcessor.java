/*
 * Copyright (C) 2012  Ilia Sotnikov (hostcc@gmail.com)
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
 *
 * Based on src/main/java/de/boksa/rt/rest/response/parser/processor/LongFieldProcessor.java
 *  copyright (C) Benjamin Boksa
 */
package de.boksa.rt.rest.response.parser.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.ConvertUtils;
import java.util.Date;

public class DateFieldProcessor extends AbstractBeanProcessor {

	private static final DateFieldProcessor SINGLETON = new DateFieldProcessor();
	
	private static final String DATE_FORMAT_PATTERNS[] = {
		// Possible date formats, see lib/RT/Date.pm in RT distribution
		"yyyy-MM-dd HH:mm:ss",				// ISO: YYYY-MM-DD hh:mm:ss
		"yyyy-MM-ddTHH:mm:ssz",				// W3CDTF:  YYYY-MM-DDThh:mm:ssTZD
		"EEE, dd MMM yyyy HH:mm:ss ZZZ", 	// RFC2822: Sun, 06 Nov 1994 08:49:37 +0000
		"EEE, dd MMM yyyy HH:mm:ss Z",	 	// RFC2616: Sun, 06 Nov 1994 08:49:37 GMT
		"yyyyMMddTHHmmss",					// iCal: 19971024T120000
		"EEE MMM dd HH:mm:ss yyyy"			// A system default
	};
	
	private DateConverter dateConverter;

	private DateFieldProcessor() {
		dateConverter = new DateConverter();
		ConvertUtils.register (dateConverter, Date.class);
	}
	
	public static DateFieldProcessor getInstance() {
		return SINGLETON;
	}

	@Override
	public void process(Object ticket, String fieldName, String fieldValue) {
		// No need the matcher here - DateConverter will handle that itself
		dateConverter.setPatterns (DATE_FORMAT_PATTERNS);
		this.copyProperty(ticket, fieldName, fieldValue);
	}
}
