package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserAddressPageUI;

public class UserAddressPageObject extends BasePage {
	private WebDriver driver;	
	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAddNewButton() {
		waitForElementClickable(driver, UserAddressPageUI.ADD_BUTTON);
		clickElement(driver, UserAddressPageUI.ADD_BUTTON);
	}
	
	public void inputFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserAddressPageUI.FIRSTNAME_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void inputLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserAddressPageUI.LASTNAME_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void inputEmailTextbox(String email) {
		waitForElementVisible(driver, UserAddressPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompanyTextbox(String company) {
		waitForElementVisible(driver, UserAddressPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.COMPANY_TEXTBOX, company);
	}

	public void selectCountryDropdown(String country) {
		waitForElementVisible(driver, UserAddressPageUI.COUNTRY_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserAddressPageUI.COUNTRY_DROPDOWN, country);
	}
	
	public void selectStateDropdown(String state) {
		waitForElementVisible(driver, UserAddressPageUI.STATE_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserAddressPageUI.STATE_DROPDOWN, state);
	}
	
	public void inputCityTextbox(String city) {
		waitForElementVisible(driver, UserAddressPageUI.CITY_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.CITY_TEXTBOX, city);
	}
	
	public void inputAddress1Textbox(String address) {
		waitForElementVisible(driver, UserAddressPageUI.ADDRESS_1_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.ADDRESS_1_TEXTBOX, address);
	}
	
	public void inputAddress2Textbox(String address) {
		waitForElementVisible(driver, UserAddressPageUI.ADDRESS_2_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.ADDRESS_2_TEXTBOX, address);
	}
	
	public void inputZipTextbox(String zip) {
		waitForElementVisible(driver, UserAddressPageUI.ZIP_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.ZIP_TEXTBOX, zip);
	}
	
	public void inputPhoneNumberTextbox(String phoneNumber) {
		waitForElementVisible(driver, UserAddressPageUI.PHONE_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.PHONE_TEXTBOX, phoneNumber);
	}
	
	public void inputFaxTextbox(String faxNumber) {
		waitForElementVisible(driver, UserAddressPageUI.FAX_TEXTBOX);
		inputIntoElement(driver,UserAddressPageUI.FAX_TEXTBOX, faxNumber);
	}
	
	public void clickSaveButton() {
		waitForElementClickable(driver, UserAddressPageUI.SAVE_BUTTON);
		clickElement(driver, UserAddressPageUI.SAVE_BUTTON);
	}
	
	public String getNameText() {
		waitForElementVisible(driver, UserAddressPageUI.NAME_TEXT);
		return getElementText(driver, UserAddressPageUI.NAME_TEXT);
	}
	
	public String getEmailText() {
		waitForElementVisible(driver, UserAddressPageUI.EMAIL_TEXT);
		return getElementText(driver, UserAddressPageUI.EMAIL_TEXT);
	}
	
	public String getPhoneText() {
		waitForElementVisible(driver, UserAddressPageUI.PHONE_TEXT);
		return getElementText(driver, UserAddressPageUI.PHONE_TEXT);
	}
	
	public String getAddress1Text() {
		waitForElementVisible(driver, UserAddressPageUI.ADDRESS_1_TEXT);
		return getElementText(driver, UserAddressPageUI.ADDRESS_1_TEXT);
	}
	
	public String getAddress2Text() {
		waitForElementVisible(driver, UserAddressPageUI.ADDRESS_2_TEXT);
		return getElementText(driver, UserAddressPageUI.ADDRESS_2_TEXT);
	}
	
	public String getCountryText() {
		waitForElementVisible(driver, UserAddressPageUI.COUNTRY_TEXT);
		return getElementText(driver, UserAddressPageUI.COUNTRY_TEXT);
	}
	
	public String getCityStateText() {
		waitForElementVisible(driver, UserAddressPageUI.CITY_STATE_TEXT);
		return getElementText(driver, UserAddressPageUI.CITY_STATE_TEXT);
	}
}
