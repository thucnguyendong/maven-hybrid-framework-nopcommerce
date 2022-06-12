package com.nopcommerce.commons;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class Common_RegisterToSystem extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	public static Set<Cookie> loginPageCookies;
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
	@BeforeTest
	public void beforeClass(String browserName) {
		log.info("Pre-condition: Open browser "+browserName+" and navigate to "+ GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver(browserName,GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
		emailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";
		
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
		homePage=registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
		
		loginPageCookies = homePage.getAllCookies(driver);
		GlobalConstants.nopcommerce_Email = emailAddress;
		GlobalConstants.nopcommerce_Password = password;
		closeBrowserAndDriver();
	}
}

