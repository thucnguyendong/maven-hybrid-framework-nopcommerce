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
import pageObjects.nopcommerce.portal.myweb.UserChangePasswordPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;

public class TC_Change_Password extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserCustomerInfoPageObject customerInfoPage;
	UserChangePasswordPageObject changePasswordPage;
	UserRegisterPageObject registerPage;
	UserLoginPageObject loginPage;
	String password;
	String emailAddress;
	
	String projectPath = System.getProperty("user.dir");
	
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
		homePage=registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void TC_01_Change_Password() {
		String newPassword = "123457";
		
		customerInfoPage =homePage.clickMyAccountLink(driver);
		changePasswordPage= customerInfoPage.openChangePasswordPage(driver);
		changePasswordPage.inputOldPassword(password);
		changePasswordPage.inputNewPassword(newPassword);
		changePasswordPage.inputConfirmPassword(newPassword);
		changePasswordPage.clickChangePassword();
		assertEquals(changePasswordPage.getSuccessMessage(), "Password was changed");
		
		changePasswordPage.closeSuccessPopUp();
		homePage = changePasswordPage.clickUserLogOutLink(driver);
		
		loginPage = homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword(password);
		loginPage.clickLoginButton();
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");
		
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword(newPassword);
		loginPage.clickLoginButton();
		customerInfoPage = loginPage.clickMyAccountLink(driver);
		assertTrue(customerInfoPage.getMyAccountPageTitle().contains("My account - Customer info"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
