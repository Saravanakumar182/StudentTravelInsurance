package com.policybazaar.tests;

import com.policybazaar.pages.TravelInsurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_verifyCountrySelection extends BaseTest
{

        @Test(priority = 1,
                description = "Verify country is selected and displayed in the input field")

        public void verifyCountrySelection()
        {

            TravelInsurancePage travelPage =
                    new TravelInsurancePage(getDriver());

            travelPage.clickTravelInsurance();

            travelPage.selectCountry("Germany");

            String actualCountry = travelPage.getSelectedCountry();

            String expectedCountry = "Germany";

            System.out.println("Selected Country : " + actualCountry);


            Assert.assertEquals(
                    actualCountry,
                    expectedCountry,
                    "❌ FAIL: Country is not selected correctly"
            );

            System.out.println("✅ PASS: Country is selected and displayed correctly -> " + actualCountry);

        }

}