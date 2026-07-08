package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11_MotorInsuranceNavTest extends BaseTest {
    private static final Logger log = LoggerManager.getLogger(TC11_MotorInsuranceNavTest.class);
    @Test
    public void verifyMotorInsuranceNav() {
        HomePage page = new HomePage(getDriver());
        page.clickMotorInsurance();
        log.info("✅ Motor Insurance navigation clicked");
        Assert.assertTrue(getDriver().getTitle().contains("General Insurance: Buy Health, Car, Bike & Travel Policy Online"),
                "Expected Motor Insurance page, got: " + getDriver().getTitle());
    }
}
