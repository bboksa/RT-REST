package de.boksa.rt.dao;

import java.util.Map;

import de.boksa.rt.rest.RTRESTClient;

public class RESTRTDAOFactory extends RTDAOFactory {

	private static RESTRTDAOFactory SINGLETON = new RESTRTDAOFactory();
	
	private RESTRTDAOFactory() { }
	
	public static RESTRTDAOFactory getInstance() {
		return SINGLETON;
	}
	
	public static final String REST_INTERFACE_BASE_URL = RESTRTDAOFactory.class.getName() + "/restInterfaceBaseURL";
	public static final String REST_INTERFACE_USERNAME = RESTRTDAOFactory.class.getName() + "/username";
	public static final String REST_INTERFACE_PASSWORD = RESTRTDAOFactory.class.getName() + "/password";
	
	
	@Override
	public RTTicketDAO getRTTicketDAO(Map<String,Object> parameters) {
		RESTRTTicketDAO dao = new RESTRTTicketDAO();
		dao.setClient(new RTRESTClient(
			parameters.get(REST_INTERFACE_BASE_URL).toString(),
			parameters.get(REST_INTERFACE_USERNAME).toString(),
			parameters.get(REST_INTERFACE_PASSWORD).toString()
		));		
		
		return dao;
	}

}