package com.policy.tests;

import com.policy.pages.CarInsurancePage;
import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.ConfigReader;
import org.testng.annotations.Test;

public class TC16_GetQuoteButtonTest extends BaseTest {

    private ConfigReader config;

    @Test
    public void verifyGetQuoteButton() {
        HomePage homepage=new HomePage(getDriver());
        CarInsurancePage page = new CarInsurancePage(getDriver());

        // Navigate to Car Insurance
        homepage.clickMotorInsurance();
        homepage.clickCarInsuranceDropdown();

        // Read test data from config.properties
        String vehicleNumber = ConfigReader.getProperty("car.vehicleNumber");
        String mobileNumber  = ConfigReader.getProperty("car.mobileNumber");
        String email         = ConfigReader.getProperty("car.email");

        // Fill required fields
        page.enterVehicleNumber(vehicleNumber);
        page.enterMobileNumber(mobileNumber);
        page.enterEmail(email);

        // Click Get Quote
        page.clickGetQuote();
        System.out.println("✅ Get Quote button clicked");


    }
}
