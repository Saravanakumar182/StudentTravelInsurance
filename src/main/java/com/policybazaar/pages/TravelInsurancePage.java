package com.policybazaar.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelInsurancePage {

    WebDriver driver;

    public TravelInsurancePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTravelInsurance() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement travel = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//p[contains(text(),'Travel')]")));
        travel.click();
    }

    public String getCurrentUrl() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.urlContains("travel.policybazaar.com"));

        return driver.getCurrentUrl();
    }
}