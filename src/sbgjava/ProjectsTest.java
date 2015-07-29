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
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f"; // API tutorial, not a developer project. 
	private String testProjectDeveloper = "ed08e793-4fd7-40b3-883d-08135cb2fafd"; // miRNA test, developer project. 
	private String billingGroupID = "3a569134-13c1-4620-a971-b6d9f81b316c";
	private String createdProjectID = "7f15c0f0-5a56-4ab7-a5a0-9481cc2e2ded"; // created during the createProject test in the test suite
	
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
	 * Test the getProjectDetails API call using a valid authentication token and the project ID of a developer project
	 */
	@Test
	public void getProjectDetailsDeveloperTest(){
		try{
			JSONObject getProjectDeveloper = projectsTest.getProjectDetails(testProjectDeveloper);
			System.out.println(getProjectDeveloper.toString());
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
	
	/**
	 * Test the createProject API call using a valid authentication token and billing group ID
	 */
	@Test
	public void createProjectTest(){
		try{
			JSONObject createProject = projectsTest.createProject("test creation", "testing from the Java client", billingGroupID);
			System.out.println(createProject.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the addProjectMember API call using a valid authentication token and project ID
	 */
	@Test
	public void addProjectMemberTest(){
		try{
			JSONObject addProjectMember = projectsTest.addProjectMember(createdProjectID, "rfranklin", false, false, false, false);
			System.out.println(addProjectMember.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the editMemberDetails API call using a valid authentication token and project ID 
	 */
	@Test
	public void editMemberDetailsTest(){
		try{
			JSONObject editMemberDetails = projectsTest.editMemberDetails(createdProjectID, "rfranklin", true, true, false, false);
			System.out.println(editMemberDetails.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the deleteProjectMember API call using a valid authentication token and project ID
	 */
	@Test
	public void deleteProjectMemberTest(){
		try{
			JSONObject deleteProjectMember = projectsTest.deleteProjectMember(createdProjectID, "rfranklin");
			System.out.println(deleteProjectMember.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the deleteProject API call using a valid authentication token and project ID
	 */
	@Test
	public void deleteProjectTest(){
		try{
			JSONObject deleteProject = projectsTest.deleteProject(createdProjectID);
			System.out.println(deleteProject.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
}
