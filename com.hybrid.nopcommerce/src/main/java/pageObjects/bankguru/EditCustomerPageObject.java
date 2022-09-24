package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerBankGuru;
import pageUI.bankguru.EditCustomerPageUI;

public class EditCustomerPageObject extends BasePage {
	private WebDriver driver;
	
	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputCustomerID(String customerID) {
		inputToTextboxByName(driver, "cusid", customerID);
	}

	public InputCustomerFormPageObject clickSubmitButton() {
		waitForElementClickable(driver, EditCustomerPageUI.SUBMIT_BUTTON);
		clickElement(driver, EditCustomerPageUI.SUBMIT_BUTTON);
		return PageGeneratorManagerBankGuru.getPageGenerator().getInputCustomerFormPage(driver);		
	}
}
