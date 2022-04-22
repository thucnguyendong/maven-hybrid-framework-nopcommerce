package com.sorting;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_SwagInventorySort {
	WebDriver driver;
	SwagLoginPageObject loginPage;
	SwagInventoryPageObject inventoryPage;
	String username = "standard_user";
	String password = "secret_sauce";
	String sortOrder;
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
				
		loginPage = PageGeneratorManager.getSwagLoginPage(driver);
		loginPage.openBrowser(driver, "https://www.saucedemo.com/");
		inventoryPage = loginPage.loginAsUser(username,password);
	}
	@Test
	public void TC_01_SortByName() {
		sortOrder = "Name (A to Z)"; 
		inventoryPage.selectProductSortDropdown(sortOrder);
		assertTrue(inventoryPage.isInventoryItemSortedByNameAscending());
		sortOrder = "Name (Z to A)"; 
		inventoryPage.selectProductSortDropdown(sortOrder);
		assertTrue(inventoryPage.isInventoryItemSortedByNameDescending());
	}
	
	@Test
	public void TC_02_SortByPrice() {
		sortOrder = "Price (low to high)"; 
		inventoryPage.selectProductSortDropdown(sortOrder);
		assertTrue(inventoryPage.isInventoryItemSortedByPriceAscending());
		sortOrder = "Price (high to low)"; 
		inventoryPage.selectProductSortDropdown(sortOrder);
		assertTrue(inventoryPage.isInventoryItemSortedByPriceDescending());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
