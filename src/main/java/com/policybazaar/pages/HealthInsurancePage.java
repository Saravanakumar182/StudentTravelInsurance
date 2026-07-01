package com.policybazaar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HealthInsurancePage {
    private WebDriver driver;

    @FindBy(id = "step1ContinueBtn")
    WebElement continueButton1;


    @FindBy(css = ".text-error")
    WebElement textError1;

    @FindBy(xpath = "//input[@id='1']")
    WebElement selfInsuranceCheckBox;

    public WebElement getSelfInsuranceCheckBox() {
        return selfInsuranceCheckBox;
    }

    public WebElement getTextError1() {
        return textError1;
    }

    public HealthInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickContinueButton1(){
        continueButton1.click();
    }

    public void selectSelfInsurance(){
        selfInsuranceCheckBox.click();
    }

}