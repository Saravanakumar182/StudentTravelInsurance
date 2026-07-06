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


    @FindBy(xpath = "//a[normalize-space(text())='Travel']")
    private WebElement travelTab;

    @FindBy(xpath = "//p[normalize-space(text())='Travel Insurance']")
    private WebElement travelInsuranceOption;

    @FindBy(xpath = "//a[contains(text(),'Health Insurance')]")
    private WebElement healthInsurance;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickTravelTab() {
        wait.until(ExpectedConditions.elementToBeClickable(travelTab)).click();
    }

    public void clickTravelInsurance() {
        wait.until(ExpectedConditions.elementToBeClickable(travelInsuranceOption)).click();
    }

    public void clickHealthInsurance() {
        wait.until(ExpectedConditions.visibilityOf(healthInsurance));
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].removeAttribute('target');",
                        healthInsurance);
        healthInsurance.click();
    }
}