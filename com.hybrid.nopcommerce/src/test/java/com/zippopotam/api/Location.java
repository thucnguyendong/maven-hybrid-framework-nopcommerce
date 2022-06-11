package com.zippopotam.api;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Location {
	public static Location getLocationData(String jsonPath) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);			
			return mapper.readValue(jsonPath, Location.class);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Place> getListPlaces (String jsonPath) {
		List<Place> places = new ArrayList<Place>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(jsonPath);
			ArrayNode responseArray = (ArrayNode) jsonNode.get("places");
			places = mapper.convertValue(responseArray, new TypeReference<List<Place>>(){});
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return places;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public String getCountry() {
		return country;
	}

	public String getCountryAbbreviation() {
		return countryAbbreviation;
	}
	
	
	@JsonProperty("post code")
	private String postCode;
	@JsonProperty("country")
	private String country;
	@JsonProperty("country abbreviation")
	private String countryAbbreviation;
	
}
