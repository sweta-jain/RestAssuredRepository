package API_CRUD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class testAPI_CRUD {
	
	@Test
	public void testGet() {
		RestAssured.get();		
	}
}
