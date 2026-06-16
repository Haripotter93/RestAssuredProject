package RestAssuredAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateJSONResponse {

	@Test
	void validateJSONResponse() {
		// Approach 1
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://jsonplaceholder.typicode.com/posts")
		.then()
			.header("Content-Type", "application/json; charset=utf-8")
			.body("[3].title", equalTo("eum et est occaecati"));

		// Approach 2
		Response res = given().when().get("https://jsonplaceholder.typicode.com/posts");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String title = res.jsonPath().get("[3].title").toString();
		Assert.assertEquals(title, "eum et est occaecati");

		// Using JSONArray
		JSONArray arr = new JSONArray(res.getBody().asString());
		for (int i = 0; i < arr.length(); i++) {
			String titleInfo = arr.getJSONObject(i).getString("title");
			System.out.println(titleInfo);
		}

		// Using List
		List<String> titles = res.jsonPath().getList("title");
		for (String titleInfo : titles) {
			System.out.println(titleInfo);
		}

		// validate id total
		JSONArray ja = new JSONArray(res.getBody().asString());
		double total = 0;
		for (int i = 0; i < ja.length(); i++) {
			String price = ja.getJSONObject(i).get("id").toString();
			total = total + Double.parseDouble(price);
		}
		System.out.println("total is: " + total);

		// Validate title is present in JSON
		boolean status = false;
		for (int i = 0; i < ja.length(); i++) {
			String title1 = ja.getJSONObject(i).get("title").toString();
			if (title1.equals("The Lord of the Rings")) {
				{
					status = true;
					break;
				}
			}
		}
		System.out.println(status);
	}
}
