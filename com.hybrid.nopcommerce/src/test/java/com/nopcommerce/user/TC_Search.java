package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;

public class TC_Search extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchPageObject searchPage;
	String searchValue;
	
	@BeforeClass
	public void beforeClass() {
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Search_Empty_Data() {
		searchPage = PageGeneratorManager.getUserSearchPage(driver);
		searchPage.clickSearchButton();
		assertEquals(searchPage.getAlertText(driver), "Please enter some search keyword");
		searchPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Search_Less_Than_3_Characters() {
		searchValue="ab";
		searchPage.inputSearch(searchValue);
		searchPage.clickSearchButton();
		assertEquals(searchPage.getSearchErrorText(), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_03_Search_Return_No_Value() {
		searchValue="abc";
		searchPage.inputSearch(searchValue);
		searchPage.clickSearchButton();
		assertEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_04_Relative_Search_With_Product_Name() {
		searchValue="Shoes";
		searchPage.inputSearch(searchValue);
		searchPage.clickSearchButton();
		assertTrue(searchPage.getNumberOfSearchResult(searchValue)>1);
	}
	
	@Test
	public void TC_05_Absolute_Search_With_Product_Name() {
		searchValue="Build your own computer";
		searchPage.inputSearch(searchValue);
		searchPage.clickSearchButton();
		assertTrue(searchPage.getNumberOfSearchResult(searchValue)==1);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
