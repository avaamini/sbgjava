package sbgjava;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

/**
 * JUnit Test Suite to test Projects API calls. 
 * 
 * @author avasoleimany
 *
 */
public class ProjectsTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
	
	Projects projectsTest = new Projects(authToken);
	
	/**
	 * Test the getProjects API call using a valid authentication token
	 */
	@Test
	public void getProjectsTest() {
		try{
			JSONObject getProjects = projectsTest.getProjects();
			System.out.println(getProjects.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getProjectDetails API call using a valid authentication token and project ID
	 */
	@Test
	public void getProjectDetailsTest(){
		try{
			JSONObject getProjectDetails = projectsTest.getProjectDetails(testProjectID);
			System.out.println(getProjectDetails.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getProjectMembers API call using a valid authentication token and project ID
	 */
	@Test
	public void getProjectMembersTest(){
		try{
			JSONObject getProjectMembers = projectsTest.getProjectMembers(testProjectID);
			System.out.println(getProjectMembers.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
