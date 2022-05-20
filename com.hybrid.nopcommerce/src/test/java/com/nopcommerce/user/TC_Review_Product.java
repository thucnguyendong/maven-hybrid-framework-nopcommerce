package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserProductReviewPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserMyProductReviewPageObject;

public class TC_Review_Product extends BaseTest {
	WebDriver driver;	
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	UserProductPageObject productPage;
	UserProductReviewPageObject productReviewPage;
	UserSearchPageObject searchPage;
	UserCustomerInfoPageObject customerInfoPage;
	UserMyProductReviewPageObject myReviewPage;
	
	String emailAddress;
	
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("chrome",GlobalConstants.USER_PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	@BeforeClass
	public void beforeClass() {
		registerPage = homePage.clickRegisterLink();
		
		emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";	
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		String password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		registerPage.selectMaleGender();
		registerPage.inputFirstName( firstName);
		registerPage.inputLastName( lastName);
		registerPage.selectDay( day);
		registerPage.selectMonth( month);
		registerPage.selectYear( year);
		registerPage.inputCompany( company);
		registerPage.inputEmail( emailAddress);
		registerPage.inputPassword( password);
		registerPage.inputConfirmPassword( confirmPassword);
		homePage=registerPage.clickRegisterButton();
	}
	
	@Test
	public void TC_01_Review_Product() {	
		searchPage = PageGeneratorManager.getPageGenerator().getUserSearchPage(driver);
		String searchValue = "Build your own computer";	
		searchPage.inputSearch(searchValue);
		searchPage.clickSearchButton();
		
		productPage = searchPage.selectProduct(searchValue);
		
		productReviewPage = productPage.clickReview();
		String reviewTitle = "Order "+ productReviewPage.getRandomNumber();
		String reviewText = "Testing" + productReviewPage.getRandomNumber();
		String rating = "3";
		productReviewPage.inputReviewTitle(reviewTitle);
		productReviewPage.inputReviewText(reviewText);
		productReviewPage.clickRating(rating);
		productReviewPage.clickSubmitReview();
		
		customerInfoPage = productReviewPage.clickMyAccountLink(driver);;
		
		myReviewPage = customerInfoPage.openMyReviewPage(driver);
		assertEquals(myReviewPage.getReviewTitle(), reviewTitle);
		assertEquals(myReviewPage.getReviewText(), reviewText);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
