package sbgjava;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class SampleUsage {
	
	/**
	 * Sample usage for API calls related to billing. 
	 */
	public void billingSample() throws Exception{
		String authToken = "4ca73bbbacc443dd99b54e84e0833d0d"; 
		Billing billing = new Billing(authToken);
		JSONObject billingInfo = billing.getBillingInfo();
	}
	
	/**
	 * Sample usage for API calls related to files. 
	 */
	public void filesSample() throws Exception{
		String authToken = "4ca73bbbacc443dd99b54e84e0833d0d"; 
		Files filesTest = new Files(authToken);
		
		String projectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		
		// Get file list in a project
		JSONObject projectFilesResponse = filesTest.getFileList(projectID);
		
		String fileID = "559d65a3e4b0566fd15a96f7";
		
		// Get info about a file
		JSONObject fileInfoResponse = filesTest.getFileInfo(projectID, fileID);
		
		// Copy file 
		ArrayList<String> fileIDToCopy = new ArrayList<String>();
		fileIDToCopy.add(fileID);
		JSONObject copyFileResponse = filesTest.copyFiles(projectID, fileIDToCopy);
		
		// Get download URL
		JSONObject downloadURLResponse = filesTest.getDownloadURL(projectID, fileID);
		
		// Update file metadata
		String newName = "new name";
		JSONObject updatedMetadataResponse = filesTest.updateMetadata(projectID, fileID, newName, null);
		
		// Delete file
		JSONObject deleteFileResponse = filesTest.deleteFile(projectID, fileID);
	}
	
	/**
	 * Sample usage for API calls related to pipelines.
	 */
	public void pipelinesSample() throws Exception{
		String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
		Pipelines pipelines = new Pipelines(authToken);
		
		// Get public pipelines
		JSONObject publicPipelinesResponse = pipelines.getPublicPipelines();
		
		// Get my pipelines
		JSONObject myPipelinesResponse = pipelines.getMyPipelines();
		
		// Get project pipelines
		String projectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		JSONObject projectPipelinesResponse = pipelines.getProjectPipelines(projectID);
		
		// Get pipeline details
		String pipelineID = "55b91446896a5d222dd3e7d0";
		JSONObject pipelineDetailsResponse = pipelines.getPipelineDetails(projectID, pipelineID);
		
		// Add a pipeline
		String destinationProject = "ed08e793-4fd7-40b3-883d-08135cb2fafd";
		String sourceProject = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		String pipelineID1 = "55b91446896a5d222dd3e7d0";
		JSONObject addPipelineResponse = pipelines.addPipeline(destinationProject, pipelineID1, "", sourceProject);
	}
	
	/**
	 * Sample usage for API calls related to tasks. 
	 */
	public void tasksSample() throws Exception{
		String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
		Tasks tasks = new Tasks(authToken);
		
		// Get tasks
		String projectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		JSONObject getTasksResponse = tasks.getTasks(projectID);
		
		// Get task details
		String taskID = "e5f3e9e5-b92a-440c-b404-a6d7f300c63e";
		JSONObject getTaskDetailsResponse = tasks.getTaskDetails(projectID, taskID);
		
		// Execute a task
		String pipelineID = "559d56d9896a5d236e19d4da";
		
		JSONObject inputs = new JSONObject();
		String inputNodeID = "177252";
		String fileInputID = "559d65a1e4b07462e815f3f3";
		inputs.put("177252", new JSONArray().put("559d65a1e4b07462e815f3f3"));
		
		JSONObject params = new JSONObject(); 
		String paramNodeID = "680142";
		params.put(paramNodeID, new JSONObject());
		
		tasks.executeTask(projectID, pipelineID, "", "sample task", "sample task for Java client library", inputs, params);
		
		// Abort a task
		tasks.taskAction(projectID, taskID, "abort");
	}
	
	/**
	 * Sample usage for API calls related to projects
	 */
	public void projectsSample() throws Exception{
		String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
		Projects projects = new Projects(authToken);
		
		// Get projects
		JSONObject getProjectsResponse = projects.getProjects();
		
		// Get project details
		String projectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		JSONObject getProjectDetailsResponse = projects.getProjectDetails(projectID);
		
		// Get project member details
		JSONObject getProjectMembersResponse = projects.getProjectMembers(projectID);
		
		// Create a project
		JSONObject createProjectResponse = projects.createProject("sample project", "sample project for Java client library", "3a569134-13c1-4620-a971-b6d9f81b316c");
		
		// Add a project member
		String newMember = "rfranklin";
		boolean copy = true;
		boolean write = true;
		boolean execute = true;
		boolean admin = false;
		JSONObject addMemberResponse = projects.addProjectMember(projectID, newMember, copy, write, execute, admin);
		
		// Edit a member's details
		String member = "rfranklin";
		boolean canCopy = false;
		boolean canWrite = false;
		boolean canExecute = false;
		boolean isAdmin = false;
		JSONObject editMemberResponse = projects.editMemberDetails(projectID, member, canCopy, canWrite, canExecute, isAdmin);
		
		// Delete a project
		String projectToDelete = "5221b991-87cd-442d-bf05-40cb4ead9f7f";
		JSONObject deleteProjectResponse = projects.deleteProject(projectToDelete);
		
		// Delete a project member
		String projectToAccess = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
		String userToDelete = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
		JSONObject deleteMemberResponse = projects.deleteProjectMember(projectToAccess, userToDelete);
	}
	
	/**
	 * Sample usage for API calls related to uploads
	 */
	public void uploadSample() throws Exception{
		String authToken = "c6d0207bed2041a5a2293f7e171b6795";
		Upload upload = new Upload(authToken);
		
		// Get info about an upload
		String uploadID = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
		JSONObject uploadInfoResponse = upload.getUploadInfo(uploadID);
		
		// Get upload URL
		JSONObject uploadURLResponse = upload.getURLFilePart(uploadID, 1);
		
		// Initialize file upload
		String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
		String fileName = "Sample1_RNASeq_chr20.pe_1.fastq";
		Integer fileSize = 5242880;
		Integer partSize = 5;
		JSONObject initializeResponse = upload.initializeUpload(projectID, fileName, fileSize, partSize);
		
		// Confirm successful upload
		String eTag = "d41d8cd98f00b204e9800998ecf8427e";
		JSONObject confirmUploadResponse = upload.confirmUpload(uploadID, 1, eTag);
		
		// Finalize upload
		JSONObject finalizeUploadResponse = upload.finalizeUpload(uploadID);
		
	}
}
