package de.boksa.rt.dao;

import java.util.List;

import de.boksa.rt.model.RTTicket;
import de.boksa.rt.rest.RTRESTClient.TicketSearchResponseFormat;

public interface RTTicketDAO {

	public RTTicket findById(Long id) throws Exception;
	public List<RTTicket> findByQuery(String query) throws Exception;
	public List<RTTicket> findByQuery(String query, String orderby) throws Exception;
	public List<RTTicket> findByQuery(String query, TicketSearchResponseFormat format) throws Exception;
	public List<RTTicket> findByQuery(String query, String orderby, TicketSearchResponseFormat format) throws Exception;
	
}
