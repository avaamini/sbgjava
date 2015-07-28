package sbgjava;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class testSBGAPI {

	@Test
	public void test() {
		Projects myProjects = new Projects("4ca73bbbacc443dd99b54e84e0833d0d");
		try {
			JSONObject getProjectsTest = myProjects.getProjects();
			System.out.println(getProjectsTest.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
