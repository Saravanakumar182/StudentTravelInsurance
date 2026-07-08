package com.policy.tests;

import com.policy.pages.CarInsurancePage;
import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC13_VehicleNumberTest extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC13_VehicleNumberTest.class);

    @Test
    public void enterVehicleNumber() {
        HomePage homepage=new HomePage(getDriver());
        CarInsurancePage page = new CarInsurancePage(getDriver());

        // Ensure navigation first
        homepage.clickMotorInsurance();
        homepage.clickCarInsuranceDropdown();

        String vehicleNumber = ExcelDataReader.get("car","vehicleNumber");

        page.enterVehicleNumber(vehicleNumber);
        log.info("Vehicle number entered: " + vehicleNumber);

        // Assertion
        Assert.assertEquals(vehicleNumber, "MH12AB1234",
                "Expected vehicle number to be MH12AB1234, but got: " + vehicleNumber);

    }
}
