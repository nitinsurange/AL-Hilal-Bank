@apiRegression
Feature: Rest API Scenarios for ReqRes scenarios


 @createUserApi
Scenario:  Perfoms Create User functionality
 
	Given ReqRes service api is up and running 
	Then Hit Create User Post API
	Then Verify the Response Code 201
	And Verify the ID returened from the response
 
	
   @registerApi400error
Scenario:  Perfom Register API without mandatory fields check response code 400
 
	Given ReqRes service api is up and running 
	Then Hit Register Post API
	Then Verify the Response Code 400
	And Verify error message returned from the response "Missing email or username"
 
	
	@getAPIjsonschemavalidation
Scenario:  verify json schema for GET API
 
	Given ReqRes service api is up and running 
	Then fetch the response from GET API
	Then Verify the Response Code 200
	And Verify response json schema 