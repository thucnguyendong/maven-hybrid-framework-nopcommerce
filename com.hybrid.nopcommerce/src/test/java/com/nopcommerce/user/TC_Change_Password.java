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
import pageObjects.nopcommerce.portal.myweb.UserChangePasswordPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import reportConfig.ExtentTestManager;

public class TC_Change_Password extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserCustomerInfoPageObject customerInfoPage;
	UserChangePasswordPageObject changePasswordPage;
	UserRegisterPageObject registerPage;
	UserLoginPageObject loginPage;
	String newPassword = "123457";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage= loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
	}
		
	@Test
	public void TC_01_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Update and save new password");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click My Account link");	
		customerInfoPage =homePage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Navigate to Change Password page");	
		changePasswordPage= customerInfoPage.openChangePasswordPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input old password");
		changePasswordPage.inputOldPasswordTextbox(GlobalConstants.nopcommerce_Password);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Input new password");
		changePasswordPage.inputNewPasswordTextbox(newPassword);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Input new password in 'Confirm Password'");
		changePasswordPage.inputConfirmPasswordTextbox(newPassword);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Click Change Password button");
		changePasswordPage.clickChangePasswordButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Check 'Change Password' success message");
		assertEquals(changePasswordPage.getSuccessMessage(), "Password was changed");
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Set global password Close pop-up and log out");
		GlobalConstants.nopcommerce_Password = newPassword;
		changePasswordPage.closeSuccessPopUp();
		homePage = changePasswordPage.clickUserLogOutLink(driver);
	}
	
	@Test
	public void TC_02_Login_Falied_With_Old_Password(Method method) {	
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Login failed with old password");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Login link");
		loginPage = homePage.clickLogInLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Input email");
		loginPage.inputEmail(GlobalConstants.nopcommerce_Email);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Input password");
		loginPage.inputPassword(GlobalConstants.nopcommerce_Password);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Login button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Check error");
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");
	}
	
	@Test
	public void TC_03_Login_Successfully_With_New_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Login sucessfully with new password");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input email");
		loginPage.inputEmail(GlobalConstants.nopcommerce_Email);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Input new password");
		loginPage.inputPassword(newPassword);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Login button");
		loginPage.clickLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click My Account Link");
		customerInfoPage = loginPage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Verify 'My Account' page title");
		assertTrue(customerInfoPage.getMyAccountPageTitle().contains("My account - Customer info"));
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();	
	}
}
