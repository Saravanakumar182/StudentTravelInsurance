package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_VerifyAddTravellers extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC05_VerifyAddTravellers.class);

    @Test(description = "TC05 - Add 2 travellers and navigate to plan page")
    public void verifyAddTravellers() {
        LoggerManager.logTestStart(this.getClass(), "TC05 - Add 2 travellers and navigate to plan page");
        String country     = ExcelDataReader.get("Travel", "destination.country");
        String startDate   = ExcelDataReader.get("Travel", "start.date");
        String endDate     = ExcelDataReader.get("Travel", "end.date");
        String mobile      = ExcelDataReader.get("Travel", "mobile.number");
        String email       = ExcelDataReader.get("Travel", "user.email");
        int traveller1Age  = ExcelDataReader.getInt("Travel", "traveller1.age");
        int traveller2Age  = ExcelDataReader.getInt("Travel", "traveller2.age");
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