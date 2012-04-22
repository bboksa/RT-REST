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

import java.util.HashMap;
import java.util.Map;


public class RTTicket implements RTCustomFieldObject {

	private Long id;
	private String subject;
	private Long timeWorked;
	private Map<String,RTCustomField> customFields;
	
	public RTTicket() {
		this.customFields = new HashMap<String,RTCustomField>();
	}
	
	
	// getter and setter methods...
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getTimeWorked() {
		return timeWorked;
	}
	public void setTimeWorked(Long timeWorked) {
		this.timeWorked = timeWorked;
	}
	public Map<String, RTCustomField> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(Map<String, RTCustomField> customFields) {
		this.customFields = customFields;
	}

	// toString...
	@Override
	public String toString() {
		return "RTTicket [id=" + id + ", subject=" + subject + ", timeWorked="
				+ timeWorked + ", customFields=" + customFields + "]";
	}
	
}