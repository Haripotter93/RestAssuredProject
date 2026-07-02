package RestAssuredAPI;

import org.testng.annotations.Test;
import static io.restassured. RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Authentication {

	@Test
	void testBasicAuthentication() {
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo (true))
			.log().all();
	}
	
	@Test
	void testDigestAuthentication() {
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo (true))
			.log().all();
	}

	@Test
	void testPreemptiveAuthentication() {
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo (true))
			.log().all();
	}
	

	@Test
	void testBearerTokenAuthentication() {
		String bearerToken="ghp_24pH0Icz1PKHC1q0tLwj57AuDYmtSz2fuYKP";
		given()
			.headers("Authorization", "Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode (200) .log().all();
	}


	@Test
	void testOAuth1Authentication()
	{
		given ()
			.auth().oauth("consumerkey", "consumerSecrat", "accessToken", "tokenSecrate") // this is for OAuth1.0 authentication
		.when()
			.get("url")
		.then()
			.statusCode (200)
			.log().all();
	}
	
	@Test
	void testOAuth2Authentication()
	{
		given ()
			.auth().oauth2("token")
		.when()
			.get("url")
		.then()
			.statusCode (200)
			.log().all();
	}

	@Test
	void apiKeyAuthentication() { // URL : https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c") // Token
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	
	}

}
