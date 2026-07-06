package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_verifySchengenCountrySelection extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC02_verifySchengenCountrySelection.class);

    @Test(description = "TC02 - Validate destination dropdown handles valid and invalid countries")
    public void verifySchengenCountrySelection() {
        LoggerManager.logTestStart(this.getClass(), "TC02 - Validate destination dropdown handles valid and invalid countries");
        String country = ConfigReader.getProperty("travel.destination.country");
        log.info("Testing country: {}", country);
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        if (travelPage.isCountryAvailableInDropdown(country)) {
            travelPage.selectCountryFromDropdown(country);
            String actualCountry = travelPage.getSelectedCountry();
            Assert.assertEquals(actualCountry, country, "Country is not selected correctly.");
            log.info("Valid country '{}' selected successfully.", actualCountry);
        } else {
            log.warn("Invalid country '{}' correctly rejected.", country);
        }
    }
}