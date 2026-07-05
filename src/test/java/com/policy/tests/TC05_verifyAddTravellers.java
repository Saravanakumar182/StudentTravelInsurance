package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_verifyAddTravellers extends BaseTest {

    @Test(description = "Add 2 travellers and navigate to plan page")
    public void verifyAddTravellers() {
        String country     = ConfigReader.getProperty("travel.destination.country");
        String startDate   = ConfigReader.getProperty("travel.start.date");
        String endDate     = ConfigReader.getProperty("travel.end.date");
        String mobile     = ConfigReader.getProperty("mobile.number");
        String email      = ConfigReader.getProperty("user.email");
        int traveller1Age  = Integer.parseInt(ConfigReader.getProperty("traveller1.age"));
        int traveller2Age  = Integer.parseInt(ConfigReader.getProperty("traveller2.age"));

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();

        travelPage.enterMobileNumber(mobile);
        travelPage.enterEmail(email);

        // Add both travellers
        travelPage.addTravellerByAge(traveller1Age);
        travelPage.addTravellerByAge(traveller2Age);

        // Continue to plan page
        travelPage.clickContinueToPlans();

        // Assert navigation
        Assert.assertTrue(
                travelPage.getCurrentUrl().contains("plan-page"),
                "Did not navigate to plan page."
        );
        System.out.println("Successfully landed on plan page.");
    }
}