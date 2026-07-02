package RestAssuredAPI;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FileUpload {

	//@Test
	void singleFileUpload() {
		File myfile = new File("C:\\Users\\hariharan.a_inapp\\Downloads\\Javascript.docx");
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode (200)
			.body("fileName", equalTo("Javascript.docx"))
			.log().all();
	}
	
	//@Test
	void multipleFilesUpload() {
		File myfile1 = new File("C:\\Users\\hariharan.a_inapp\\Downloads\\Javascript.docx");
		File myfile2 = new File("C:\\Users\\hariharan.a_inapp\\Downloads\\Javascript1.docx");
		given()
			.multiPart("files", myfile1)
			.multiPart("files", myfile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode (200)
			.body("[0].fileName", equalTo("Javascript.docx"))
			.body("[1].fileName", equalTo("Javascript1.docx"))
			.log().all();
	}
	
	@Test
	void fileDownload() {
		File myfile = new File("C:\\Users\\hariharan.a_inapp\\Downloads\\Javascript.docx");
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode (200)
			.body("fileName", equalTo("Javascript.docx"))
			.log().all();
		
		given()
		.when()
			.get("http://localhost:8080/downloadFile/Javascript.docx")
		.then()
			.statusCode(200)
			.log().body();
	}

}
