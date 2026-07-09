package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.utils.ExcelDataReader;
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

        healthInsurancePage.enterMobileNumber(
                ExcelDataReader.get("Health", "invalid.mobile"));

        String mobileError =
                healthInsurancePage.getMobileErrorMessage();

        errorList.add(mobileError);

        Assert.assertEquals(
                mobileError,
                ExcelDataReader.get("Health", "expected.mobile.error"));

        healthInsurancePage.enterEmail(
                ExcelDataReader.get("Health", "invalid.email"));

        String emailError =
                healthInsurancePage.getEmailErrorMessage();

        errorList.add(emailError);

        Assert.assertEquals(
                emailError,
                ExcelDataReader.get("Health", "expected.email.error"));

        healthInsurancePage.triggerInvalidAdultDobValidation(
                ExcelDataReader.get("Health", "invalid.adult.day"),
                ExcelDataReader.get("Health", "invalid.adult.month"),
                ExcelDataReader.get("Health", "invalid.adult.year"));

        String adultDobError =
                healthInsurancePage.getAdultDobErrorMessage();

        errorList.add(adultDobError);

        Assert.assertEquals(
                adultDobError,
                ExcelDataReader.get("Health", "expected.adult.dob.error"));

        healthInsurancePage.triggerInvalidKidDobValidation(
                ExcelDataReader.get("Health", "invalid.kid.day"),
                ExcelDataReader.get("Health", "invalid.kid.month"),
                ExcelDataReader.get("Health", "invalid.kid.year"));

        String kidDobError =
                healthInsurancePage.getKidDobErrorMessage();

        errorList.add(kidDobError);

        Assert.assertEquals(
                kidDobError,
                ExcelDataReader.get("Health", "expected.kid.dob.error"));

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