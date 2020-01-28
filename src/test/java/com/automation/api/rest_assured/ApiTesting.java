package com.automation.api.rest_assured;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTesting {

	@Test
	public void test_api() {

		String username = "TSMTeamAcceptance";
		String password = "password";

		test_Success_Scenario(username, password);

		username = "userName";
		test_Failure_InvalidCredentials(username, password);
	}

	/**
	 * @param username
	 * @param password
	 * Success Scenario Test Case
	 */
	public void test_Success_Scenario(String username, String password) {

		String restAPIUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/invitation";
		String apiBody = "{\"mobileApplication\":\"AAMK\",\"validityDuration\":\"P1D\"}";
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiBody);
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		Response response = RestAssured.given().authentication().preemptive().basic(username, password).spec(requestSpec).when().post(restAPIUrl);

		String jsonString = response.body().asString();

		JSONObject jsonObject = new JSONObject(jsonString);

		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.body().asString());
		Assert.assertEquals(jsonObject.get("serviceProvider"), "TSMTeamAcceptance");
	}

	/**
	 * @param userName
	 * @param password
	 * Test Case with incorrect credentials
	 */
	public void test_Failure_InvalidCredentials(String userName, String password) {

		String restAPIUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/invitation";
		String apiBody = "{\"mobileApplication\":\"AAMK\",\"validityDuration\":\"P1D\"}";
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(apiBody);
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		Response response = RestAssured.given().authentication().preemptive().basic(userName, password).spec(requestSpec).when().post(restAPIUrl);

		String jsonString = response.body().asString();

		Assert.assertEquals(jsonString, "Credentials are required to access this resource.");
	}
}