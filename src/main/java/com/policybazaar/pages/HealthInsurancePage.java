package com.policybazaar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HealthInsurancePage {
    private WebDriver driver;

    @FindBy(id = "step1ContinueBtn")
    WebElement continueButton1;

    @FindBy(css = ".text-error")
    WebElement textError;

    @FindBy(xpath = "//input[@id='1']")
    WebElement selfInsuranceCheckBox;

    @FindBy(id = "Self")
    WebElement ageSelect;

    @FindBy(id = "step2ContinueBtn")
    WebElement continueButton2;

    @FindBy(xpath = "//li[text()='Pune']")
    WebElement puneOption;

    @FindBy(id = "step3ContinueBtn")
    WebElement continueButton3;

    @FindBy(id = "fullName")
    WebElement fullNameTextBox;

    @FindBy(id = "mobile")
    WebElement mobileNumberTextBox;

    @FindBy(id = "step4ContinueBtn")
    WebElement continueButton4;

    @FindBy(id = "ped_last")
    WebElement noneOfTheseCheckBox;

    @FindBy(className = "primaryButton")
    WebElement continueButton5;

    @FindBy(xpath = "//input[@id=2]")
    WebElement noRadioButton;

    @FindBy(id = "viewPlansBtn")
    WebElement viewPlansButton;

    @FindBy(className = "qv2_primaryMainCta")
    WebElement applyFilterButton;

    @FindBy(id = "1-2")
    WebElement sortLowToHighPremium;

    @FindBy(css = "#sort-by-filter > button")
    WebElement sortByFilterButton;

    public WebElement getSelfInsuranceCheckBox() {
        return selfInsuranceCheckBox;
    }

    public WebElement getTextError() {
        return textError;
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

    public void selectAge21(){
        Select select = new Select(ageSelect);
        select.selectByValue("21");
    }

    public void clickContinueButton2(){
        continueButton2.click();
    }

    public void clickPuneCity(){
        puneOption.click();
    }

    public void clickContinueButton3(){
        continueButton3.click();
    }

    public void enterFullName(String name){
        fullNameTextBox.sendKeys(name);
    }

    public void enterMobileNumber(String number){
        mobileNumberTextBox.sendKeys(number);
    }

    public void clickContinueButton4(){
        continueButton4.click();
    }

    public void clickNoneOfThese(){
        noneOfTheseCheckBox.click();
    }

    public void clickContinueButton5(){
        continueButton5.click();
    }

    public void selectNoOption(){
        noRadioButton.click();
    }

    public void clickViewPlansButton(){
        viewPlansButton.click();
    }

    public void clickSortByFilter(){
        sortByFilterButton.click();
    }

    public void clickLowToHighPremium(){
        sortLowToHighPremium.click();
    }

    public void clickApplyFilterButton(){
        applyFilterButton.click();
    }

}