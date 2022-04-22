package com.facebook;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class TC_AssertAndVerify extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName,url);	
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.clickRegisterLink();
	}
	
	@Test
	public void TC_01_Assert_True() {	
		verifyTrue(registerPage.isEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_02_Assert_False() {	
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_03_Fail() {	
		verifyFalse(registerPage.isLoginButtonNotDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
