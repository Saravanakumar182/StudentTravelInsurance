package com.policybazaar.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.policybazaar.pages.TravelInsurancePage;

public class TC01_ValidatingURL extends BaseTest {

    @Test
    public void verifyTravelInsuranceURL() {

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());

        travelPage.clickTravelInsurance();

        String actualUrl = travelPage.getCurrentUrl();

        System.out.println("Current URL : " + actualUrl);

        Assert.assertTrue(
                actualUrl.contains("travel.policybazaar.com"),
                "Travel Insurance URL validation failed");
    }
}