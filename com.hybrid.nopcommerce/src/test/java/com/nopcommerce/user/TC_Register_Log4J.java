package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class TC_Register_Log4J extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String emailAddress;
	private String firstName = "Thuc";
	private String lastName= "Nguyen";
	private String company = "Livegroup";
	private String password = "123456";
	private String confirmPassword= "123456";
	private String day = "5";
	private String month = "May";
	private String year = "1995";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition: Open browser "+browserName+" and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		emailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		log.info("Test Case 1: Register with empty data");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Click Register Button");
		registerPage.clickRegisterButton();
		log.info("Step 3: Verify all fields and error");
		assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		log.info("Test Case 2: Register with invalid email");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Click input invalid email format");
		registerPage.inputEmail("Test123");
		log.info("Step 3: Click Register Button");
		registerPage.clickRegisterButton();
		log.info("Step 4: Verify email field");
		assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Sucessfully() {
		log.info("Test Case 3: Register successfully");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		log.info("Step 3: Input First Name");
		registerPage.inputFirstName(firstName);
		log.info("Step 4: Input Last Name");
		registerPage.inputLastName(lastName);
		log.info("Step 5: Select Birthday Date: day, month, year");
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		log.info("Step 5: Input company name");
		registerPage.inputCompany(company);
		log.info("Step 6: Input email address");
		registerPage.inputEmail(emailAddress);
		log.info("Step 7: Input password");
		registerPage.inputPassword(password);
		log.info("Step 8: Input confirm password");
		registerPage.inputConfirmPassword(confirmPassword);
		log.info("Step 9: Click Register button");
		registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		log.info("Step 11: Click log out link");
		homePage =registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		log.info("Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}	
}
