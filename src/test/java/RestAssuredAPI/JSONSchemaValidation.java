package RestAssuredAPI;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured. RestAssured.*;

public class JSONSchemaValidation {

	@Test
	void validateJSONSchema() { // Schema validation is done to validate the data types of data in json
		given ()
		.when ()
			.get("https://jsonplaceholder.typicode.com/posts")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchema.json"));
	}

}
