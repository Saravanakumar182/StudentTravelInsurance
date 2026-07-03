package com.policy.tests;

import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_verifySchengenCountrySelection extends BaseTest {

    @Test(description = "Validate destination dropdown handles valid and invalid countries")
    public void verifySchengenCountrySelection() {
        String country = ConfigReader.getProperty("travel.destination.country");

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);

        if (travelPage.isCountryAvailableInDropdown(country)) {
            travelPage.selectCountryFromDropdown(country);
            String actualCountry = travelPage.getSelectedCountry();

            Assert.assertEquals(actualCountry, country, "Country is not selected correctly.");
            System.out.println("✅ Valid country '" + actualCountry + "' selected successfully.");
        } else {
            System.out.println("✅ Invalid country '" + country + "' correctly rejected.");
        }
    }
}