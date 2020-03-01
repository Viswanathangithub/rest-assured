package com.automation.api.restassured;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.automation.api.model.LoginRequest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(RestAssuredTesting.class);

	LoginRequest loginRequest = new LoginRequest();

	/**
	 * @param username
	 * @param password
	 * @param payload
	 * @return
	 */

	public JSONObject test_Success_Scenario(String payload) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(payload);
		builder.setContentType("application/json; charset=UTF-8");
		RequestSpecification requestSpec = builder.build();
		Response response = RestAssured.given().authentication().preemptive().basic(loginRequest.getUsername(), loginRequest.getPassword()).spec(requestSpec).when().post(loginRequest.getLoginUrl());
		JSONObject jsonObject = new JSONObject(response.body().asString());
		LOGGER.info("Value of Code: "+jsonObject.get("code"));
		return jsonObject;
	}

	/**
	 * @param payload
	 * @return
	 */
	public Response test_Failure_InvalidCredentials(String payload) {
		loginRequest.setUsername("userName");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(payload);
		builder.setContentType("application/json; charset=UTF-8");
		RequestSpecification requestSpec = builder.build();
		Response response = RestAssured.given().authentication().preemptive().basic(loginRequest.getUsername(), loginRequest.getPassword()).spec(requestSpec).when().post(loginRequest.getLoginUrl());
		return response;
	}
	
	/**
	 * @param invitationCode
	 * @return
	 */
	public Response sendInvitationCode(String invitationCode) {
		return RestAssured.given().auth().basic(loginRequest.getUsername(), loginRequest.getPassword()).when().get(loginRequest.getLoginUrl()+"/code/"+invitationCode);
	}
}
