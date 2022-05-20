package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.portal.UserSearchPageUI;

public class UserSearchPageObject extends BasePage {
	private WebDriver driver;
	
	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void inputSearch(String searchValue) {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_TEXTBOX);
		inputIntoElement(driver, UserSearchPageUI.SEARCH_TEXTBOX,searchValue);
	}
	
	public void clickSearchButton() {
		waitForElementClickable(driver, UserSearchPageUI.SEARCH_BUTTON);
		clickElement(driver, UserSearchPageUI.SEARCH_BUTTON);
	}
	
	public UserProductPageObject selectProduct(String searchValue) {
		String xpath = UserSearchPageUI.PRODUCT_TITLE +"/a[contains(text(),'"+ searchValue + "')]";
		waitForElementClickable(driver, xpath);
		clickElement(driver, xpath);
		return PageGeneratorManager.getPageGenerator().getUserProductPage(driver);
	}

	public String getSearchErrorText() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_ERROR);
		return getElementText(driver, UserSearchPageUI.SEARCH_ERROR);
	}
	
	public String getSearchNoValueText() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_NO_RESULT);
		return getElementText(driver, UserSearchPageUI.SEARCH_NO_RESULT);
	}


	public int getNumberOfSearchResult(String searchValue) {
		waitForAllElementsVisible(driver, UserSearchPageUI.PRODUCT_TITLE);
		return getElementSize(driver, UserSearchPageUI.PRODUCT_TITLE);
	}
}
