package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserProductComparisonPageUI;

public class UserProductComparisonPageObject extends BasePage {
	private WebDriver driver;
	private String[] firstColumnForOneItem = {"Name", "Price", "Screensize", "CPU Type", "Memory", "Hard drive"};
	private String[] firstColumnForMultipleItems = {"Name", "Price"};
	
	public UserProductComparisonPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductDisplayedByProductName(String productName) {
		return isElementDisplayed(driver, UserProductComparisonPageUI.DYNAMIC_PRODUCT_VALUE_BY_FIRST_COLUMN_NAME,"Name",productName);
	}
	
	public boolean isFirstColumnDisplayedCorrectlyForOneItem() {
		return isAllElementsTextEqualList(driver, UserProductComparisonPageUI.FIRST_COLUMN_LIST_TEXT, firstColumnForOneItem);
	}
	
	public boolean isFirstColumnDisplayedCorrectlyForMultipleItem() {
		return isAllElementsTextEqualList(driver, UserProductComparisonPageUI.FIRST_COLUMN_LIST_TEXT, firstColumnForMultipleItems);
	}

	public void clickClearListButton() {
		waitForElementClickable(driver, UserProductComparisonPageUI.CLEAR_LIST_BUTTON);
		clickElement(driver, UserProductComparisonPageUI.CLEAR_LIST_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public String getNoItemText() {
		waitForElementVisible(driver, UserProductComparisonPageUI.NO_ITEM_TEXT);
		return getElementText(driver, UserProductComparisonPageUI.NO_ITEM_TEXT);
	}
}