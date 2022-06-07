package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserCheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickBillingContinueButton() {
		waitForElementClickable(driver, UserCheckoutPageUI.BILLING_CONTINUE_BUTTON);
		clickElement(driver, UserCheckoutPageUI.BILLING_CONTINUE_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void inputFirstNameTextbox(String firstName) {
		inputToTextboxByID(driver, "BillingNewAddress_FirstName", firstName);
	}

	public void inputLastNameTextbox(String lastName) {
		inputToTextboxByID(driver, "BillingNewAddress_LastName", lastName);		
	}

	public void inputEmailTextbox(String emailAddress) {
		inputToTextboxByID(driver, "BillingNewAddress_Email", emailAddress);		
	}

	public void inputCompanyTextbox(String company) {
		inputToTextboxByID(driver, "BillingNewAddress_Company", company);				
	}

	public void selectCountryDropdown(String country) {
		selectDropdownListByName(driver, "BillingNewAddress.CountryId", country);
	}

	public void selectStateDropdown(String state) {
		selectDropdownListByName(driver, "BillingNewAddress.StateProvinceId", state);		
	}

	public void inputCityTextbox(String city) {
		inputToTextboxByID(driver, "BillingNewAddress_City", city);				
	}

	public void inputAddress1Textbox(String address_1) {
		inputToTextboxByID(driver, "BillingNewAddress_Address1", address_1);			
	}

	public void inputAddress2Textbox(String address_2) {
		inputToTextboxByID(driver, "BillingNewAddress_Address2", address_2);
	}

	public void inputZipTexbox(String zipCode) {
		inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipCode);
	}

	public void inputPhoneTextbox(String phone) {
		inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phone);
	}

	public void inputFaxTextbox(String fax) {
		inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", fax);		
	}

	public void clickShippingMethodRadioButtonByMethodName(String methodName) {
		waitForElementClickable(driver,UserCheckoutPageUI.SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME,methodName);
		clickElement(driver,UserCheckoutPageUI.SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME,methodName);
	}

	public void clickShippingContinueButton() {
		waitForElementClickable(driver, UserCheckoutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
		clickElement(driver, UserCheckoutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void clickPaymentMethodRadioButtonByMethodName(String paymentMethod) {
		waitForElementClickable(driver,UserCheckoutPageUI.PAYMENT_METHOD_RADIO_BUTTON_BY_METHOD_NAME,paymentMethod);
		clickElement(driver,UserCheckoutPageUI.PAYMENT_METHOD_RADIO_BUTTON_BY_METHOD_NAME,paymentMethod);
	}
	
	public void clickPaymentContinueButton() {
		waitForElementClickable(driver, UserCheckoutPageUI.PAYMENT_METHOD_CONTINUE_BUTTON);
		clickElement(driver, UserCheckoutPageUI.PAYMENT_METHOD_CONTINUE_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void selectCreditCardDropdown(String creditCardType) {
		selectDropdownListByName(driver, "CreditCardType", creditCardType);		
	}

	public void inputCardholderNameTextbox(String cardholderName) {
		inputToTextboxByID(driver, "CardholderName", cardholderName);
	}

	public void inputCardNumberTextbox(String cardNumber) {
		inputToTextboxByID(driver, "CardNumber", cardNumber);
	}

	public void selectExpireMonthDropdown(String expireMonth) {
		selectDropdownListByName(driver, "ExpireMonth", expireMonth);			
	}

	public void selectExpireYearDropdown(String expireYear) {
		selectDropdownListByName(driver, "ExpireYear", expireYear);					
	}

	public void inputCardCodeTextbox(String cardCode) {
		inputToTextboxByID(driver, "CardCode", cardCode);				
	}

	public void clickPaymentInfoContinueButton() {
		waitForElementClickable(driver, UserCheckoutPageUI.PAYMENT_INFO_CONTINUE_BUTTON);
		clickElement(driver, UserCheckoutPageUI.PAYMENT_INFO_CONTINUE_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public String getBillingDetailText() {
		return getElementText(driver, UserCheckoutPageUI.BILLING_DETAIL_TEXT);
	}

	public void clickConfirmButton() {
		clickButtonByText(driver, "Confirm");
		areJQueryAndJSLoadedSuccess(driver);
	}

	public boolean isSuccessOrderTextDisplayed() {
		return isElementDisplayed(driver, UserCheckoutPageUI.SUCCESS_ORDER_TEXT);
	}

	public boolean isOrderNumberDisplayed() {
		return isElementDisplayed(driver, UserCheckoutPageUI.ORDER_NUMBER_TEXT);
	}
}
