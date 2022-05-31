package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserWishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;
	
	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isProductNameDisplayed(String productName) {
		return isOneElementTextInListEqualValue(driver, UserWishlistPageUI.DYNAMIC_ITEM_IN_TABLE, productName,"product");
	}
}