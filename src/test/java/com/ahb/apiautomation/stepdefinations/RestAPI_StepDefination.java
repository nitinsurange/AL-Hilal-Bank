package com.ahb.apiautomation.stepdefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.json.simple.JSONObject;

import com.ahb.mobileautomation.pages.BasePage;
import com.ahb.mobileautomation.utils.ConfigReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;

public class RestAPI_StepDefination extends BasePage {


	
	
		@Given("^ReqRes service api is up and running$")
	public void reqres_service_api_is_up_and_running() throws Throwable {
	  
			System.out.println("URI::"+ConfigReader.URI);
			_REQUEST_SPEC = given().baseUri(ConfigReader.URI);
	}
	
	@SuppressWarnings("unchecked")
	@Then("^Hit Create User Post API$")
	public void hit_Create_User_Post_API() throws Throwable {
	 
		_REQUEST_SPEC = given().baseUri(ConfigReader.URI);
			System.out.println("Base URL::"+ConfigReader.URI);
			
		JSONObject body_json = new JSONObject();
		
		body_json.put("name", "Johnnn");
		body_json.put("job", "ITTT");

        System.out.println("Json request Body is::"+body_json);

		_RESP = _REQUEST_SPEC.body(body_json).when().post("api/users");


        responsebody();
	}
	
	
	@Then("^Verify the Response Code (\\d+)$")
	public void verify_the_Response_Code(int int1) throws Throwable {
		_RESP.then().assertThat().statusCode(int1);
		System.out.println("Status code appearing as: " + int1);
	}


	
	@Then("^Verify the ID returened from the response$")
	public void verify_the_ID_returened_from_the_response() throws Throwable {
		
		String idNumber = _RESP.jsonPath().get("id");
		
		System.out.println("ID Number::"+idNumber);
	}
	
	@SuppressWarnings("unchecked")
	@Then("^Hit Register Post API$")
	public void hit_Register_Post_API() throws Throwable {
	 
		_REQUEST_SPEC = given().baseUri(ConfigReader.URI);
		System.out.println("Base URL::"+ConfigReader.URI);
		
	JSONObject body_json = new JSONObject();
	
	body_json.put("email", "testJohn@gmail.com");

    System.out.println("Json request Body is::"+body_json);

	_RESP = _REQUEST_SPEC.body(body_json).when().post("api/register");


    responsebody();
	}
	
	@Then("^Verify error message returned from the response$")
	public void verify_error_message_returned_from_the_response() throws Throwable {

		
	}
		
	@Then("^Verify error message returned from the response \"([^\"]*)\"$")
	public void verify_error_message_returned_from_the_response(String errorMsg) throws Throwable {
		
		_RESP.then().assertThat().body("error", equalTo(errorMsg));
	}

	
	
	@Then("^fetch the response from GET API$")
	public void fetch_the_response_from_GET_API() throws Throwable {
	  
		_REQUEST_SPEC = given().baseUri(ConfigReader.URI);
		_RESP = _REQUEST_SPEC.log().all().when().get("/api/unknown/2");
		
		 responsebody();
	}

	@Then("^Verify response json schema$")
	public void verify_response_json_schema() throws Throwable {
		given().baseUri(ConfigReader.URI).log().all().when().get("/api/unknown/2").then().body(JsonSchemaValidator.matchesJsonSchema(new File("./schemas/jsonSchema.json")));
	}
}
