package com.helen.framework.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    static ExtentReports extent;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter html = new ExtentSparkReporter(Paths.get("ExtentReports","ExtentReportResults.html").toString());
            html.config().setDocumentTitle("Selenium Framework");
            html.config().setReportName("MyApp");
            extent = new ExtentReports();
            extent.attachReporter(html);
        }

        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        return test;
    }
}
