package listenerConfig;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;

public class ReportNGListener extends BaseTest implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("---------- " + result.getName() + " PASS test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebdriver();

		String screenshotPath = "data:image/png;base64," +saveScreenShootAsBase64(webDriver);
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target='_blank' href=\"file:///" + screenshotPath + "\">" + "<img src=" + screenshotPath+" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			System.out.println("---------- " + result.getName() + " FAILED test ----------");
			System.setProperty("org.uncommons.reportng.escape-output", "false");

			Object testClass = result.getInstance();
			WebDriver webDriver = ((BaseTest) testClass).getWebdriver();

			String screenshotPath = captureScreenshoot(webDriver, result.getName());
			Reporter.getCurrentTestResult();
			Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
			Reporter.setCurrentTestResult(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
