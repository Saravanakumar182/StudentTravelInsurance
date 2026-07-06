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

    protected void navigateToCuratedPlansPage() {
        String country     = ConfigReader.getProperty("travel.destination.country");
        String startDate   = ConfigReader.getProperty("travel.start.date");
        String endDate     = ConfigReader.getProperty("travel.end.date");
        String mobile     = ConfigReader.getProperty("travel.mobile.number");
        String email      = ConfigReader.getProperty("travel.user.email");
        int traveller1Age  = Integer.parseInt(ConfigReader.getProperty("travel.traveller1.age"));
        int traveller2Age  = Integer.parseInt(ConfigReader.getProperty("travel.traveller2.age"));

        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();

        travelPage.enterMobileNumber(mobile);
        travelPage.enterEmail(email);

        // Add both travellers
        travelPage.addTravellerByAge(traveller1Age);
        travelPage.addTravellerByAge(traveller2Age);

        // Continue to plan page
        travelPage.clickContinueToPlans();
        log.info("=== Landed on curated-plans page (assumed after form submit) ===");
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