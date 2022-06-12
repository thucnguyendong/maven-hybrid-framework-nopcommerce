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
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import reportConfig.ExtentTestManager;

public class TC_Search_Advanced extends BaseTest {
	WebDriver driver;	
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	UserSearchPageObject searchPage;
	UserSearchBarPageObject searchBar;
	
	String searchValue;
	String category;
	String manufacturer;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		ExtentTestManager.startTest("Search Advanced testcases on " + browserName, "Search Advanced Testcase");
		driver = getBrowserDriver(browserName,GlobalConstants.NOPCOMMERCE_USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserHomePage(driver);
		homePage.openFooterPageByName(driver, "Search");
	}
	
	@Test
	public void TC_01_Search_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		searchPage = PageGeneratorManagerNopCommerce.getPageGenerator().getUserSearchPage(driver);
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
		verifyEquals(searchPage.getSearchErrorText(), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_02_Search_Less_Than_3_Characters(Method method) {
		searchValue="ab";
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Search less than 3 characters");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value less than 3 characters");
		searchPage.inputSearchTextbox(searchValue);
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
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check error");
		verifyEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_04_Relative_Search_With_Product_Name(Method method) {
		searchValue="Apple";
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Search product relatively");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check that product title contains search value");
		verifyTrue(searchPage.isSearchValueContainProductTitle(searchValue));
		verifyFalse(searchPage.isSearchValueEqualProductTitle(searchValue));
	}
	
	@Test
	public void TC_05_Absolute_Search_With_Product_Name(Method method) {
		searchValue="Apple MacBook Pro 13-inch";
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Search product absolutely");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Check that product title equals search value");
		verifyTrue(searchPage.isSearchValueEqualProductTitle(searchValue));
	}
	
	@Test
	public void TC_06_Advanced_Search_Parent_Category(Method method) {
		searchValue="Apple MacBook Pro";
		category = "Computers";
		ExtentTestManager.startTest(method.getName(), "Test Case 6: Search incorrectly with parent category");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check Advanced Search checkbox");
		searchPage.checkAdvancedSearchCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select parent category");
		searchPage.selectCategoryDropdown(category);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Check error");
		verifyEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_07_Advanced_Search_Sub_Category(Method method) {
		searchValue="Apple MacBook Pro";
		category = "Computers";
		ExtentTestManager.startTest(method.getName(), "Test Case 7: Search correctly with sub parent category");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check Advanced Search checkbox");
		searchPage.checkAdvancedSearchCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select parent category");
		searchPage.selectCategoryDropdown(category);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoryCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Check that product title equals search value");
		verifyTrue(searchPage.isSearchValueContainProductTitle(searchValue));
	}
	
	@Test
	public void TC_08_Advanced_Search_Incorrect_Manufacturer(Method method) {
		searchValue="Apple MacBook Pro";
		category = "Computers";
		manufacturer = "HP";
		ExtentTestManager.startTest(method.getName(), "Test Case 7: Search incorrectly with Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check Advanced Search checkbox");
		searchPage.checkAdvancedSearchCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select parent category");
		searchPage.selectCategoryDropdown(category);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoryCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select manufacturer category");
		searchPage.selectManufacturerDropdown(manufacturer);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Check error");
		verifyEquals(searchPage.getSearchNoValueText(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_09_Advanced_Search_Correct_Manufacturer(Method method) {
		searchValue="Apple MacBook Pro";
		category = "Computers";
		manufacturer = "Apple";
		ExtentTestManager.startTest(method.getName(), "Test Case 7: Search correctly with Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchPage.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check Advanced Search checkbox");
		searchPage.checkAdvancedSearchCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Select parent category");
		searchPage.selectCategoryDropdown(category);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoryCheckbox();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Select manufacturer category");
		searchPage.selectManufacturerDropdown(manufacturer);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Click Search button");
		searchPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Check that product title equals search value");
		verifyTrue(searchPage.isSearchValueContainProductTitle(searchValue));
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
