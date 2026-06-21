package Soap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

//import static io.restassured.matcher.RestAssuredMatchers.matchesDtdInClasspath;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static org.hamcrest.Matchers.*;

public class testSoap {
	
	@Test
	public void requestSoap() throws IOException {
		File file = new File("./mockAPI/AddRequest.xml");
		FileInputStream input = new FileInputStream(file);
		String requestBody = IOUtils.toString(input, "UTF-8");	
		
		File file1 = new File("./src/main/resources/addRequest.xsd");
		
		baseURI  = "http://www.dneonline.com/";
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("/calculator.asmx").
		then().
			statusCode(200).
			log().all().
		and().
			body("//*:AddResult.text()", equalTo("5")).
			assertThat().body(matchesXsd(file1));
			//assertThat().body(matchesDtdInClasspath("addRequest.xsd"));
	}

}
