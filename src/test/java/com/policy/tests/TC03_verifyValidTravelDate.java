package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_verifyValidTravelDate extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC03_verifyValidTravelDate.class);

    @Test(description = "TC03 - Validate date-picker accepts valid dates and rejects past dates")
    public void verifyValidTravelDate() {
        LoggerManager.logTestStart(this.getClass(), "TC03 - Validate date-picker accepts valid dates and rejects past dates");

        String country = ConfigReader.getProperty("travel.destination.country");
        String startDate = ConfigReader.getProperty("travel.start.date");
        String endDate = ConfigReader.getProperty("travel.end.date");
        log.info("Test data — Country: {}, Start: {}, End: {}", country, startDate, endDate);

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);

        if (travelPage.isPastDate(startDate)) {
            log.warn("Start date '{}' is in the past — verifying rejection...", startDate);
            boolean rejected = travelPage.tryPastDateAndCheckRejection(startDate);
            Assert.assertTrue(rejected, "Past start date was NOT rejected by calendar!");
            log.info("Past date '{}' correctly rejected by calendar.", startDate);
        } else {
            travelPage.enterTravelDates(startDate, endDate);

            String actualStart = travelPage.getTravelStartDate();
            String actualEnd = travelPage.getTravelEndDate();

            Assert.assertEquals(actualStart, startDate, "Travel start date not entered correctly.");
            Assert.assertEquals(actualEnd, endDate, "Travel end date not entered correctly.");

            log.info("Dates selected successfully — Start: {}, End: {}", actualStart, actualEnd);
        }

        travelPage.clickContinue();
        log.info("Clicked Continue button to proceed to next page.");
    }
}