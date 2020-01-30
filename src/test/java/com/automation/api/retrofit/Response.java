package com.automation.api.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

	@SerializedName("code")
	@Expose
	private String code;

	@Override
	public String toString() {
		return "Response [code=" + code + "]";
	}


}
