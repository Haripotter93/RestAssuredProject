package APIChaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CreateUser {

	public Logger logger;
	@Test
	void createUser(ITestContext context) {
		logger = LogManager.getLogger(this.getClass());
		logger.info("-----Create User-----");
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		int id = given()
					.headers("Authorization", "Bearer " + bearerToken)
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
		System.out.println("Generated id is: " + id);
		context.setAttribute("user_id", id);
		logger.info("-----User is created-----");
	}
}
