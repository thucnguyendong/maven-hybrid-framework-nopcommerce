package com.zippopotam.api;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;


import io.restassured.specification.RequestSpecification;
import utilities.RestAPIHelper;

public class TC_Check_Response_Value {
	@Test
	public void TC_01_Check_Status_Code_200() {
		RequestSpecification httpRequest = RestAPIHelper.initAPI().createRequestSpecification();
		String responseJson = RestAPIHelper.initAPI().runGetMethod(httpRequest, "http://api.zippopotam.us/us/90210").getBody().asString();
		Location location = Location.getLocationData(responseJson);
		List<Place> places = location.getListPlaces(responseJson);
		assertEquals(places.get(0).getPlaceName(),"Beverly Hills");
	}
}
