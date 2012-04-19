package de.boksa.rt.dao;

import java.util.Map;


public abstract class RTDAOFactory {
	
	public enum RTDAOFactoryType {
		REST
	}
		
	public static RTDAOFactory getRTDAOFactory(RTDAOFactoryType type) {
		switch (type) {
			case REST:
				return RESTRTDAOFactory.getInstance();
			default:
				return null;
		}
	}
	
	public abstract RTTicketDAO getRTTicketDAO(Map<String,Object> parameters);

}