package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.myweb.UserAddressPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;

public class TC_Add_New_Address extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserAddressPageObject addressPage;
	UserRegisterPageObject registerPage;
	UserCustomerInfoPageObject customerInfoPage;
		
	@BeforeTest
	public void beforeTest() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@BeforeClass
	public void beforeClass() {
		String emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		String password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		registerPage = homePage.clickRegisterLink();
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany(company);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
	}
	
	@Test
	public void TC_Update_Address() {
		String addressFirstName = "Automation";
		String addressLastName = "FC";
		String addressEmail = "automationnfc@gmail.com";
		String addressCompany = "Automation FBC";
		String addressCountry = "Viet Nam";
		String addressState = "Other";
		String addressCity = "Ho Chi Minh";
		String address1 = "Test 123";
		String address2 = "NTL 456";
		String addressZip = "550000";
		String addressPhoneNumber = "0123456789";
		String addressFaxNumber = "0987456123";
		
		homePage = registerPage.clickRegisterButton();
		customerInfoPage = homePage.clickMyAccountLink(driver);
		
		addressPage = customerInfoPage.openAddressPage(driver);
		addressPage.clickAddNewButton();
		addressPage.inputFirstName( addressFirstName);
		addressPage.inputLastName( addressLastName);
		addressPage.inputEmail( addressEmail);
		//addressPage.inputCompany( addressCompany);
		addressPage.selectCountry( addressCountry);
		addressPage.selectState( addressState);
		addressPage.inputCity( addressCity);
		addressPage.inputAddress1( address1);
		addressPage.inputAddress2( address2);
		addressPage.inputZip( addressZip);
		addressPage.inputPhoneNumber( addressPhoneNumber);
		//addressPage.inputFaxNumber( addressFaxNumber);
		addressPage.clickSaveButton();
		
		assertEquals(addressPage.getNameText(), addressFirstName + " "+ addressLastName);
		assertEquals(addressPage.getEmailText(), "Email: "+ addressEmail);
		assertEquals(addressPage.getPhoneText(), "Phone number: "+ addressPhoneNumber);
		//assertEquals(addressPage.getElementText("//li[@class='fax']"), "Fax number: " +addressFaxNumber);
		//assertEquals(addressPage.getElementText("//li[@class='company']"), addressCompany);
		assertEquals(addressPage.getAddress1Text(), address1);
		assertEquals(addressPage.getAddress2Text(), address2);
		assertEquals(addressPage.getCityStateText(), addressCity+", "+addressZip);
		assertEquals(addressPage.getCountryText(), addressCountry);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
