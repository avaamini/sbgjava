package tests;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import sbgjava.Tasks;

/**
 * JUnit Test Suite to test Tasks API calls. 
 * 
 * @author avasoleimany
 *
 */
public class TasksTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f"; // API tutorial, not a developer project. 
	private String testTaskID = "e5f3e9e5-b92a-440c-b404-a6d7f300c63e";
	private String testProjectDeveloper = "9ace096e-9d22-428d-8302-c536b22c9192"; // Samtools test, developer project. 
	private String testPipelineID = "559d56d9896a5d236e19d4da"; // FastQC pipeline
	private String taskToAbort = "1eac7092-df01-48ca-97b9-79ff5370e1f1"; // task initiated in the test suite. 
	
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
	 * Test the getTasks API call using a valid authentication token and a developer projectID.
	 */
	@Test
	public void getTasksDeveloperTest(){
		try{
			JSONObject getTasksDeveloper = tasksTest.getTasks(testProjectDeveloper);
			System.out.println(getTasksDeveloper.toString());
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
	
	/**
	 * Test the executeTask API call using a valid authentication token and project ID.
	 */
	@Test
	public void executeTaskTest(){
		try{
			JSONObject inputsForTask = new JSONObject();
			inputsForTask.put("177252", new JSONArray().put("559d65a1e4b07462e815f3f3"));
			JSONObject parametersForTask = new JSONObject();
			parametersForTask.put("680142", new JSONObject());
			JSONObject executeTask = tasksTest.executeTask(testProjectID, testPipelineID, "", "", "", inputsForTask, parametersForTask);
			System.out.println(executeTask.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the taskAction API call using a valid authentication token, project ID, and running task. 
	 */
	@Test
	public void abortTaskTest(){
		try{
			JSONObject abortTask = tasksTest.taskAction(testProjectID, taskToAbort, "abort");
			System.out.println(abortTask.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
}
