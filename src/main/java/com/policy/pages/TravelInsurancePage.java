package com.policy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TravelInsurancePage {

    WebDriver driver;
    WebDriverWait wait;

    public TravelInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ================= Locators =================
    @FindBy(xpath = "//a[normalize-space(text())='Travel']")
    private WebElement travelTab;

    @FindBy(xpath = "//p[normalize-space(text())='Travel Insurance']")
    private WebElement travelInsuranceOption;

    @FindBy(id = "ilcountry")
    private WebElement countryButton;

    @FindBy(xpath = "//app-single-trip//input[@type='text']")
    private WebElement travellingToInput;

    @FindBy(xpath = "//app-single-trip//ul/li/span")
    private WebElement selectedCountryChip;

    @FindBy(id = "il-start-date")
    private WebElement travelStartDateField;

    @FindBy(id = "il-end-date")
    private WebElement travelEndDateField;

    @FindBy(xpath = "//app-single-trip/div/div[4]/a")
    private WebElement continueButton;

    @FindBy(id = "mul-no")
    private WebElement mobileNumberField;

    @FindBy(id = "mul-em")
    private WebElement emailField;

    @FindBy(xpath = "//app-view-price//app-traveller-form/div[2]/a")
    private WebElement continueToPlansButton;

    // ================= Helpers =================

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element);
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void switchToMainPage() {
        driver.switchTo().defaultContent();
    }

    // ================= Navigation Actions =================
    public void clickTravelTab() {
        wait.until(ExpectedConditions.elementToBeClickable(travelTab)).click();
    }

    public void clickTravelInsurance() {
        wait.until(ExpectedConditions.elementToBeClickable(travelInsuranceOption)).click();

    }

    // ================= Country Actions =================
    public void clickCountryButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ilcountry")));
        scrollIntoView(countryButton);
        safeClick(countryButton);
    }

    public void enterDestinationCountry(String country) {
        wait.until(ExpectedConditions.visibilityOf(travellingToInput));
        travellingToInput.clear();
        travellingToInput.sendKeys(country);
    }

    public boolean isCountryAvailableInDropdown(String country) {
        String xpath = "//app-single-trip//*[contains(@class,'dropdown-item') and normalize-space(text())='" + country + "']";
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectCountryFromDropdown(String country) {
        String xpath = "//app-single-trip//*[contains(@class,'dropdown-item') and normalize-space(text())='" + country + "']";
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath(xpath))
        )).click();
    }

    public String getSelectedCountry() {
        wait.until(ExpectedConditions.visibilityOf(selectedCountryChip));
        return selectedCountryChip.getText().trim();
    }

    // ================= Date-Picker Actions =================
    public void enterTravelDates(String startDate, String endDate) {
        wait.until(ExpectedConditions.elementToBeClickable(travelStartDateField));
        scrollIntoView(travelStartDateField);
        safeClick(travelStartDateField);
        selectDateFromCalendar(startDate);
        selectDateFromCalendar(endDate);
    }

    private void selectDateFromCalendar(String dateStr) {
        String day = dateStr.split("-")[0];
        if (day.startsWith("0")) day = day.substring(1);
        String dayXpath = "//app-calender//div[normalize-space(text())='" + day + "']";
        WebElement dayCell = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(dayXpath))
        );
        scrollIntoView(dayCell);
        safeClick(dayCell);
    }

    public String getTravelStartDate() {
        return travelStartDateField.getAttribute("value").trim().replace("/", "-");
    }

    public String getTravelEndDate() {
        return travelEndDateField.getAttribute("value").trim().replace("/", "-");
    }

    public boolean isPastDate(String dateStr) {
        LocalDate inputDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return inputDate.isBefore(LocalDate.now());
    }

    public boolean tryPastDateAndCheckRejection(String dateStr) {
        wait.until(ExpectedConditions.elementToBeClickable(travelStartDateField));
        scrollIntoView(travelStartDateField);
        safeClick(travelStartDateField);
        String day = dateStr.split("-")[0];
        if (day.startsWith("0")) day = day.substring(1);
        String dayXpath = "//app-calender//div[normalize-space(text())='" + day + "']";
        try {
            WebElement dayCell = driver.findElement(By.xpath(dayXpath));
            String classAttr = dayCell.getAttribute("class");
            if (classAttr != null && (classAttr.contains("disabled") || classAttr.contains("inactive"))) {
                return true;
            }
            try {
                dayCell.click();
            } catch (Exception ignored) {
                return true;
            }
            String value = travelStartDateField.getAttribute("value");
            return value == null || value.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    // ================= Continue Button =================
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        scrollIntoView(continueButton);
        safeClick(continueButton);
        // Switch driver context into results-page iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("sfFrontendHtml")));

    }

    // ================= Mobile & Email =================
    public void enterMobileNumber(String mobile) {
        wait.until(ExpectedConditions.elementToBeClickable(mobileNumberField));
        scrollIntoView(mobileNumberField);
        mobileNumberField.clear();
        mobileNumberField.sendKeys(mobile);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        scrollIntoView(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public String getMobileNumberValue() {
        return mobileNumberField.getAttribute("value").trim();
    }

    public String getEmailValue() {
        return emailField.getAttribute("value").trim();
    }

    // ================= Validation =================
    public boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("^[6-9]\\d{9}$");
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // ---------- Action: Add ONE traveller by exact age ----------
    public void addTravellerByAge(int age) {
        int bandIndex = getBandIndexForAge(age);
        String xpath = "//app-view-price//app-traveller-form/div[1]/div[" + bandIndex + "]/div/div/a[2]";
        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );
        scrollIntoView(addButton);
        safeClick(addButton);
        System.out.println("✅ Added traveller of age " + age);
    }

    // ---------- Helper: map exact age → age band index ----------
    private int getBandIndexForAge(int age) {
        if (age >= 0  && age <= 50) return 1;   // 0-50
        if (age >= 51 && age <= 60) return 2;   // 51-60
        if (age >= 61 && age <= 70) return 3;   // 61-70
        if (age >= 71)              return 4;   // 71+
        throw new IllegalArgumentException("Invalid age: " + age);
    }

    // ---------- Action ----------
    public void clickContinueToPlans() {
        wait.until(ExpectedConditions.elementToBeClickable(continueToPlansButton));
        scrollIntoView(continueToPlansButton);
        safeClick(continueToPlansButton);
        // Wait for navigation to plan page
        wait.until(ExpectedConditions.urlContains("plan-page"));
        System.out.println("✅ Navigated to plan page: " + driver.getCurrentUrl());
    }

    // ---------- Getter for Assertion ----------
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
