package APIChaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;

public class UpdateUser {

	@Test
	void updateUser(ITestContext iContext) {
		Faker faker=new Faker();
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress()); data.put("status", "active");
		String bearerToken="c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		int id = (Integer) iContext.getAttribute("user_id");
		given ()
			.headers("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode (200)
			.log().all();
	}
}
