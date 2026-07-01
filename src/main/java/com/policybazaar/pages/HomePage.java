package com.policybazaar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    protected WebDriver driver;
    protected WebDriverWait wait; // ✅ DECLARED here

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ CREATED here
    }


    By travelOption = By.xpath("//p[text()='Travel']");

    public void clickTravel() {
        driver.findElement(travelOption).click();
    }

}