package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerBankGuru;
import pageUI.bankguru.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputUserIDTextbox(String userId) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		inputIntoElement(driver, LoginPageUI.USER_ID_TEXTBOX, userId);
	}

	public void inputPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public HomePageObject clickLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new HomePageObject(driver);
	}
	
	public HomePageObject loginAsUser(String userId, String password) {
		inputUserIDTextbox(userId);
		inputPasswordTextbox(password);
		return clickLoginButton();
	}

	public GenerateCredentialPageObject clickHereLink() {
		waitForElementClickable(driver, LoginPageUI.HERE_LINK);
		clickElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManagerBankGuru.getPageGenerator().getGenerateCredentialPage(driver);
	}

}
