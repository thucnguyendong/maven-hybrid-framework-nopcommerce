package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserCheckoutPageObject;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserProductCartPageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class TC_Order extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchBarPageObject searchBar;
	UserSearchPageObject searchPage;
	UserProductPageObject productPage;
	UserProductCartPageObject productCartPage;
	UserCheckoutPageObject checkoutPage;
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
	private String searchValue;

	
	@BeforeClass
	public void beforeClass() {
		log.info("Pre-condition: Open browser chrome and navigate to "+ GlobalConstants.USER_PORTAL_PAGE_URL);
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		emailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		log.info("Step 3: Input First Name");
		registerPage.inputFirstName(firstName);
		log.info("Step 4: Input Last Name");
		registerPage.inputLastName(lastName);
		log.info("Step 5: Select Birthday Date: day, month, year");
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		log.info("Step 5: Input company name");
		registerPage.inputCompany(company);
		log.info("Step 6: Input email address");
		registerPage.inputEmail(emailAddress);
		log.info("Step 7: Input password");
		registerPage.inputPassword(password);
		log.info("Step 8: Input confirm password");
		registerPage.inputConfirmPassword(confirmPassword);
		log.info("Step 9: Click Register button");
		registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}

	//@Test
	public void TC_01_Add_Product_To_Cart(Method method) {
		searchValue = "Build your own computer";
		String processor="2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		String ram = "4GB [+$20.00]";
		String hdd = "400 GB [+$100.00]";
		String os = "Vista Home [+$50.00]";
		String software = "Microsoft Office [+$50.00]";
		String totalPrice = "$1,435.00";
		String fullProductDetail = "Processor: "+processor+"\n"+"RAM: "+ram+"\n"+"HDD: "+hdd+"\n"+"OS: "+os+"\n"+"Software: "+software;
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		//ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		productPage.selectProcessorDropdown(processor);
		productPage.selectRamDropdown(ram);
		productPage.selectHDDRadio(hdd);
		productPage.selectOSRadio(os);
		productPage.checkSoftwareCheckbox(software);
		assertEquals(productPage.getTotalPrice(), totalPrice);
		productPage.clickAddToCartButton();
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		assertTrue(productCartPage.isProductDisplayedByProductName(searchValue));
		assertTrue(productCartPage.getProductDetailByProductName(searchValue).equals(fullProductDetail));
	}
	
	//@Test(dependsOnMethods = "TC_01_Add_Product_To_Cart")
	public void TC_02_Edit_Product(Method method) {
		searchValue = "Build your own computer";
		String processor="2.2 GHz Intel Pentium Dual-Core E2200";
		String ram = "2 GB";
		String hdd = "320 GB";
		String os = "Vista Premium [+$60.00]";
		String software = "Microsoft Office [+$50.00]";
		String totalPrice = "$1,310.00";
		String fullProductDetail = "Processor: "+processor+"\n"+"RAM: "+ram+"\n"+"HDD: "+hdd+"\n"+"OS: "+os+"\n"+"Software: "+software;
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		productPage= productCartPage.clickEditLinkByProductName(searchValue);
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage.selectProcessorDropdown(processor);
		productPage.selectRamDropdown(ram);
		productPage.selectHDDRadio(hdd);
		productPage.selectOSRadio(os);
		assertEquals(productPage.getTotalPrice(), totalPrice);
		productPage.clickUpdateButton();
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		assertTrue(productCartPage.getProductDetailByProductName(searchValue).equals(fullProductDetail));
	}
	
	//@Test(dependsOnMethods = "TC_01_Add_Product_To_Cart")
	public void TC_03_Remove_Product(Method method) {
		searchValue = "Build your own computer";
		productCartPage.clickRemoveButtonByProductName(searchValue);
		assertTrue(productCartPage.isProductUndisplayedByProductName(searchValue));
		assertEquals(productCartPage.getNoDataText(),"Your Shopping Cart is empty!");
	}
	
	//@Test
	public void TC_04_Update_Product_Quantity(Method method) {
		searchValue = "Build your own computer";
		String processor="2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		String ram = "4GB [+$20.00]";
		String hdd = "400 GB [+$100.00]";
		String os = "Vista Home [+$50.00]";
		String software = "Microsoft Office [+$50.00]";
		float price = 1435;
		String quantity = "5";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		//ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		productPage.selectProcessorDropdown(processor);
		productPage.selectRamDropdown(ram);
		productPage.selectHDDRadio(hdd);
		productPage.selectOSRadio(os);
		productPage.checkSoftwareCheckbox(software);
		productPage.clickAddToCartButton();
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		assertTrue(productCartPage.isPriceDisplayedCorrectlyByProductName(searchValue,price));
		productCartPage.inputQtyByProductName(searchValue,quantity);
		productCartPage.clickUpdateShoppingCart();
		assertTrue(productCartPage.isTotalPriceDisplayedCorrectlyByProductName(searchValue,price*productCartPage.convertStringToFloat(quantity)));
		productCartPage.clickRemoveButtonByProductName(searchValue);
		assertTrue(productCartPage.isProductUndisplayedByProductName(searchValue));
		assertEquals(productCartPage.getNoDataText(),"Your Shopping Cart is empty!");
	}
	
	@Test
	public void TC_05_Update_Product_Quantity(Method method) {
		searchValue = "Apple MacBook Pro 13-inch";
		String giftWrapping = "Yes [+$10.00]";
		String country = "United States";
		String state = "Hawaii";
		String zipCode = "5000";
		String methodName = "Next Day Air";
		
		firstName = "Automation"; 
		lastName= "FC"; 
		company ="NEO Livegroup"; 
		String city = "Ho Chi Minh";
		String address_1 = "220 NTL";
		String address_2 = "440 THD";
		String phone ="0123456789";
		String fax = "123511";
		String paymentMethod = "Credit Card";
		String creditCardType = "Master card";
		String cardholderName = "Testing";
		String cardNumber = "000011112222333";
		String expireMonth = "12";
		String expireYear = "2025";
		String cardCode = "847";
		
		String billingDetail =firstName+" "+lastName+"\n"+"Email: "+emailAddress+"\n"+"Phone: "+phone+"\n"+"Fax: "+fax+"\n"+company+"\n"+address_1+"\n"+address_2
				+"\n"+city+","+state+","+zipCode+"\n"+country;
		
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		//ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		productPage.clickAddToCartButton();
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		productCartPage.selectGiftWrappingDropdown(giftWrapping);
		productCartPage.clickEstimateShippingButton();
		productCartPage.selectShipToCountryDropdown(country);
		productCartPage.selectShipToStateDropdown(state);
		productCartPage.inputShipToZipCodeTextbox(zipCode);
		productCartPage.clickShippingMethodRadioButtonByMethodName(methodName);
		productCartPage.clickApplyButton();
		productCartPage.checkTermOfServiceCheckbox();
		checkoutPage = productCartPage.clickCheckOutButton();
		checkoutPage.inputFirstNameTextbox(firstName);
		checkoutPage.inputLastNameTextbox(lastName);
		checkoutPage.inputEmailTextbox(emailAddress);
		checkoutPage.inputCompanyTextbox(company);
		checkoutPage.selectCountryDropdown(country);
		checkoutPage.selectStateDropdown(state);
		checkoutPage.inputCityTextbox(city);
		checkoutPage.inputAddress1Textbox(address_1);
		checkoutPage.inputAddress2Textbox(address_2);
		checkoutPage.inputZipTexbox(zipCode);
		checkoutPage.inputPhoneTextbox(phone);
		checkoutPage.inputFaxTextbox(fax);
		checkoutPage.clickBillingContinueButton();
		checkoutPage.clickShippingMethodRadioButtonByMethodName(methodName);
		checkoutPage.clickShippingContinueButton();
		checkoutPage.clickPaymentMethodRadioButtonByMethodName(paymentMethod);
		checkoutPage.clickPaymentContinueButton();
		checkoutPage.selectCreditCardDropdown(creditCardType);
		checkoutPage.inputCardholderNameTextbox(cardholderName);
		checkoutPage.inputCardNumberTextbox(cardNumber);
		checkoutPage.selectExpireMonthDropdown(expireMonth);
		checkoutPage.selectExpireYearDropdown(expireYear);
		checkoutPage.inputCardCodeTextbox(cardCode);
		checkoutPage.clickPaymentInfoContinueButton();
		assertEquals(checkoutPage.getBillingDetailText(), billingDetail);
		checkoutPage.clickConfirmButton();
		assertTrue(checkoutPage.isSuccessOrderTextDisplayed());
		assertTrue(checkoutPage.isOrderNumberDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser chrome");
		//closeBrowserAndDriver();
	}	
}