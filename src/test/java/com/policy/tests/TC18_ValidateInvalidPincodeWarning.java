package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC18_ValidateInvalidPincodeWarning extends BaseTest {

    private static final Logger log =
            LoggerManager.getLogger(TC18_ValidateInvalidPincodeWarning.class);
    @Test(description = "TC18 - Validate Invalid Pincode Warning")

    public void validateInvalidPincodeWarning() {
        LoggerManager.logTestStart(
                this.getClass(),
                "TC18 - Validate Invalid Pincode Warning");
        homePage.clickHealthInsurance();
        healthInsurancePage.enterPincode(
                ConfigReader.getProperty("health.invalid.pincode"));
        String actualError =
                healthInsurancePage.getPincodeErrorMessage();
        log.info("Pincode Error : {}", actualError);
        Assert.assertEquals(
                actualError,
                ConfigReader.getProperty("health.expected.pincode.error"));
        LoggerManager.logVerification(
                this.getClass(),
                "Invalid pincode validation displayed successfully",
                true);
        LoggerManager.logTestEnd(
                this.getClass(),
                "TC18",
                "PASSED");
    }
}