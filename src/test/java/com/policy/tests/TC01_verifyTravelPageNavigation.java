package com.policy.tests;

import com.policy.pages.TravelInsurancePage;
import org.testng.annotations.Test;

public class TC01_verifyTravelPageNavigation extends BaseTest {

    @Test(description = "Verify navigation to Travel Insurance page from Home")
    public void verifyTravelPageNavigation() {
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();

        System.out.println("Travel Page Navigation validated successfully");
    }
}
