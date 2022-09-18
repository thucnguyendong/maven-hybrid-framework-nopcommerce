package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.bankguru.AddNewCustomerPageObject;
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

	public AddNewCustomerPageObject getAddNewCustomerPage(WebDriver driver) {
		return new AddNewCustomerPageObject(driver);
	}

	public ViewCustomerPageOject getViewCustomerPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new ViewCustomerPageOject(driver);
	}
}
