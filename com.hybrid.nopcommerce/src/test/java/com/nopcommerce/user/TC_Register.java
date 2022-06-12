package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class TC_Register extends BaseTest {
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
	
	@BeforeClass
	public void beforeClass() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
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
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		verifyEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		verifyEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		verifyEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
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
		verifyEquals(registerPage.getEmailErrorMessage(), "Wrong email");
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
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
		log.info("Step 11: Click log out link");
		homePage =registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
	}
	
	@Test
	public void TC_04_Register_Existed_Email() {
		log.info("Test Case 4: Register with existed email");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Input first name");
		registerPage.inputFirstName(firstName);
		log.info("Step 3: Input last name");
		registerPage.inputLastName(lastName);
		log.info("Step 4: Input email");
		registerPage.inputEmail(emailAddress);
		log.info("Step 5: Input password");
		registerPage.inputPassword(password);
		log.info("Step 6: Input confirm password");
		registerPage.inputConfirmPassword(confirmPassword);
		log.info("Step 7: Click register button");
		registerPage.clickRegisterButton();
		log.info("Step 8: Verify error message");
		verifyEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Password_Less_Than_6() {
		log.info("Test Case 5: Register with password less than 6");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Input first name");
		registerPage.inputFirstName(firstName);
		log.info("Step 3: Input last name");
		registerPage.inputLastName(lastName);
		log.info("Step 4: Input email");
		registerPage.inputEmail(emailAddress);
		log.info("Step 5: Input password");
		registerPage.inputPassword("12345");
		log.info("Step 6: Input confirm password");
		registerPage.inputConfirmPassword("12345");
		log.info("Step 7: Click register button");
		registerPage.clickRegisterButton();
		log.info("Step 8: Verify error message");
		verifyEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:"+"\n"+"must have at least 6 characters");
	}
	
	@Test
	public void TC_06_Incorrect_Confirm_Password() {
		log.info("Test Case 6: Register with incorrect confirm password");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Input first name");
		registerPage.inputFirstName(firstName);
		log.info("Step 3: Input last name");
		registerPage.inputLastName(lastName);
		log.info("Step 4: Input email");
		registerPage.inputEmail(emailAddress);
		log.info("Step 5: Input password");
		registerPage.inputPassword(password);
		log.info("Step 6: Input confirm password");
		registerPage.inputConfirmPassword("12345");
		log.info("Step 7: Click register button");
		registerPage.clickRegisterButton();
		log.info("Step 8: Verify error message");
		verifyEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser chrome");
		closeBrowserAndDriver();
	}	
}
