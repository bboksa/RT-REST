package de.boksa.rt.rest.response.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTResponse;
import de.boksa.rt.rest.response.parser.processor.api.TicketFieldProcessor;
import de.boksa.rt.rest.response.parser.processor.api.TicketFieldProcessorRegistry;

public final class MultilineTicketSearchResponseParser implements TicketSearchResponseParser {
	
	private static MultilineTicketSearchResponseParser SINGLETON = new MultilineTicketSearchResponseParser();
	
	private MultilineTicketSearchResponseParser() { }
	
	public static MultilineTicketSearchResponseParser getInstance() {
		return SINGLETON;
	}
	
	
	
	
	@Override
	public List<RTTicket> parseTicketSearchResponse(RTRESTResponse response) {

		List<Map<String,String>> resultData = new LinkedList<Map<String,String>>();
		
		for (String ticketString : response.getBody().split("\n\n--\n\n")) {
			Map<String,String> ticketData = new HashMap<String,String>();
			
			String fieldName = null;
			StringBuffer tmp = new StringBuffer();
			for (String ticketLine : ticketString.split("\n")) {
				Matcher m = Pattern.compile("^(.*?): ?(.*)").matcher(ticketLine);
				if (m.matches()) {
					if (fieldName != null) {
						ticketData.put(fieldName, tmp.toString());
						tmp.setLength(0); // delete the buffer
					}
					
					fieldName = m.group(1);
					tmp.append(m.group(2));
				} else {
					tmp.append(ticketLine);
				}	
			}						
						
			ticketData.put(fieldName, tmp.toString());
			
			resultData.add(ticketData);
		}

		return MultilineTicketSearchResponseParser.processResultData(resultData);
	}

	private static List<RTTicket> processResultData(List<Map<String,String>> resultData) {
		List<RTTicket> result = new LinkedList<RTTicket>();
				
		for (Map<String,String> ticketData : resultData) {
			result.add(MultilineTicketSearchResponseParser.processTicketData(ticketData));
		}
		
		return result;
	}
	
	private static RTTicket processTicketData(Map<String,String> ticketData) {
		RTTicket ticket = new RTTicket();
		
		TicketFieldProcessor ticketFieldProcessor = null;
		
		for (Entry<String,String> e : ticketData.entrySet()) {
			ticketFieldProcessor = TicketFieldProcessorRegistry.getInstance().getTicketFieldProcessor(ticket, e.getKey());
			ticketFieldProcessor.process(ticket, e.getKey(), e.getValue());
		}
		
		return ticket;
	}
	
}