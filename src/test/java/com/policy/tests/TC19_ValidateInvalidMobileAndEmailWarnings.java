package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class TC19_ValidateInvalidMobileAndEmailWarnings extends BaseTest {

    private static final Logger log =
            LoggerManager.getLogger(TC19_ValidateInvalidMobileAndEmailWarnings.class);
    @Test(description = "TC19 - Validate Invalid Mobile And Email Warnings")
    public void validateInvalidWarnings() {
        LoggerManager.logTestStart(
                this.getClass(),
                "TC19 - Validate Invalid Mobile And Email Warnings");
        homePage.clickHealthInsurance();
        List<String> errorList = new ArrayList<>();
        healthInsurancePage.enterMobileNumber(ConfigReader.getProperty("health.invalid.mobile"));
        String mobileError = healthInsurancePage.getMobileErrorMessage();
        errorList.add(mobileError);

        Assert.assertEquals(
                mobileError,
                ConfigReader.getProperty("health.expected.mobile.error"));

        healthInsurancePage.enterEmail(
                ConfigReader.getProperty("health.invalid.email"));
        String emailError = healthInsurancePage.getEmailErrorMessage();
        errorList.add(emailError);

        Assert.assertEquals(emailError,
                ConfigReader.getProperty("health.expected.email.error"));
        healthInsurancePage.triggerInvalidAdultDobValidation(
                ConfigReader.getProperty("health.invalid.adult.day"),
                ConfigReader.getProperty("health.invalid.adult.month"),
                ConfigReader.getProperty("health.invalid.adult.year"));
        String adultDobError = healthInsurancePage.getAdultDobErrorMessage();
        errorList.add(adultDobError);

        Assert.assertEquals(adultDobError,
                ConfigReader.getProperty("health.expected.adult.dob.error"));
        healthInsurancePage.triggerInvalidKidDobValidation(
                ConfigReader.getProperty("health.invalid.kid.day"),
                ConfigReader.getProperty("health.invalid.kid.month"),
                ConfigReader.getProperty("health.invalid.kid.year"));
        String kidDobError = healthInsurancePage.getKidDobErrorMessage();
        errorList.add(kidDobError);

        Assert.assertEquals(
                kidDobError,
                ConfigReader.getProperty("health.expected.kid.dob.error"));
        log.info("Captured Validation Messages : {}", errorList);
        LoggerManager.logVerification(
                this.getClass(),
                "All validation messages captured successfully",
                true);
        LoggerManager.logTestEnd(
                this.getClass(),
                "TC19",
                "PASSED");
    }
}