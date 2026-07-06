package com.policy.pages;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Health Insurance')]")
    private WebElement healthInsurance;

    public void clickHealthInsurance() {
        wait.until(ExpectedConditions.visibilityOf(healthInsurance));
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].removeAttribute('target');",
                        healthInsurance);
        healthInsurance.click();
    }
}