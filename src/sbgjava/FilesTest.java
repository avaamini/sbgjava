package sbgjava;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * JUnit Test Suite to test Files API calls. 
 * 
 * @author avasoleimany
 *
 */
public class FilesTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f"; // API tutorial, not a developer project. 
	private String testProjectDeveloper = "ed08e793-4fd7-40b3-883d-08135cb2fafd"; // miRNA test, developer project. 
	private String testFileIDTemp = "559d65a3e4b0566fd15a96f7"; // will need to replace after testing delete on this file. 
	private String testFileToCopy = "55b8e1bae4b09f18a3f0fa2a"; // to copy to API tutorial
	private String testFileForMetadata = "55b8e7d4e4b09f18a3f0fc5e"; // to update metadata. 
	
	Files filesTest = new Files(authToken);

	/**
	 * Test the getFileList API call using a valid authentication token and a valid project ID.
	 */
	@Test
	public void getFileListTest() {
		try{
			JSONObject getFileList = filesTest.getFileList(testProjectID);
			System.out.println(getFileList.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getFileList API call using a valid authentication token and a valid developer project ID.
	 */
	@Test
	public void getFileListDeveloperTest() {
		try{
			JSONObject getFileList = filesTest.getFileList(testProjectDeveloper);
			System.out.println(getFileList.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getFileInfo API call using a valid authentication token, project ID, and existing file ID. 
	 */
	@Test
	public void getFileInfoTest(){
		try{
			System.out.print(testFileIDTemp);
			JSONObject getFileInfo = filesTest.getFileInfo(testProjectID, testFileIDTemp);
			System.out.println(getFileInfo.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getDownloadURL call using a valid authentication token, project ID, and existing file ID.
	 */
	@Test
	public void getDownloadURLTest(){
		try{
			JSONObject getDownloadURL = filesTest.getDownloadURL(testProjectID, testFileIDTemp);
			System.out.println(getDownloadURL.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	/**
	 * Test the deleteFile call using a valid authentication token, project ID, and existing file ID. 
	 */
	@Test
	public void deleteFileTest(){
		try{
			JSONObject deleteFile = filesTest.deleteFile(testProjectID, testFileIDTemp);
			System.out.println(deleteFile.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the copyFiles call using a valid authentication token, project ID, and existing file ID. 
	 */
	@Test
	public void copyFilesTest(){
		try{
			ArrayList<String> fileToCopy = new ArrayList<String>(Arrays.asList(testFileToCopy));
			JSONObject copyFile = filesTest.copyFiles(testProjectID, fileToCopy);
			System.out.println(copyFile.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the updateMetadata call using a valid authentication token, project ID, and existing file ID.
	 */
	@Test
	public void updateMetadataTest(){
		try{
			JSONObject updateMetadataParams = new JSONObject();
			updateMetadataParams.put("file_type", "fastq");
			updateMetadataParams.put("sample", "some sample id");
			updateMetadataParams.put("paired_end", "1");
			updateMetadataParams.put("qual_scale", "illumina13");
			updateMetadataParams.put("seq_tech", "Illumina");
			JSONObject updateMetadata = filesTest.updateMetadata(testProjectID, testFileForMetadata, "updated metadata", null);
			System.out.println(updateMetadata.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
}
