package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.bankguru.GenerateCredentialPageObject;
import pageObjects.bankguru.LoginPageObject;

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
}
