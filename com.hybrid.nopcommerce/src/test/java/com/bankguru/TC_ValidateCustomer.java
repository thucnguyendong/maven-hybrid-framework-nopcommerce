package com.bankguru;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerBankGuru;
import pageObjects.bankguru.EditCustomerPageObject;
import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.HomePageObject;
import pageObjects.bankguru.InputCustomerFormPageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ViewCustomerPageOject;
import utilities.DataHelper;

public class TC_ValidateCustomer extends BaseTest{
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
	public void TC_01_Empty_Customer() {		
		homePage.clickBankGuruMenuItemByText(driver,"New Customer");
		inputCustomerInfoPage = PageGeneratorManagerBankGuru.getPageGenerator().getInputCustomerFormPage(driver);
		inputCustomerInfoPage.clickSubmitButton();
		assertEquals(inputCustomerInfoPage.getFillAllFieldPopup(),"please fill all fields");		
		inputCustomerInfoPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Verify_Blank_Error() {		
		homePage.clickBankGuruMenuItemByText(driver,"New Customer");
		inputCustomerInfoPage = PageGeneratorManagerBankGuru.getPageGenerator().getInputCustomerFormPage(driver);
		inputCustomerInfoPage.tabCustomerInformationByLabel("Customer Name");
		inputCustomerInfoPage.tabCustomerInformationByLabel("City");
		inputCustomerInfoPage.tabCustomerInformationByLabel("State");
		assertEquals(inputCustomerInfoPage.getCustomerNameErrorByLabel("Customer Name"),"Customer name must not be blank");
		assertEquals(inputCustomerInfoPage.getCustomerNameErrorByLabel("City"),"City Field must not be blank");
		assertEquals(inputCustomerInfoPage.getCustomerNameErrorByLabel("State"),"State must not be blank");
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
