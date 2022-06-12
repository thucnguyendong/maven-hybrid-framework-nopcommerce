package com.wordpress.portal;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerWordpress;
import pageObjects.wordpress.portal.UserLoginPageObject;

public class TC_Login extends BaseTest {
	WebDriver driver;
	UserLoginPageObject login;
	String username= "Thuc Nguyen";
	String password= "Test@123";
	
	@BeforeClass
	public void beforeClass() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.WORDPRESS_LOGIN_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.WORDPRESS_LOGIN_PAGE_URL);
		login = PageGeneratorManagerWordpress.getPageGenerator().getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Login_Empty_Username_Password() {
		login.clickLoginButton();
		assertEquals(login.getErrorText(),"Error: The username field is empty."+"\n"+"Error: The password field is empty.");
		login.inputUsername(username);
		login.clickLoginButton();
		assertEquals(login.getErrorText(),"Error: The password field is empty.");
		login.inputUsername("");
		login.inputPassword(password);
		login.clickLoginButton();
		assertEquals(login.getErrorText(),"Error: The username field is empty.");
	}
	
	@Test
	public void TC_02_Login_Not_Exist_Username() {
		login.openBrowser(driver, GlobalConstants.WORDPRESS_LOGIN_PAGE_URL);
		login.inputUsername("Test");
		login.inputPassword(password);
		login.clickLoginButton();
		assertEquals(login.getErrorText(),"Error: The username Test is not registered on this site. If you are unsure of your username, try your email address instead.");
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		login.openBrowser(driver, GlobalConstants.WORDPRESS_LOGIN_PAGE_URL);
		login.inputUsername(username);
		login.inputPassword("Test");
		login.clickLoginButton();
		assertEquals(login.getErrorText(),"Error: The password you entered for the username Thuc Nguyen is incorrect. Lost your password?");
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
