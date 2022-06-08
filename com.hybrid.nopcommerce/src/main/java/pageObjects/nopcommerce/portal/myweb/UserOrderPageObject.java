package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserOrderDetailsPageObject;
import pageUI.nopcommerce.portal.UserOrderPageUI;

public class UserOrderPageObject extends BasePage {
	private WebDriver driver;	
	public UserOrderPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public UserOrderDetailsPageObject clickDetailsLinkByOrderNumber(String orderNumber) {
		waitForElementClickable(driver,UserOrderPageUI.DETAILS_LINK_BY_ORDER_NUMBER,"Order Number: "+orderNumber);
		clickElement(driver, UserOrderPageUI.DETAILS_LINK_BY_ORDER_NUMBER,"Order Number: "+orderNumber);
		return PageGeneratorManager.getPageGenerator().getUserOrderDetailsPage(driver);
	}
}
