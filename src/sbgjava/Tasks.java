package sbgjava;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

/**
 * Class for making API requests related to managing tasks on the Seven Bridges Platform. Constructed by supplying a user's authentication token. 
 * 
 * @author avasoleimany
 *
 */
public class Tasks {

	private final String authToken;
	
	/**
	 * Create a Tasks object. 
	 * 
	 * @param authToken
	 * 
	 * @author avasoleimany
	 */
	public Tasks(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Make a GET call to return a list of all the tasks within a given project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-Addapipeline:POST/project/:project_id/pipeline
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getTasks(String projectID) throws Exception{
		if (projectID == null || projectID == ""){
			throw new Exception("Project ID must be non null and non empty.");
		}
		SBG getTasksRequest = new SBG(authToken, "project/" + projectID + "/task", "GET", null, null);
		return getTasksRequest.checkAndRetrieveResponse(getTasksRequest.generateRequest());
	}
	
	/**
	 * Make a POST call to run a task within a given project, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-Addapipeline:POST/project/:project_id/pipeline
	 * 
	 * @param projectID: ID of the project to execute the task in. must be non-null and non-empty.
	 * @param pipelineID: ID of the pipeline to execute. must be non-null and non-empty. 
	 * @param pipelineRevision: revision of the pipeline to execute. if set to null or "", the latest revision will be used. 
	 * @param name: name for the task to be executed. if set to null or "", the task will be named automatically. 
	 * @param description: description of the task to be executed. if set to null or "", no description will be set. 
	 * @param inputs: JSON key-value pairs mapping pipeline input node IDs to an array of file IDs. there must be an array associated with every input node. 
	 * @param parameters: JSON key-value pairs mapping pipeline node IDs to app-specific parameters. some parameter, even if it is null, must be supplied for every node ID. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject executeTask(String projectID, String pipelineID, String pipelineRevision, String name, String description, JSONObject inputs, JSONObject parameters) throws Exception{
		if ((projectID == null || pipelineID == null) || (projectID == "" || pipelineID == "")){
			throw new Exception("Project ID and pipeline ID must both be non null and non empty.");
		}
		if (inputs == null || parameters == null){
			throw new Exception("Inputs and parameters must be specified.");
		}
		JSONObject executeTaskBodyParams = new JSONObject();
		executeTaskBodyParams.put("project_id", projectID);
		executeTaskBodyParams.put("pipeline_id", pipelineID);
		if (pipelineRevision != null && pipelineRevision != ""){
			executeTaskBodyParams.put("pipeline_revision", pipelineRevision);
		}
		if (name != null && name != ""){
			executeTaskBodyParams.put("name", name);
		}
		if (description != null && description != ""){
			executeTaskBodyParams.put("description", description);
		}
		executeTaskBodyParams.put("inputs", inputs);
		executeTaskBodyParams.put("parameters", parameters);
		
		SBG executeTaskRequest = new SBG(authToken, "project/" + projectID + "/task", "POST", null, executeTaskBodyParams);
		return executeTaskRequest.checkAndRetrieveResponse(executeTaskRequest.generateRequest());
	}
	
	/**
	 * Make a GET request to obtain information about a given task, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Tasks#API:Tasks-get-task-id-detailsGettaskdetails:GET/project/:project_id/task/:task_id
	 * 
	 * @param projectID: ID of project to access
	 * @param taskID: ID of task to access
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getTaskDetails(String projectID, String taskID) throws Exception{
		if ((projectID == null || taskID == null) || (projectID == "" || taskID == "")){
			throw new Exception("Project ID and task ID must both be non null and non empty.");
		}
		SBG getTaskDetailsRequest = new SBG(authToken, "project/" + projectID + "/task/" + taskID, "GET", null, null);
		return getTaskDetailsRequest.checkAndRetrieveResponse(getTaskDetailsRequest.generateRequest());
	}
	
	/**
	 * Make a POST request to perform an action on a given task, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Tasks#API:Tasks-Abortatask:POST/project/:project_id/task/:task_id
	 * @param projectID: ID of project to access
	 * @param taskID: ID of task to perform action on	
	 * @param action: desired action. currently, the only supported action is "abort". if set to null or "", defaults to "abort"
	 * @return
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject taskAction(String projectID, String taskID, String action) throws Exception{
		if ((projectID == null || taskID == null) || (projectID == "" || taskID == "")){
			throw new Exception("Project ID and task ID must both be non null and non empty.");
		}
		JSONObject actionQueryParams = new JSONObject();
		if (action != null && action != ""){
			actionQueryParams.put("action", action);
		} else {
			actionQueryParams.put("actiokn", "abort");
		}
		SBG taskActionRequest = new SBG(authToken, "project/" + projectID + "/task/" + taskID, "POST", actionQueryParams, null);
		return taskActionRequest.checkAndRetrieveResponse(taskActionRequest.generateRequest());
	}
	
}
