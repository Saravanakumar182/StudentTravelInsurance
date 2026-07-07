package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_VerifyAddTravellers extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC05_VerifyAddTravellers.class);

    @Test(description = "TC05 - Add 2 travellers and navigate to plan page")
    public void verifyAddTravellers() {
        LoggerManager.logTestStart(this.getClass(), "TC05 - Add 2 travellers and navigate to plan page");
        String country     = ConfigReader.getProperty("travel.destination.country");
        String startDate   = ConfigReader.getProperty("travel.start.date");
        String endDate     = ConfigReader.getProperty("travel.end.date");
        String mobile      = ConfigReader.getProperty("travel.mobile.number");
        String email       = ConfigReader.getProperty("travel.user.email");
        int traveller1Age  = Integer.parseInt(ConfigReader.getProperty("travel.traveller1.age"));
        int traveller2Age  = Integer.parseInt(ConfigReader.getProperty("travel.traveller2.age"));
        log.info("Test data — Country: {}, Start: {}, End: {}, Mobile: {}, Email: {}, Traveller1 Age: {}, Traveller2 Age: {}",
                country, startDate, endDate, mobile, email, traveller1Age, traveller2Age);
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();
        log.info("Switched to results page iframe.");
        travelPage.enterMobileNumber(mobile);
        travelPage.enterEmail(email);
        log.info("Contact details entered — Mobile: {}, Email: {}", mobile, email);
        // Add both travellers
        travelPage.addTravellerByAge(traveller1Age);
        travelPage.addTravellerByAge(traveller2Age);
        log.info("Added 2 travellers with ages {} and {}", traveller1Age, traveller2Age);
        // Continue to plan page
        travelPage.clickContinueToPlans();
        // Assert navigation
        Assert.assertTrue(
                travelPage.getCurrentUrl().contains("plan-page"),
                "Did not navigate to plan page."
        );
        log.info("Successfully landed on plan page: {}", travelPage.getCurrentUrl());
    }
}