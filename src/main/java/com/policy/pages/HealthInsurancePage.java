package com.policy.pages;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthInsurancePage {
    WebDriver driver;
    WebDriverWait wait;
    public HealthInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[contains(text(),'Health Insurance')]")
    WebElement healthInsurance;
    @FindBy(xpath = "//span[contains(text(),'Adult')]")
    WebElement adults;
    @FindBy(xpath = "//span[contains(text(),'Kid')]")
    WebElement kids;
    @FindBy(id = "maxprotec-landing-mobile")
    WebElement mobileNumber;
    @FindBy(id = "maxprotec-landing-email")
    WebElement email;
    @FindBy(id = "maxprotec-landing-pincode")
    WebElement pincode;
    @FindBy(id = "maxprotec-landing-name")
    WebElement name;
    @FindBy(xpath = "//button[contains(.,'Get quote')]")
    WebElement getQuoteButton;

    public void clickHealthInsurance() {
        wait.until(
                ExpectedConditions.elementToBeClickable(healthInsurance));
        healthInsurance.click();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public boolean isQuoteFormDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(mobileNumber));
        return adults.isDisplayed()
                && kids.isDisplayed()
                && mobileNumber.isDisplayed()
                && email.isDisplayed()
                && pincode.isDisplayed()
                && name.isDisplayed()
                && getQuoteButton.isDisplayed();
    }
}