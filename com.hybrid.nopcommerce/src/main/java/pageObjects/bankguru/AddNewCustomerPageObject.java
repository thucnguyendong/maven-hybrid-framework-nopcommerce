package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerBankGuru;
import pageUI.bankguru.AddNewCustomerPageUI;

public class AddNewCustomerPageObject extends BasePage {
	private WebDriver driver;
	
	public AddNewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputCustomerName(String customerName) {
		inputToTextboxByName(driver, "name", customerName);
	}

	public void selectGender(String genderValue) {
		waitForElementVisible(driver, AddNewCustomerPageUI.DYNAMIC_GENDER_RADIO_BUTTON_BY_VALUE, genderValue);
		clickElement(driver, AddNewCustomerPageUI.DYNAMIC_GENDER_RADIO_BUTTON_BY_VALUE, genderValue);
	}

	public void inputDateOfBirth(String dateOfBirth) {
		removeAttributeInDOM(driver,AddNewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX,"type");
		waitForElementVisible(driver,AddNewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
		inputIntoElement(driver, AddNewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
	}

	public void inputAddress(String address) {
		waitForElementVisible(driver, AddNewCustomerPageUI.ADDRESS_TEXT_AREA);
		inputIntoElement(driver, AddNewCustomerPageUI.ADDRESS_TEXT_AREA,address);	
	}

	public void inputCity(String city) {
		inputToTextboxByName(driver, "city", city);		
	}

	public void inputState(String state) {
		inputToTextboxByName(driver, "state", state);		
	}

	public void inputPIN(String pin) {
		inputToTextboxByName(driver, "pinno", pin);		
	}

	public void inputMobileNumber(String mobileNumber) {
		inputToTextboxByName(driver, "telephoneno", mobileNumber);	
	}

	public void inputEmail(String email) {
		inputToTextboxByName(driver, "emailid", email);	
	}

	public void inputPassword(String password) {
		inputToTextboxByName(driver, "password", password);		
	}

	public ViewCustomerPageOject clickSubmitButton() {
		waitForElementClickable(driver, AddNewCustomerPageUI.SUBMIT_BUTTON);
		clickElement(driver, AddNewCustomerPageUI.SUBMIT_BUTTON);
		return PageGeneratorManagerBankGuru.getPageGenerator().getViewCustomerPage(driver);
	}
}
