package com.policybazaar.tests;

import com.policybazaar.pages.HealthInsurancePage;
import com.policybazaar.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC22_VerifyingCitySelection extends BaseTest{

    @Test
    public void verifyingCitySelection(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickHealthInsurance();

        HealthInsurancePage healthInsurancePage = new HealthInsurancePage(getDriver());

        healthInsurancePage.selectSelfInsurance();
        healthInsurancePage.clickContinueButton1();

        healthInsurancePage.selectSelfInsurance();
        healthInsurancePage.clickContinueButton1();

        healthInsurancePage.selectAge21();
        healthInsurancePage.clickContinueButton2();

        healthInsurancePage.clickContinueButton3();
        Assert.assertEquals(healthInsurancePage.getTextError().getText(),"City info is required");

        healthInsurancePage.clickPuneCity();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains("https://health.policybazaar.com/mobile"));

        Assert.assertTrue(isUrlCorrect,"Page URL did not contain expected path within timeout.");
    }
}
