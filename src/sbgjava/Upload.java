package sbgjava;

import org.json.JSONObject;

/**
 * Class representing API requests relating to uploading files to the Seven Bridges Platform. Constructed by supplying user's authentication token.
 * 
 * @author avasoleimany
 *
 */
public class Upload {
	
	private final String authToken;
	
	/**
	 * Create an Upload object
	 * 
	 * @param authToken	
	 * @author avasoleimany
	 */
	public Upload(String authToken){
		this.authToken = authToken;
	}
	
	/**
	 * Make a GET Upload Info request as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-Getinfoaboutanupload:GET/1.1/upload/multipart/:upload_id
	 * 
	 * @param uploadID: ID of the upload
	 * @return response of the HTTP Request
	 * @throws Exception	
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getUploadInfo(String uploadID) throws Exception{
		if (uploadID == null){
			throw new Exception("Must provide a non-null upload ID.");
		}
		SBG getUploadInfoRequest = new SBG(this.authToken, "upload/multipart/" + uploadID, "GET", null, null);
		return getUploadInfoRequest.checkAndRetrieveResponse(getUploadInfoRequest.generateRequest());
	}
	
	/**
	 * Make a GET Upload URL for a File Part request as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-GetuploadURLforafilepart:GET/1.1/upload/multipart/:upload_id/:part_number
	 * 
	 * @param uploadID: ID of the upload
	 * @param partNumber: part number in the range 1 to 10000, inclusive
	 * @return response of the HTTP Request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject getURLFilePart(String uploadID, int partNumber) throws Exception{
		if (uploadID == null || (!((0 < partNumber) && (partNumber < 10001)))){
			throw new Exception("Both a upload ID and a valid part number must not be null.");
		}
		SBG getURLFilePartRequest = new SBG(this.authToken, "upload/multipart/" + uploadID + "/" + Integer.toString(partNumber), "GET", null, null);
		return getURLFilePartRequest.checkAndRetrieveResponse(getURLFilePartRequest.generateRequest());	
	}
	
	/**
	 * Make a POST file upload request to initialize upload of a file, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-Initializefileupload:POST/1.1/upload/multipart
	 * 
	 * @param projectID: ID of the destination project. required. 	
	 * @param name: file name. required.
	 * @param size: file size. number in the range 0 - 5497558138880, inclusive. required.
	 * @param partSize: requested part size. if set to null, defaults to 5 (GB). 
	 * @return response of the HTTP Request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject initializeUpload(String projectID, String name, Integer size, Integer partSize) throws Exception{
		if (projectID == null || name == null || size == null){
			throw new Exception("ID of destination project, file name, and file size must not be null.");
		}
		JSONObject uploadBodyParams = new JSONObject();
		uploadBodyParams.put("project_id", projectID);
		uploadBodyParams.put("name", name);
		uploadBodyParams.put("size", size);
		if (partSize != null){
			uploadBodyParams.put("part_size", partSize);
		}
		SBG initializeUploadRequest = new SBG(this.authToken, "upload/multipart", "POST", null, uploadBodyParams);
		return initializeUploadRequest.checkAndRetrieveResponse(initializeUploadRequest.generateRequest());
	}
	
	/**
	 * Make a POST request to notify the API that a part of a file has been uploaded to backing storage, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-Confirmsuccessfulupload:POST/1.1/upload/multipart/:upload_id
	 * 
	 * @param uploadID: ID of the upload
	 * @param partNumber: ID of the part to be reported as completed
	 * @param eTag: value of the ETag header obtained during the part upload
	 * @return response of the HTTP Request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject confirmUpload(String uploadID, Integer partNumber, String eTag) throws Exception{
		if (uploadID == null || partNumber == null || eTag == null){
			throw new Exception("Upload ID, part number, and eTag must not be null.");
		}
		JSONObject confirmUploadBodyParams = new JSONObject();
		confirmUploadBodyParams.put("part_number", partNumber);
		confirmUploadBodyParams.put("e_tag", eTag);
		SBG confirmUploadRequest = new SBG(this.authToken, "upload/multipart/" + uploadID, "POST", null, confirmUploadBodyParams);
		return confirmUploadRequest.checkAndRetrieveResponse(confirmUploadRequest.generateRequest());
	}
	
	/**
	 * Make a POST request to finalize the upload of a file, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-Finalizefileupload:POST/1.1/upload/multipart/:upload_id/complete
	 * 
	 * @param uploadID: ID of the upload
	 * @return response of the HTTP Request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject finalizeUpload(String uploadID) throws Exception{
		if (uploadID == null){
			throw new Exception("Upload ID must not be null.");
		}
		SBG finalizeUploadRequest = new SBG(this.authToken, "upload/multipart/" + uploadID + "/complete", "POST", null, null);
		return finalizeUploadRequest.checkAndRetrieveResponse(finalizeUploadRequest.generateRequest());
	}
	
	/**
	 * Make a DELETE request to abort an initialized upload, as specified by: 
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files#API:UploadFiles-Abortupload:DELETE/1.1/upload/multipart/:upload_id
	 * 
	 * @param uploadID: ID of the upload
	 * @return response of the HTTP Request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject abortUpload(String uploadID) throws Exception{
		if (uploadID == null){
			throw new Exception("Upload ID must not be null.");
		}
		SBG abortUploadRequest = new SBG(this.authToken, "upload/multipart/" + uploadID, "DELETE", null, null);
		return abortUploadRequest.checkAndRetrieveResponse(abortUploadRequest.generateRequest());
	}
}
