package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC20_ValidateMandatoryFieldTests extends BaseTest {

    private static final Logger log =
            LoggerManager.getLogger(TC20_ValidateMandatoryFieldTests.class);

    @Test(description = "TC20 - Validate Mandatory Field Tests")
    public void validateMandatoryFieldErrors() {

        LoggerManager.logTestStart(
                this.getClass(),
                "TC20 - Validate Mandatory Field Tests");

        homePage.clickHealthInsurance();

        healthInsurancePage.triggerMobileNumberValidation();

        String mobileError =
                healthInsurancePage.getMobileErrorMessage();

        log.info("Mobile Error : {}", mobileError);

        Assert.assertEquals(
                mobileError,
                ExcelDataReader.get("Health", "expected.mobile.error"));

        healthInsurancePage.triggerEmailValidation();

        String emailError =
                healthInsurancePage.getEmailErrorMessage();

        log.info("Email Error : {}", emailError);

        Assert.assertEquals(
                emailError,
                ExcelDataReader.get("Health", "expected.email.error"));

        LoggerManager.logVerification(
                this.getClass(),
                "Mandatory validation messages displayed successfully",
                true);

        LoggerManager.logTestEnd(
                this.getClass(),
                "TC20",
                "PASSED");
    }
}