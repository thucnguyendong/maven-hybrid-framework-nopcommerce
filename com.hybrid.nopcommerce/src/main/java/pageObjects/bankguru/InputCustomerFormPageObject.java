package pageObjects.bankguru;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerBankGuru;
import pageUI.bankguru.InputCustomerFormPageUI;

public class InputCustomerFormPageObject extends BasePage {
	private WebDriver driver;
	
	public InputCustomerFormPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputCustomerName(String customerName) {
		inputToTextboxByName(driver, "name", customerName);
	}

	public void selectGender(String genderValue) {
		waitForElementVisible(driver, InputCustomerFormPageUI.DYNAMIC_GENDER_RADIO_BUTTON_BY_VALUE, genderValue);
		clickElement(driver, InputCustomerFormPageUI.DYNAMIC_GENDER_RADIO_BUTTON_BY_VALUE, genderValue);
	}

	public void inputDateOfBirth(String dateOfBirth) {
		removeAttributeInDOM(driver,InputCustomerFormPageUI.DATE_OF_BIRTH_TEXTBOX,"type");
		waitForElementVisible(driver,InputCustomerFormPageUI.DATE_OF_BIRTH_TEXTBOX);
		inputIntoElement(driver, InputCustomerFormPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
	}

	public void inputAddress(String address) {
		waitForElementVisible(driver, InputCustomerFormPageUI.ADDRESS_TEXT_AREA);
		inputIntoElement(driver, InputCustomerFormPageUI.ADDRESS_TEXT_AREA,address);	
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
		waitForElementClickable(driver, InputCustomerFormPageUI.SUBMIT_BUTTON);
		clickElement(driver, InputCustomerFormPageUI.SUBMIT_BUTTON);
		return PageGeneratorManagerBankGuru.getPageGenerator().getViewCustomerPage(driver);
	}

	
	public String getAddress() {
		return getElementText(driver, InputCustomerFormPageUI.ADDRESS_TEXT_AREA);
	}
	
	public String getCustomerValueByLabel(String label) {
		return getElementAttribute(driver, InputCustomerFormPageUI.DYNAMIC_CUSTOMER_INFORMATION_BY_LABEL,"value",label);
	}

	public String getFillAllFieldPopup() {
		return getAlertText(driver);
	}
	
	public void tabCustomerInformationByLabel(String label){
		clickElement(driver,InputCustomerFormPageUI.DYNAMIC_CUSTOMER_INFORMATION_BY_LABEL, label);
		sendKeyboard(driver,Keys.TAB);
	}

	public String getCustomerNameErrorByLabel(String label) {
		return getElementText(driver, InputCustomerFormPageUI.DYNAMIC_ERROR_BY_LABEL,label);
	}
}
