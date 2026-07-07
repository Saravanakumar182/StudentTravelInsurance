package com.policy.tests;

import com.policy.pages.CarInsurancePage;
import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC15_EmailTest extends BaseTest {

    private ConfigReader config;

    @Test
    public void validateEmail() {
        HomePage homepage=new HomePage(getDriver());
        CarInsurancePage page = new CarInsurancePage(getDriver());

        // Navigate to Car Insurance
        homepage.clickMotorInsurance();
        homepage.clickCarInsuranceDropdown();

        // Read test data from config.properties
        String vehicleNumber = ExcelDataReader.get("Car", "vehicleNumber");
        String mobileNumber  = ExcelDataReader.get("Car", "validMobileNumber");
        String email         = ExcelDataReader.get("Car", "email");

        // Fill required fields
        page.enterVehicleNumber(vehicleNumber);
        page.enterMobileNumber(mobileNumber);
        page.enterEmail(email);

        page.clickGetQuote();

        if (!email.contains("@")) {
            // ❌ Expect error message for invalid email
            String errorMsg = page.getEmailErrorMessage();
            System.out.println("❌ Invalid email: " + email + " | Error: " + errorMsg);
            Assert.assertTrue(errorMsg.toLowerCase().contains("valid email"),
                    "Expected error message for invalid email");
        } else {
            // ✅ Expect success navigation
            System.out.println("✅ Valid email: " + email);
            Assert.assertTrue(getDriver().getCurrentUrl().contains("quote"),
                    "Expected navigation for valid email, but got: " + getDriver().getCurrentUrl());
        }
    }
}
