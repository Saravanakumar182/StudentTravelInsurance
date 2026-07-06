package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import org.testng.annotations.Test;

public class TC01_VerifyTravelPageNavigation extends BaseTest {

    @Test(description = "TC01 - Verify navigation to Travel Insurance page from Home")
    public void verifyTravelPageNavigation() {
        LoggerManager.logTestStart(this.getClass(), "TC01 - Verify navigation to Travel Insurance page from Home");
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        log.info("Travel Page Navigation validated successfully");
    }
}