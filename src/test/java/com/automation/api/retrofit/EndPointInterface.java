package com.automation.api.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EndPointInterface {

	@POST("mobile-keys-management/invitation")
	Call<Response> invokeApi(@Body Request request);

}
