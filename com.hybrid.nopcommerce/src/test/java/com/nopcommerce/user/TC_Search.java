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
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import reportConfig.ExtentTestManager;

public class TC_Search extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchPageObject searchPage;
	String searchValue;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);		
	}
	
	@Test
	public void TC_01_Search_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		searchPage = PageGeneratorManager.getPageGenerator().getUserSearchPage(driver);
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
		verifyEquals(searchPage.getAlertText(driver), "Please enter some search keyword");
		searchPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Search_Less_Than_3_Characters(Method method) {
		searchValue="ab";
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Search less than 3 characters");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value less than 3 characters");
		searchPage.inputSearch(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check error");
		verifyEquals(searchPage.getSearchErrorText(), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_03_Search_Return_No_Value(Method method) {
		searchValue="abc";
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Search non-existed product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input non-existed product");
		searchPage.inputSearch(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check error");
		verifyEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_04_Relative_Search_With_Product_Name(Method method) {
		searchValue="Shoes";
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Search returning multiple products");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input product");
		searchPage.inputSearch(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check search list returned");
		verifyTrue(searchPage.getNumberOfSearchResult(searchValue)>1);
	}
	
	@Test
	public void TC_05_Absolute_Search_With_Product_Name(Method method) {
		searchValue="Build your own computer";
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Search returning only 1 product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input product");
		searchPage.inputSearch(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
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
