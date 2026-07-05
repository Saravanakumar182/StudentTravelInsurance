package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[normalize-space(text())='Travel']")
    private WebElement travelTab;

    @FindBy(xpath = "//p[normalize-space(text())='Travel Insurance']")
    private WebElement travelInsuranceOption;

    public HomePage(WebDriver driver){
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

}