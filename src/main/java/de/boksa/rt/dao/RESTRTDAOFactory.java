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