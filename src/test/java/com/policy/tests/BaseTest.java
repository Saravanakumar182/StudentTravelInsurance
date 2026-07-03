package com.policy.tests;

import com.policy.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {

        WebDriver webDriver = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver();
        };

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get(ConfigReader.getProperty("app.url"));

        driver.set(webDriver);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

     @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}