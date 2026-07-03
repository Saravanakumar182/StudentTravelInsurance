package com.policy.basetest;
import com.policy.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        WebDriver webDriver=null;
        switch (browser) {
            case "chrome" -> {
//                ChromeOptions options = new ChromeOptions().addArguments("--headless=new");
                webDriver = new ChromeDriver();
            }
            case "edge" -> {
//                EdgeOptions options = new EdgeOptions().addArguments("--headless");
                webDriver = new EdgeDriver();
            }
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get(ConfigReader.getProperty("app.url"));
        driver.set(webDriver);
    }
    public WebDriver getDriver() {
        return driver.get();
    }
    @AfterTest
    public void tearDown() {
       if (getDriver() != null) {
           getDriver().quit();
            driver.remove();
        }
    }
}