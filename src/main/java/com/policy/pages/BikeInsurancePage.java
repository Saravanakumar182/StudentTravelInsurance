package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BikeInsurancePage {
    private WebDriver driver;

    public BikeInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}