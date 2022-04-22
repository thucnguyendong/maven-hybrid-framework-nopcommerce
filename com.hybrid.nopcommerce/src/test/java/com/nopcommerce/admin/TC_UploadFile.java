package com.nopcommerce.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.admin.AdminProductDetailPageObject;
import pageObjects.nopcommerce.admin.AdminProductsPageObject;

public class TC_UploadFile extends BaseTest {
	WebDriver driver;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminProductsPageObject adminProductPage;
	AdminProductDetailPageObject adminProductDetailPage;
	String adminEmailAddress = "admin@yourstore.com";
	String adminPassword="admin";
	String productName;
	
	@BeforeTest
	public void beforeTest() {
		setEnvironmentURL("DEV");
		driver = getBrowserDriver("chrome",adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
	}
	
	@Test
	public void TC_Upload_File() {
		productName = "Nikon D5500 DSLR";
		String headerName = "Product name";
		String panel = "Pictures";
		String picture = "Test_01.jpg";
		adminDashboardPage.clickAdminSideMenuItem(driver, "Catalog");
		adminDashboardPage.clickAdminSideMenuSubItem(driver, "Products");
		adminProductPage = PageGeneratorManager.getAdminProductsPage(driver);
		adminProductPage.inputProductName(productName);
		adminProductPage.clickSearchButton();
		adminProductDetailPage = adminProductPage.clickEditByValueOfColumn(headerName,productName);
		
		adminProductDetailPage.clickToExpandPanel(panel);
		adminProductDetailPage.clickToAddProductPictureButton(picture);
		adminProductDetailPage.inputAltTextbox(picture);
		adminProductDetailPage.inputTitleTextbox(picture);
		adminProductDetailPage.addProductPicture();
		assertTrue(adminProductDetailPage.isPictureDisplayed("0",picture,picture));
		
		adminProductDetailPage.clickSaveButton();
		assertTrue(adminProductPage.getSuccessMessage().contains("The product has been updated successfully."));
		
		adminDashboardPage.clickAdminSideMenuItem(driver, "Catalog");
		adminDashboardPage.clickAdminSideMenuSubItem(driver, "Products");
		adminProductPage = PageGeneratorManager.getAdminProductsPage(driver);
		adminProductPage.inputProductName(productName);
		adminProductPage.clickSearchButton();
		adminProductDetailPage = adminProductPage.clickEditByValueOfColumn(headerName,productName);
		
		adminProductDetailPage.clickToExpandPanel(panel);
		adminProductDetailPage.deletePictureByTitle(picture);
		assertTrue(adminProductDetailPage.isPictureRemoved("0",picture,picture));
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
