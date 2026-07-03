package com.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HealthInsurancePage {
    private WebDriver driver;

    public HealthInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}