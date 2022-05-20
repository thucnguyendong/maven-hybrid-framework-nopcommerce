package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.portal.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputPassword(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public UserHomePageObject clickLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getLoginErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_ERROR_MESSAGE);
	}

	public UserHomePageObject loginAsUser(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		return clickLoginButton();	
	}

	public UserHomePageObject openHomePage() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_IMG);
		clickElement(driver, UserLoginPageUI.LOGIN_IMG);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
}
