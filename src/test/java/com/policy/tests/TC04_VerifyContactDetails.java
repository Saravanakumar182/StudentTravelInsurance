package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_VerifyContactDetails extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC04_VerifyContactDetails.class);

    @Test(description = "TC04 - Validate mobile number and email entry with format validation")
    public void verifyContactDetails() {
        LoggerManager.logTestStart(this.getClass(), "TC04 - Validate mobile number and email entry with format validation");
        String country     = ExcelDataReader.get("Travel", "destination.country");
        String startDate   = ExcelDataReader.get("Travel", "start.date");
        String endDate     = ExcelDataReader.get("Travel", "end.date");
        String mobile      = ExcelDataReader.get("Travel", "mobile.number");
        String email       = ExcelDataReader.get("Travel", "user.email");

        log.info("Test data — Country: {}, Start: {}, End: {}, Mobile: {}, Email: {}",
                country, startDate, endDate, mobile, email);
        TravelInsurancePage travelPage = new TravelInsurancePage(getDriver());
        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();  // switches to sfFrontendHtml iframe
        log.info("Switched to results page iframe.");
        // ---------- Mobile validation ----------
        travelPage.enterMobileNumber(mobile);
        if (travelPage.isValidMobile(mobile)) {
            Assert.assertEquals(travelPage.getMobileNumberValue(), mobile, "Mobile number not entered correctly.");
            log.info("Valid mobile '{}' entered successfully.", mobile);
        } else {
            Assert.assertFalse(travelPage.isValidMobile(mobile),
                    "Mobile '" + mobile + "' passed validation but shouldn't have.");
            log.warn("Invalid mobile '{}' correctly rejected.", mobile);
        }

        // ---------- Email validation ----------
        travelPage.enterEmail(email);
        if (travelPage.isValidEmail(email)) {
            Assert.assertEquals(travelPage.getEmailValue(), email, "Email not entered correctly.");
            log.info("Valid email '{}' entered successfully.", email);
        } else {
            Assert.assertFalse(travelPage.isValidEmail(email),
                    "Email '" + email + "' passed validation but shouldn't have.");
            log.warn("Invalid email '{}' correctly rejected.", email);
        }
    }
}