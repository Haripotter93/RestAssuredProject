package RestAssuredAPI;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.User;

public class SerializationDeSerialization {

	@Test
	void serialization() throws JsonProcessingException { // POJO to JSON conversion

		User user = new User();
		user.setId(1);
		user.setTitle("Demo user");
		user.setUserId(22);
		user.setBody("Test data");

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		System.out.println(jsonData);
	}

	@Test
	void deSerialization() throws JsonMappingException, JsonProcessingException { // JSON to POJO conversion
		String jsonData = "{\"id\":1,\"title\":\"Demo user\",\"body\":\"Test data\",\"userId\":22}";
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonData, User.class);
		System.out.println(user.getId());
		System.out.println(user.getTitle());
		System.out.println(user.getUserId());
		System.out.println(user.getBody());
	}

}
