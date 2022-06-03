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
	
	public void clickAddToWishList() {
		clickButtonByText(driver, "Add to wishlist");
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
	public void clickAddToCompareListButton() {
		clickButtonByText(driver, "Add to compare list");	
	}
	
	public UserProductComparisonPageObject clickProductComparisonLinkOnSuccessMessage() {
		waitForElementClickable(driver, UserProductPageUI.PRODUCT_COMPARISON_LINK_SUCCESS_MESSAGE);
		clickElement(driver, UserProductPageUI.PRODUCT_COMPARISON_LINK_SUCCESS_MESSAGE);
		return PageGeneratorManager.getPageGenerator().getUserProductComparisonPage(driver);
	}
}