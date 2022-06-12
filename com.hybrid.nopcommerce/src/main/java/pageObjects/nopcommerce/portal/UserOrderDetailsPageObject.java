package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerNopCommerce;

public class UserOrderDetailsPageObject extends BasePage {
	private WebDriver driver;	
	public UserOrderDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public UserProductCartPageObject clickReOrderButton() {
		clickButtonByText(driver, "Re-order");
		return PageGeneratorManagerNopCommerce.getPageGenerator().getUserProductCartPage(driver);
	}
}
