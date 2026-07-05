package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_verifyValidTravelDate extends BaseTest {

    @Test(description = "Validate date-picker accepts valid dates and rejects past dates")
    public void verifyValidTravelDate() {
        String country = ConfigReader.getProperty("travel.destination.country");
        String startDate = ConfigReader.getProperty("travel.start.date");
        String endDate = ConfigReader.getProperty("travel.end.date");

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);

        if (travelPage.isPastDate(startDate)) {
            boolean rejected = travelPage.tryPastDateAndCheckRejection(startDate);
            Assert.assertTrue(rejected, "Past start date was NOT rejected by calendar!");
            System.out.println("✅ Past date '" + startDate + "' correctly rejected.");
        } else {
            travelPage.enterTravelDates(startDate, endDate);

            String actualStart = travelPage.getTravelStartDate();
            String actualEnd = travelPage.getTravelEndDate();

            Assert.assertEquals(actualStart, startDate, "Travel start date not entered correctly.");
            Assert.assertEquals(actualEnd, endDate, "Travel end date not entered correctly.");

            System.out.println("✅ Dates selected — Start: " + actualStart + ", End: " + actualEnd);
        }
        travelPage.clickContinue();
    }
}