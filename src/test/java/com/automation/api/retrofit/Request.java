package com.automation.api.retrofit;

public class Request {

	private String mobileApplication;
	private String validityDuration;
	
	public Request(String mobileApplication, String validityDuration) {
		this.mobileApplication = mobileApplication;
		this.validityDuration = validityDuration;
	}
}
