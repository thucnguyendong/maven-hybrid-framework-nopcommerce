package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserProductCartPageUI;

public class UserProductCartPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductDisplayedByProductName(String productName) {
		return isElementDisplayed(driver, UserProductCartPageUI.DYNAMIC_PRODUCT_BUTTON_BY_PRODUCT_NAME,productName);
	}
}