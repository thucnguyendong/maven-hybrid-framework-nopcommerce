package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

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
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserProductReviewPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchBarPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserMyProductReviewPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class TC_Review_Product extends BaseTest {
	WebDriver driver;	
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	UserProductPageObject productPage;
	UserProductReviewPageObject productReviewPage;
	UserSearchPageObject searchPage;
	UserSearchBarPageObject searchBar;
	UserCustomerInfoPageObject customerInfoPage;
	UserMyProductReviewPageObject myReviewPage;
	UserLoginPageObject loginPage;
	
	String emailAddress;
	String searchValue;
	String reviewTitle;
	String reviewText;
	String rating;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName,GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
		loginPage = homePage.clickLogInLink();
		homePage= loginPage.loginAsUser(GlobalConstants.nopcommerce_Email, GlobalConstants.nopcommerce_Password);
		searchBar = PageGeneratorManager.getPageGenerator().getUserSearchBar(driver);

		
		searchValue = "Build your own computer";
		reviewTitle = "Order "+ DataHelper.getData().getRandomNumber();
		reviewText = "Testing" + DataHelper.getData().getRandomNumber();
		rating = "3";
	}
	
	@Test
	public void TC_01_Review_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Test Case 1: Search and review product");
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Input Search value");
		searchBar.inputSearchTextbox(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Click Search button");
		searchPage = searchBar.clickSearchButton();
		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click Product link");
		productPage = searchPage.clickProductLink(searchValue);
		ExtentTestManager.getTest().log(Status.INFO, "Step 4: Click Review link");
		productReviewPage = productPage.clickAddReviewLink();
		ExtentTestManager.getTest().log(Status.INFO, "Step 5: Input Review Title");
		productReviewPage.inputReviewTitleTextbox(reviewTitle);
		ExtentTestManager.getTest().log(Status.INFO, "Step 6: Input Review Text");
		productReviewPage.inputReviewTextarea(reviewText);
		ExtentTestManager.getTest().log(Status.INFO, "Step 7: Select rating");
		productReviewPage.selectRating(rating);
		ExtentTestManager.getTest().log(Status.INFO, "Step 8: Select Submit Review button");
		productReviewPage.clickSubmitReview();
		ExtentTestManager.getTest().log(Status.INFO, "Step 9: Click My Account link");
		customerInfoPage = productReviewPage.clickMyAccountLink(driver);;
		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Navigate to My Review page");
		myReviewPage = customerInfoPage.openMyReviewPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Verify review title and text");
		assertEquals(myReviewPage.getReviewTitle(), reviewTitle);
		assertEquals(myReviewPage.getReviewText(), reviewText);
	}
	
	@Parameters("browser")
	@AfterClass
	public void afterClass(String browserName) {
		ExtentTestManager.getTest().log(Status.INFO, "Post-condition: Close browser "+browserName);
		closeBrowserAndDriver();	
	}
}
