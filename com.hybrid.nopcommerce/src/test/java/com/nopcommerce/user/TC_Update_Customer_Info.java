package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import reportConfig.ExtentTestManager;

public class TC_Update_Customer_Info extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	UserCustomerInfoPageObject customerInfoPage;
	UserLoginPageObject loginPage;
	String firstName = "Automation";
	String lastName = "FC";
	String company = "Automation FC";
	String day="1";
	String month="January";
	String year="1990";
	String emailAddress= "automationfc.vn@gmail.com";
		
	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage=loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
	}
	
	@Test
	public void TC_01_Update_Customer_Info(Method method) {	
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Update and save customer info");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click My Account link");
		customerInfoPage = homePage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Select female gender radio button");
		customerInfoPage.selectFemaleGender();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input first name");
		customerInfoPage.inputFirstNameTextbox(firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Input last name");
		customerInfoPage.inputLastNameTextbox(lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Input day, month and year");
		customerInfoPage.selectDayDropdown(day);
		customerInfoPage.selectMonthDropdown(month);
		customerInfoPage.selectYearDropdown(year);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Input email address");
		customerInfoPage.inputEmailTextbox(emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Input company name");
		customerInfoPage.inputCompanyTextbox(company);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Input first name");
		customerInfoPage.clickSaveButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Check that customer info are updated successfully");
		assertTrue(customerInfoPage.isFemaleSelected());
		assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
		assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
		assertEquals(customerInfoPage.getSelectedDayValue(),day);
		assertEquals(customerInfoPage.getSelectedMonthValue(),month);
		assertEquals(customerInfoPage.getSelectedYearValue(),year);
		assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);
		assertEquals(customerInfoPage.getCompanyTextboxValue(),company);
		
	}
	
	@Parameters("browser")	
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
