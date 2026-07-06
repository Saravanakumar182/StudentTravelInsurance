package com.policy.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ExtentManager {

    private static ExtentReports extent;
    private static String reportPath;

    private ExtentManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        // Timestamped filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "ExtentReports";
        new File(reportDir).mkdirs();

        reportPath = reportDir + File.separator + "ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("ICICI Lombard Automation Report");
        spark.config().setReportName("Travel Insurance – Test Execution Results");
        spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
        spark.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // System info block (shows at top of report)
        extent.setSystemInfo("Project", "ICICI Lombard Automation");
        extent.setSystemInfo("Framework", "Java + Selenium + TestNG + POM");
        extent.setSystemInfo("Author", "Senbagavel, Saravanakumar");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA");
    }

    public static String getReportPath() {
        return reportPath;
    }
}