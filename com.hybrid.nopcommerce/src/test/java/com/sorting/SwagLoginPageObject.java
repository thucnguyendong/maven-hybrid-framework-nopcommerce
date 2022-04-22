package com.sorting;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class SwagLoginPageObject extends BasePage {
	private WebDriver driver;

	public SwagLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public SwagInventoryPageObject loginAsUser(String username, String password) {
		inputToTextboxByID(driver, "user-name", username);
		inputToTextboxByID(driver, "password", password);
		clickLoginButton();
		return PageGeneratorManager.getSwagInventoryPage(driver);
	}

	private void clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, SwagLoginPageUI.LOGIN_BUTTON);
		clickElement(driver, SwagLoginPageUI.LOGIN_BUTTON);
	}

}
