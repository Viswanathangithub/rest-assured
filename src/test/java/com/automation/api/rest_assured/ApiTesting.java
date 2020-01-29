package com.automation.api.rest_assured;


import java.io.InputStream;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.Test;

import com.automation.api.exception.ApiException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Rest Assured Test
 * 
 * Test class for running Rest API testing
 *
 */
public class ApiTesting {

	private static final Logger LOGGER = Logger.getLogger(ApiTesting.class);

	@Test
	public void test_api() {
		String username = "TSMTeamAcceptance";
		String password = "password";

		JSONObject inputPayload = readPayloadFromFile();

		test_Success_Scenario(username, password, inputPayload.toString());

		username = "userName";
		test_Failure_InvalidCredentials(username, password, inputPayload.toString());
	}

	/**
	 * @return
	 * Read Payload from File
	 */
	private JSONObject readPayloadFromFile() {
		InputStream inputStream = ApiTesting.class.getResourceAsStream("/com/automation/api/payload/Payload.json");
		if(inputStream == null) {
			throw new ApiException("Input payload is Empty");
		}
		JSONTokener jsonTokener = new JSONTokener(inputStream);
		JSONObject inputPayload = new JSONObject(jsonTokener);
		return inputPayload;
	}

	/**
	 * @param username
	 * @param password
	 * Success Scenario Test Case
	 */
	public void test_Success_Scenario(String username, String password, String payload) {
		String restAPIUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/invitation";
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(payload);
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		Response response = RestAssured.given().authentication().preemptive().basic(username, password).spec(requestSpec).when().post(restAPIUrl);
		JSONObject jsonObject = new JSONObject(response.body().asString());

		LOGGER.info("Value of Code: "+jsonObject.get("code"));
		
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.body().asString());
		Assert.assertEquals(jsonObject.get("serviceProvider"), "TSMTeamAcceptance");
	}

	/**
	 * @param userName
	 * @param password
	 * Test Case with incorrect credentials
	 */
	public void test_Failure_InvalidCredentials(String userName, String password, String payload) {
		String restAPIUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/invitation";
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(payload);
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		Response response = RestAssured.given().authentication().preemptive().basic(userName, password).spec(requestSpec).when().post(restAPIUrl);

		Assert.assertEquals(response.body().asString(), "Credentials are required to access this resource.");
	}
}