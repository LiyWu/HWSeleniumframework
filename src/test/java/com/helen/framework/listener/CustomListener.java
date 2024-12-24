package com.helen.framework.listener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomListener implements ITestListener {
	public static String workingDir=System.getProperty("user.dir");
	private ExtentReports extentR=new ExtentReports(workingDir+"/ExtentReports/ExtentReportResults.html", true);
	static {
		// delete log file before each run
		/*try {
			*//*Files.delete(Paths.get(workingDir + "\\ExtentReports\\DevTools_log.html"));
			Files.delete(Paths.get(workingDir + "\\ExtentReports\\selenium_log.html"));*//*
		} catch (IOException e) {
			System.out.println("can't delete file " + e);
		}*/
	}
	private static ExtentTest extentT;
	private WebDriver driver;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("---------------------------------------------------");
		System.out.println("Method "+result.getName() + " Start");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String method = "";
		System.out.println("Method " + method + " Success");
	
		extentT.log(LogStatus.PASS, "Test Success "+method);
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String method = "";
		System.out.println("Method " + method + " Failed");
		extentT.log(LogStatus.FAIL, "Test Failed: "+method);
		
		if(!result.getTestContext().getAttributeNames().contains("driver")) {
			return;
		}
		
		driver=(WebDriver)result.getTestContext().getAttribute("driver");
		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
                getScreenshotAs(OutputType.BASE64);
				
 
        //Extentreports log and screenshot operations for failed tests.
       extentT.log(LogStatus.FAIL,"Test Failed",
                extentT.addBase64ScreenShot(base64Screenshot));
		extentT.log(LogStatus.INFO, "Failed Reason: " + result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String method = "";

		System.out.println("Method " + result.getName() + " Skipped");
		extentT.log(LogStatus.SKIP, "Test Skipped "+method);
		
		if(!result.getTestContext().getAttributeNames().contains("driver")) {
			return;
		}
		
		driver=(WebDriver)result.getTestContext().getAttribute("driver");
		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
                getScreenshotAs(OutputType.BASE64);
		 extentT.log(LogStatus.SKIP,"Test Skipped",
	                extentT.addBase64ScreenShot(base64Screenshot)); 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
		System.out.println("Method " + result.getName() + " Failed but Within Success Percentage");
		extentT.log(LogStatus.SKIP, "Test is failed but within Success Percentage." +result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
			
		System.out.println("Test "+context.getName()+" Start");
		
		extentT=extentR.startTest(context.getName(), "Start Test");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Test "+context.getName()+" End");
		
		extentR.endTest(extentT);
		extentR.flush();
		
	}
	
	//Method for adding logs passed from test cases
	 public static void reportLog(String message, String screenshotPath) {   
		extentT.log(LogStatus.INFO, message);
        //Extentreports log and screenshot operations for INFO steps.
        extentT.log(LogStatus.INFO, message,
                extentT.addScreenCapture(screenshotPath)); 

	}
}
