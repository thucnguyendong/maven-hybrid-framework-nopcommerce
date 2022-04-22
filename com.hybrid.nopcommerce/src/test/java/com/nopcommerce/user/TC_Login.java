package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;

public class TC_Login extends BaseTest {
	private WebDriver driver;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private String emailAddress;
	private String password;
		
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@BeforeClass
	public void beforeClass() {		
		emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		UserRegisterPageObject registerPage = homePage.clickRegisterLink();
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany (company);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
		registerPage.clickRegisterButton();
		homePage = registerPage.clickLogOutLink();
	}
	
	@Test
	public void TC_01_Login_Empty() {
		loginPage = homePage.clickLogInLink();
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail("Test");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void TC_03_Login_Not_Registered_Email() {
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail("Testing123@yopmail.com");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"No customer account found");
	}
	
	@Test
	public void TC_04_Login_With_Empty_Password() {
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.clickLoginButton();
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");	
	}
	
	@Test
	public void TC_05_Login_With_Wrong_Password() {
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword("123457");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_Login_Successfully() {
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword(password);
		homePage=loginPage.clickLoginButton();
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
