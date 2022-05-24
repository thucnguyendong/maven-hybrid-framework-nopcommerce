package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserProductCatgoryPageObject;

public class TC_Sort_Display_Paging extends BaseTest {
	WebDriver driver;	
	UserHomePageObject homePage;
	UserProductCatgoryPageObject productCatogoryPage;
	
	String category = "Notebooks";
	String sortBy;
	String numberPerPage;
	
	//@Parameters("browser")
	@BeforeClass
	public void beforeClass() {
		//ExtentTestManager.startTest("Search Advanced testcases on " + browserName, "Search Advanced Testcase");
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		homePage.openHeaderMenuByName(driver, "Computers");
		productCatogoryPage = PageGeneratorManager.getPageGenerator().getUserProductCategoryPage(driver);
		productCatogoryPage.clickProductCategoryLinkItem(category);
		assertEquals(productCatogoryPage.getCategoryHeader(),category);
	}
	
	@Test
	public void TC_01_Sort_A_To_Z(Method method) {
		sortBy = "Name: A to Z";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		assertTrue(productCatogoryPage.isProductCategorySortedAscendingByName());
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	@Test
	public void TC_02_Sort_Low_To_High(Method method) {
		sortBy = "Price: Low to High";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		assertTrue(productCatogoryPage.isProductCategorySortedAscendingByPrice());
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	@Test
	public void TC_03_Sort_High_To_Low(Method method) {
		sortBy = "Price: High to Low";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		assertTrue(productCatogoryPage.isProductCategorySortedDescendingByPrice());
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	@Test
	public void TC_04_Display_3_Value(Method method) {
		numberPerPage = "3";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
 		productCatogoryPage.clickNextPage();
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		productCatogoryPage.clickPreviousPage();
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	@Test
	public void TC_05_Display_6_Value(Method method) {
		numberPerPage = "6";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	@Test
	public void TC_06_Display_9_Value(Method method) {
		numberPerPage = "9";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
	}
	
	//@Parameters("browser")
	@AfterClass
	public void afterClass() {
		//ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
