package sbgjava;

import org.json.JSONObject;

/**
 * Class for making API requests related to managing and creating projects on the Seven Bridges Platform. Constructed by supplying a user's authentication token. 
 * 
 * @author avasoleimany
 *
 */
public class Projects {
	
	private final String authToken;
	
	/**
	 * Create a Projects object. 
	 * 
	 * @param authToken: user's authentication token. 
	 * 
	 * @author avasoleimany
	 */
	public Projects(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Make a GET call to return a list of all the projects a user determined by authToken has access to, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-get-projectGetprojects:GET/1.1/project
	 * 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getProjects() throws Exception{
		SBG getProjectsRequest = new SBG(authToken, "project", "GET", null, null);
		return getProjectsRequest.checkAndRetrieveResponse(getProjectsRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to return the details of a given project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Getprojectdetails:GET/1.1/project/:project_id
	 * 
	 * @param projectID: ID of project to access. must be non null and non empty. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getProjectDetails(String projectID) throws Exception{
		if (projectID == "" || projectID == null){
			throw new Exception("Project ID must be non null and non empty.");
		}
		SBG getProjectDetailsRequest = new SBG(authToken, "project/" + projectID, "GET", null, null);
		return getProjectDetailsRequest.checkAndRetrieveResponse(getProjectDetailsRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to return information about each member in a project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Getprojectmemberdetails:GET/project/:project_id/members
	 * 
	 * @param projectID: ID of project to access. must be non null and non empty. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getProjectMembers(String projectID) throws Exception{
		if (projectID == null || projectID == ""){
			throw new Exception("Project ID must be non null and non empty.");
		}
		SBG getProjectMembersRequest = new SBG(authToken, "project/" + projectID + "/members", "GET", null, null);
		return getProjectMembersRequest.checkAndRetrieveResponse(getProjectMembersRequest.generateRequest());
	}
	
	/**
	 * Make a POST call to create a new project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Createaproject:POST/1.1/project
	 * 
	 * @param name: name of the project to create. must be non null and non empty. 
	 * @param description: description for the project to create. if set to null or "", no description will be provided. 
	 * @param billingGroupID. must be non null and non empty. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject createProject(String name, String description, String billingGroupID) throws Exception{
		if (name == null || billingGroupID == null || name == "" || billingGroupID == ""){
			throw new Exception("Name and billing group ID must both be non null and non empty.");
		}
		
		JSONObject createProjectBodyParams = new JSONObject();
		createProjectBodyParams.put("name", name);
		if (description != null && description != ""){
			createProjectBodyParams.put("description", description);
		}
		createProjectBodyParams.put("billing_group_id", billingGroupID);
		SBG createProjectRequest = new SBG(authToken, "project", "POST", null, createProjectBodyParams);
		return createProjectRequest.checkAndRetrieveResponse(createProjectRequest.generateRequest());
	}
	
	/**
	 * Make a POST call to add a user to a project and set his privileges, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Addprojectmembers:POST/project/:project_id/members
	 * 
	 * @param projectID: ID of the project to add a user to. must be non null and non empty. 
	 * @param username: Seven Bridges username for the user to add to the project. must be non null and non empty. 
	 * 
	 * The following parameters describe permissions for the specified user. If all permissions are set to false, the specified user is 
	 * 		given "read only" permission.
	 * @param canWrite: true to permit the specified user to create, edit, and delete project objects; false otherwise. 
	 * @param canCopy: true to permit the specified user to download and copy files; false otherwise. 
	 * @param canExecute: true to permit the specified user to run tasks; false otherwise.
	 * @param isAdmin: true to permit the specified user to have all rights on the project; false otherwise. 
	 * 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject addProjectMember(String projectID, String username, boolean canWrite, boolean canCopy, boolean canExecute, boolean isAdmin) throws Exception{
		if (projectID == null || username == null || projectID == "" || username == ""){
			throw new Exception("Project ID and username must both be non null and non empty.");
		}
		
		JSONObject addMemberBodyParams = new JSONObject();
		JSONObject userPrivileges = new JSONObject();
		userPrivileges.put("write", canWrite);
		userPrivileges.put("copy", canCopy);
		userPrivileges.put("execute", canExecute);
		userPrivileges.put("admin", isAdmin);
		addMemberBodyParams.put("username", username);
		addMemberBodyParams.put("permissions", userPrivileges);
		
		SBG addProjectMemberRequest = new SBG(authToken, "project/" + projectID + "/members", "POST", null, addMemberBodyParams);
		return addProjectMemberRequest.checkAndRetrieveResponse(addProjectMemberRequest.generateRequest());
	}
	
	/**
	 * Make a PUT call to set a project member's permissions, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Editprojectmembers'details:PUT/project/:project_id/members/:user_id
	 * 
	 * @param projectID: ID of the project to access. must be non null and non empty.
	 * @param userID: ID of the user whose permissions are to be set. must be non null and non empty. 
	 * 
	 * The following parameters describe permissions for the specified user. To set a user's permissions to "read only", set all permissions to false. 
	 * @param canWrite: true to permit the specified user to create, edit, and delete project objects; false otherwise. 
	 * @param canCopy: true to permit the specified user to download and copy files; false otherwise. 
	 * @param canExecute: true to permit the specified user to run tasks; false otherwise. 
	 * @param isAdmin: true to permit the specified user to have all rights on the project; false otherwise
	 * 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject editMemberDetails(String projectID, String userID, boolean canWrite, boolean canCopy, boolean canExecute, boolean isAdmin) throws Exception{
		if (projectID == null || userID == null || projectID == "" || userID == ""){
			throw new Exception("Project ID and user ID must both be non null and non empty.");
		}
		
		JSONObject editDetailsBodyParams = new JSONObject();
		editDetailsBodyParams.put("write", canWrite);
		editDetailsBodyParams.put("copy", canCopy);
		editDetailsBodyParams.put("execute", canExecute);
		editDetailsBodyParams.put("admin", isAdmin);
		
		SBG editMemberDetailsRequest = new SBG(authToken, "project/" + projectID + "/members/" + userID, "PUT", null, editDetailsBodyParams);
		return editMemberDetailsRequest.checkAndRetrieveResponse(editMemberDetailsRequest.generateRequest());
	}
	
	/**
	 * Make a DELETE call to remove a given project from the Seven Bridges Platform, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Deleteaproject:DELETE/project/:project_id
	 * 
	 * @param projectID: ID of the project to delete. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject deleteProject(String projectID) throws Exception{
		if (projectID == null || projectID == ""){
			throw new Exception("Project ID must be non null and non empty.");
		}
		SBG deleteProjectRequest = new SBG(authToken, "project/" + projectID, "DELETE", null, null);
		return deleteProjectRequest.checkAndRetrieveResponse(deleteProjectRequest.generateRequest());
	}
	
	/**
	 * Make a DELETE call to remove a member from a given project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Projects#API:Projects-Deleteaproject:DELETE/project/:project_id/members/:user_id
	 * 
	 * @param projectID: ID of project to access.
	 * @param userID: ID of user to remove. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject deleteProjectMember(String projectID, String userID) throws Exception{
		if (projectID == null || projectID == "" || userID == null || userID == ""){
			throw new Exception("Project ID and user ID must be non null and non empty.");
		}
		SBG deleteProjectMemberRequest = new SBG(authToken, "project/" + projectID + "/members/" + userID, "DELETE", null, null);
		return deleteProjectMemberRequest.checkAndRetrieveResponse(deleteProjectMemberRequest.generateRequest());
	}
	
}
