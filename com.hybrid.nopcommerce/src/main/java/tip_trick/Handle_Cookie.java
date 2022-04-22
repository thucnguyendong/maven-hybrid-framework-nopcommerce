package tip_trick;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Handle_Cookie extends BaseTest {
	WebDriver driver;
	Set<Cookie> allCookies;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = getBrowserDriver("chrome","https://www.facebook.com/");
	}

	@Test
	public void TC_01_Get_Cookie() {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("...");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("...");
		driver.findElement(By.xpath("//input[@data-testid='royal_login_button']")).click();

		sleepInSecond(5);

		allCookies = driver.manage().getCookies();
		System.out.println("Cookie name = " + allCookies);
	}

	@Test
	public void TC_02_Set_Cookie() {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

		allCookies = driver.manage().getCookies();
		System.out.println("Cookie name = " + allCookies);
		driver.navigate().refresh();
		sleepInSecond(5);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}