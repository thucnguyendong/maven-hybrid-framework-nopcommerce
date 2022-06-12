package com.nopcommerce.commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;

public class Common_01_LoginToSystem extends BaseTest {
	private WebDriver driver;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition: Open browser "+browserName+" and navigate to "+ GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver(browserName,GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
		
		log.info("Pre-condition - Step 1: Click Login Link");
		loginPage = homePage.clickLogInLink();
		log.info("Pre-condition - Step 2: Input login Cookies");
		loginPage.setAllCookies(driver, Common_RegisterToSystem.loginPageCookies);
		
		log.info("Pre-condition - Step 3: Open Home page");
		loginPage.openHomePage();
	}
	
	@Test
	public void TC_01_Login_Successfully() {
		log.info("Step 1: Verify My Account link displays");
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
}
