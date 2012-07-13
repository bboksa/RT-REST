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
package de.boksa.rt.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.params.ClientPNames;

public class RTRESTClient {
	
	public enum TicketSearchResponseFormat {
		IDONLY("i"),
		IDANDSUBJECT("s"),
		MULTILINE("l");
		
		private String formatString;
		
		private TicketSearchResponseFormat(String formatString) {
			this.formatString = formatString;
		}
		
		public String getFormatString() {
			return this.formatString;
		}
	}
	
	private static final Pattern PATTERN_RESPONSE_BODY = Pattern.compile("^(.*) (\\d+) (.*)\n((.*\n)*)", Pattern.MULTILINE);
	private static final String NO_MATCHING_RESULTS = "No matching results";
		
	private String restInterfaceBaseURL;
	private String username;
	private String password;
	
	private DefaultHttpClient httpClient;
	
	public RTRESTClient(String restInterfaceBaseURL, String username, String password) {
		this.setRestInterfaceBaseURL(restInterfaceBaseURL);
		this.setUsername(username);
		this.setPassword(password);
		
		this.httpClient = new DefaultHttpClient();
	}

	public RTRESTResponse login() throws IOException {
		String url = String.format("user/%s", this.getUsername());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user", this.getUsername()));
		params.add(new BasicNameValuePair("pass", this.getPassword()));
				
		return this.getResponse(url, params);
	}
	
	public RTRESTResponse logout() throws IOException {
		return this.getResponse("logout");
	}
	
	public RTRESTResponse searchTickets(String query) throws IOException {
		return this.searchTickets(query, null, null);
	}

	public RTRESTResponse searchTickets(String query, String orderby) throws IOException {
		return this.searchTickets(query, orderby, null);
	}		

	public RTRESTResponse searchTickets(String query, TicketSearchResponseFormat format) throws IOException {
		return this.searchTickets(query, null, format);
	}		
	
	public RTRESTResponse searchTickets(String query, String orderby, TicketSearchResponseFormat format) throws IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("query", query));		
		
		if (orderby != null) {
			params.add(new BasicNameValuePair("orderby", orderby));
		}
		
		if (format != null) {
			params.add(new BasicNameValuePair("format", format.getFormatString()));
		}
		
		return this.getResponse("search/ticket", params);
	}

	private RTRESTResponse getResponse(String url) throws IOException {
		return this.getResponse(url, new ArrayList<NameValuePair>());
	}
	
	private RTRESTResponse getResponse(String url, List<NameValuePair> params) throws IOException {
		HttpPost postRequest = new HttpPost(this.getRestInterfaceBaseURL() + url);
		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
        postEntity.setContentType("application/x-www-form-urlencoded");
		
        postRequest.setEntity(postEntity);
        
		// Explicitly enable handling of authentication automatically
		this.httpClient.getParams().setParameter(ClientPNames.HANDLE_AUTHENTICATION, true);
		// Set credentials for the given host:port in advance - the client will use them
		//  automatically when the server will require authentication
		this.httpClient.getCredentialsProvider().setCredentials(
			new AuthScope (postRequest.getURI().getHost(), postRequest.getURI().getPort()), 
			new UsernamePasswordCredentials(this.getUsername(), this.getPassword()));

		HttpResponse httpResponse = this.httpClient.execute(postRequest);
		
		String responseBody = IOUtils.toString(httpResponse.getEntity().getContent(), HTTP.UTF_8);
				
		Matcher matcher = PATTERN_RESPONSE_BODY.matcher(responseBody);
		
		RTRESTResponse response = new RTRESTResponse();
		if (matcher.matches()) {
			String body = matcher.group(4).trim();
			// Check if response is 'No matching results'
			if (body.startsWith (NO_MATCHING_RESULTS)) {
				// Signal upper layers of no records are available by setting
				// response code to -1 and body to actual response from
				// endpoint
				response.setStatusCode (-1l);
				response.setStatusMessage (matcher.group(4).trim());
				return response;
			}
			response.setVersion(matcher.group(1));
			response.setStatusCode(Long.valueOf(matcher.group(2)));
			response.setStatusMessage(matcher.group(3));
			response.setBody(body);
			return response;
		} else {
			// Pattern didn't match - signal upper layers by setting response
			// code to -1
			response.setStatusCode (-1l);
			response.setStatusMessage ("Response body contents - no match");
		}
		return response;
	}

	// getter and setter methods...
	public String getRestInterfaceBaseURL() {
		return restInterfaceBaseURL;
	}
	public void setRestInterfaceBaseURL(String restInterfaceBaseURL) {
		this.restInterfaceBaseURL = restInterfaceBaseURL;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
