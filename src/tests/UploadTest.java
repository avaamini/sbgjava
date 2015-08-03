package tests;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import sbgjava.Upload;

/**
 * JUnit test suite to test Upload API calls. 
 * 
 * @author avasoleimany
 *
 */
public class UploadTest {
	
	private final String authToken = "4ca73bbbacc443dd99b54e84e0833d0d";
	private String testProjectID = "5221b991-87cd-442d-bf05-40cb4ead9f7f"; // API tutorial, not a developer project. 
	private String uploadID = "NP05piA2acEIMF2Mvlnkhyqt0D0HtXdiu4LlUVXZLuTQo08cwFTtZz0jHvQdnVr7"; 
	
	Upload uploadTest = new Upload(authToken);
	
	/**
	 * Test the initializeUpload API call using a valid authentication and project ID
	 */
	@Test
	public void initializeUploadTest() {
		try{
			JSONObject initializeUpload = uploadTest.initializeUpload(testProjectID, "Sample1_RNASeq_chr20.pe_1.fastq", 5242880, 5);
			System.out.println(initializeUpload.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void getUploadInfoTest(){
		try{
			JSONObject getInfo = uploadTest.getUploadInfo(uploadID);
			System.out.println(getInfo.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void getUploadURLTest(){
		try{
			JSONObject getUploadURL = uploadTest.getURLFilePart(uploadID, 1);
			System.out.println(getUploadURL.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void abortUploadTest(){
		try{
			JSONObject abortUpload = uploadTest.abortUpload(uploadID);
			System.out.println(abortUpload.toString());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
}
