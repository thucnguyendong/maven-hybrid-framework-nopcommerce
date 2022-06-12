package com.zippopotam.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
	
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
