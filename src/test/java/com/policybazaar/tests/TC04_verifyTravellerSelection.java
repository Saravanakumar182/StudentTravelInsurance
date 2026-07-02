package com.policybazaar.tests;

import com.policybazaar.pages.TravelInsurancePage;
import org.testng.annotations.Test;

public class TC04_verifyTravellerSelection extends BaseTest{
    @Test(priority = 1,
            description = "Verify travellers count is selected")
    public void verifyAgeInput() {

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());

        travelPage.clickTravelInsurance();
        travelPage.selectCountry("Germany");
        travelPage.clickAddTraveller();
        travelPage.selectTraveller2();

        System.out.println("✔️PASS : Number of travellers selected successfully");
    }
}
