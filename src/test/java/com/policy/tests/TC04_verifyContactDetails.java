package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_verifyContactDetails extends BaseTest {

    @Test(description = "Validate mobile number and email entry with format validation")
    public void verifyContactDetails() {
        String country    = ConfigReader.getProperty("travel.destination.country");
        String startDate  = ConfigReader.getProperty("travel.start.date");
        String endDate    = ConfigReader.getProperty("travel.end.date");
        String mobile     = ConfigReader.getProperty("travel.mobile.number");
        String email      = ConfigReader.getProperty("travel.user.email");

        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();  // switches to sfFrontendHtml iframe

        // ---------- Mobile validation ----------
        travelPage.enterMobileNumber(mobile);
        if (travelPage.isValidMobile(mobile)) {
            Assert.assertEquals(travelPage.getMobileNumberValue(), mobile, "Mobile number not entered correctly.");
            System.out.println("✅ Valid mobile '" + mobile + "' entered successfully.");
        } else {
            Assert.assertFalse(travelPage.isValidMobile(mobile),
                    "Mobile '" + mobile + "' passed validation but shouldn't have.");
            System.out.println("✅ Invalid mobile '" + mobile + "' correctly rejected.");
        }

        // ---------- Email validation ----------
        travelPage.enterEmail(email);
        if (travelPage.isValidEmail(email)) {
            Assert.assertEquals(travelPage.getEmailValue(), email, "Email not entered correctly.");
            System.out.println("✅ Valid email '" + email + "' entered successfully.");
        } else {
            Assert.assertFalse(travelPage.isValidEmail(email),
                    "Email '" + email + "' passed validation but shouldn't have.");
            System.out.println("✅ Invalid email '" + email + "' correctly rejected.");
        }
    }
}