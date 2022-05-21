package com.nopcommerce.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import utilities.DataHelper;

public class TC_Login extends BaseTest {
	WebDriver driver;
	UserLoginPageObject userLoginPage;
	AdminLoginPageObject adminLoginPage;
	UserHomePageObject userHomePage;
	AdminDashboardPageObject adminDashboardPage;
	String userEmailAddress;
	String userPassword;
	String adminEmailAddress = "admin@yourstore.com";
	String adminPassword="admin";
		
	@BeforeTest
	public void beforeTest() {
		setEnvironmentURL("DEV");
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	@BeforeClass
	public void beforeClass() {
		userEmailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		userPassword = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		UserRegisterPageObject registerPage = userHomePage.clickRegisterLink();
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany (company);
		registerPage.inputEmail(userEmailAddress);
		registerPage.inputPassword(userPassword);
		registerPage.inputConfirmPassword(confirmPassword);
		registerPage.clickRegisterButton();
		userHomePage = registerPage.clickLogOutLink();
	}
	
	@Test
	public void TC_01_User_To_Admin() {
		userLoginPage =userHomePage.clickLogInLink();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userPassword);
		userHomePage=userHomePage.clickUserLogOutLink(driver);
		adminLoginPage = userHomePage.openAdminPage(driver,adminUrl);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress,adminPassword);
		assertTrue(adminDashboardPage.isDashboardPageDisplayed());
	}
	
	@Test
	public void TC_02_Login_Role_Admin() {
		adminLoginPage = adminDashboardPage.clickAdminLogOutLink(driver);
		userHomePage = adminLoginPage.openPortalPage(driver,userUrl);
		userLoginPage =userHomePage.clickLogInLink();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userPassword);
		assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
