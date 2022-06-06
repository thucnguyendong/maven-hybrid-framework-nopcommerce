package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserCheckoutPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
