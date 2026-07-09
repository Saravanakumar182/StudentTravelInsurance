package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.utils.ConfigReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10_ValidateDiscountAndNavigateHome extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC10_ValidateDiscountAndNavigateHome.class);

    @Test(description = "TC_10 - Validate discount tag, price comparison & navigate back to Home")
    public void validateDiscountAndNavigateHome() {

        new CommonCode(getDriver()).navigateToCuratedPlansPage();

        // ---- Part A: Discount tag ----
        boolean discountVisible = travelPlanPage.isDiscountTagVisible();
        Assert.assertTrue(discountVisible,
                "'Online discount applied' tag should be visible");

        LoggerManager.logVerification(this.getClass(),
                "'Online discount applied' tag is visible", discountVisible);

        // ---- Part B: Original vs final price ----
        int originalPrice = travelPlanPage.getOriginalPrice();
        int finalPrice    = travelPlanPage.getTotalPayable();
        log.info("Original price: ₹{} | Final price: ₹{}", originalPrice, finalPrice);

        Assert.assertTrue(originalPrice > finalPrice,
                "Original price should be greater than the discounted final price");
        LoggerManager.logVerification(this.getClass(),
                "Original ₹" + originalPrice + " > Final ₹" + finalPrice, true);


        travelPlanPage.clickHomeLogo();
        log.info("Clicked Home logo");

        Assert.assertTrue(
                getDriver().getCurrentUrl().equalsIgnoreCase(ConfigReader.getProperty("app.url")) ||
                        getDriver().getCurrentUrl().startsWith(ConfigReader.getProperty("app.url")),
                "User should land back on the Home page");

        LoggerManager.logVerification(this.getClass(),"User successfully navigated back to Home page", true);
        LoggerManager.logTestEnd(this.getClass(), "TC_10", "PASSED");
    }
}