package com.policy.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.policy.basetest.BaseTest;
import com.policy.utils.ExtentManager;
import com.policy.utils.ExtentTestManager;
import com.policy.utils.LoggerManager;
import com.policy.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onStart(ITestContext context) {
        LoggerManager.info(this.getClass(),
                "==== Extent suite started: " + context.getName() + " ====");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription() != null
                ? result.getMethod().getDescription() : testName;

        ExtentTest test = extent.createTest(testName, description);
        test.assignCategory(result.getTestClass().getName());
        ExtentTestManager.setTest(test);

        LoggerManager.info(this.getClass(), "▶ Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.PASS, "✔ Test Passed: " + result.getMethod().getMethodName());
        }
        LoggerManager.info(this.getClass(), "✅ PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        String testName = result.getMethod().getMethodName();

        if (test != null) {
            test.log(Status.FAIL, "✘ Test Failed: " + testName);
            test.log(Status.FAIL, result.getThrowable());

            // Attach screenshot
            WebDriver driver = getDriverFromInstance(result);
            if (driver != null) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
                if (screenshotPath != null) {
                    try {
                        test.addScreenCaptureFromPath(screenshotPath);
                    } catch (Exception e) {
                        LoggerManager.error(this.getClass(),
                                "Failed to attach screenshot", e);
                    }
                }
            }
        }
        LoggerManager.error(this.getClass(),
                "❌ FAILED: " + testName, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, "⚠ Test Skipped: " + result.getMethod().getMethodName());
            if (result.getThrowable() != null) {
                test.log(Status.SKIP, result.getThrowable());
            }
        }
        LoggerManager.warn(this.getClass(),
                "⏭ SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();   // 🚨 Critical — writes report to disk
        LoggerManager.info(this.getClass(),
                "==== Extent report generated at: " + ExtentManager.getReportPath() + " ====");
        ExtentTestManager.removeTest();
    }

    private WebDriver getDriverFromInstance(ITestResult result) {
        try {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseTest baseTest) {
                return baseTest.getDriver();
            }
        } catch (Exception e) {
            LoggerManager.error(this.getClass(), "Could not fetch driver", e);
        }
        return null;
    }
}
