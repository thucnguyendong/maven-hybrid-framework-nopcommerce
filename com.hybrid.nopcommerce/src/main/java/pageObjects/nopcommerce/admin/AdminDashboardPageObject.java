package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import pageUI.nopcommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardPageDisplayed() {
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}
}
