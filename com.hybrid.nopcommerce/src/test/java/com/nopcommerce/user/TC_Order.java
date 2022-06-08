package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserCheckoutPageObject;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserOrderDetailsPageObject;
import pageObjects.nopcommerce.portal.UserProductCartPageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserOrderPageObject;

public class TC_Order extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchBarPageObject searchBar;
	UserSearchPageObject searchPage;
	UserProductPageObject productPage;
	UserProductCartPageObject productCartPage;
	UserCheckoutPageObject checkoutPage;
	UserCustomerInfoPageObject customerInfoPage;
	UserOrderPageObject orderPage;
	UserOrderDetailsPageObject orderDetailsPage;
	String orderNumber;
	String emailAddress;
	String firstName;
	String lastName;
	String company;
	String searchValue;
	String processor;
	String ram;
	String hdd;
	String os;
	String software;
	String city;
	String address_1;
	String address_2;
	String phone;
	String fax;
	String paymentMethod;
	String country = "United States";
	String state = "Hawaii";
	String zipCode = "5000";
	String methodName = "Next Day Air";
	String creditCardType = "Master card";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);		
	}

	@Test
	public void TC_01_Add_Product_To_Cart(Method method) {
		searchValue = "Build your own computer";
		processor="2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram = "4GB [+$20.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Home [+$50.00]";
		software = "Microsoft Office [+$50.00]";
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
	
	@Test
	public void TC_02_Edit_Product(Method method) {
		searchValue = "Build your own computer";
		processor="2.2 GHz Intel Pentium Dual-Core E2200";
		ram = "2 GB";
		hdd = "320 GB";
		os = "Vista Premium [+$60.00]";
		software = "Microsoft Office [+$50.00]";
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
	
	@Test
	public void TC_03_Remove_Product(Method method) {
		searchValue = "Build your own computer";
		productCartPage.clickRemoveButtonByProductName(searchValue);
		assertTrue(productCartPage.isProductUndisplayedByProductName(searchValue));
		assertEquals(productCartPage.getNoDataText(),"Your Shopping Cart is empty!");
	}
	
	@Test
	public void TC_04_Update_Product_Quantity(Method method) {
		searchValue = "Build your own computer";
		processor="2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram = "4GB [+$20.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Home [+$50.00]";
		software = "Microsoft Office [+$50.00]";
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
	public void TC_05_Checkout_With_Credit_Card(Method method) {
		searchValue = "Apple MacBook Pro 13-inch";
		firstName = "Automation"; 
		lastName= "FC"; 
		company ="NEO Livegroup"; 
		city = "Ho Chi Minh";
		address_1 = "220 NTL";
		address_2 = "440 THD";
		phone ="0123456789";
		fax = "123511";
		paymentMethod = "Credit Card";
		
		String giftWrapping = "Yes [+$10.00]";
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
		assertEquals(checkoutPage.getPaymentMethodText(), "Payment Method: "+paymentMethod);
		checkoutPage.clickConfirmButton();
		assertTrue(checkoutPage.isSuccessOrderTextDisplayed());
		assertTrue(checkoutPage.isOrderNumberDisplayed());
		orderNumber = checkoutPage.getOrderNumberText();
	}
	
	@Test
	public void TC_06_Reorder(Method method) {		
		searchValue = "Apple MacBook Pro 13-inch";		
		firstName = "Testing"; 
		lastName= "Framework"; 
		company ="ONE Livegroup"; 
		city = "Ha Noi";
		address_1 = "221 NTL";
		address_2 = "441 THD";
		phone ="0123456780";
		fax = "123512";
		methodName = "Ground";
		paymentMethod = "Check / Money Order";
		
		String quantity = "10";
		float price = 1800;
		String newAddress = "New Address";
		String billingDetail =firstName+" "+lastName+"\n"+"Email: "+emailAddress+"\n"+"Phone: "+phone+"\n"+"Fax: "+fax+"\n"+company+"\n"+address_1+"\n"+address_2
				+"\n"+city+","+state+","+zipCode+"\n"+country;
		
		customerInfoPage = checkoutPage.clickMyAccountLink(driver);
		orderPage = customerInfoPage.openOrderPage(driver) ;
		orderDetailsPage = orderPage.clickDetailsLinkByOrderNumber(orderNumber);
		productCartPage=orderDetailsPage.clickReOrderButton();
		productCartPage.inputQtyByProductName(searchValue, quantity);
		productCartPage.clickUpdateShoppingCart();
		productCartPage.checkTermOfServiceCheckbox();
		checkoutPage = productCartPage.clickCheckOutButton();
		checkoutPage.selectNewAddressDropdown(newAddress);
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
		assertTrue(checkoutPage.isPaymentInfoForCheckDisplayed());
		checkoutPage.clickPaymentInfoContinueButton();
		assertEquals(checkoutPage.getBillingDetailText(), billingDetail);
		assertEquals(checkoutPage.getPaymentMethodText(), "Payment Method: "+paymentMethod);
		assertTrue(checkoutPage.isQtyDisplayedCorrectlyByProductName(searchValue, quantity));
		assertTrue(checkoutPage.isTotalPriceDisplayedCorrectlyByProductName(searchValue,price*productCartPage.convertStringToFloat(quantity)));
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Post-condition: Close browser chrome");
		closeBrowserAndDriver();
	}	
}
