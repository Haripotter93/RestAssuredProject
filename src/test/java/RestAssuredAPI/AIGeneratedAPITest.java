package RestAssuredAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AIGeneratedAPITest {

	@Test
	public void getUsersPage2_shouldReturnPage2AndNonEmptyData() {
		Response response = RestAssured
				.given()
				.when()
				.get("https://reqres.in/api/users?page=2");

		response.then().log().all();

		// Validate status code
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

		// Validate page = 2
		int page = response.jsonPath().getInt("page");
		Assert.assertEquals(page, 2, "Response 'page' should be 2");

		// Validate data array is not empty
		List<?> data = response.jsonPath().getList("data");
		Assert.assertNotNull(data, "Data list should not be null");
		Assert.assertTrue(data.size() > 0, "Data array should not be empty");
	}

}
