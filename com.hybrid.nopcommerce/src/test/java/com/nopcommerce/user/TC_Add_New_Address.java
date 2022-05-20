package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.myweb.UserAddressPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import reportConfig.ExtentTestManager;

public class TC_Add_New_Address extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserAddressPageObject addressPage;
	UserRegisterPageObject registerPage;
	UserCustomerInfoPageObject customerInfoPage;
	UserLoginPageObject loginPage;
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
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage= loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
	}
	
	@Test
	public void TC_Update_Address(Method method) {	
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Update and save new address");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click My Account link");		
		customerInfoPage = homePage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Navigate to Address page");
		addressPage = customerInfoPage.openAddressPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Add New button");
		addressPage.clickAddNewButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Input Address First Name");
		addressPage.inputFirstNameTextbox( addressFirstName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Input Address Last Name");
		addressPage.inputLastNameTextbox( addressLastName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Input Address Email");
		addressPage.inputEmailTextbox( addressEmail);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Select Country");
		addressPage.selectCountryDropdown( addressCountry);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Select State");
		addressPage.selectStateDropdown( addressState);
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Input City");
		addressPage.inputCityTextbox( addressCity);
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Input Address 1");
		addressPage.inputAddress1Textbox( address1);
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Input Address 2");
		addressPage.inputAddress2Textbox( address2);
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input Zip code");
		addressPage.inputZipTextbox( addressZip);
		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Input Phone Number");
		addressPage.inputPhoneNumberTextbox( addressPhoneNumber);
		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click Save button");
		addressPage.clickSaveButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Check error");
		assertEquals(addressPage.getNameText(), addressFirstName + " "+ addressLastName);
		assertEquals(addressPage.getEmailText(), "Email: "+ addressEmail);
		assertEquals(addressPage.getPhoneText(), "Phone number: "+ addressPhoneNumber);
		assertEquals(addressPage.getAddress1Text(), address1);
		assertEquals(addressPage.getAddress2Text(), address2);
		assertEquals(addressPage.getCityStateText(), addressCity+", "+addressZip);
		assertEquals(addressPage.getCountryText(), addressCountry);
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();	
	}
}
