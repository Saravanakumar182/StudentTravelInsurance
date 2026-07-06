package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class TC21_ValidateSuccessfulFormSubmission extends BaseTest {
    private static final Logger log =
            LoggerManager.getLogger(
                    TC21_ValidateSuccessfulFormSubmission.class);

    @Test(description = "TC21 - Validate Successful Form Submission")
    public void validateSuccessfulFormSubmission() {
        LoggerManager.logTestStart(
                this.getClass(),
                "TC21 - Validate Successful Form Submission");
        homePage.clickHealthInsurance();

        healthInsurancePage.enterAdultDetails(
                ConfigReader.getProperty("health.adult.day"),
                ConfigReader.getProperty("health.adult.month"),
                ConfigReader.getProperty("health.adult.year"));
        log.info("Adult details entered successfully");

        healthInsurancePage.enterKidDetails(
                ConfigReader.getProperty("health.kid.day"),
                ConfigReader.getProperty("health.kid.month"),
                ConfigReader.getProperty("health.kid.year"));
        log.info("Kid details entered successfully");

        healthInsurancePage.enterMobileNumber(
                ConfigReader.getProperty("health.valid.mobile"));
        healthInsurancePage.enterEmail(
                ConfigReader.getProperty("health.valid.email"));
        healthInsurancePage.enterPincode(
                ConfigReader.getProperty("health.valid.pincode"));
        healthInsurancePage.clickGetQuote();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains("plan-page"));
        wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        wait.until(ExpectedConditions.visibilityOf(
                        healthInsurancePage.getIciciLogo()));
        log.info("Form submitted successfully and redirected to plans page");
        healthInsurancePage.clickIciciLogo();
        log.info("Home page loaded successfully after clicking logo");
        LoggerManager.logVerification(this.getClass(),
                "Successful form submission verified",
                true);
        LoggerManager.logTestEnd(
                this.getClass(),
                "TC21",
                "PASSED");
    }
}