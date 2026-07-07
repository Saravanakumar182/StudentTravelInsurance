package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.pages.BikeInsurancePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC24_TermsAndConditionsValidation extends BaseTest {

    @Test
    public void verifyTermsAndConditionsMandatory() {
        BikeInsurancePage bikePage = new BikeInsurancePage(getDriver());

        bikePage.clickBikeTab();
        bikePage.enterRegistrationNumber(ConfigReader.getProperty("bike.regNumber"));
        bikePage.enterMobileNumber(ConfigReader.getProperty("bike.mobile"));
        bikePage.uncheckTermsAndConditions();
        bikePage.clickGetQuote();

        Assert.assertTrue(bikePage.isTermsErrorDisplayed(),
                "The validation error message for terms and conditions was not displayed.");
        String expectedError = "Please accept terms and conditions";
        Assert.assertEquals(bikePage.getTermsErrorText().trim(), expectedError,
                "The error message text does not match the UI validation specifications.");
        System.out.println("TC24 Passed: Correctly captured the validation error - " + bikePage.getTermsErrorText());
    }
}