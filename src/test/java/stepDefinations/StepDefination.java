package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO_Serialization.AddPlace;
import POJO_Serialization.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	ResponseSpecification responseSpec;
	RequestSpecification request;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		 request = given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
	}
	
	
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		//Constructor will be call with value of resource which we pass
		
		APIResources resouceAPI = APIResources.valueOf(resource);
		System.out.println(resouceAPI.getResource());
		
		 responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		 if(method.equalsIgnoreCase("POST"))
			 response  = request.when().post(resouceAPI.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response  = request.when().get(resouceAPI.getResource());
	}
			
	
	
	@Then("the API call got Success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(response.getStatusCode(),200);
		


	}
	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String KeyValue, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions

		
		assertEquals(getJsonPath(response, KeyValue),ExpectedValue);
			

}

	
	@Then("place_id created maps to {string} using {string}")
	public void place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_call_with_http_request(resource, "GET");
		String actual_Name = getJsonPath(response, "name");
		assertEquals(actual_Name, expectedName);
		
	    
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		request = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
