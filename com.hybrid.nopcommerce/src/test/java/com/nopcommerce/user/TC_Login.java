package com.nopcommerce.user;

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
import reportConfig.ExtentTestManager;

public class TC_Login extends BaseTest {
	private WebDriver driver;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);		
	}
	
	@Test
	public void TC_01_Login_Empty(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Login with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Login Button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Verify error");
		verifyEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}
	
	@Test
	public void TC_02_Login_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Login with invalid email");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2:Input invalid email");
		loginPage.inputEmail("Test");
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Login Button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Verify error");
		verifyEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void TC_03_Login_Not_Registered_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Login with email not registered");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2:Input email not registered");
		loginPage.inputEmail("Testing123@yopmail.com");
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Login Button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Verify error");
		verifyEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"No customer account found");
	}
	
	@Test
	public void TC_04_Login_With_Empty_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Login with empty password");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2:Input valid email");
		loginPage.inputEmail(GlobalConstants.nopcommerce_Email);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Login Button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Verify error");
		verifyEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");	
	}
	
	@Test
	public void TC_05_Login_With_Wrong_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Login with wrong password");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2:Input valid email");
		loginPage.inputEmail(GlobalConstants.nopcommerce_Email);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input incorrect password");
		loginPage.inputPassword("123457");
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Login Button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Verify error");
		verifyEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_Login_Successfully(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 6: Login successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2:Input valid email");
		loginPage.inputEmail(GlobalConstants.nopcommerce_Email);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input valid password");
		loginPage.inputPassword(GlobalConstants.nopcommerce_Password);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Login Button");
		homePage=loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Verify My Account");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}	
}
