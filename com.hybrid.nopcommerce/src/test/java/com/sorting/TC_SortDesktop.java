package com.sorting;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_SortDesktop {
	WebDriver driver;
	SortDesktopPageObject desktopPage;
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		desktopPage = PageGeneratorManager.getDesktopPage(driver);
	}
	@Test
	public void TC_01_SortByName() {
		String sortOrder = "Name: A to Z";
		desktopPage.openBrowser(driver, "https://demo.nopcommerce.com/desktops");
		desktopPage.selectDropdownListByName(driver,"products-orderby",sortOrder);
		assertTrue(desktopPage.isItemSortedByNameAscending());
	}
	
	@Test
	public void TC_02_SortByPrice() {
		String sortOrder = "Price: Low to High";
		desktopPage.openBrowser(driver, "https://demo.nopcommerce.com/desktops");
		desktopPage.selectDropdownListByName(driver,"products-orderby",sortOrder);
		assertTrue(desktopPage.isItemSortedByPriceAscending());
		sortOrder = "Price: High to Low";
		desktopPage.selectDropdownListByName(driver,"products-orderby",sortOrder);
		assertTrue(desktopPage.isItemSortedByPriceDescending());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
