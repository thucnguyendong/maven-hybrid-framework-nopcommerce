package com.zippopotam.api;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestAPIHelper;

public class TC_Check_Response {
	
	@Test
	public void TC_01_Check_Status_Code_200() {
		RequestSpecification httpRequest = RestAPIHelper.initAPI().createRequestSpecification();
		Response res= RestAPIHelper.initAPI().runGetMethod(httpRequest, "http://api.zippopotam.us/us/90210");
		assertEquals(RestAPIHelper.initAPI().getStatusCode(res), 200);
	}
	
	@Test
	public void TC_02_Check_Status_Code_404() {
		RequestSpecification httpRequest = RestAPIHelper.initAPI().createRequestSpecification();
		Response res= RestAPIHelper.initAPI().runGetMethod(httpRequest, "http://api.zippopotam.us/us/100000");
		assertEquals(RestAPIHelper.initAPI().getStatusCode(res), 404);
	}
}
