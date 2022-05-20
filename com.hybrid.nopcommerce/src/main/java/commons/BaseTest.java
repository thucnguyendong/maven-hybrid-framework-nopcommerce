package commons;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected String projectPath = System.getProperty("user.dir");
	private String screenShotLocation = projectPath+File.separator+"extentReportScrShoot"+File.separator;
	private String chromeVersion = "93.0.4577.63";
	protected String userUrl,adminUrl;
	protected final Log log;
	
	protected BaseTest() {
		log=LogFactory.getLog(getClass());
	}
	
	@BeforeSuite
	public void beforeSuit(){
		System.out.println("---------- START delete file in folder ----------");
		deleteAllFileInFolder("extentReportScrShoot");
		deleteAllFileInFolder("allure-json");
		System.out.println("---------- END delete file in folder ----------");
	}
	
	@AfterSuite(alwaysRun = true)
	protected void cleanExecutableDriver() {
		log.info("Close all drivers after suite");
		closeBrowserAndDriver();
	}
	
	public WebDriver getWebdriver() {
		return this.driver;
	}
	
	protected WebDriver getLocalBrowserDriver(String browserName) {
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("edge")){
			System.setProperty("webdriver.edge.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	protected WebDriver getHeadlessBrowserDriver(String browserName) {
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");
			option.addArguments("window-size-1440x900");
			driver = new ChromeDriver(option);
		}
		
		else if (browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"geckodriver.exe");
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("-headless");
			option.addArguments("window-size-1440x900");
			driver = new FirefoxDriver(option);
		}
		
		else {
			throw new RuntimeException("Browser name invalid");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	protected WebDriver getBrowserDriver(String browserName, String url) {
		if (browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if (browserName.equals("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserName.equals("opera")){
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		else if (browserName.equals("coc coc")){
			WebDriverManager.chromedriver().driverVersion(chromeVersion).setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String url, String ipAddress, String portNumber) {
		DesiredCapabilities capability = null;
		if (browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.WINDOWS);
			
			ChromeOptions options = new ChromeOptions();
			options.merge(capability);
		}
		
		else if (browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.WINDOWS);
			
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
		}
		
		else if (browserName.equals("edge")){
			WebDriverManager.edgedriver().setup();
			capability = DesiredCapabilities.edge();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.WINDOWS);
			
			EdgeOptions options = new EdgeOptions();
			options.merge(capability);
		}
		else if (browserName.equals("safari")){
			capability = DesiredCapabilities.safari();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.MAC);
			
			SafariOptions options = new SafariOptions();
			options.merge(capability);
		}
		else if (browserName.equals("ie")){
			WebDriverManager.iedriver().arch32().setup();
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internetExplorer");
			capability.setPlatform(Platform.WINDOWS);
			capability.setJavascriptEnabled(true);
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub",ipAddress,portNumber)),capability);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	protected void setEnvironmentURL(String environment) {
		switch (environment) {
			case "DEV":
				userUrl = GlobalConstants.USER_PORTAL_PAGE_URL;
				adminUrl = GlobalConstants.ADMIN_PAGE_URL;
				break;
			default:
				System.out.println("Wrong environment name");
		}
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	

	public void deleteAllFileInFolder(String fileName) {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + File.separator+fileName;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public String captureScreenshoot(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String srcShootPath = screenShotLocation+screenshotName+"_"+formater.format(calendar.getTime())+".png";
			FileUtils.copyFile(srcFile, new File(srcShootPath));
			return srcShootPath;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String saveScreenShootAsBase64(WebDriver driver) {
		try {
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			return scrShot.getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
