package com.policy.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    /**
     * Captures screenshot and returns the file path relative to the report
     * directory so ExtentReports can render it inline.
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = System.getProperty("user.dir")
                    + File.separator + "test-output"
                    + File.separator + "ExtentReports"
                    + File.separator + "screenshots";
            new File(screenshotDir).mkdirs();

            String fileName = testName + "_" + timestamp + ".png";
            String fullPath = screenshotDir + File.separator + fileName;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));

            // Return relative path (works when opening HTML report)
            return "screenshots" + File.separator + fileName;
        } catch (Exception e) {
            LoggerManager.error(ScreenshotUtil.class, "Screenshot capture failed", e);
            return null;
        }
    }
}