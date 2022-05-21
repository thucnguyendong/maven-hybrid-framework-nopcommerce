package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputReviewTitleTextbox(String reviewTitle) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX);
		inputIntoElement(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}
	
	public void inputReviewTextarea(String reviewText) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TEXTAREA);
		inputIntoElement(driver, UserProductReviewPageUI.REVIEW_TEXTAREA, reviewText);
	}
	
	public void selectRating(String rating) {
		waitForElementVisible(driver,UserProductReviewPageUI.DYNAMIC_RATING, rating);
		clickElement(driver,UserProductReviewPageUI.DYNAMIC_RATING, rating);
	}
	
	public void clickSubmitReview() {
		waitForElementVisible(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickElement(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}
}
