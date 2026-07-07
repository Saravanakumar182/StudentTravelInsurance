package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.BikeInsurancePage;
import com.policy.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC25_Validquotesubmission extends BaseTest {

    @Test
    public void verifyValidBikeQuoteSubmission() {
        BikeInsurancePage bikePage = new BikeInsurancePage(getDriver());

        bikePage.clickBikeTab();
        bikePage.enterRegistrationNumber(ConfigReader.getProperty("bike.regNumber"));
        bikePage.enterMobileNumber(ConfigReader.getProperty("bike.mobile"));
        bikePage.checkTermsAndConditions();
        bikePage.clickGetQuote();

        WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        boolean isUrlChanged = shortWait.until(ExpectedConditions.urlContains("get-quote"));
        System.out.println("Navigated to URL: " + getDriver().getCurrentUrl());
        Assert.assertTrue(isUrlChanged, "The page failed to transition after clicking Get Quote.");
        System.out.println("TC_25 Passed: Valid bike quote submission successful. URL: " + getDriver().getCurrentUrl());
    }
}