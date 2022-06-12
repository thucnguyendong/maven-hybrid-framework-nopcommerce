package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import reportConfig.ExtentTestManager;

public class TC_Search_With_SearchBar extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchPageObject searchPage;
	UserSearchBarPageObject searchBar;
	String searchValue;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		ExtentTestManager.startTest("Search with Search Bar testcases on " + browserName, "Search Bar Testcase");
		driver = getBrowserDriver(browserName,GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);		
	}
	
	@Test
	public void TC_01_Search_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		searchBar = PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchBar(driver);
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
		verifyEquals(searchBar.getAlertText(driver), "Please enter some search keyword");
		searchBar.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Search_Less_Than_3_Characters(Method method) {
		searchValue="ab";
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Search less than 3 characters");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value less than 3 characters");
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check error");
		verifyEquals(searchPage.getSearchErrorText(), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_03_Search_Return_No_Value(Method method) {
		searchValue="abc";
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Search non-existed product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input non-existed product");
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check error");
		verifyEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_04_Search_Return_Multiple_Value(Method method) {
		searchValue="Shoes";
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Search returning multiple products");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input product");
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check search list returned");
		verifyTrue(searchPage.getNumberOfSearchResult(searchValue)>1);
	}
	
	@Test
	public void TC_05_Search_Return_One_Value(Method method) {
		searchValue="Build your own computer";
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Search returning only 1 product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input product");
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check search list returned");
		verifyTrue(searchPage.getNumberOfSearchResult(searchValue)==1);
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
