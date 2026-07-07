package com.policy.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BikeInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;


    public BikeInsurancePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='PublicWrapper']//ul[contains(@class,'menu-list')]/li[1]/a")
    private WebElement motorInsuranceMenu;

    @FindBy(id = "Two_Wheeler_Insurance")
    private WebElement bikeInsuranceDropdownOption;

    @FindBy(xpath = "//input[@id='twlanding-vehile']")
    private WebElement regNumberInput;

    @FindBy(xpath = "//input[@id='twlanding-mobile']")
    private WebElement mobileInput;

    @FindBy(id = "newveh")
    private WebElement gotANewVehicleLink;

    @FindBy(xpath= "//button[@id='keyboardbindLast']")
    private WebElement clickGetQuoteButton;

    @FindBy(xpath = "//span[contains(@class,'ui-error')]")
    private WebElement  mobileErrorMessage;

    @FindBy(id = "tc-con")
    private WebElement termsCheckbox;

    @FindBy(id = "error-accept")
    private WebElement termsErrorMessage;

    @FindBy(xpath = "//input[@id='twoRg']")
    private WebElement citySearchInput;

    @FindBy(xpath = "//a[@class='primary-btn']")
    private WebElement ProceedButton;

    @FindBy(xpath = "//span[(@class='error_message')]")
    private WebElement vehicleDetailsErrorMessage;

    public void navigateToBikeInsuranceViaMenu() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(motorInsuranceMenu));
        actions.moveToElement(motorInsuranceMenu).perform();
    }

    public void clickBikeTab() {
        navigateToBikeInsuranceViaMenu();
        bikeInsuranceDropdownOption.click();
    }

    public boolean isBikeQuoteFormDisplayed() {
        return regNumberInput.isDisplayed();
    }

    public void enterRegistrationNumber(String regNumber) {
        regNumberInput.clear();
        regNumberInput.sendKeys(regNumber);
    }

    public void enterMobileNumber(String mobile) {
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }


    public void clickGetQuote() {
        clickGetQuoteButton.click();
    }

    public boolean isMobileErrorDisplayed() {
        try {
            return mobileErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMobileErrorText() {
        return mobileErrorMessage.getText();
    }

    public void clickGotANewVehicle() {
        gotANewVehicleLink.click();
    }

    public void uncheckTermsAndConditions() {
        if (termsCheckbox.isSelected()) {
            try {
                termsCheckbox.click();
            } catch (ElementNotInteractableException e) {
                js.executeScript("arguments[0].click();", termsCheckbox);
            }
        }
    }

    public boolean isTermsErrorDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            return termsErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTermsErrorText() {
        wait.until(ExpectedConditions.visibilityOf(termsErrorMessage));
        return termsErrorMessage.getText();
    }

    public void checkTermsAndConditions() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tc-con")));
        if (!termsCheckbox.isSelected()) {
            try {
                termsCheckbox.click();
            } catch (ElementNotInteractableException e) {
                js.executeScript("arguments[0].click();", termsCheckbox);
            }
        }
    }


    public void selectCityOfRegistration(String cityFromProperties) {
        wait.until(ExpectedConditions.visibilityOf(citySearchInput));
        citySearchInput.sendKeys(cityFromProperties.split("-")[1]);
        String cityXpath = "//li[contains(text(), '" + cityFromProperties + "')]";
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cityXpath)));
        cityOption.click();
    }


    public void clickProceed() {
        ProceedButton.click();
    }

    public boolean isVehicleDetailsErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(vehicleDetailsErrorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getVehicleDetailsErrorText() {
        return wait.until(ExpectedConditions.visibilityOf(vehicleDetailsErrorMessage)).getText();
    }

}
