package RestAssuredAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class Cookies {


	@Test(priority=1)
	void testCookies() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC", "AdJVEavBZYKXZbYSZbkNWUhT46BNTeXPzFIcjXSO2OerBS7m5R1SIN9Y4w")
			.log().all();
	}

	@Test(priority=2)
	void getCookiesInfo() {
		Response res = 	given()
						.when()
							.get("https://www.google.com/");
							//get single cookie info
		String cookie_value = res.getCookie("AEC"); 
		System.out.println("Value of cookie is====>" + cookie_value);
	}

}
