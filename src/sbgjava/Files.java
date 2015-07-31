package sbgjava;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
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
	 * Make a PUT call to update the metadata of a given file, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Updatefilemetadata:PUT/project/:project_id/file/:file_id
	 *		https://docs.sbgenomics.com/display/sbg/Required+Metadata+Fields#RequiredMetadataFields-Metadatarequirementsforcommonfiletypes 
	 *
	 * @param projectID: ID of the project to access. must be non-null and non-empty
	 * @param fileID: ID of the file to access. must be non-null and non-empty
	 * @param newFileName: new file name for the given file, if applicable. otherwise, passed in as null or ""
	 * @param metadataFields: specifies metadata to be updated, with the mapping metadata_field --> new value. null if no metadata fields are to be updated. 
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject updateMetadata(String projectID, String fileID, String newFilename, JSONObject metadataFields) throws Exception{
		if (projectID == null || fileID == null || (metadataFields == null && (newFilename == null || newFilename == ""))){
			throw new Exception("Project ID and file ID must both be non null and must supply at least one metadata field to update.");
		}
		JSONObject updateMetadataBodyParams = new JSONObject();
		// user wants to update file name
		if (newFilename != null && newFilename != ""){
			updateMetadataBodyParams.put("name", newFilename);
		}
		if (metadataFields != null ){
			updateMetadataBodyParams.put("metadata", metadataFields);
		}
		SBG updateMetadataRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID, "PUT", null, updateMetadataBodyParams);
		return updateMetadataRequest.checkAndRetrieveResponse(updateMetadataRequest.generateRequest());
	}
	
	/**
	 * Make a PUT call to update the metadata of a given file, as specified by:
	 * 		https://docs.sbgenomics.com/display/developerhub/API%3A+Files#API:Files-Updatefilemetadata:PUT/project/:project_id/file/:file_id
	 * 		https://docs.sbgenomics.com/display/sbg/Required+Metadata+Fields#RequiredMetadataFields-Metadatarequirementsforcommonfiletypes
	 * 
	 * @param projectID: ID of the project to access. must be non-null and non-empty
	 * @param fileID: ID of the file to access. must be non-null and non-empty
	 * @param newFilename: new file name for the given file, if applicable. otherwise, passed in as null or ""
	 * @param fileType: file type. must be non-null and non-empty. 
	 * @param qualScale: This metadata field specifies the quality scale encoding scheme. 
	 * 		For FASTQ files, you enter these metadata values, unless using them in a pipeline that contains the FASTQ quality scale detector wrapper. In this case, you can specify the quality scale encoding scheme by setting the qual_scale parameter inside the pipeline. 
	 * 		For BAM files, the quality scale encoding metadata value must be ‘sanger’.
	 * @param seqTech: This field allows you to specify the sequencing technology used. 
	 * 		This metadata field is only required by some apps and pipelines, however, it is recommended that you set it whenever possible, unless you are certain that your pipeline will work without it.
	 * 		Supported values: "454", "Helicos", "Illumina", "Solid", "IonTorrent"
	 * @param sampleID: The Sample ID metadata field refers to the biological sample that each file describes. Apps use Sample ID to separate files that come from different samples. 
	 * 		For SAM and BAM files, the value supplied in the Sample ID  field is written to the read group tag (@RG:SM). 
	 * 		All aligners add read group fields to the aligned BAM file using the file’s Sample ID metadata.
	 * @param library: The library ID field describes the library for the read. The value set in this field is inconsequential for pipeline functioning, but all files that come from the same sequencer run must have the same value. 
	 * 		The Library ID will be written to the read group tag (@RG:LB) in SAM or BAM files. 
	 * 		All aligner apps are programmed to add RG fields to the aligned BAM according to the Library ID.
	 * @param platformUnit: This is equivalent to the lane/slide field. The platformUnit field describes the flow-cell lane number of the sequencer where the data were produced. 
	 * 		The value supplied in the platformUnit field will be written to the read group tag (@RG:PU) in SAM or BAM files. 
	 * 		All aligner apps add read group fields to the aligned BAM file on the basis of Lane/slide metadata.
	 * @param pairedEnd: For paired-end read files, this field indicates whether the read file is left end or right end. Set ‘1’ for left end and ‘2’ for right end reads. 
	 * 		If the FASTQ file is a single-end read this field should be left as ‘-’. For paired-end data, it is important to assign each pair of paired-end files  a unique combination of the other metadata fields: Sample ID, Library ID, Lane/slide, and Chunk number.
	 * @return response of the HTTP request
	 * @throws Exception
	 * 
	 * @author avasoleimany
	 */
	public JSONObject updateMetadata(String projectID, String fileID, String newFilename, String fileType, String qualScale, String seqTech, String sampleID, String library, String platformUnit, String pairedEnd) throws Exception{
		if (projectID == null || fileID == null || projectID == "" || fileID == ""){
			throw new Exception("Project ID and file ID must both be non null and non empty.");
		}
		JSONObject updateMetadataBodyParams = new JSONObject();
		JSONObject metadataFields = new JSONObject();
		
		addMetadataField("name", newFilename, updateMetadataBodyParams);
		addMetadataField("file_type", fileType, metadataFields);
		addMetadataField("qual_scale", qualScale, metadataFields);
		addMetadataField("seq_tech", seqTech, metadataFields);
		addMetadataField("sample", sampleID, metadataFields);
		addMetadataField("library", library, metadataFields);
		addMetadataField("platform_unit", platformUnit, metadataFields);
		addMetadataField("paired_end", pairedEnd, metadataFields);
		
		if (metadataFields.length() != 0){
			updateMetadataBodyParams.put("metadata", metadataFields);
		}
		SBG updateMetadataRequest = new SBG(authToken, "project/" + projectID + "/file/" + fileID, "PUT", null, updateMetadataBodyParams);
		return updateMetadataRequest.checkAndRetrieveResponse(updateMetadataRequest.generateRequest());
	}
	
	/**
	 * Helper function to determine if a metadata field is non-null and non-empty and to add it to a JSONObject
	 * 
	 * @param field: metadata field key
	 * @param value: metadata value
	 * @param fields
	 * @return updated metadata fields object
	 * @throws JSONException 
	 */
	private JSONObject addMetadataField(String field, String value, JSONObject metadataFields) throws JSONException{
		if (value != null && value != ""){
			metadataFields.put(field, value);
		} return metadataFields;
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
	 * @return response of the HTTP request. 
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
