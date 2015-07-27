package sbgjava;

import org.json.JSONObject;

/**
 * Class representing API requests relating to billing information on the Seven Bridges Platform. Constructed by supplying user's authentication token. 
 * 
 * @author avasoleimany
 *
 */
public class Billing {
	
	private final String authToken;
	
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
	 * Make a GET Billing Info request as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Billing
	 * @return response of the HTTP Request
	 * @throws Exception
	 * @author avasoleimany
	 */
	public JSONObject getBillingInfo() throws Exception{
		SBG getBillingInfoRequest = new SBG(this.authToken, "billing", "GET", null, null);
		return getBillingInfoRequest.checkAndRetrieveResponse(getBillingInfoRequest.generateRequest());
	}
	
	
}
