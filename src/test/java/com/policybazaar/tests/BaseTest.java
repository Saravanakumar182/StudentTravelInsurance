package com.policybazaar.tests;

import com.policybazaar.utils.ConfigReader;
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

        WebDriver webDriver = null;

        switch (browser.toLowerCase()) {

            case "chrome":
                webDriver = new ChromeDriver();
                break;

            case "edge":
                webDriver = new EdgeDriver();
                break;

            default:
                webDriver = new ChromeDriver();
                break;
        }
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