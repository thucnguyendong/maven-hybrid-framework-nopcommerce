package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.portal.UserProductPageUI;

public class UserProductPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductPageObject(WebDriver driver) {
		this.driver = driver;
	}	
	public UserProductReviewPageObject clickAddReviewLink() {
		waitForElementClickable(driver, UserProductPageUI.ADD_REVIEW_LINK);
		clickElement(driver, UserProductPageUI.ADD_REVIEW_LINK);
		return PageGeneratorManager.getPageGenerator().getUserProductReviewPage(driver);
	}
	
	public void clickAddToWishListButton() {
		clickButtonByText(driver, "Add to wishlist");
	}
	
	public void clickAddToCompareListButton() {
		clickButtonByText(driver, "Add to compare list");	
	}
	
	public void clickAddToCartButton() {
		clickButtonByText(driver, "Add to cart");		
	}
	
	public void clickUpdateButton() {
		clickButtonByText(driver, "Update");		
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, UserProductPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserProductPageUI.SUCCESS_MESSAGE);
	}
	
	public void closeSuccessMessage() {
		if(!isElementUndisplayed(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON)) {
			clickElement(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON);
			waitForElementInvisible(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON);
		}
	}
	public UserWishlistPageObject clickWishlistLinkOnSuccessMessage() {
		waitForElementClickable(driver, UserProductPageUI.WISHLIST_LINK_SUCCESS_MESSAGE);
		clickElement(driver, UserProductPageUI.WISHLIST_LINK_SUCCESS_MESSAGE);
		return PageGeneratorManager.getPageGenerator().getUserWishlistPage(driver);
	}

	public UserProductComparisonPageObject clickProductComparisonLinkOnSuccessMessage() {
		waitForElementClickable(driver, UserProductPageUI.PRODUCT_COMPARISON_LINK_SUCCESS_MESSAGE);
		clickElement(driver, UserProductPageUI.PRODUCT_COMPARISON_LINK_SUCCESS_MESSAGE);
		return PageGeneratorManager.getPageGenerator().getUserProductComparisonPage(driver);
	}
	public void selectProcessorDropdown(String processor) {
		selectItemInDefaultDropdown(driver, UserProductPageUI.PROCESSOR_DROPDOWN, processor);
	}
	public void selectRamDropdown(String ram) {
		selectItemInDefaultDropdown(driver, UserProductPageUI.RAM_DROPDOWN, ram);		
	}
	public void selectHDDRadio(String hdd) {
		checkToDefaultCheckboxRadio(driver, UserProductPageUI.HDD_RADIO_BUTTON,hdd);
	}
	public void selectOSRadio(String os) {
		checkToDefaultCheckboxRadio(driver, UserProductPageUI.OS_RADIO_BUTTON,os);	
	}
	public void checkSoftwareCheckbox(String software) {
		checkToDefaultCheckboxRadio(driver, UserProductPageUI.SOFTWARE_CHECKBOX,software);
	}
	
	public void uncheckSoftwareCheckbox(String software) {
		uncheckToDefaultCheckboxRadio(driver, UserProductPageUI.SOFTWARE_CHECKBOX,software);
	}
	public String getTotalPrice() {
		areJQueryAndJSLoadedSuccess(driver);
		return getElementText(driver, UserProductPageUI.TOTAL_PRICE_TEXT);
	}
	public UserProductCartPageObject clickProductCartLinkOnSuccessMessage() {
		waitForElementClickable(driver, UserProductPageUI.PRODUCT_CART_LINK_SUCCESS_MESSAGE);
		clickElement(driver, UserProductPageUI.PRODUCT_CART_LINK_SUCCESS_MESSAGE);
		return PageGeneratorManager.getPageGenerator().getUserProductCartPage(driver);
	}
}