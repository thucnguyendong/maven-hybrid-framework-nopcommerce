package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserFactory {
	@Override
	public WebDriver getBrowserDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FOLDER_PATH);
		options.addPreference("browser.download.useDownloaddir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", 
				"multipart/x-zip,application/vnd.ms-excel,application/x-7z-compressed,application/msword,application/pdf,application/x-rar-compressed,text/csv,image/png,image/jpeg,text/plain,text/html,application/octet-stream");
		options.addPreference("pdfjs.disabled", true);
		return new FirefoxDriver(options);
	}
}
