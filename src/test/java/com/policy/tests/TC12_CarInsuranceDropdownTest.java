package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC12_CarInsuranceDropdownTest extends BaseTest {
    private static final Logger log = LoggerManager.getLogger(TC12_CarInsuranceDropdownTest.class);
    @Test
    public void verifyCarInsuranceDropdown() {
        HomePage page = new HomePage(getDriver());
        page.clickMotorInsurance();
        page.clickCarInsuranceDropdown();
        log.info("✅ Car Insurance dropdown selected");
        Assert.assertTrue(getDriver().getTitle().contains("Car Insurance"),
                "Expected Car Insurance page, got: " + getDriver().getTitle());
    }
}
