package sbgjava;

import static org.junit.Assert.*;

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
	private String testProjectID = "ed08e793-4fd7-40b3-883d-08135cb2fafd";
	private String testFileIDTemp = "55b7daf6e4b09f18a3f043f3"; // will need to replace after testing delete on this file. 
	
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

}
