package com.policy.basetest;

import com.policy.pages.*;
import com.policy.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected HomePage homePage;
    protected TravelInsurancePage travelPage;
    protected TravelInsurancePlanPage travelPlanPage;
    protected HealthInsurancePage healthInsurancePage;
    protected CarInsurancePage carInsurancePage;

    protected static final String COUNTRY = "Germany";
    protected static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        WebDriver webDriver = getDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get(ConfigReader.getProperty("app.url"));

        driver.set(webDriver);

        homePage= new HomePage(webDriver);
        travelPage = new TravelInsurancePage(webDriver);
        travelPlanPage =new TravelInsurancePlanPage(webDriver);
        healthInsurancePage = new HealthInsurancePage(webDriver);
        carInsurancePage = new CarInsurancePage(webDriver);

        log.info("Browser [{}] launched | URL = {}",
                browser, ConfigReader.getProperty("app.url"));
    }

    private static WebDriver getDriver(String browser) {
        WebDriver webDriver;
        switch (browser) {
            case "chrome" -> {
                webDriver = new ChromeDriver();
            }
            case "edge" -> {
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