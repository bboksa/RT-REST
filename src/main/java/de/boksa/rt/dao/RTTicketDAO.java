/*
 * Copyright 2012 Benjamin Boksa (http://www.boksa.de/)
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
