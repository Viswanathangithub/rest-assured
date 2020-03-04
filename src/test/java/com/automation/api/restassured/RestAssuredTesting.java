package com.automation.api.restassured;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.automation.api.utils.Utils;

import io.restassured.response.Response;

/**
 * @author Rest Assured Test
 * 
 * Test class for running Rest API testing
 *
 */
public class RestAssuredTesting {

	private static final Logger LOGGER = Logger.getLogger(RestAssuredTesting.class);

	RestAssuredServiceImpl restAssuredServiceImpl = new RestAssuredServiceImpl();

	Utils utils = new Utils();

	// 1st API - Success login
	@Test
	public void test_Loginapi_Success() {
		JSONObject inputPayload = utils.readPayloadFromFile("loginPayload");
		JSONObject jsonObject = restAssuredServiceImpl.test_Success_Scenario(inputPayload.toString());
		Assert.assertEquals(jsonObject.get("serviceProvider"), "TSMTeamAcceptance");
	}

	// 2nd API - Failure login
	@Test
	public void test_Loginapi_Failure() {
		JSONObject inputPayload = utils.readPayloadFromFile("loginPayload");
		Response response = restAssuredServiceImpl.test_Failure_InvalidCredentials(inputPayload.toString());
		Assert.assertEquals(response.body().asString(), "Credentials are required to access this resource.");
	}

	// 3rd API - send invitation code
	@Test
	public void testInvitation() {
		JSONObject inputPayload = utils.readPayloadFromFile("loginPayload");
		JSONObject jsonObject = restAssuredServiceImpl.test_Success_Scenario(inputPayload.toString());
		String invitationCode = jsonObject.get("code").toString();
		LOGGER.info("Invitation Code "+invitationCode);
		Response response = restAssuredServiceImpl.sendInvitationCode(invitationCode);
		LOGGER.info(response.body().asString());
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	// 4th API - Issue Credential
	@Test
	public void testIssueCredential() {
		JSONObject issueCredentialPayload = utils.readPayloadFromFile("issueCredentialPayload");
		//Replace the endpoint here with the right value
		String updatedPayload = issueCredentialPayload.toString().replace("_dynamic", "500710741");
		Response response = restAssuredServiceImpl.issueCredential(updatedPayload);
		LOGGER.info(response.body().asString());
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}