//package com.policybazaar.tests;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class BaseTest {
//    private WebDriver driver;
//    private Properties config;
//
//    @BeforeClass
//    public void loadConfig() throws IOException {
//        config = new Properties();
//        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
//        config.load(fis);
//    }
//
//    @Parameters("browser")
//    @BeforeMethod
//    public void setUp(@Optional("") String browserParam) {
//        // Prefer browser from XML, else fallback to config.properties
//        String browser = browserParam;
//        if (browser == null || browser.isEmpty()) {
//            browser = config.getProperty("browser");
//        }
//
//        if (browser == null || browser.isEmpty()) {
//            throw new RuntimeException("Browser is not defined in testng.xml or config.properties");
//        }
//
//        // Initialize driver
//        if (browser.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//        } else if (browser.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        } else {
//            throw new RuntimeException("Unsupported browser: " + browser);
//        }
//
//        driver.manage().window().maximize();
//        driver.get(config.getProperty("url")); // ✅ URL always comes from config.properties
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//}

//package com.policy.basetest;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class BaseTest {
//    // Make driver protected so subclasses can access it
//    protected WebDriver driver;
//    private Properties config;
//
//    @BeforeClass
//    public void loadConfig() throws IOException {
//        config = new Properties();
//        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
//        config.load(fis);
//    }
//
//    @Parameters("browser")
//    @BeforeMethod
//    public void setUp(@Optional("") String browserParam) {
//        String browser = browserParam;
//        if (browser == null || browser.isEmpty()) {
//            browser = config.getProperty("browser");
//        }
//
//        if (browser == null || browser.isEmpty()) {
//            throw new RuntimeException("Browser is not defined in testng.xml or config.properties");
//        }
//
//        switch (browser.toLowerCase()) {
//            case "chrome": driver = new ChromeDriver(); break;
//            case "firefox": driver = new FirefoxDriver(); break;
//            case "edge": driver = new EdgeDriver(); break;
//            default: throw new RuntimeException("Unsupported browser: " + browser);
//        }
//
//        driver.manage().window().maximize();
//        driver.get(config.getProperty("url"));
//    }
//
//    @AfterMethod
//    public void tearDown() throws InterruptedException {
//        // Pause so user can see result before browser closes
//        Thread.sleep(2000);
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//}


package com.policy.basetest;

import com.policy.pages.HomePage;
import com.policy.pages.CarInsurancePage;
import com.policy.pages.TravelInsurancePage;
import com.policy.pages.TravelInsurancePlanPage;
import com.policy.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected HomePage homePage;
    protected TravelInsurancePage travelPage;
    protected TravelInsurancePlanPage travelPlanPage;
    protected CarInsurancePage carInsurancePage;


    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {

        WebDriver webDriver = getDriver(browser);

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get(ConfigReader.getProperty("app.url"));

        driver.set(webDriver);

        // Initialise page objects for this thread
        homePage= new HomePage(webDriver);
        travelPage = new TravelInsurancePage(webDriver);
        travelPlanPage =new TravelInsurancePlanPage(webDriver);
        carInsurancePage = new CarInsurancePage(webDriver);

        log.info("Browser [{}] launched | URL = {}",
                browser, ConfigReader.getProperty("app.url"));
    }

    private static WebDriver getDriver(String browser) {
        WebDriver webDriver;
        switch (browser) {
            case "chrome" -> {
//                ChromeOptions options = new ChromeOptions().addArguments("--headless=new");
                webDriver = new ChromeDriver();
            }
            case "edge" -> {
//                EdgeOptions options = new EdgeOptions().addArguments("--headless");
                webDriver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return webDriver;
    }

    public WebDriver getDriver() {
        return driver.get();
    }


    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            log.info("Closing browser session...");
            getDriver().quit();
            driver.remove();
            log.info("Browser closed and driver removed from ThreadLocal.");
        }
    }
}

