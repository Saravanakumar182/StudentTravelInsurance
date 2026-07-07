package com.policy.tests;

import com.policy.pages.CarInsurancePage;
import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC13_VehicleNumberTest extends BaseTest {
    private ConfigReader config;


    @Test
    public void enterVehicleNumber() {
        HomePage homepage=new HomePage(getDriver());
        CarInsurancePage page = new CarInsurancePage(getDriver());

        // Ensure navigation first
        homepage.clickMotorInsurance();
        homepage.clickCarInsuranceDropdown();

        // Read vehicle number from config.properties
        String vehicleNumber = ConfigReader.getProperty("car.vehicleNumber");

        page.enterVehicleNumber(vehicleNumber);
        System.out.println("✅ Vehicle number entered: " + vehicleNumber);

        // Assertion
        Assert.assertEquals(vehicleNumber, "MH12AB1234",
                "Expected vehicle number to be MH12AB1234, but got: " + vehicleNumber);

    }
}
