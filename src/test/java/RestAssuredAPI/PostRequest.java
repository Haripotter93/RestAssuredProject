package RestAssuredAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import POJO.User;

public class PostRequest {

	@Test
	void postRequestUsingHashMap() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("id", 1);
		data.put("title", "Updated Title");
		data.put("body", "Updated Body");
		data.put("userId", 1);

		given()
			.contentType("application/json").body(data)
		.when()
			.put("https://jsonplaceholder.typicode.com/posts/1")
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			.contentType("application/json")
			.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

	@Test
	void postRequestUsingJSONObject() {
		JSONObject data = new JSONObject();
		data.put("id", 1);
		data.put("title", "Updated Title");
		data.put("body", "Updated Body");
		data.put("userId", 1);

//		JSONObject innerData = new JSONObject();
//		innerData.put("inner title","Inner Title");
//		innerData.put("innerbody","Inner Body");
//		data.put("data",innerData);
//		String arr[] = {innerData.toString(), ""};
//		data.put("arr",arr);
//		System.out.println(data);		

		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.put("https://jsonplaceholder.typicode.com/posts/1")
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			.contentType("application/json")
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

	@Test
	void postRequestUsingJSONFile() {
		try {
			File file = new File(".\\User.json");
			FileReader fr = new FileReader(file);
			JSONTokener jt = new JSONTokener(fr);
			JSONObject data = new JSONObject(jt);

			given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.put("https://jsonplaceholder.typicode.com/posts/1")
			.then()
				.statusCode(200)
				.body("id", equalTo(1))
				.contentType("application/json")
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
		} catch (Exception e) {
			System.out.println("Error occured in reading file");
		}
	}

	@Test
	void postRequestUsingPOJO() {
		User user = new User();
		user.setId(1);
		user.setTitle("Updated Title");
		user.setBody("Updated Body");
		user.setUserId(1);
		given()
			.contentType("application/json")
			.body(user)
		.when()
			.put("https://jsonplaceholder.typicode.com/posts/1")
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			.contentType("application/json")
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

}
