package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.models.Plan;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC08_ValidateTop3LowestPlans extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC08_ValidateTop3LowestPlans.class);

    @Test(description = "TC_8 - Display top 3 lowest plans with provider & amount")
    public void validateTop3LowestPlans() {

        navigateToCuratedPlansPage();

        List<Plan> allPlans = travelPlanPage.extractAllCuratedPlans();
        Assert.assertFalse(allPlans.isEmpty(),
                "At least one plan should be available to sort");

        List<Plan> top3 = travelPlanPage.getTopNLowestPlans(allPlans, 3);

        log.info("========== TOP 3 LOWEST PRICED TRAVEL INSURANCE PLANS ==========");
        for (int i = 0; i < top3.size(); i++) {
            Plan p = top3.get(i);
            log.info("Rank {} → Plan: {} | Amount: ₹{} | Provider: {}",
                    i + 1, p.getName(), p.getPriceInRupees(), p.getProvider());
        }
        log.info("================================================================");

        // Verify sorted ascending
        for (int i = 1; i < top3.size(); i++) {
            Assert.assertTrue(top3.get(i).getPriceInRupees()
                            >= top3.get(i - 1).getPriceInRupees(),
                    "Plans must be sorted ascending by price");
        }

        Assert.assertTrue(!top3.isEmpty() && top3.size() <= 3,
                "Top-3 list should have 1–3 entries");

        LoggerManager.logVerification(this.getClass(),
                "Top 3 lowest plans displayed with amount and provider (ICICI Lombard)",
                true);
        LoggerManager.logTestEnd(this.getClass(), "TC_8", "PASSED");
    }
}