package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {	
	private WebDriver driver;	
	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputOldPasswordTextbox(String password) {
		waitForElementVisible(driver, UserChangePasswordPageUI.OLD_PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, password);
	}
	
	public void inputNewPasswordTextbox(String password) {
		waitForElementVisible(driver, UserChangePasswordPageUI.NEW_PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, password);
	}
	
	public void inputConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, UserChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}
	
	public void clickChangePasswordButton() {
		waitForElementClickable(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickElement(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public void closeSuccessPopUp() {
		waitForElementClickable(driver, UserChangePasswordPageUI.CLOSE_POPUP_BUTTON);
		clickElement(driver, UserChangePasswordPageUI.CLOSE_POPUP_BUTTON);
		waitForStaleness(driver, UserChangePasswordPageUI.CLOSE_POPUP_BUTTON);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, UserChangePasswordPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserChangePasswordPageUI.SUCCESS_MESSAGE);
	}
}
