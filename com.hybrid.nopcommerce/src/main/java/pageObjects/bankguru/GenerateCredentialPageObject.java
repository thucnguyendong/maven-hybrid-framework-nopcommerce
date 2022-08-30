package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.bankguru.GenerateCredentialPageUI;

public class GenerateCredentialPageObject extends BasePage {
	private WebDriver driver;
	
	public GenerateCredentialPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputEmailTextbox(String email) {
		waitForElementVisible(driver, GenerateCredentialPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver, GenerateCredentialPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickSubmitButton() {
		waitForElementClickable(driver, GenerateCredentialPageUI.SUBMIT_BUTTON);
		clickElement(driver, GenerateCredentialPageUI.SUBMIT_BUTTON);
	}

	public String getUserIdText() {
		waitForElementVisible(driver, GenerateCredentialPageUI.USER_ID_TEXT);
		return getElementText(driver, GenerateCredentialPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, GenerateCredentialPageUI.PASSWORD_TEXT);
		return getElementText(driver, GenerateCredentialPageUI.PASSWORD_TEXT);
	}
}
