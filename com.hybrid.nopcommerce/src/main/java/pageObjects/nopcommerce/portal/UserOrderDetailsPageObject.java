package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;

public class UserOrderDetailsPageObject extends BasePage {
	private WebDriver driver;	
	public UserOrderDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public UserProductCartPageObject clickReOrderButton() {
		clickButtonByText(driver, "Re-order");
		return PageGeneratorManager.getPageGenerator().getUserProductCartPage(driver);
	}
}
