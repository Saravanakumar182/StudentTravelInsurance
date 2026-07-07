package com.policy.pages;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Adult')]")
    WebElement members;

    @FindBy(id = "maxprotec-landing-mobile")
    WebElement mobileNumber;

    @FindBy(id = "maxprotec-landing-email")
    WebElement email;

    @FindBy(id = "maxprotec-landing-pincode")
    WebElement pincode;

    @FindBy(xpath = "//span[contains(text(),'Please enter a valid Mobile number')]")
    WebElement mobileErrorMsg;

    @FindBy(xpath = "//span[contains(text(),'Please enter a valid email id')]")
    WebElement emailErrorMsg;

    @FindBy(xpath = "//*[contains(text(),'valid pincode')]")
    WebElement pincodeErrorMsg;

    @FindBy(xpath = "//button[contains(@class,'js-maxpaddsadult')]")
    WebElement adultPlusButton;

    @FindBy(id = "valid-adult1date")
    WebElement adultDay;

    @FindBy(id = "valid-adult1month")
    WebElement adultMonth;

    @FindBy(id = "valid-adult1year")
    WebElement adultYear;

    @FindBy(xpath = "//a[contains(@class,'primary-button') and contains(text(),'Add')]")
    WebElement addAdultButton;

    @FindBy(xpath = "//*[contains(text(),'Please enter valid DOB for Adult 1')]")
    WebElement adultDobErrorMsg;

    @FindBy(xpath = "//button[contains(@class,'js-maxpaddskid')]")
    WebElement kidPlusButton;

    @FindBy(id = "valid-kid1Date")
    WebElement kidDay;

    @FindBy(id = "valid-kid1Month")
    WebElement kidMonth;

    @FindBy(id = "valid-kid1Year")
    WebElement kidYear;

    @FindBy(xpath = "(//a[contains(@class,'primary-button') and text()='Add'])[2]")
    WebElement addKidButtonValidation;

    @FindBy(xpath = "//a[contains(@class,'primary-button') and text()='Add']")
    WebElement addKidButton;

    @FindBy(xpath = "//*[contains(text(),'Please enter valid DOB for Kid 1')]")
    WebElement kidDobErrorMsg;

    @FindBy(xpath = "//button[contains(.,'Get quote')]")
    WebElement getQuoteButton;

    @FindBy(xpath = "//div[contains(@class,'pageLoader')]")
    WebElement pageLoader;

    @FindBy(xpath = "//img[@alt='ICICI Lombard Logo']/parent::a")
    WebElement iciciLogo;

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isQuoteFormDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(mobileNumber));
        return members.isDisplayed()
                && mobileNumber.isDisplayed()
                && email.isDisplayed()
                && pincode.isDisplayed();
    }

    public void triggerMobileNumberValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(mobileNumber));
        mobileNumber.click();
        mobileNumber.sendKeys(Keys.TAB);
    }

    public String getMobileErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(mobileErrorMsg));
        return mobileErrorMsg.getText().trim();
    }

    public void triggerEmailValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.click();
        email.sendKeys(Keys.TAB);
    }

    public String getEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMsg));
        return emailErrorMsg.getText().trim();
    }

    public void enterPincode(String pincodeValue) {
        wait.until(ExpectedConditions.elementToBeClickable(pincode));
        pincode.clear();
        pincode.sendKeys(pincodeValue);
        pincode.sendKeys(Keys.TAB);
    }

    public String getPincodeErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(pincodeErrorMsg));
        return pincodeErrorMsg.getText().trim();
    }

    public void enterMobileNumber(String mobile) {
        wait.until(ExpectedConditions.elementToBeClickable(mobileNumber));
        mobileNumber.clear();
        mobileNumber.sendKeys(mobile);
        mobileNumber.sendKeys(Keys.TAB);
    }

    public void enterEmail(String emailValue) {
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.clear();
        email.sendKeys(emailValue);
        email.sendKeys(Keys.TAB);
    }

    public void triggerInvalidAdultDobValidation(String day, String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(adultPlusButton));
        adultPlusButton.click();
        wait.until(ExpectedConditions.visibilityOf(adultDay));
        adultDay.clear();
        adultDay.sendKeys(day);
        adultMonth.clear();
        adultMonth.sendKeys(month);
        adultYear.clear();
        adultYear.sendKeys(year);
        addAdultButton.click();
    }

    public String getAdultDobErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(adultDobErrorMsg));
        return adultDobErrorMsg.getText().trim();
    }

    public void triggerInvalidKidDobValidation(String day, String month, String year) {
        kidPlusButton.click();
        kidDay.sendKeys(day);
        kidMonth.sendKeys(month);
        kidYear.sendKeys(year);
        addKidButtonValidation.click();
    }

    public String getKidDobErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(kidDobErrorMsg));
        return kidDobErrorMsg.getText().trim();
    }

    public void enterAdultDetails(String day, String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(adultPlusButton));
        adultPlusButton.click();
        adultDay.sendKeys(day);
        adultMonth.sendKeys(month);
        adultYear.sendKeys(year);
        addAdultButton.click();
    }

    public void enterKidDetails(String day, String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(kidPlusButton));
        kidPlusButton.click();
        wait.until(ExpectedConditions.visibilityOf(kidDay));
        kidDay.clear();
        kidDay.sendKeys(day);
        kidMonth.clear();
        kidMonth.sendKeys(month);
        kidYear.clear();
        kidYear.sendKeys(year);
        kidYear.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.elementToBeClickable(addKidButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addKidButton);
    }

    public void clickGetQuote() {
        wait.until(ExpectedConditions.elementToBeClickable(getQuoteButton));
        getQuoteButton.click();
    }
    public WebElement getIciciLogo() {
        return iciciLogo;
    }
    public void clickIciciLogo() {
        wait.until(ExpectedConditions.visibilityOf(iciciLogo));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iciciLogo);
    }
}
