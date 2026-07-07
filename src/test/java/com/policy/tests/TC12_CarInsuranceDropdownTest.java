package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC12_CarInsuranceDropdownTest extends BaseTest {

    @Test
    public void verifyCarInsuranceDropdown() {
        HomePage page = new HomePage(getDriver());
        page.clickMotorInsurance();
        page.clickCarInsuranceDropdown();
        System.out.println("✅ Car Insurance dropdown selected");
        Assert.assertTrue(getDriver().getTitle().contains("Car Insurance"),
                "Expected Car Insurance page, got: " + getDriver().getTitle());
    }
}
