package sbgjava;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Class for making API requests relating to managing project files on the Seven Bridges Platform. Constructed by supplying a user's authentication token. 
 * 
 * @author avasoleimany
 *
 */
public class Files {
	
	private final String authToken; 
	
	/**
	 * Create a Files object
	 * 
	 * @param authToken
	 * 
	 * @author avasoleimany
	 */
	public Files(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Make a GET file list request to obtain the list of all files for the specified project, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-get-files-listGetfilelist:GET/project/:project_id/file
	 * 
	 * @param projectID: ID of the project to access. set to "public" for accessing the repository of public files
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getFileList(String projectID) throws Exception{
		if (projectID == null){
			throw new Exception("Project ID must be non-null.");
		}
		SBG getFileListRequest = new SBG(authToken, "project/" + projectID + "/file", "GET", null, null);
		return getFileListRequest.checkAndRetrieveResponse(getFileListRequest.generateRequest());
	}
	
	/**
	 * Make a GET file info request to obtain detailed information about the file specified by the parameter fileID, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Getfileinfo:GET/project/:project_id/file/:file_id
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty
	 * @param fileID: ID of the file to access. must be non-null and non-empty
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getFileInfo(String projectID, String fileID) throws Exception{
		if (projectID == null || fileID == null){
			throw new Exception("Project ID and file ID must both be non null.");
		}
		SBG getFileInfoRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID, "GET", null, null);
		return getFileInfoRequest.checkAndRetrieveResponse(getFileInfoRequest.generateRequest());
	}
	
	/**
	 * Make a POST request to copy a group of uploaded files to the specified project, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Copyfiles:POST/project/:project_id/file
	 * 
	 * @param projectID: ID of the project to copy files to. must be non-null and non-empty
	 * @param fileIDs: IDs of the files to copy to the specified project
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject copyFiles(String projectID, ArrayList<String> fileIDs) throws Exception{
		if (projectID == null || fileIDs == null){
			throw new Exception("Project ID and file IDs to copy must both be non null.");
		}
		JSONArray fileIdJSON = new JSONArray(fileIDs);
		JSONObject copyFilesBodyParams = new JSONObject();
		copyFilesBodyParams.put("file_id", fileIdJSON);
		SBG copyFilesRequest = new SBG(authToken, "project/" + projectID + "/file", "POST", null, copyFilesBodyParams);
		return copyFilesRequest.checkAndRetrieveResponse(copyFilesRequest.generateRequest());
	}
	
	/**
	 * Make a POST call to update the metadata of a given file, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Updatefilemetadata:PUT/project/:project_id/file/:file_id
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty
	 * @param fileID: ID of the file to access. must be non-null and non-empty
	 * @param newFileName: new file name for the given file, if applicable. otherwise, passed in as null or ""
	 * @param metadataFields: specifies metadata to be updated, with the mapping metadata_field --> new value
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject updateMetadata(String projectID, String fileID, String newFileName, JSONObject metadataFields) throws Exception{
		if (projectID == null || fileID == null || (metadataFields == null && (newFileName == null || newFileName == ""))){
			throw new Exception("Project ID and file ID must both be non null and must supply at least one metadata field to update.");
		}
		JSONObject updateMetadataBodyParams = new JSONObject();
		// user wants to update file name
		if (newFileName != null && newFileName != ""){
			updateMetadataBodyParams.put("name", newFileName);
		}
		updateMetadataBodyParams.put("metadata", metadataFields);
		SBG updateMetadataRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID, "POST", null, updateMetadataBodyParams);
		return updateMetadataRequest.checkAndRetrieveResponse(updateMetadataRequest.generateRequest());
	}
	
	/**
	 * Make a DELETE call to remove the file specified by the fileID from project with the given projectID, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Deleteafile:DELETE/project/:project_id/file/:file_id
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty
	 * @param fileID: ID of the file to delete. must be non-null and non-empty
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject deleteFile(String projectID, String fileID) throws Exception{
		if ((projectID == null || fileID == null) || (projectID == "" || fileID == "")){
			throw new Exception("Project ID and file ID must both be non null and non empty.");
		}
		SBG deleteFileRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID, "DELETE", null, null);
		return deleteFileRequest.checkAndRetrieveResponse(deleteFileRequest.generateRequest());
	}
	
	/**
	 * Make a GET call to obtain the direct download URL for a given file in a given project, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-GetdownloadURL:GET/project/:project_id/file/:file_id/download
	 * 
	 * @param projectID
	 * @param fileID
	 * @return
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getDownloadURL(String projectID, String fileID) throws Exception{
		if ((projectID == null || fileID == null) || (projectID == "" || fileID == "")){
			throw new Exception("Project ID and file ID must both be non null and non empty.");
		}
		SBG getDownloadURLRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID + "/download", "GET", null, null);
		return getDownloadURLRequest.checkAndRetrieveResponse(getDownloadURLRequest.generateRequest());
	}
}
