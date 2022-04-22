package com.sorting;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static SortDesktopPageObject desktopPage;
	private static SwagLoginPageObject swagLoginPage;
	private static SwagInventoryPageObject swagInventoryPage;
	private PageGeneratorManager(){};
	public static SortDesktopPageObject getDesktopPage(WebDriver driver) {
		if(desktopPage == null) {
			desktopPage = new SortDesktopPageObject(driver);
		}
		return desktopPage;
	}
	public static SwagLoginPageObject getSwagLoginPage(WebDriver driver) {
		if(swagLoginPage == null) {
			swagLoginPage = new SwagLoginPageObject(driver);
		}
		return swagLoginPage;
	}
	public static SwagInventoryPageObject getSwagInventoryPage(WebDriver driver) {
		if(swagInventoryPage == null) {
			swagInventoryPage = new SwagInventoryPageObject(driver);
		}
		return swagInventoryPage;
	}
}
