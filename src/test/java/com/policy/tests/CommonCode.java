package com.policy.tests;

import com.policy.basetest.BaseTest;
import com.policy.pages.TravelInsurancePage;
import com.policy.utils.ExcelDataReader;
import com.policy.utils.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CommonCode{

    private static final Logger log = LoggerManager.getLogger(CommonCode.class);
    private WebDriver driver;
    private TravelInsurancePage travelPage;

    public CommonCode(WebDriver driver){
        this.driver = driver;
        this.travelPage = new TravelInsurancePage(driver);
    }

    public void navigateToCuratedPlansPage() {
        String country     = ExcelDataReader.get("Travel", "destination.country");
        String startDate   = ExcelDataReader.get("Travel", "start.date");
        String endDate     = ExcelDataReader.get("Travel", "end.date");
        String mobile      = ExcelDataReader.get("Travel", "mobile.number");
        String email       = ExcelDataReader.get("Travel", "user.email");
        int traveller1Age  = ExcelDataReader.getInt("Travel", "traveller1.age");
        int traveller2Age  = ExcelDataReader.getInt("Travel", "traveller2.age");

        travelPage.clickTravelTab();
        travelPage.clickTravelInsurance();
        travelPage.clickCountryButton();
        travelPage.enterDestinationCountry(country);
        travelPage.selectCountryFromDropdown(country);
        travelPage.enterTravelDates(startDate, endDate);
        travelPage.clickContinue();

        travelPage.enterMobileNumber(mobile);
        travelPage.enterEmail(email);

        // Add both travellers
        travelPage.addTravellerByAge(traveller1Age);
        travelPage.addTravellerByAge(traveller2Age);

        // Continue to plan page
        travelPage.clickContinueToPlans();
        log.info("=== Landed on curated-plans page (assumed after form submit) ===");
    }
}
