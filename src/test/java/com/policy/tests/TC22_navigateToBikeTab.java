package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.pages.BikeInsurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC22_navigateToBikeTab extends BaseTest {

    @Test
    public void navigateToBikeTab() {
        BikeInsurancePage bikePage = new BikeInsurancePage(getDriver());
        bikePage.clickBikeTab();
        Assert.assertTrue(bikePage.isBikeQuoteFormDisplayed(),
                "Bike quote form was not displayed after clicking Bike tab");
        System.out.println("TC_22 Passed: Bike quote form displayed successfully");
    }
}