package sbgjava;

import org.json.JSONObject;

/**
 * Class representing API requests relating to billing information on the Seven Bridges Platform. Constructed by supplying user's authentication token. 
 * 
 * @author avasoleimany
 *
 */
public class Billing {
	
	private String authToken;
	
	/** 
	 * Create a Billing object 
	 * 
	 * @param authToken
	 * @author avasoleimany
	 */
	public Billing(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Get the authentication token associated with this Billing object. 
	 * 
	 * @return authentication token
	 */
	public String getAuthToken() {
		return authToken;
	}
	
	/**
	 * Set the authentication token of this Billing object. 
	 * @param authToken
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * Make a GET Billing Info request as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Billing
	 * @return response of the HTTP Request
	 * @throws Exception
	 * @author avasoleimany
	 */
	public JSONObject getBillingInfo() throws Exception {
		SBG getBillingInfoRequest = new SBG(this.authToken, "billing", "GET", null, null);
		return getBillingInfoRequest.checkAndRetrieveResponse(getBillingInfoRequest.generateRequest());
	}
	
	
}
