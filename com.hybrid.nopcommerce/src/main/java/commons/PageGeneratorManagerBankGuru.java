package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.bankguru.InputCustomerFormPageObject;
import pageObjects.bankguru.EditCustomerPageObject;
import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ViewCustomerPageOject;

public class PageGeneratorManagerBankGuru {
	public static PageGeneratorManagerBankGuru getPageGenerator() {
		return new PageGeneratorManagerBankGuru();
	}

	public LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public GenerateCredentialPageObject getGenerateCredentialPage(WebDriver driver) {
		return new GenerateCredentialPageObject(driver);
	}

	public InputCustomerFormPageObject getInputCustomerFormPage(WebDriver driver) {
		return new InputCustomerFormPageObject(driver);
	}

	public ViewCustomerPageOject getViewCustomerPage(WebDriver driver) {
		return new ViewCustomerPageOject(driver);
	}

	public EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
}
