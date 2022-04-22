package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class RegisterData {
	public static RegisterData getRegisterData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			System.out.println(GlobalConstants.TEST_DATA_PATH+ "RegisterData.json");
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_DRIVEN_PATH+ "RegisterData.json"), RegisterData.class);
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
	
	@JsonProperty("company")
	private String company;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("dob")
	DateOfBirth dob;
	
	public class DateOfBirth {
		@JsonProperty("day")
		String day;
		
		@JsonProperty("month")
		String month;
		
		@JsonProperty("year")
		String year;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getCompany() {
		return company;
	}

	public String getPassword() {
		return password;
	}

	public String getDay() {
		return dob.day;
	}
	
	public String getMonth() {
		return dob.month;
	}
	
	public String getYear() {
		return dob.year;
	}
	
}
