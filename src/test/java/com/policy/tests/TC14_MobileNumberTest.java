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

public class TC14_MobileNumberTest extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC14_MobileNumberTest.class);
@Test
public void validateMobileNumber() {
    HomePage homepage=new HomePage(getDriver());
    CarInsurancePage page = new CarInsurancePage(getDriver());

    // Navigate to Car Insurance
    homepage.clickMotorInsurance();
    homepage.clickCarInsuranceDropdown();

    // Read test data from config.properties
    String vehicleNumber = ExcelDataReader.get("Car", "vehicleNumber");
    String mobileNumber  = ExcelDataReader.get("Car", "validMobileNumber");

    // Fill required fields
    page.enterVehicleNumber(vehicleNumber);
    page.enterMobileNumber(mobileNumber);

    page.clickGetQuote();

    if (mobileNumber.length() < 10) {
        // ❌ Expect error message for invalid mobile
        String errorMsg = page.getMobileErrorMessage();
        log.info("Invalid mobile: " + mobileNumber + " | Error: " + errorMsg);
        Assert.assertTrue(errorMsg.toLowerCase().contains("valid mobile number"),
                "Expected error message for invalid mobile number");
    } else {
        // ✅ Expect success navigation
        log.info("Valid mobile: " + mobileNumber);
        Assert.assertTrue(getDriver().getTitle().contains("Car Insurance"),
                "Expected Car Insurance page after valid mobile number, but got: " + getDriver().getTitle());
    }
}

}
