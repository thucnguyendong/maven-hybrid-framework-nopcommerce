package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserWishlistPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class TC_Wishlist_Compare_Recent_View extends BaseTest {
	WebDriver driver;
	UserHomePageObject homePage;
	UserSearchPageObject searchPage;
	UserSearchBarPageObject searchBar;
	UserProductPageObject productPage;
	String searchValue;
	UserWishlistPageObject wishlistPage;
	
	private String emailAddress;
	private String firstName = "Thuc";
	private String lastName= "Nguyen";
	private String company = "Livegroup";
	private String password = "123456";
	private String confirmPassword= "123456";
	private String day = "5";
	private String month = "May";
	private String year = "1995";
	private UserRegisterPageObject registerPage;
	
	//@Parameters("browser")
	@BeforeClass
	public void beforeClass() {
		//ExtentTestManager.startTest("Search with Search Bar testcases on " + browserName, "Search Bar Testcase");
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);	
		
		emailAddress = "test"+ DataHelper.getData().getRandomNumber()+"@gmail.com";		
		log.info("Step 1: Click Register Link");
		registerPage = homePage.clickRegisterLink();
		log.info("Step 2: Select radio button Male");
		registerPage.selectMaleGender();
		log.info("Step 3: Input First Name");
		registerPage.inputFirstName(firstName);
		log.info("Step 4: Input Last Name");
		registerPage.inputLastName(lastName);
		log.info("Step 5: Select Birthday Date: day, month, year");
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		log.info("Step 5: Input company name");
		registerPage.inputCompany(company);
		log.info("Step 6: Input email address");
		registerPage.inputEmail(emailAddress);
		log.info("Step 7: Input password");
		registerPage.inputPassword(password);
		log.info("Step 8: Input confirm password");
		registerPage.inputConfirmPassword(confirmPassword);
		log.info("Step 9: Click Register button");
		homePage=registerPage.clickRegisterButton();
		log.info("Step 10: Verify register's success message");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
		
	}
	
	//@Test
	public void TC_01_Search_Empty_Data(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		searchPage = searchBar.clickSearchButton();
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
		productPage =searchPage.clickProductLink(searchValue);
		productPage.clickAddToWishList();
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your wishlist");
		productPage.closeSuccessMessage();
		productPage.openUserHeaderLinkByName(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getPageGenerator().getUserWishlistPage(driver);
		assertTrue(wishlistPage.isProductNameDisplayed(searchValue));
	}
	
	@Test
	public void TC_02_Search_Empty_Data(Method method) {
		searchValue = "Asus N551JK-XO076H Laptop";
		//ExtentTestManager.startTest(method.getName(), "Test Case 1: Search empty data");
		//ExtentTestManager.getTest().log(Status.INFO, "Step 1: Click Search button");
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);
		searchBar.inputSearchTextbox(searchValue);
		searchPage = searchBar.clickSearchButton();
		//ExtentTestManager.getTest().log(Status.INFO, "Step 2: Check error");
		productPage =searchPage.clickProductLink(searchValue);
		productPage.clickAddToWishList();
		assertEquals(productPage.getSuccessMessage(),"The product has been added to your wishlist");
		productPage.closeSuccessMessage();
		productPage.openUserHeaderLinkByName(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getPageGenerator().getUserWishlistPage(driver);
		assertTrue(wishlistPage.isProductNameDisplayed(searchValue));
	}
	
	//@Parameters("browser")
	@AfterClass
	public void afterClass() {
		//ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();
	}
}
