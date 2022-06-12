package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.portal.UserLoginPageObject;

public class PageGeneratorManagerWordpress {
	public static PageGeneratorManagerWordpress getPageGenerator() {
		return new PageGeneratorManagerWordpress();
	}

	public UserLoginPageObject getUserHomePage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
}
