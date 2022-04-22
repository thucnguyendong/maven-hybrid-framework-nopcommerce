package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserMyProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	private WebDriver driver;	
	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getReviewTitle() {
		waitForElementVisible(driver, UserMyProductReviewPageUI.REVIEW_TITLE);
		return getElementText(driver, UserMyProductReviewPageUI.REVIEW_TITLE);
	}
	
	public String getReviewText() {
		waitForElementVisible(driver, UserMyProductReviewPageUI.REVIEW_TEXT);
		return getElementText(driver, UserMyProductReviewPageUI.REVIEW_TEXT);
	}
}
