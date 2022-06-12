package pageObjects.nopcommerce.portal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManagerNopCommerce;
import pageUI.nopcommerce.portal.UserWishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;
	
	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isProductDisplayedByProductName(String productName) {
		return isElementDisplayed(driver, UserWishlistPageUI.DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME, productName);
	}

	public boolean isProductRemovedByProductName(String productName) {
		// TODO Auto-generated method stub
		return isElementUndisplayed(driver, UserWishlistPageUI.DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME, productName);
	}

	public void clickRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, UserWishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME ,productName);
		clickElement(driver, UserWishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME ,productName);
		areJQueryAndJSLoadedSuccess(driver);
	}
		

	public void checkItemByProductName(String productName) {
		checkToDefaultCheckboxRadio(driver, UserWishlistPageUI.DYNAMIC_ADD_CART_CHECKBOX_BY_PRODUCT_NAME, productName);
	}
	
	public void uncheckItemByProductName(String productName) {
		uncheckToDefaultCheckboxRadio(driver, UserWishlistPageUI.DYNAMIC_ADD_CART_CHECKBOX_BY_PRODUCT_NAME, productName);
	}

	public UserProductCartPageObject clickAddToCartButton() {
		waitForElementClickable(driver, UserWishlistPageUI.ADD_TO_CART_BUTTON);
		clickElement(driver, UserWishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManagerNopCommerce.getPageGenerator().getUserProductCartPage(driver);
	}

	public void removeAllProducts() {
		List<WebElement> removeButtonList = getListElement(driver, UserWishlistPageUI.DYNAMIC_ITEM_IN_TABLE,"remove-from-cart");
		for (WebElement removeButton:removeButtonList) {
			removeButton.click();
			areJQueryAndJSLoadedSuccess(driver);
		}
	}

	public boolean isWishlistEmpty() {
		return isElementDisplayed(driver, UserWishlistPageUI.EMPTY_WISHLIST_TEXT);
	}
}