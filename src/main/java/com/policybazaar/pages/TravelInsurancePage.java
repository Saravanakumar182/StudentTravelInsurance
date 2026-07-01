package com.policybazaar.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelInsurancePage {

    WebDriver driver;
    WebDriverWait wait;

    public TravelInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickTravelInsurance() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[contains(text(),'Travel')]"))).click();
    }

    public void selectCountry(String country) {

        // Click country field
        WebElement countryInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("country")));
        countryInput.click();

        // Enter country name
        countryInput.sendKeys(country);

        // Select from dropdown
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(text(),'" + country + "')]")));

        option.click();

        // Click Done button if available
//        try {
//            WebElement doneButton = wait.until(
//                    ExpectedConditions.elementToBeClickable(
//                            By.xpath("//button[contains(text(),'Done')]")));
//            doneButton.click();
//        } catch (Exception e) {
//            // Ignore if Done button not available
//        }
    }

    public String getSelectedCountry() {

        WebElement selectedCountry = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'selectedCountryWrap')]//p")));

        return selectedCountry.getText().trim();
    }
}