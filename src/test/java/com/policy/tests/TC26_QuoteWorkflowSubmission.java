package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.BikeInsurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.policy.utils.ConfigReader;

public class TC26_QuoteWorkflowSubmission extends BaseTest {

    @Test
    public void verifyEndToEndQuoteDetailsWorkflow() {
        BikeInsurancePage bikePage = new BikeInsurancePage(getDriver());
        bikePage.clickBikeTab();
        bikePage.clickGotANewVehicle();
        bikePage.enterMobileNumber(ConfigReader.getProperty("bike.mobile"));
        bikePage.checkTermsAndConditions();
        bikePage.clickGetQuote();
        bikePage.selectCityOfRegistration(ConfigReader.getProperty("bike.city"));
        bikePage.clickProceed();


        Assert.assertTrue(bikePage.isVehicleDetailsErrorDisplayed(), "Error message was not displayed!");
        Assert.assertEquals(bikePage.getVehicleDetailsErrorText(), "Please enter a valid vehicle details");

        System.out.println("TC_26 Passed: Error captured - " + bikePage.getVehicleDetailsErrorText());

    }
}