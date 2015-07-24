package sbgjava;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * A class to make requests and receive responses to and from the Seven Bridges Genomics API. 
 * 
 * A request is specified by its headers, the request type (GET, PUT, POST, DELETE), request path, associated query and body. 
 * 
 * @author avasoleimany
 *
 */
public class SBG {
	
	private final String baseURL = "https://api.sbgenomics.com/" ;
	private String requestURL;
	private final String authToken;
	private final String version;
	private String path;
	private final String method;
	private JSONObject queryParams = null;
	private JSONObject bodyParams = null;
	private final HashMap<String, String> headers;
	private HttpClient sbgClient;
	
	/**
	 * Construct the base class for making an HTTP request to the Seven Bridges Genomics API. 
	 * 
	 * @param authToken
	 * @param version
	 * @param path
	 * @param method
	 * @param queryParams
	 * @param bodyParams
	 */
	public SBG(String authToken, String version, String path, String method, JSONObject queryParams, JSONObject bodyParams){
		this.authToken = authToken;
		this.version = version;
		this.path = path;
		this.method = method;
		this.queryParams = queryParams;
		this.bodyParams = bodyParams;
		
		this.headers = new HashMap<String, String>();
		headers.put("X-SBG-Auth-Token", authToken);
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		this.requestURL = baseURL + version + "/" + path;
		
		sbgClient = HttpClientBuilder.create().build();
		
	}
	
	/**
	 * Generate and make the necessary HTTP request. 
	 * 
	 * @param URL
	 * @param queryParamsNVPair
	 * @param bodyParams
	 * @return
	 * @throws Exception
	 */
	public HttpResponse generateRequest(String URL, List<NameValuePair> queryParamsNVPair, JSONObject bodyParams) throws Exception{
		
		HttpRequestBase request = null; 
		
		switch (method){
			case "GET":
				request = new HttpGet(URL);
				for (String headerKey : this.headers.keySet()){
					request.addHeader(headerKey, headers.get(headerKey));
				}
				break;
			case "POST":
				HttpPost postRequest = new HttpPost(URL);
				for (String headerKey : this.headers.keySet()){
					postRequest.addHeader(headerKey, headers.get(headerKey));
				}
				if (bodyParams != null){
					StringEntity requestBodySE = new StringEntity(bodyParams.toString());
					postRequest.setEntity(requestBodySE);
				}
				request = postRequest;
				break;
			case "PUT":
				HttpPut putRequest = new HttpPut(URL);
				for (String headerKey : this.headers.keySet()){
					putRequest.addHeader(headerKey, headers.get(headerKey));
				}
				if (bodyParams != null){
					StringEntity requestBodySE = new StringEntity(bodyParams.toString());
					putRequest.setEntity(requestBodySE);
				}
				request = putRequest;
				break;
			case "DELETE":
				request = new HttpDelete(URL);
				for (String headerKey : this.headers.keySet()){
					request.addHeader(headerKey, headers.get(headerKey));
				}
				break;	
			default:
				break;
		}
		
		if (queryParamsNVPair != null){
			URIBuilder getUriBuilder = new URIBuilder(request.getURI());
			for (NameValuePair paramPair : queryParamsNVPair){
				getUriBuilder.addParameter(paramPair.getName(), paramPair.getValue());
			} 
			URI getUri = getUriBuilder.build();
			((HttpRequestBase) request).setURI(getUri);
		}
		
		HttpResponse response = sbgClient.execute(request);
		
		return response;
		
	}
	
	/**
	 * Evaluate the status of the HTTP Request and return the appropriate response. 
	 * 
	 * @param requestResult
	 * @return
	 * @throws Exception
	 */
	public JSONObject checkAndRetrieveResponse(HttpResponse requestResult) throws Exception {
		
		int requestStatusCode = requestResult.getStatusLine().getStatusCode();
		
		if (requestStatusCode / 100 != 2){
			throw new Exception("Server responded with status code: " + Integer.toString(requestStatusCode) + " . " + requestResult.getStatusLine().getReasonPhrase());
		}
		
		else {
			String JSONString = EntityUtils.toString(requestResult.getEntity());
			JSONObject responseJSON = new JSONObject(JSONString);
			return responseJSON;
		}
	}
	
	
	
}

