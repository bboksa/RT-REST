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
		
	private String restInterfaceBaseURL;
	private String username;
	private String password;
	
	private HttpClient httpClient;
	
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
        
		HttpResponse httpResponse = this.httpClient.execute(postRequest);
		
		String responseBody = IOUtils.toString(httpResponse.getEntity().getContent(), HTTP.UTF_8);
				
		Matcher matcher = PATTERN_RESPONSE_BODY.matcher(responseBody);
		
		if (matcher.matches()) {
			RTRESTResponse response = new RTRESTResponse();
			response.setVersion(matcher.group(1));
			response.setStatusCode(Long.valueOf(matcher.group(2)));
			response.setStatusMessage(matcher.group(3));
			response.setBody(matcher.group(4).trim());
			return response;
		} else {
			System.err.println("not matched");
		}
				
		return new RTRESTResponse();
	}

	/* Simple Getter & Setter */
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