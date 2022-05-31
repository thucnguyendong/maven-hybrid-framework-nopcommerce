package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUI.nopcommerce.portal.UserBasePageUI;
import pageUI.nopcommerce.portal.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void openHomePage() {
		openBrowser(driver, GlobalConstants.USER_PORTAL_PAGE_URL);
	}
	public UserLoginPageObject clickLogInLink() {
		waitForElementClickable(driver, UserBasePageUI.LOGIN_LINK);
		clickElement(driver, UserBasePageUI.LOGIN_LINK);
		return PageGeneratorManager.getPageGenerator().getUserLoginPage(driver);
	}
	
	public UserRegisterPageObject clickRegisterLink() {
		waitForElementClickable(driver, UserBasePageUI.REGISTER_LINK);
		clickElement(driver, UserBasePageUI.REGISTER_LINK);
		return PageGeneratorManager.getPageGenerator().getUserRegisterPage(driver);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, UserBasePageUI.MY_ACCOUNT_LINK);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}
}
