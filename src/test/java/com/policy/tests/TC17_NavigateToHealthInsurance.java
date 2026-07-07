package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC17_NavigateToHealthInsurance extends BaseTest {
    private static final Logger log =
            LoggerManager.getLogger(TC17_NavigateToHealthInsurance.class);
    @Test(description = "TC17 - Navigate To Health Insurance")

    public void validateHealthInsuranceNavigation() {

        LoggerManager.logTestStart(this.getClass(),
                "TC17 - Navigate To Health Insurance");
        homePage.clickHealthInsurance();

        boolean isDisplayed = healthInsurancePage.isQuoteFormDisplayed();
        Assert.assertTrue(isDisplayed,
                "Health Insurance quote form is not displayed");
        log.info("Health Insurance quote form displayed successfully with Mobile Number, Email, Pincode and Members fields");
        LoggerManager.logVerification(this.getClass(),
                "Health Insurance quote form displayed successfully",
                true);
        LoggerManager.logTestEnd(
                this.getClass(),
                "TC17",
                "PASSED");
    }
}