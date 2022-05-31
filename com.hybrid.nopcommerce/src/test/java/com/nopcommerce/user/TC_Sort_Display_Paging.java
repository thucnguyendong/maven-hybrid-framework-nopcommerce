package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pageObjects.nopcommerce.portal.UserProductCatgoryPageObject;
import reportConfig.ExtentTestManager;

public class TC_Sort_Display_Paging extends BaseTest {
	WebDriver driver;	
	UserHomePageObject homePage;
	UserProductCatgoryPageObject productCatogoryPage;
	
	String category = "Notebooks";
	String sortBy;
	String numberPerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		ExtentTestManager.startTest("Search Advanced testcases on " + browserName, "Search Advanced Testcase");
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
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Sort A to Z ");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select sort A to Z on dropdown list");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check list after sorting");
		assertTrue(productCatogoryPage.isProductCategorySortedAscendingByName());
	}
	
	@Test
	public void TC_02_Sort_Low_To_High(Method method) {
		sortBy = "Price: Low to High";
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Sort Low to high by Price");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select sort 'Low to high' on dropdown list");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check list after sorting");
		assertTrue(productCatogoryPage.isProductCategorySortedAscendingByPrice());
	}
	
	@Test
	public void TC_03_Sort_High_To_Low(Method method) {
		sortBy = "Price: High to Low";
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Sort High to low by Price");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select sort 'High to low' on dropdown list");
		productCatogoryPage.selectProductOrderDropdown(sortBy);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check list after sorting");
		assertTrue(productCatogoryPage.isProductCategorySortedDescendingByPrice());
	}
	
	@Test
	public void TC_04_Display_3_Value(Method method) {
		numberPerPage = "3";
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Display 3 value");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select number of item per page = 3");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check number per page");
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Next Page");
 		productCatogoryPage.clickNextPage();
 		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Check number per page");
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Click Previous Page");
		productCatogoryPage.clickPreviousPage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Check number per page");
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
	}
	
	@Test
	public void TC_05_Display_6_Value(Method method) {
		numberPerPage = "6";
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Display 6 value");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select number of item per page = 6");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check number per page");
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));	
	}
	
	@Test
	public void TC_06_Display_9_Value(Method method) {
		numberPerPage = "9";
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Display 9 value");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Select number of item per page = 9");
		productCatogoryPage.selectNumberPerPageDropdown(numberPerPage);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check number per page");
		assertTrue(productCatogoryPage.isNumberPerPageCorrect(productCatogoryPage.getNumberOfProductPerPage(),Integer.parseInt(numberPerPage)));
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
