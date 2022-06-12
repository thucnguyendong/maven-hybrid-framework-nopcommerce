package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopcommerce.portal.UserCheckoutPageObject;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserOrderDetailsPageObject;
import pageObjects.nopcommerce.portal.UserProductCartPageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserOrderPageObject;
import reportConfig.ExtentTestManager;

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
	UserLoginPageObject loginPage;

	String orderNumber;
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
		driver = getBrowserDriver(browserName,GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage=loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
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
		
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Add product to cart");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Select processor");
		productPage.selectProcessorDropdown(processor);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select ram");
		productPage.selectRamDropdown(ram);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Select hdd");
		productPage.selectHDDRadio(hdd);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Select OS");
		productPage.selectOSRadio(os);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Select software");
		productPage.checkSoftwareCheckbox(software);
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Check total price");
		assertEquals(productPage.getTotalPrice(), totalPrice);
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Click Add to Cart button");
		productPage.clickAddToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Check success message");
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Navigate to Product Cart page");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Check product displayed on Product Cart");
		assertTrue(productCartPage.isProductDisplayedByProductName(searchValue));
		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Check product detail on Product Cart");
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
		
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Edit Product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Edit button");
		productPage= productCartPage.clickEditLinkByProductName(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Select processor");
		productPage.selectProcessorDropdown(processor);
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select ram");
		productPage.selectRamDropdown(ram);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Select hdd");
		productPage.selectHDDRadio(hdd);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select os");
		productPage.selectOSRadio(os);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Check new total price");
		assertEquals(productPage.getTotalPrice(), totalPrice);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Click Update button");
		productPage.clickUpdateButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Check success message");
		assertEquals(productPage.getSuccessMessage(), "The product has been added to your shopping cart");
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Check success message");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Check product detail");
		assertTrue(productCartPage.getProductDetailByProductName(searchValue).equals(fullProductDetail));
	}
	
	@Test
	public void TC_03_Remove_Product(Method method) {
		searchValue = "Build your own computer";
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Remove Product from cart");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Remove link on the product");
		productCartPage.clickRemoveButtonByProductName(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check that product is removed");
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
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Add new product");
		searchBar = PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		searchPage = searchBar.clickSearchButton();
		productPage =searchPage.clickProductLink(searchValue);
		productPage.selectProcessorDropdown(processor);
		productPage.selectRamDropdown(ram);
		productPage.selectHDDRadio(hdd);
		productPage.selectOSRadio(os);
		productPage.checkSoftwareCheckbox(software);
		productPage.clickAddToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Navigate to cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check total price before update");
		assertTrue(productCartPage.isTotalPriceDisplayedCorrectlyByProductName(searchValue,price));
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Input new quantity");
		productCartPage.inputQtyByProductName(searchValue,quantity);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Click update shopping cart button");
		productCartPage.clickUpdateShoppingCart();
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Check total price after update");
		assertTrue(productCartPage.isTotalPriceDisplayedCorrectlyByProductName(searchValue,price*productCartPage.convertStringToFloat(quantity)));
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Clean up test case");
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
		
		String billingDetail =firstName+" "+lastName+"\n"+"Email: "+GlobalConstants.nopcommerce_Email+"\n"+"Phone: "+phone+"\n"+"Fax: "+fax+"\n"+company+"\n"+address_1+"\n"+address_2
				+"\n"+city+","+state+","+zipCode+"\n"+country;
		
		ExtentTestManager.startTest(method.getName(), "Test Case 05: Order and checkout with credit card");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Add new product to cart");
		searchBar = PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		searchPage = searchBar.clickSearchButton();
		productPage =searchPage.clickProductLink(searchValue);
		productPage.clickAddToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Navigate to product cart");
		productCartPage = productPage.clickProductCartLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select Gift wrapping");
		productCartPage.selectGiftWrappingDropdown(giftWrapping);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Estimate Shipping button");
		productCartPage.clickEstimateShippingButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select country to ship to");
		productCartPage.selectShipToCountryDropdown(country);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Select state to ship to");
		productCartPage.selectShipToStateDropdown(state);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Select zip code to ship to");
		productCartPage.inputShipToZipCodeTextbox(zipCode);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Select shipping method");
		productCartPage.clickShippingMethodRadioButtonByMethodName(methodName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Click Apply button");
		productCartPage.clickApplyButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Tick Term Of Service checkbox");
		productCartPage.checkTermOfServiceCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click Checkout button");
		checkoutPage = productCartPage.clickCheckOutButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input Billing information");
		checkoutPage.inputFirstNameTextbox(firstName);
		checkoutPage.inputLastNameTextbox(lastName);
		checkoutPage.inputEmailTextbox(GlobalConstants.nopcommerce_Email);
		checkoutPage.inputCompanyTextbox(company);
		checkoutPage.selectCountryDropdown(country);
		checkoutPage.selectStateDropdown(state);
		checkoutPage.inputCityTextbox(city);
		checkoutPage.inputAddress1Textbox(address_1);
		checkoutPage.inputAddress2Textbox(address_2);
		checkoutPage.inputZipTexbox(zipCode);
		checkoutPage.inputPhoneTextbox(phone);
		checkoutPage.inputFaxTextbox(fax);
		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click Continue button to save Billing information");
		checkoutPage.clickBillingContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Select shipping method");
		checkoutPage.clickShippingMethodRadioButtonByMethodName(methodName);
		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click Continue button to save shipping method");
		checkoutPage.clickShippingContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Select payment method: Credit card");
		checkoutPage.clickPaymentMethodRadioButtonByMethodName(paymentMethod);
		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Click Continue button to save payment method");
		checkoutPage.clickPaymentContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Input credit card information");
		checkoutPage.selectCreditCardDropdown(creditCardType);
		checkoutPage.inputCardholderNameTextbox(cardholderName);
		checkoutPage.inputCardNumberTextbox(cardNumber);
		checkoutPage.selectExpireMonthDropdown(expireMonth);
		checkoutPage.selectExpireYearDropdown(expireYear);
		checkoutPage.inputCardCodeTextbox(cardCode);
		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Click Continue button to save payment information");
		checkoutPage.clickPaymentInfoContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Check billing detail");
		assertEquals(checkoutPage.getBillingDetailText(), billingDetail);
		ExtentTestManager.getTest().log(Status.INFO, "Step 21: Check payment method detail");
		assertEquals(checkoutPage.getPaymentMethodText(), "Payment Method: "+paymentMethod);
		ExtentTestManager.getTest().log(Status.INFO, "Step 22: Click Confirm button");
		checkoutPage.clickConfirmButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 23: Verify success message and order number displays");
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
		String billingDetail =firstName+" "+lastName+"\n"+"Email: "+GlobalConstants.nopcommerce_Email+"\n"+"Phone: "+phone+"\n"+"Fax: "+fax+"\n"+company+"\n"+address_1+"\n"+address_2
				+"\n"+city+","+state+","+zipCode+"\n"+country;
		
		ExtentTestManager.startTest(method.getName(), "Test Case 06: Re-order");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click My Account link");
		customerInfoPage = checkoutPage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Naviage to Order Page");
		orderPage = customerInfoPage.openOrderPage(driver) ;
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Naviage to Order Detail Page");
		orderDetailsPage = orderPage.clickDetailsLinkByOrderNumber(orderNumber);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Reoder button");
		productCartPage=orderDetailsPage.clickReOrderButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Update quantity and click Checkout button");
		productCartPage.inputQtyByProductName(searchValue, quantity);
		productCartPage.clickUpdateShoppingCart();
		productCartPage.checkTermOfServiceCheckbox();
		checkoutPage = productCartPage.clickCheckOutButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Select option: New Address");
		checkoutPage.selectNewAddressDropdown(newAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Update Billing information and click Continue");
		checkoutPage.inputFirstNameTextbox(firstName);
		checkoutPage.inputLastNameTextbox(lastName);
		checkoutPage.inputEmailTextbox(GlobalConstants.nopcommerce_Email);
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
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Update Shipping method and click Continue");
		checkoutPage.clickShippingMethodRadioButtonByMethodName(methodName);
		checkoutPage.clickShippingContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Update Payment method = Check and click Continue");
		checkoutPage.clickPaymentMethodRadioButtonByMethodName(paymentMethod);
		checkoutPage.clickPaymentContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Check text for Payment method = Check");
		assertTrue(checkoutPage.isPaymentInfoForCheckDisplayed());
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click Continue");
		checkoutPage.clickPaymentInfoContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Check Billing Detail");
		assertEquals(checkoutPage.getBillingDetailText(), billingDetail);
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Check Payment method = check");
		assertEquals(checkoutPage.getPaymentMethodText(), "Payment Method: "+paymentMethod);
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Check new quantity of the product");
		assertTrue(checkoutPage.isQtyDisplayedCorrectlyByProductName(searchValue, quantity));
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Check new Total Price of the product");
		assertTrue(checkoutPage.isTotalPriceDisplayedCorrectlyByProductName(searchValue,price*productCartPage.convertStringToFloat(quantity)));
	}
	
	@Parameters("browser")	
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
