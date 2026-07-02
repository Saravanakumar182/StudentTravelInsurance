package com.policybazaar.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelInsurancePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public TravelInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // =========================================
    // Travel Insurance
    // =========================================

    @FindBy(xpath = "//p[contains(text(),'Travel')]")
    private WebElement travelButton;

    @FindBy(xpath = "//p[normalize-space()='Schengen']")
    private WebElement schengenOption;

    @FindBy(xpath = "//*[@id='modal-root']/section/article/div/div/div[2]/div[1]/input")
    private WebElement schengenCountryInput;

    @FindBy(xpath = "//label[contains(normalize-space(),'Germany')]")
    private WebElement germanyCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//div[contains(@class,'selectedCountryWrap')]//p")
    private WebElement selectedCountryText;

    // =========================================
    // Travel Dates
    // =========================================

    @FindBy(xpath = "(//div[contains(@class,'newPq_duration_wrap__dateCol')])[1]")
    private WebElement startDateField;

    @FindBy(xpath = "//button[@aria-label='Jul 10, 2026']")
    private WebElement startDate;

    @FindBy(xpath = "//button[@aria-label='Jul 16, 2026']")
    private WebElement endDate;

    @FindBy(xpath = "//button[contains(text(),'Continue') or contains(text(),'Done')]")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class,'newPq_duration_wrap__dateCol')][1]")
    private WebElement selectedStartDate;

    @FindBy(xpath = "//div[contains(@class,'newPq_duration_wrap__dateCol')][2]")
    private WebElement selectedEndDate;

    // =========================================
    // Traveller Details
    // =========================================

    @FindBy(partialLinkText = "traveller")
    private WebElement addTraveller;

    @FindBy(xpath = "//*[@id='modal-root']//article")
    private WebElement travellerModal;

    @FindBy(id = "traveller_2")
    private WebElement travellerTwoRadio;

    @FindBy(xpath = "(//*[contains(@id,'divarrow')])[1]")
    private WebElement firstTravellerAgeDropdown;

    @FindBy(xpath = "(//*[contains(@id,'divarrow')])[2]")
    private WebElement secondTravellerAgeDropdown;

    @FindBy(xpath = "//*[@id='modal-root']//article//*[contains(text(),'21')]")
    private WebElement age21Option;

    @FindBy(xpath = "//*[@id='modal-root']//article//*[contains(text(),'22')]")
    private WebElement age22Option;

    @FindBy(id = "ped_no")
    private WebElement pedNoRadio;

    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement doneButton;

    // =========================================
    // Explore Plans
    // =========================================

    @FindBy(xpath = "//button[contains(text(),'Explore Plans ›')]")
    private WebElement explorePlansButton;

    // =========================================
    // Utility
    // =========================================

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // =========================================
    // Actions
    // =========================================

    public void clickTravelInsurance() {
        wait.until(ExpectedConditions.elementToBeClickable(travelButton));
        jsClick(travelButton);
    }

    public void selectCountry(String country) {

        wait.until(ExpectedConditions.elementToBeClickable(schengenOption));
        jsClick(schengenOption);

        wait.until(ExpectedConditions.visibilityOf(schengenCountryInput));
        schengenCountryInput.clear();
        schengenCountryInput.sendKeys(country);

        wait.until(ExpectedConditions.elementToBeClickable(germanyCheckbox));
        jsClick(germanyCheckbox);

        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        jsClick(addButton);
    }

    public String getSelectedCountry() {

        wait.until(ExpectedConditions.visibilityOf(selectedCountryText));

        return selectedCountryText.getText().trim();
    }

    public void clickStartDate() {

        wait.until(ExpectedConditions.elementToBeClickable(startDateField));
        jsClick(startDateField);
    }

    public void selectTravelDates() {

        wait.until(ExpectedConditions.elementToBeClickable(startDate));
        startDate.click();

        wait.until(ExpectedConditions.elementToBeClickable(endDate));
        endDate.click();

        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    public String getStartDate() {

        wait.until(ExpectedConditions.visibilityOf(selectedStartDate));

        return selectedStartDate.getText()
                .replace("Start date", "")
                .trim();
    }

    public String getEndDate() {

        wait.until(ExpectedConditions.visibilityOf(selectedEndDate));

        return selectedEndDate.getText()
                .replace("End date", "")
                .trim();
    }

    public void clickAddTraveller() {

        wait.until(ExpectedConditions.visibilityOf(addTraveller));
        jsClick(addTraveller);
    }

    public void selectTraveller2() {

        wait.until(ExpectedConditions.visibilityOf(travellerModal));
        jsClick(travellerTwoRadio);
    }

    public void selectTravellerAges() {

        wait.until(ExpectedConditions.visibilityOf(travellerModal));

        jsClick(firstTravellerAgeDropdown);

        wait.until(ExpectedConditions.visibilityOf(age21Option));
        jsClick(age21Option);

        jsClick(secondTravellerAgeDropdown);

        wait.until(ExpectedConditions.visibilityOf(age22Option));
        jsClick(age22Option);
    }

    public void completeTravellerDetails() {

        wait.until(ExpectedConditions.visibilityOf(travellerModal));

        jsClick(pedNoRadio);
        jsClick(doneButton);
    }

    public void clickExplorePlans() {

        wait.until(ExpectedConditions.elementToBeClickable(explorePlansButton));
        jsClick(explorePlansButton);
    }
}