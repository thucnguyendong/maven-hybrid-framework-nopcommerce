package com.bankguru;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerBankGuru;
import pageObjects.bankguru.AddNewCustomerPageObject;
import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.HomePageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ViewCustomerPageOject;
import utilities.DataHelper;

public class TC_AddNewCustomer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private GenerateCredentialPageObject generateCredentialPage;
	private HomePageObject homePage;
	private AddNewCustomerPageObject addNewCustomerPage;
	private ViewCustomerPageOject viewCustomerPage;
	String userId, password, email;
	static String customerID;
	
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
		loginPage = PageGeneratorManagerBankGuru.getPageGenerator().getLoginPage(driver);
		loginPage.inputUserIDTextbox(userId);
		loginPage.inputPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
	}
	
	@Test
	public void TC_01_Create_New_Customer() {
		String customerName = "Thuc";
		String gender="m";
		String dateOfBirth = "05/11/1995";
		String address ="220 NTL";
		String city="HCM";
		String state="VN";
		String pin="100000";
		String mobileNumber="0123456";
		
		homePage.clickBankGuruMenuItemByText(driver,"New Customer");
		addNewCustomerPage = PageGeneratorManagerBankGuru.getPageGenerator().getAddNewCustomerPage(driver);
		addNewCustomerPage.inputCustomerName(customerName);
		addNewCustomerPage.selectGender(gender);
		addNewCustomerPage.inputDateOfBirth(dateOfBirth);
		addNewCustomerPage.inputAddress(address);
		addNewCustomerPage.inputCity(city);
		addNewCustomerPage.inputState(state);
		addNewCustomerPage.inputPIN(pin);
		addNewCustomerPage.inputMobileNumber(mobileNumber);
		addNewCustomerPage.inputEmail(email);
		addNewCustomerPage.inputPassword(password);
		viewCustomerPage = addNewCustomerPage.clickSubmitButton();
		assertTrue(viewCustomerPage.isSuccessfulMessageDisplay());
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Customer Name"), customerName);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Gender"), "male");
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Birthdate"), "1995-05-11");
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Address"), address);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("City"), city);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("State"), state);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Pin"), pin);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Mobile No."), mobileNumber);
		assertEquals(viewCustomerPage.getCustomerInformationByLabel("Email"), email);
		customerID = viewCustomerPage.getCustomerInformationByLabel("Customer ID");
	}
	
	//@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
