package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputReviewTitle(String reviewTitle) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX);
		inputIntoElement(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}
	
	public void inputReviewText(String reviewText) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TEXTAREA);
		inputIntoElement(driver, UserProductReviewPageUI.REVIEW_TEXTAREA, reviewText);
	}
	
	public void clickRating(String rating) {
		String xpath = UserProductReviewPageUI.VALUE_RADIOBUTTON+ rating + "']";
		waitForElementVisible(driver, xpath);
		clickElement(driver, xpath);
	}
	
	public void clickSubmitReview() {
		waitForElementVisible(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickElement(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}
}
