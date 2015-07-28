package sbgjava;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

/**
 * JUnit Test Suite to test Pipelines API calls. 
 * 
 * @author avasoleimany
 *
 */
public class PipelinesTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
	private String testPipelineID = "";
	
	Pipelines pipelinesTest = new Pipelines(authToken);
	
	/**
	 * Test the getPublicPipelines API call using a valid authentication token. 
	 */
	@Test
	public void getPublicPipelinesTest(){
		try{
			JSONObject getPublicPipelines = pipelinesTest.getPublicPipelines();
			System.out.println(getPublicPipelines);
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getMyPipelines API call using a valid authentication token. 
	 */
	@Test
	public void getMyPipelinesTest(){
		try{
			JSONObject getMyPipelines = pipelinesTest.getMyPipelines();
			System.out.println(getMyPipelines.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
//	/**
//	 * Test the getProjectPipelines call using a valid authentication token and project ID for which the user has appropriate privileges. 
//	 */
//	@Test
//	public void getProjectPipelinesTest(){
//		try{
//			JSONObject getProjectPipelines = pipelinesTest.getProjectPipelines(testProjectID);
//			System.out.println(getProjectPipelines.toString());
//		} catch (Exception e){
//			e.printStackTrace();
//			fail();
//		}
//	}
//	
//	/**
//	 * Test the getPipelineDetails call using a valid authentication token, project ID, and pipeline ID for which the user has appropriate privileges. 
//	 */
//	@Test
//	public void getPipelineDetailsTest(){
//		try{
//			JSONObject getPipelineDetails = pipelinesTest.getPipelineDetails(testProjectID, testPipelineID);
//			System.out.println(getPipelineDetails.toString());
//		} catch (Exception e){
//			e.printStackTrace();
//			fail();
//		}
//	}
}
