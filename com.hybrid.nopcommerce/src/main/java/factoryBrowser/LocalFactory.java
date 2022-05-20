package factoryBrowser;

import org.openqa.selenium.WebDriver;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch(browser) {
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
