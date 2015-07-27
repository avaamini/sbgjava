package sbgjava;

import org.json.JSONObject;

/**
 * Class representing API requests related to managing pipelines on the Seven Bridges Platform. Constructed by supplying a user's authentication token. 
 * @author avasoleimany
 *
 */
public class Pipelines {
	
	private final String authToken;
	
	/**
	 * Create a Pipelines object. 
	 * 
	 * @param authToken
	 * 
	 * @author avasoleimany
	 */
	public Pipelines(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Make a GET call to return information about the public pipelines on the Seven Bridges Platform, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-get-public-pipelinesGetpublicpipelines:GET/pipeline/public
	 * 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getPublicPipelines() throws Exception{
		SBG getPublicPipelinesRequest = new SBG(authToken, "pipeline/public", "GET", null, null);
		return getPublicPipelinesRequest.checkAndRetrieveResponse(getPublicPipelinesRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to return information about the pipelines under a user's "My Pipelines" on the Seven Bridges Platform, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-get-pipelineGetmypipelines:GET/pipeline/my
	 * 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getMyPipelines() throws Exception{
		SBG getMyPipelinesRequest = new SBG(authToken, "pipeline/my", "GET", null, null);
		return getMyPipelinesRequest.checkAndRetrieveResponse(getMyPipelinesRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to return information about the pipelines in a given project specified by the projectID, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-get-project-pipelineGetpipelinesinaproject:GET/project/:project_id/pipeline
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty. 
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getProjectPipelines(String projectID) throws Exception{
		if (projectID == null || projectID == ""){
			throw new Exception("Project ID must be non null and non empty.");
		}
		SBG getProjectPipelinesRequest = new SBG(authToken, "project/" + projectID + "/pipeline", "GET", null, null);
		return getProjectPipelinesRequest.checkAndRetrieveResponse(getProjectPipelinesRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to return information about a particular pipeline, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-Getpipelinedetails:GET/project/:project_id/pipeline/:pipeline_id
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty. 
	 * @param pipelineID: ID of the pipeline to access. must be non-null and non-empty
	 * @return HTTP response of the request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getPipelineDetails(String projectID, String pipelineID) throws Exception{
		if ((projectID == null || pipelineID == null) || (projectID == "" || pipelineID == "")){
			throw new Exception("Project ID and pipeline ID must both be non null and non empty.");
		}
		SBG getPipelineDetails = new SBG(authToken, "project/" + projectID + "/pipeline/" + pipelineID, "GET", null, null);
		return getPipelineDetails.checkAndRetrieveResponse(getPipelineDetails.generateRequest());
	}
	
	/**
	 * Make a POST call to add a given pipeline to a given project, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines#API:Pipelines-Addapipeline:POST/project/:project_id/pipeline
	 * 
	 * @param destinationProjectID: ID of the project to copy the pipeline into. must be non-null and non-empty.
	 * @param pipelineID: ID of the pipeline to add to the destination project. must be non-null and non-empty. 
	 * @param revision: revision of the pipeline to add to the destination project. if null or "", the latest revision will be copied. 
	 * @param sourceProjectID: ID of the project to copy the pipeline from. for a pipeline from "My Pipelines", set to "my". set to null or "" for copying from the repository of public pipelines.
	 * @return
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject addPipeline(String destinationProjectID, String pipelineID, String revision, String sourceProjectID) throws Exception{
		if ((destinationProjectID == null || pipelineID == null) || (destinationProjectID == "" || pipelineID == "")){
			throw new Exception("Destination project ID and pipeline ID must both be non null and non empty.");
		}
		JSONObject addPipelineBodyParams = new JSONObject();
		addPipelineBodyParams.put("pipeline_id", pipelineID);
		if (revision != null && revision != ""){
			addPipelineBodyParams.put("revision", revision);
		}
		if (sourceProjectID != null && sourceProjectID != ""){
			addPipelineBodyParams.put("project_id", sourceProjectID);
		}
		SBG addPipelineRequest = new SBG(authToken, "project/" + destinationProjectID + "/pipeline", "POST", null, addPipelineBodyParams);
		return addPipelineRequest.checkAndRetrieveResponse(addPipelineRequest.generateRequest());
	}
	
}
