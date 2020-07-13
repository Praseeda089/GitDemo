package stepDefinitions;

import static io.restassured.RestAssured.given;

//import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resouces.TestDataBuild;
import resouces.Utils;

public class StepDefinition extends Utils {
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		 res = given().spec(requestSpecification())
				 .body(data.addPlacePayload(name,language,address)); // to break the given when then

	    // Write code here that turns the phrase above into concrete actions
	   
	}
	

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String string2) {
		 response = res.when().post("maps/api/place/add/json")
				.then().spec(resspec).extract().response();

	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("The API call is success with status code {string}")
	public void the_API_call_is_success_with_status_code(String string) {
		assertEquals(response.getStatusCode(),200);
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		String resp =response.asString();
		JsonPath js= new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(),Expectedvalue);
	    
	}



}
