package API_CRUD;

import org.testng.Assert;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@SuppressWarnings("unused")
public class testAPI_CRUD {
	
	@Test
	public void testGetResponse() {	
		Response response = get("http://localhost:3000/users/1");
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	public void testGet() {	
		baseURI  = "http://localhost:3000";
		
		given().
			//param("firstName", "Peter").
			get("/users").
		then().
			statusCode(200).
			assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
			//body("id", contains("2")).
			log().all();	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPost() {
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Barack");
		request.put("lastName", "Obama");
		request.put("subjectId", 2);
		
		baseURI  = "http://localhost:3000/";
		given().header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users/").
		then().
			statusCode(201).
			log().all();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPatch() {
		JSONObject request = new JSONObject();
		
		request.put("lastName", "Hussein");
		
		baseURI  = "http://localhost:3000/";
		given().header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/users/5").
		then().
			statusCode(200).
			log().all();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPut() {
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Michelle");
		request.put("lastName", "Obama");
		request.put("subjectId", 1);
		
		baseURI  = "http://localhost:3000/";
		given().header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/5").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test
	public void testDelete() {
		baseURI  = "http://localhost:3000/";
		
		when().
			delete("/users/5").
		then().
			statusCode(200).
			log().all();
	}
}
