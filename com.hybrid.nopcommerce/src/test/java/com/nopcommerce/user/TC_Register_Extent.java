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
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class TC_Register_Extent extends BaseTest {
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
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";
	}
	
	@Test
	public void TC_01_Register_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Register with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Register Button");
		registerPage.clickRegisterButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Verify all fields and error");
		assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}
	
	
	@Test
	public void TC_02_Register_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Register with invalid email");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click input invalid email format");
		registerPage.inputEmail("Test123");
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Register Button");
		registerPage.clickRegisterButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Verify email field");
		assertEquals(registerPage.getEmailErrorMessage(), "Wrong email 1");
	}
	
	@Test
	public void TC_03_Register_Sucessfully(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Register successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input First Name");
		registerPage.inputFirstName(firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Input Last Name");
		registerPage.inputLastName(lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select Birthday Date: day, month, year");
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Input company name");
		registerPage.inputCompany(company);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Input email address");
		registerPage.inputEmail(emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Input password");
		registerPage.inputPassword(password);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Input confirm password");
		registerPage.inputConfirmPassword(confirmPassword);
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Click Register button");
		registerPage.clickRegisterButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify register's success message");
		assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click log out link");
		homePage =registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
	}
	
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}	
	
}
