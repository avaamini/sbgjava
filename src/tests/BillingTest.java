package tests;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import sbgjava.Billing;

/**
 * JUnit Test Suite to test Billing API calls. 
 * 
 * @author avasoleimany
 *
 */
public class BillingTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	Billing billingTest = new Billing(authToken);
	
	/**
	 * Test the getBillingInfo API call using a valid authentication token. 
	 */
	@Test
	public void getBillingInfoTest() {
		try {
			JSONObject getBillingInfo = billingTest.getBillingInfo();
			System.out.print(getBillingInfo.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
