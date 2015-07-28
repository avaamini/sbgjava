package sbgjava;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

/**
 * JUnit Test Suite to test Tasks API calls. 
 * 
 * @author avasoleimany
 *
 */
public class TasksTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
	private String testTaskID = "e5f3e9e5-b92a-440c-b404-a6d7f300c63e";
	
	Tasks tasksTest = new Tasks(authToken);
	
	/**
	 * Test the getTasks API call using a valid authentication token and a valid project ID. 
	 */
	@Test
	public void getTasksTest() {
		try{
			JSONObject getTasks = tasksTest.getTasks(testProjectID);
			System.out.println(getTasks.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getTaskDetails API call using a valid authentication token, project ID, and task ID.
	 */
	@Test
	public void getTaskDetailsTest(){
		try{
			JSONObject getTaskDetails = tasksTest.getTaskDetails(testProjectID, testTaskID);
			System.out.println(getTaskDetails.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
}
