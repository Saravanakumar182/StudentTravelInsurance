package com.policybazaar.tests;


import com.policybazaar.pages.HealthInsurancePage;
import com.policybazaar.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC20_VerifyingMemberForInsuring extends BaseTest{

    @Test
    public void verifyingMemberForInsuring(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickHealthInsurance();

        HealthInsurancePage healthInsurancePage = new HealthInsurancePage(getDriver());

        healthInsurancePage.selectSelfInsurance();
        healthInsurancePage.clickContinueButton1();
        Assert.assertEquals(healthInsurancePage.getTextError1().getText(),"Please select at least one member");

        healthInsurancePage.selectSelfInsurance();
        healthInsurancePage.clickContinueButton1();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains("https://health.policybazaar.com/members-age"));

        Assert.assertTrue(isUrlCorrect,
                "Page URL did not contain expected path within timeout.");

    }
}
