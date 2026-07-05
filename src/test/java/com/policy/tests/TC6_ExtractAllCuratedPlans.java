package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.models.Plan;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC6_ExtractAllCuratedPlans extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC6_ExtractAllCuratedPlans.class);

    @Test(description = "TC_6 - Extract all curated plans into a List<Plan>")
    public void extractAllCuratedPlans() {
        LoggerManager.logTestStart(this.getClass(),"TC_6 - Extract all curated plans");

        navigateToCuratedPlansPage();

        Assert.assertTrue(travelPlanPage.isOnCuratedPlansPage(),
                "User should be on the curated plans page");

        List<Plan> plans = travelPlanPage.extractAllCuratedPlans();
        log.info("Total plans extracted: {}", plans.size());
        plans.forEach(p -> log.info(p.toString()));

        Assert.assertFalse(plans.isEmpty(),
                "At least one curated plan should be extracted");

        LoggerManager.logVerification(this.getClass(),
                "Extracted " + plans.size() + " curated plans into List<Plan>", true);
        LoggerManager.logTestEnd(this.getClass(), "TC_6", "PASSED");
    }
}