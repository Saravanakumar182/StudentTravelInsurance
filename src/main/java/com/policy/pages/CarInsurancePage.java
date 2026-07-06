package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CarInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "car-registration")
    private WebElement vehicleNumberField;

    @FindBy(id = "car-registration-mob")
    private WebElement mobileNumberField;

    @FindBy(id = "car-registration-email")
    private WebElement emailField;

    @FindBy(id = "keyboardbindLast")
    private WebElement getQuoteBtn;

    @FindBy(xpath = "//div[@class='il-input-block focus']//span[@class='ui-error'][normalize-space()='Please enter a valid mobile number']")
    private WebElement mobileErrorMessage;

    @FindBy(xpath = "//span[normalize-space()='Please enter valid email id']")
    private WebElement emailErrorMessage;

    public void enterVehicleNumber(String vehicleNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        wait.until(ExpectedConditions.visibilityOf(vehicleNumberField));
        vehicleNumberField.clear();
        vehicleNumberField.sendKeys(vehicleNumber);
    }

    public void enterMobileNumber(String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOf(mobileNumberField)).clear();
        mobileNumberField.sendKeys(mobileNumber);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
    }

    public void clickGetQuote() {
        wait.until(ExpectedConditions.elementToBeClickable(getQuoteBtn)).click();
    }

    public String getMobileErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(mobileErrorMessage)).getText();
    }

    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emailErrorMessage)).getText();
    }
}
