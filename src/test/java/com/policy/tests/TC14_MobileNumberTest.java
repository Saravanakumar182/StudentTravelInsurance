package com.policy.tests;

import com.policy.pages.CarInsurancePage;
import com.policy.basetest.BaseTest;
import com.policy.pages.HomePage;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC14_MobileNumberTest extends BaseTest {

    private ConfigReader config;


@Test
public void validateMobileNumber() {
    HomePage homepage=new HomePage(getDriver());
    CarInsurancePage page = new CarInsurancePage(getDriver());

    // Navigate to Car Insurance
    homepage.clickMotorInsurance();
    homepage.clickCarInsuranceDropdown();

    // Read test data from config.properties
    String vehicleNumber = ExcelDataReader.get("Car", "vehicleNumber");
    String mobileNumber  = ExcelDataReader.get("Car", "mobileNumber");
    String email         = ExcelDataReader.get("Car", "email");

    // Fill required fields
    page.enterVehicleNumber(vehicleNumber);
    page.enterMobileNumber(mobileNumber);
    page.enterEmail(email);

    page.clickGetQuote();

    if (mobileNumber.length() < 10) {
        // ❌ Expect error message for invalid mobile
        String errorMsg = page.getMobileErrorMessage();
        System.out.println("❌ Invalid mobile: " + mobileNumber + " | Error: " + errorMsg);
        Assert.assertTrue(errorMsg.toLowerCase().contains("valid mobile number"),
                "Expected error message for invalid mobile number");
    } else {
        // ✅ Expect success navigation
        System.out.println("✅ Valid mobile: " + mobileNumber);
        Assert.assertTrue(getDriver().getTitle().contains("Car Insurance"),
                "Expected Car Insurance page after valid mobile number, but got: " + getDriver().getTitle());
    }
}

}
