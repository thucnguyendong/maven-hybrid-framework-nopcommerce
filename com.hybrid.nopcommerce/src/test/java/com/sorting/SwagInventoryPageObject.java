package com.sorting;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class SwagInventoryPageObject extends BasePage {
	private WebDriver driver;

	public SwagInventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectProductSortDropdown(String sortOrder) {
		selectItemInDefaultDropdown(driver, SwagInventoryPageUI.PRODUCT_SORT_DROPDOWNLIST, sortOrder);
	}

	public boolean isInventoryItemSortedByNameAscending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataStringSortedAscending(driver, SwagInventoryPageUI.PRODUCT_NAME_LABEL);
	}

	public boolean isInventoryItemSortedByNameDescending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataStringSortedDescending(driver, SwagInventoryPageUI.PRODUCT_NAME_LABEL);
	}
	
	public boolean isInventoryItemSortedByPriceAscending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataFloatSortedAscending(driver, SwagInventoryPageUI.PRODUCT_PRICE_LABEL);
	}

	public boolean isInventoryItemSortedByPriceDescending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataFloatSortedDescending(driver, SwagInventoryPageUI.PRODUCT_PRICE_LABEL);
	}

}
