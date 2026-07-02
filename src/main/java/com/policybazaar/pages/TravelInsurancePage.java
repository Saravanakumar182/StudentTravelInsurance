package com.policybazaar.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

    public void clickStartDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement startDate = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[contains(@class,'newPq_duration_wrap__dateCol')])[1]")));

        startDate.click();
    }

    public void selectTravelDates() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Select Start Date - 10 July 2026
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Jul 10, 2026']"))).click();

        // Select End Date - 15 July 2026
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Jul 16, 2026']"))).click();

        // Click Done
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Done')]"))).click();
    }
    public String getStartDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement startDate = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'newPq_duration_wrap__dateCol')][1]")));

        return startDate.getText().replace("Start date","").trim();
    }

    public String getEndDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement endDate = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'newPq_duration_wrap__dateCol')][2]")));

        return endDate.getText().replace("End date","").trim();
    }
}