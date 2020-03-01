package com.automation.api.utils;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.automation.api.exception.ApiException;
import com.automation.api.restassured.RestAssuredTesting;

public class Utils {

	private static final Logger LOGGER = Logger.getLogger(RestAssuredTesting.class);

	/**
	 * @return
	 * Read Payload from File
	 */
	public JSONObject readPayloadFromFile() {
		InputStream inputStream = RestAssuredTesting.class.getResourceAsStream("/com/automation/api/payload/Payload.json");
		if(inputStream == null) {
			throw new ApiException("Input payload is Empty");
		}
		JSONTokener jsonTokener = new JSONTokener(inputStream);
		JSONObject inputPayload = new JSONObject(jsonTokener);
		return inputPayload;
	}

}
