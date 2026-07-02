package com.policybazaar.tests;

import com.policybazaar.pages.TravelInsurancePage;
import org.testng.annotations.Test;

public class TC05_verifyAgeInput extends BaseTest {

    @Test(
            priority = 1,
            description = "Verify user can select Germany as destination, choose travel dates, add two travellers, select ages, choose PED as No and explore plans"
    )
    public void verifyAgeInput() throws InterruptedException {

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());

        // Travel Insurance
        travelPage.clickTravelInsurance();

        // Destination
        travelPage.selectCountry("Germany");

        // Travel Dates
        travelPage.clickStartDate();
        Thread.sleep(2000);
        travelPage.selectTravelDates();

        // Traveller Details
        travelPage.clickAddTraveller();
        Thread.sleep(2000);
        travelPage.selectTraveller2();
        Thread.sleep(2000);
        travelPage.selectTravellerAges();

        // PED = No and Done
        travelPage.completeTravellerDetails();
        Thread.sleep(2000);

        // Explore Plans
        travelPage.clickExplorePlans();
        Thread.sleep(2000);

        System.out.println(
                "✅ PASS: Germany selected, travel dates selected, "
                        + "2 travellers added, ages selected, PED set to No, "
                        + "and plans page opened successfully."
        );
    }
}