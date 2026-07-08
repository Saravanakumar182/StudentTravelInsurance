package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.pages.BikeInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC23_InvalidMobNumber extends BaseTest {

    @Test
    public void validateInvalidMobileNumber() {
        BikeInsurancePage bikePage = new BikeInsurancePage(getDriver());
        bikePage.clickBikeTab();
        bikePage.enterRegistrationNumber(ExcelDataReader.get("Bike", "regNumber"));
        bikePage.enterMobileNumber(ExcelDataReader.get("Bike", "invalidMobile"));
        bikePage.clickGetQuote();

      Assert.assertTrue(bikePage.isMobileErrorDisplayed(),
        "Please enter a valid Mobile number");
        System.out.println("TC_23 Passed: Error captured - " + bikePage.getMobileErrorText());
    }
}