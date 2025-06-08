package com.helen.framework.listener;

import com.aventstack.extentreports.Status;

import com.helen.framework.PageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomListener implements ITestListener {
	private static final Path ScreenshotPath = Paths.get("src", "test", "resources", "Screenshots");

	static {
		try {
			FileUtils.deleteDirectory(ScreenshotPath.toFile());
		} catch (IOException e) {
			System.out.println("can't delete folder: " + e);
		}
	}
	public void onTestFailure(ITestResult result) {
		WebDriver driver = PageObject.getDriver();// Get the current driver
		try {
			if(driver != null) {
				File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//take screenshot
				String imagePathFull = Paths.get(ScreenshotPath.toString(),
						result.getTestClass().getRealClass().getSimpleName(), result.getName() + ".png").toString();
				String imagePathForReport = Paths.get("..", imagePathFull).toString();
				try {
					FileUtils.copyFile(file, new File(imagePathFull));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

				ExtentReport.getTest().fail("Test Failed - Screenshot Attached")
						.addScreenCaptureFromPath(imagePathForReport);
				ExtentReport.getTest().fail(result.getThrowable());
			}
			else
			{
				System.out.println("Fatal error: Empty driver");
			}
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error happened during screenshot");
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
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
		ExtentReport.getReporter().flush();
	}
}
