package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerNopCommerce;
import pageUI.nopcommerce.portal.UserSearchBarPageUI;

public class UserSearchBarPageObject extends BasePage {
	private WebDriver driver;
	
	public UserSearchBarPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputSearchTextbox(String searchValue) {
		waitForElementVisible(driver, UserSearchBarPageUI.SEARCH_TEXTBOX);
		inputIntoElement(driver, UserSearchBarPageUI.SEARCH_TEXTBOX,searchValue);
	}
	
	public UserSearchPageObject clickSearchButton() {
		waitForElementClickable(driver, UserSearchBarPageUI.SEARCH_BUTTON);
		clickElement(driver, UserSearchBarPageUI.SEARCH_BUTTON);
		return PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchPage(driver);
	}
}
