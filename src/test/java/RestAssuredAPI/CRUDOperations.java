package RestAssuredAPI;

import org.testng.annotations.Test;
import static io.restassured. RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class CRUDOperations {
	
	@Test
	void getRequestWithAllData() {
		given ()
		.when ()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode (200)
		.log().all();
	}
	
	@Test
	void getRequestWithOneData() {
		given ()
		.when ()
		.get("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test
	void postRequest() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("title", "foo");
		data.put("body", "bar");
		data.put("userId", 1);

		given ()
		.body(data)
		.when ()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.body("id", equalTo(101))
		.log().all();
	}
	
	@Test
	void putRequest() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("id", 1);
		data.put("title", "Updated Title");
		data.put("body", "Updated Body");
		data.put("userId", 1);

		given ()
		.contentType("application/json")
		.body(data)
		.when ()
		.put("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.statusCode(200)
		.body("id", equalTo(1))
		.contentType("application/json")
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}

	@Test
	void patchRequest() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("title", "Patched Title");

		given ()
		.contentType("application/json")
		.body(data)
		.when ()
		.patch("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.statusCode(200)
		.contentType("application/json")
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	@Test
	void deleteRequest() {
		given ()
		.when ()
		.delete("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.statusCode(200);
	}

}
