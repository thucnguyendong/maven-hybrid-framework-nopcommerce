package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserRecentlyViewProductPageUI;

public class UserRecentlyViewProductPageObject extends BasePage {
	private WebDriver driver;
	
	public UserRecentlyViewProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductDisplayedByProductName(String productName) {
		return isElementDisplayed(driver, UserRecentlyViewProductPageUI.DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME,productName);
	}
}
