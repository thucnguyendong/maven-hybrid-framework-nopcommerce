package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserInformationData {
	public static UserInformationData getUserInformation(String jsonFileName) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_PATH+ jsonFileName), UserInformationData.class);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("dob")
	private String dob;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("address")
	private String address;
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}
	
	@JsonProperty("cc")
	CardInformation cardInfo;
	
	public class CardInformation{
		@JsonProperty("number")
		String number;
		
		@JsonProperty("billing")
		String billing;
	}
	
	public String getCardNumber() {
		return cardInfo.number;
	}
}
