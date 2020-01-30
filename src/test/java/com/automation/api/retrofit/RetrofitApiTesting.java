package com.automation.api.retrofit;

import java.io.IOException;

import org.junit.Test;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiTesting {

	@Test
	public void successTest() throws IOException {
		String baseUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/";

		OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
			
			public Response intercept(Chain chain) throws IOException {
				okhttp3.Request request = chain.request();
				Builder authHeader = request.newBuilder().addHeader("Authorization", Credentials.basic("TSMTeamAcceptance", "password"));
				return chain.proceed(authHeader.build());
			}
		});
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.client(httpClient.build())
				.build();

		EndPointInterface endPoint = retrofit.create(EndPointInterface.class);
		Call<com.automation.api.retrofit.Response> response = endPoint.invokeApi(new Request("AAMK", "P1D"));

		retrofit2.Response<com.automation.api.retrofit.Response> apiResponse = response.execute();
		System.out.println("Response "+apiResponse.body().toString());
	}
}
