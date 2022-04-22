package com.sorting;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class SortDesktopPageObject extends BasePage {
	private WebDriver driver;

	public SortDesktopPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isItemSortedByNameAscending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataStringSortedAscending(driver, SortDesktopPageUI.PRODUCT_TITLE_LABEL);
	}

	public boolean isItemSortedByPriceAscending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataFloatSortedAscending(driver, SortDesktopPageUI.PRODUCT_PRICE_LABEL);
	}

	public boolean isItemSortedByPriceDescending() {
		areJQueryAndJSLoadedSuccess(driver);
		return isDataFloatSortedDescending(driver, SortDesktopPageUI.PRODUCT_PRICE_LABEL);
	}
}
