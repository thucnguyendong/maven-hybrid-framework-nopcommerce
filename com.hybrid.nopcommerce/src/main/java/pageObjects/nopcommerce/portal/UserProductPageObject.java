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
		waitForElementInvisible(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON);
		if(!isElementUndisplayed(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON)) {
			clickElement(driver, UserProductPageUI.CLOSE_SUCCESS_BUTTON);			
		}
	}	
}