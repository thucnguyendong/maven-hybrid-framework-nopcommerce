package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputEmail(String email) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX,email);
	}

	public void inputPassword(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX,password);		
	}

	public AdminDashboardPageObject clickLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getPageGenerator().getAdminDashboardPage(driver);
	}

	public AdminDashboardPageObject loginAsAdmin(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		return clickLoginButton();
	}
	
}
