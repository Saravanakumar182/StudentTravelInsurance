package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;


public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // ⏱ increase timeout
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@role='button'][contains(text(),'Motor')]")
    private WebElement motorInsuranceNav;

    @FindBy(xpath = "//span[normalize-space()='Custom cover for your car']")
    private WebElement carInsuranceDropdown;

    public void clickMotorInsurance() {
        wait.until(ExpectedConditions.elementToBeClickable(motorInsuranceNav)).click();
    }

    public boolean isCarInsuranceDropdownVisible() {
        return wait.until(ExpectedConditions.visibilityOf(carInsuranceDropdown)).isDisplayed();
    }

    public void clickCarInsuranceDropdown() {
        Actions actions = new Actions(driver);
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(motorInsuranceNav))).perform();

        WebElement carInsurance = wait.until(ExpectedConditions.elementToBeClickable(carInsuranceDropdown));
        carInsurance.click();
    }
}
