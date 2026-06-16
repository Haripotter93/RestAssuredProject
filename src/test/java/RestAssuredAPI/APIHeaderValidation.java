package RestAssuredAPI;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIHeaderValidation {

	@Test
	void getHeader() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	}
	
	@Test
	void getHeaders() {
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		String headerValue = res.getHeader("Content-Type");
		System.out.println(headerValue);
		Headers headers = res.getHeaders();
		
		for(Header header : headers) {
			System.out.println("Header Name : "+ header.getName() + " , Header Value : " + header.getValue());
		}
	}

}
