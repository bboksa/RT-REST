package de.boksa.rt.model;

import java.util.HashMap;
import java.util.Map;

public class RTTicket {

	private Long id;
	private String subject;
	private Long timeWorked;
	private Map<String,RTCustomField> customFields;
	
	public RTTicket() {
		this.customFields = new HashMap<String,RTCustomField>();
	}
	
	
	/* Simple Getter & Setter */
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

	/* toString */
	@Override
	public String toString() {
		return "RTTicket [id=" + id + ", subject=" + subject + ", timeWorked="
				+ timeWorked + ", customFields=" + customFields + "]";
	}
	
}