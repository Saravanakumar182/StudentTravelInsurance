package com.policy.tests;
import com.policy.basetest.BaseTest;
import com.policy.pages.HealthInsurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC01_ValidateURL extends BaseTest {
    @Test
    public void validateHealthInsuranceNavigation() {
        HealthInsurancePage healthPage =  new HealthInsurancePage(driver.get());
        healthPage.clickHealthInsurance();
        for(String tab : driver.get().getWindowHandles()) {
            driver.get().switchTo().window(tab);
        }
        String actualUrl = healthPage.getCurrentUrl();
        System.out.println("Actual URL : " + actualUrl);
        Assert.assertTrue(actualUrl.contains("health-insurance"),
                "Failed to navigate to Health Insurance page");
        Assert.assertTrue(healthPage.isQuoteFormDisplayed(),
                "Health Insurance quote form is not displayed");
        System.out.println("Health Insurance quote form displayed successfully with Mobile Number, Email, Pincode and Members fields");
    }
}