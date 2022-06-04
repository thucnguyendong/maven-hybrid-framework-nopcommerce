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
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserProductCartPageObject;
import pageObjects.nopcommerce.portal.UserProductComparisonPageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserRecentlyViewProductPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.UserWishlistPageObject;
import reportConfig.ExtentTestManager;

public class TC_Wishlist_Compare_Recent_View extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchPageObject searchPage;
	UserSearchBarPageObject searchBar;
	UserProductPageObject productPage;
	String searchValue;
	UserWishlistPageObject wishlistPage;
	UserProductCartPageObject productCart;
	UserProductComparisonPageObject productComparison;
	UserRecentlyViewProductPageObject recentlyViewProduct;
	UserLoginPageObject loginPage;
	
	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage=loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
	}
	
	@Test
	public void TC_01_Add_To_Wishlist(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Add to Wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Add product to Wishlist");
		productPage.clickAddToWishListButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Check success message");
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Navigate to Wishlist page");
		wishlistPage = productPage.clickWishlistLinkOnSuccessMessage();
		wishlistPage = PageGeneratorManager.getPageGenerator().getUserWishlistPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Check that product displays on Wishlist");
		assertTrue(wishlistPage.isProductDisplayedByProductName(searchValue));
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Remove product from Wishlist page");
		wishlistPage.clickRemoveButtonByProductName(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Check that product is removed on Wishlist");
		assertTrue(wishlistPage.isProductRemovedByProductName(searchValue));
	}
	
	@Test
	public void TC_02_Add_To_Product_Cart(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		ExtentTestManager.startTest(method.getName(), "Test Case 2: Add product to cart");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search Value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Add product to wishlist");
		productPage.clickAddToWishListButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Open wishlist");
		wishlistPage = productPage.clickWishlistLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Select product");
		wishlistPage.checkItemByProductName(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Add product to cart");
		productCart = wishlistPage.clickAddToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Check that product displays on Cart");
		assertTrue(productCart.isProductDisplayedByProductName(searchValue));
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Navigate to Wishlist again");
		productPage.openUserHeaderLinkByName(driver, "Wishlist");		
		wishlistPage = PageGeneratorManager.getPageGenerator().getUserWishlistPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Check that product is removed from Wishlist");
		assertTrue(wishlistPage.isProductRemovedByProductName(searchValue));
	}
	
	@Test
	public void TC_03_Check_Empty_Wishlist(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		ExtentTestManager.startTest(method.getName(), "Test Case 3: Check wishlist is empty after removing all products");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Add product to wishlist");
		productPage.clickAddToWishListButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Check success message");
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Navigate to Success message");
		wishlistPage = productPage.clickWishlistLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Remove all products in wishlist");
		wishlistPage.removeAllProducts();
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Check that wishlist is empty");
		assertTrue(wishlistPage.isWishlistEmpty());
	}
	
	@Test
	public void TC_04_Add_Product_To_Compare(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		String searchValue2 = "Build your own computer";
		
		ExtentTestManager.startTest(method.getName(), "Test Case 4: Add product to Product Comparison page");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Add product to Compare List");
		productPage.clickAddToCompareListButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Check success message");
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your product comparison");
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Navigate to Product Comparison page");
		productComparison = productPage.clickProductComparisonLinkOnSuccessMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Click Search button");
		assertTrue(productComparison.isProductDisplayedByProductName(searchValue));
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Check that first column displays correctly for 1 product on Product Comparison");
		assertTrue(productComparison.isFirstColumnDisplayedCorrectlyForOneItem());
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Add another product on product Comparison page");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue2);
		searchPage = searchBar.clickSearchButton();
		productPage =searchPage.clickProductLink(searchValue2);
		productPage.clickAddToCompareListButton();
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your product comparison");
		productComparison = productPage.clickProductComparisonLinkOnSuccessMessage();
		assertTrue(productComparison.isProductDisplayedByProductName(searchValue));
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Check that first column displays correctly for multiple products on Product Comparison");
		assertTrue(productComparison.isFirstColumnDisplayedCorrectlyForMultipleItem());
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click Clear List button");
		productComparison.clickClearListButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Check that no item text displays");
		assertEquals(productComparison.getNoItemText(),"You have no items to compare.");
	}
	
	@Test
	public void TC_05_Check_Recently_View_Product(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		String searchValue2 = "Build your own computer";
		
		ExtentTestManager.startTest(method.getName(), "Test Case 5: Check that products display on Recently View Product after viewing product page");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input search value");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Open Product page");
		productPage =searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Search and open another product page");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue2);
		searchPage = searchBar.clickSearchButton();
		productPage =searchPage.clickProductLink(searchValue2);
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Open Recently View Product");
		productPage.openFooterPageByName(driver, "Recently viewed products");
		recentlyViewProduct = PageGeneratorManager.getPageGenerator().getUserRecentlyViewProductPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Check that all viewed products display on Recently View Product");
		assertTrue(recentlyViewProduct.isProductDisplayedByProductName(searchValue));
		assertTrue(recentlyViewProduct.isProductDisplayedByProductName(searchValue2));
	}
	
	@Parameters("browser")	
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
