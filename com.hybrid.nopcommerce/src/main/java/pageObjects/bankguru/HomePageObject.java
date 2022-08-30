package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.bankguru.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getManagerIdText() {
		waitForElementVisible(driver, HomePageUI.MANAGER_ID_TEXT);
		return getElementText(driver, HomePageUI.MANAGER_ID_TEXT);
	}
}
