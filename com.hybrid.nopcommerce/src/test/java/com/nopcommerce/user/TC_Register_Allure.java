package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;

@Epic("Web")
@Feature("user")
public class TC_Register_Allure extends BaseTest {
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
		log.info("Pre-condition: Open browser "+browserName+" and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";
	}
	
	@Story("")
	@Severity(SeverityLevel.NORMAL)
	@Description("Register with empty data")
	@Test
	public void TC_01_Register_Empty_Data() {
		registerPage = homePage.clickRegisterLink();
		registerPage.clickRegisterButton();
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		verifyEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		verifyEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		verifyEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}
	
	@Story("")
	@Severity(SeverityLevel.NORMAL)
	@Description("Register with invalid email")
	@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage = homePage.clickRegisterLink();
		registerPage.inputEmail("Test123");
		registerPage.clickRegisterButton();
		verifyEquals(registerPage.getEmailErrorMessage(), "Wrong email 1");
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser");
		closeBrowserAndDriver();
	}	
	
}
