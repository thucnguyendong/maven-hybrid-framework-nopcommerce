package com.nopcommerce.datadriven;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.data.RegisterData;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class TC_Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String emailAddress;
	private RegisterData registerData;
	
	@BeforeClass
	public void beforeClass() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		emailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";
		registerData = RegisterData.getRegisterData();
	}
	
	@Test
	public void TC_01_Register_Sucessfully() {
		log.info("Test Case 1: Register successfully");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		log.info("Step 3: Input First Name");
		registerPage.inputFirstName(registerData.getFirstname());
		log.info("Step 4: Input Last Name");
		registerPage.inputLastName(registerData.getLastname());
		log.info("Step 5: Select Birthday Date: day, month, year");
		registerPage.selectDay(registerData.getDay());
		registerPage.selectMonth(registerData.getMonth());
		registerPage.selectYear(registerData.getYear());
		log.info("Step 5: Input company name");
		registerPage.inputCompany(registerData.getCompany());
		log.info("Step 6: Input email address");
		registerPage.inputEmail(emailAddress);
		log.info("Step 7: Input password");
		registerPage.inputPassword(registerData.getPassword());
		log.info("Step 8: Input confirm password");
		registerPage.inputConfirmPassword(registerData.getPassword());
		log.info("Step 9: Click Register button");
		registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
		log.info("Step 11: Click log out link");
		homePage =registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser chrome");
		closeBrowserAndDriver();
	}	
}
