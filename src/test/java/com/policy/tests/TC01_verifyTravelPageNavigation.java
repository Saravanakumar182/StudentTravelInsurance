package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TC01_verifyTravelPageNavigation extends BaseTest {
    private static final Logger log = LoggerManager.getLogger(TC01_verifyTravelPageNavigation.class);

    @Test(description = "TC01 - Verify navigation to Travel Insurance page from Home")
    public void verifyTravelPageNavigation() {
        LoggerManager.logTestStart(this.getClass(), "TC01 - Verify navigation to Travel Insurance page from Home");
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        log.info("Travel Page Navigation validated successfully");
    }
}