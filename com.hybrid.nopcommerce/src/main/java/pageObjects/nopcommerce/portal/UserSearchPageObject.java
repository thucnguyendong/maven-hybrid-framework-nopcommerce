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
	
	
	public void inputSearchTextbox(String searchValue) {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_TEXTBOX);
		inputIntoElement(driver, UserSearchPageUI.SEARCH_TEXTBOX,searchValue);
	}
	
	public void clickSearchButton() {
		waitForElementClickable(driver, UserSearchPageUI.SEARCH_BUTTON);
		clickElement(driver, UserSearchPageUI.SEARCH_BUTTON);
	}
	
	public UserProductPageObject clickProductLink(String searchValue) {
		waitForElementClickable(driver, UserSearchPageUI.DYNAMIC_PRODUCT_LINK ,searchValue);
		clickElement(driver, UserSearchPageUI.DYNAMIC_PRODUCT_LINK ,searchValue);
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


	public void checkAdvancedSearchCheckbox() {
		waitForElementClickable(driver, UserSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
	}
	public void uncheckAdvancedSearchCheckbox() {
		waitForElementClickable(driver, UserSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		uncheckToDefaultCheckboxRadio(driver, UserSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
	}

	public void selectCategoryDropdown(String category) {
		selectItemInDefaultDropdown(driver, UserSearchPageUI.CATEGORY_DROPDOWN, category);
	}

	public void checkSearchSubCategoryCheckbox() {
		waitForElementClickable(driver, UserSearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserSearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
	}
	public void uncheckSearchSubCategoryCheckbox() {
		waitForElementClickable(driver, UserSearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
		uncheckToDefaultCheckboxRadio(driver, UserSearchPageUI.SEARCH_SUB_CATEGORY_CHECKBOX);
	}

	public boolean isSearchValueEqualProductTitle(String searchValue) {
		areJQueryAndJSLoadedSuccess(driver);
		return isElementTextInListEqualValue(driver, UserSearchPageUI.PRODUCT_TITLE, searchValue);
	}


	public boolean isSearchValueContainProductTitle(String searchValue) {
		areJQueryAndJSLoadedSuccess(driver);
		return isElementTextInListContainValue(driver, UserSearchPageUI.PRODUCT_TITLE, searchValue);
	}


	public void selectManufacturerDropdown(String manufacturer) {
		selectItemInDefaultDropdown(driver, UserSearchPageUI.MANUFACTURER_DROPDOWN, manufacturer);
	}
}
