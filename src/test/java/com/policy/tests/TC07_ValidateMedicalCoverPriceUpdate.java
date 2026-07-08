package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.utils.ConfigReader;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TC07_ValidateMedicalCoverPriceUpdate extends BaseTest {

    private static final Logger log = LoggerManager.getLogger(TC07_ValidateMedicalCoverPriceUpdate.class);

    @Test(description = "TC_7 - Change Medical Cover dropdown updates Total payable")
    public void validateMedicalCoverPriceUpdate() {

        new CommonCode().navigateToCuratedPlansPage();

        int initialTotal = travelPlanPage.getTotalPayable();
        log.info("Initial Total payable: ₹{}", initialTotal);

        String medicalCover = ExcelDataReader.get("Travel", "result.medicalCoverAmount");
        travelPlanPage.changeMedicalCover(medicalCover);
        log.info("Changed Medical Cover to "+medicalCover);

        int newTotal = travelPlanPage.getTotalPayable();
        log.info("New Total payable: ₹{}", newTotal);

        List<String> capturedPrices = new ArrayList<>();
        capturedPrices.add("Before: ₹" + initialTotal);
        capturedPrices.add("After : ₹" + newTotal);
        log.info("Captured price list -> {}", capturedPrices);

        Assert.assertNotEquals(newTotal, initialTotal,
                "Total payable should change after switching Medical Cover");

        LoggerManager.logVerification(this.getClass(),
                "Total payable updated dynamically after dropdown change", true);
        LoggerManager.logTestEnd(this.getClass(), "TC_7", "PASSED");
    }
}