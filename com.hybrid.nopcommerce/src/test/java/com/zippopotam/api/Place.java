package com.zippopotam.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Place {
	
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
	
	public String getPlaceName() {
		return placeName;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public String getState() {
		return state;
	}
	public String getStateAbbreviation() {
		return stateAbbreviation;
	}
	public String getLatitude() {
		return latitude;
	}
	
	@JsonProperty("place name")
	private String placeName;
	@JsonProperty("longitude")
	private String longtitude;
	@JsonProperty("state")
	private String state;
	@JsonProperty("state abbreviation")
	private String stateAbbreviation;
	@JsonProperty("latitude")
	private String latitude;
}
