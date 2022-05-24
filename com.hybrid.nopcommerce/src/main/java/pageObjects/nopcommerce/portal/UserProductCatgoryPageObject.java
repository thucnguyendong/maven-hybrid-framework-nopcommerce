package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.portal.UserProductCatgoryPageUI;

public class UserProductCatgoryPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductCatgoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductCategoryLinkItem(String category) {
		waitForElementClickable(driver, UserProductCatgoryPageUI.DYNAMIC_CATEGORY_PRODUCT,category);
		clickElement(driver, UserProductCatgoryPageUI.DYNAMIC_CATEGORY_PRODUCT,category);
	}

	public String getCategoryHeader() {
		waitForElementVisible(driver, UserProductCatgoryPageUI.PRODUCT_CATEGORY_LABEL);
		return getElementText(driver, UserProductCatgoryPageUI.PRODUCT_CATEGORY_LABEL);
	}

	public void selectProductOrderDropdown(String sortBy) {
		selectItemInDefaultDropdown(driver, UserProductCatgoryPageUI.PRODUCT_ORDER_DROPDOWN, sortBy);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public boolean isProductCategorySortedAscendingByName() {
		return isDataStringSortedAscending(driver, UserProductCatgoryPageUI.PRODUCT_TITLE_LABEL);
	}
	
	public boolean isProductCategorySortedDescendingByName() {
		return isDataStringSortedDescending(driver, UserProductCatgoryPageUI.PRODUCT_TITLE_LABEL);
	}

	public boolean isProductCategorySortedAscendingByPrice() {
		return isDataFloatSortedAscending(driver, UserProductCatgoryPageUI.PRODUCT_PRICE_LABEL);
	}

	public boolean isProductCategorySortedDescendingByPrice() {
		return isDataFloatSortedDescending(driver, UserProductCatgoryPageUI.PRODUCT_PRICE_LABEL);
	}

	public void selectNumberPerPageDropdown(String numberPerPage) {
		selectItemInDefaultDropdown(driver, UserProductCatgoryPageUI.PRODUCT_PAGE_SIZE_DROPDOWN, numberPerPage);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public int getNumberOfProductPerPage() {
		waitForAllElementsVisible(driver, UserProductCatgoryPageUI.PRODUCT_TITLE_LABEL);
		return getElementSize(driver, UserProductCatgoryPageUI.PRODUCT_TITLE_LABEL);
	}
	
	public boolean isPagerDisplayed() {
		return isElementDisplayed(driver, UserProductCatgoryPageUI.PRODUCT_PAGERS);
	}
	
	public boolean isPagerNotDisplayed() {
		return isElementUndisplayed(driver, UserProductCatgoryPageUI.PRODUCT_PAGERS);
	}

	public boolean isNumberPerPageCorrect(int totalItemsDisplayed, int numberPerPage) {
		if ((totalItemsDisplayed == numberPerPage)&&(!isPagerNotDisplayed()))
			return true;
		else if ((totalItemsDisplayed <= numberPerPage)&&(isPagerNotDisplayed()))
			return true;
		else return false;
	}

	public void clickNextPage() {
		if(isPagerDisplayed()) {
			if (!isCurrentPageLastPage()) {
				waitForElementClickable(driver, UserProductCatgoryPageUI.PRODUCT_NEXT_PAGE_BUTTON);
				clickElement(driver, UserProductCatgoryPageUI.PRODUCT_NEXT_PAGE_BUTTON);
			}
		}
	}

	public void clickPreviousPage() {
		if(isPagerDisplayed()) {
			if (!isCurrentPageFirstPage()){
				waitForElementClickable(driver, UserProductCatgoryPageUI.PRODUCT_PREVIOUS_PAGE_BUTTON);
				clickElement(driver, UserProductCatgoryPageUI.PRODUCT_PREVIOUS_PAGE_BUTTON);
			}
		}
	}
	
	public boolean isCurrentPageFirstPage() {
		int currentPagePos = getElementSize(driver, UserProductCatgoryPageUI.PRODUCT_CURRENT_PAGE_POS)+1;
		if(currentPagePos==1)
			return true;
		return false;
	}
	
	public boolean isCurrentPageLastPage() {
		int currentPagePos = getElementSize(driver, UserProductCatgoryPageUI.PRODUCT_CURRENT_PAGE_POS)+1;
		int totalPages = getElementSize(driver, UserProductCatgoryPageUI.PRODUCT_PAGERS);
		if(currentPagePos==totalPages)
			return true;
		return false;
	}
}
