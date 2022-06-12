package pageObjects.wordpress.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.portal.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputUsername(String username) {
		inputToTextboxByID(driver, "user_login", username);
		
	}

	public void inputPassword(String password) {
		inputToTextboxByID(driver, "user_pass", password);		
	}
	
	public void clickLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public String getErrorText() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_ERROR_TEXT);
		return getElementText(driver, UserLoginPageUI.LOGIN_ERROR_TEXT);
	}
	
}
