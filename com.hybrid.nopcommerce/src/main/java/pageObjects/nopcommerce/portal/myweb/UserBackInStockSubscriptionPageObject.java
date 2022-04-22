package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserMyProductReviewPageUI;

public class UserBackInStockSubscriptionPageObject extends BasePage {
	private WebDriver driver;	
	public UserBackInStockSubscriptionPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
