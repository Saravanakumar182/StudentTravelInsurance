package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.pages.HealthInsurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC17_ValidateMandatoryFieldTests extends BaseTest {
    @Test(priority = 2)
    public void validateMandatoryFieldErrors() {
        HealthInsurancePage healthPage =
                new HealthInsurancePage(driver.get());
        healthPage.triggerMobileNumberValidation();
        String mobileError =
                healthPage.getMobileErrorMessage();
        Assert.assertEquals(
                mobileError,
                "Please enter a valid Mobile number");
        healthPage.triggerEmailValidation();
        String emailError =
                healthPage.getEmailErrorMessage();
        Assert.assertEquals(
                emailError,
                "Please enter a valid email id");
        System.out.println(
                "Error captured for both Email and phone number");
    }
}