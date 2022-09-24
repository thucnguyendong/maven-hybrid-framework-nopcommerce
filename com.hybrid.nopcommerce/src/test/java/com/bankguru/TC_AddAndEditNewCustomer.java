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
import pageObjects.bankguru.InputCustomerFormPageObject;
import pageObjects.bankguru.EditCustomerPageObject;
import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.HomePageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ViewCustomerPageOject;
import utilities.DataHelper;

public class TC_AddAndEditNewCustomer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private GenerateCredentialPageObject generateCredentialPage;
	private HomePageObject homePage;
	private InputCustomerFormPageObject inputCustomerInfoPage;
	private ViewCustomerPageOject viewCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private String userId, password, email,customerName, gender,dateOfBirth,address,city,state,pin,mobileNumber;
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
		customerName = "Thuc";
		gender="m";
		dateOfBirth = "05/11/1995";
		address ="220 NTL";
		city="HCM";
		state="VN";
		pin="100000";
		mobileNumber="0123456";
		
		homePage.clickBankGuruMenuItemByText(driver,"New Customer");
		inputCustomerInfoPage = PageGeneratorManagerBankGuru.getPageGenerator().getInputCustomerFormPage(driver);
		inputCustomerInfoPage.inputCustomerName(customerName);
		inputCustomerInfoPage.selectGender(gender);
		inputCustomerInfoPage.inputDateOfBirth(dateOfBirth);
		inputCustomerInfoPage.inputAddress(address);
		inputCustomerInfoPage.inputCity(city);
		inputCustomerInfoPage.inputState(state);
		inputCustomerInfoPage.inputPIN(pin);
		inputCustomerInfoPage.inputMobileNumber(mobileNumber);
		inputCustomerInfoPage.inputEmail(email);
		inputCustomerInfoPage.inputPassword(password);
		viewCustomerPage = inputCustomerInfoPage.clickSubmitButton();
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
	
	@Test
	public void TC_02_Edit_New_Customer() {
		address ="220 NTL HCM";
		city="HN";
		state="Japan";
		pin="100001";
		mobileNumber="0123456789";
		email = DataHelper.getData().getRandomEmail("test","gmail");
		
		inputCustomerInfoPage.clickBankGuruMenuItemByText(driver,"Edit Customer");
		editCustomerPage = PageGeneratorManagerBankGuru.getPageGenerator().getEditCustomerPage(driver);
		editCustomerPage.inputCustomerID(customerID);
		inputCustomerInfoPage=editCustomerPage.clickSubmitButton();		
		inputCustomerInfoPage.inputAddress(address);
		inputCustomerInfoPage.inputCity(city);
		inputCustomerInfoPage.inputState(state);
		inputCustomerInfoPage.inputPIN(pin);
		inputCustomerInfoPage.inputMobileNumber(mobileNumber);
		inputCustomerInfoPage.inputEmail(email);
		inputCustomerInfoPage.clickSubmitButton();
		inputCustomerInfoPage.acceptAlert(driver);
		editCustomerPage = PageGeneratorManagerBankGuru.getPageGenerator().getEditCustomerPage(driver);
		editCustomerPage.inputCustomerID(customerID);
		inputCustomerInfoPage=editCustomerPage.clickSubmitButton();
		
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("Customer Name"), customerName);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("Gender"), "male");
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("Date of Birth"), "1995-05-11");
		assertEquals(inputCustomerInfoPage.getAddress(), address);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("City"), city);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("State"), state);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("PIN"), pin);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("Mobile Number"), mobileNumber);
		assertEquals(inputCustomerInfoPage.getCustomerValueByLabel("E-mail"), email);
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
