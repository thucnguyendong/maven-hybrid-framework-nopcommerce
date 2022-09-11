package com.bankguru;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerBankGuru;
import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.HomePageObject;
import pageObjects.bankguru.LoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class TC_Login extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private GenerateCredentialPageObject generateCredentialPage;
	private HomePageObject homePage;
	String userId, password, email;
	
	@BeforeClass
	public void beforeClass() {
		driver = getBrowserDriver("chrome",GlobalConstants.BANK_GURU_PAGE_URL);
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
		generateCredentialPage = loginPage.clickHereLink();
		email = DataHelper.getData().getRandomEmail("test","gmail");
		generateCredentialPage.inputEmailTextbox(email);
		generateCredentialPage.clickSubmitButton();
		userId = generateCredentialPage.getUserIdText();
		password = generateCredentialPage.getPasswordText();
		generateCredentialPage.openBrowser(driver, GlobalConstants.BANK_GURU_PAGE_URL);
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Login_Empty_Username_Password() {	
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
		loginPage.clickLoginButton();
		assertEquals(loginPage.getAlertText(driver), "User or Password is not valid");
		loginPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Login_Incorrect_Username_Password() {
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
		loginPage.inputUserIDTextbox("Test");
		loginPage.inputPasswordTextbox("123456");
		homePage = loginPage.clickLoginButton();
		assertEquals(loginPage.getAlertText(driver), "User or Password is not valid");
		loginPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_03_Login_Successfully() {
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
		loginPage.inputUserIDTextbox(userId);
		loginPage.inputPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		assertEquals(homePage.getManagerIdText(), "Manger Id : "+userId);
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}	
}
