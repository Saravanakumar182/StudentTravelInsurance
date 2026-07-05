package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CarInsurancePage {
    private WebDriver driver;

    public CarInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
