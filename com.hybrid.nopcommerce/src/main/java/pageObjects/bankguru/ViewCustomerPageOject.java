package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.bankguru.ViewCustomerPageUI;

public class ViewCustomerPageOject extends BasePage {
	private WebDriver driver;
	
	public ViewCustomerPageOject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessfulMessageDisplay() {
		return isElementDisplayed(driver, ViewCustomerPageUI.REGISTER_SUCCESSFUL_MESSAGE);
	}

	public String getCustomerInformationByLabel(String label) {
		return getElementText(driver, ViewCustomerPageUI.DYNAMIC_CUSTOMER_INFORMATION_BY_LABEL,label);
	}
}
