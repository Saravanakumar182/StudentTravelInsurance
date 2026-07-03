package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TravelInsurancePage {
    private WebDriver driver;

    public TravelInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
