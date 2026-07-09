package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC09_ExpandAdditionalBenefits extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC09_ExpandAdditionalBenefits.class);

    @Test(description = "TC_9 - Expand Additional Benefits accordion & extract benefits")
    public void expandAdditionalBenefits() {

        new CommonCode(getDriver()).navigateToCuratedPlansPage();

        travelPlanPage.expandAdditionalBenefits();
        log.info("Expanded Additional Benefits accordion");

        List<String> benefits = travelPlanPage.extractAdditionalBenefits();
        log.info("========== ADDITIONAL BENEFITS ({} items) ==========", benefits.size());
        for (int i = 0; i < benefits.size(); i++) {
            log.info("{}. {}", i + 1, benefits.get(i));
        }
        log.info("=====================================================");

        Assert.assertFalse(benefits.isEmpty(),
                "At least one additional benefit should be extracted");

        LoggerManager.logVerification(this.getClass(),
                "Extracted " + benefits.size()
                        + " additional benefits into List<String>", true);
        LoggerManager.logTestEnd(this.getClass(), "TC_9", "PASSED");
    }
}