package com.nopcommerce.datadriven;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class TC_Register_Random_Data extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private DataHelper data;
	private String emailAddress;
	private String password;
	private String firstname;
	private String lastname;
	private String day = "5";
	private String month = "May";
	private String year = "1995";
	private String company = "Livegroup";
	
	@BeforeClass
	public void beforeClass() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		data = DataHelper.getData();
		emailAddress = data.getEmailAddress();
		password = data.getPassword();
		firstname = data.getFirstname();
		lastname = data.getLastname();
	}
	
	@Test
	public void TC_01_Register_Sucessfully() {
		log.info("Test Case 1: Register successfully");
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		log.info("Step 3: Input First Name");
		registerPage.inputFirstName(firstname);
		log.info("Step 4: Input Last Name");
		registerPage.inputLastName(lastname);
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
		registerPage.inputConfirmPassword(password);
		log.info("Step 9: Click Register button");
		registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
		log.info("Step 11: Click log out link");
		homePage =registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
		
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword(password);
		homePage=loginPage.clickLoginButton();
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser chrome");
		closeBrowserAndDriver();
	}	
}
