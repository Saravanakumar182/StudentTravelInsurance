package com.policy.pages;

import com.policy.models.Plan;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TravelInsurancePlanPage {

    WebDriver driver;
    WebDriverWait wait;

    public TravelInsurancePlanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "travel-plan-title")
    private WebElement curatedHeader;

    @FindBy(id = "other-plan-btn")
    private WebElement seeOtherBenefitsButton;

    @FindBy(css = ".next-coverage")
    private WebElement carouselNextArrow;

    @FindBy(css = ".plan-curated-block")
    private List<WebElement> planTiles;

    @FindBy(id = "jobsummery-alldate")
    private WebElement medicalCoverDropdown;

    @FindBy(className = "payable-amt")
    private WebElement totalPayable;

    @FindBy(css = ".plan-accord-sec.additional-benefit-sec")
    private WebElement additionalBenefitsHeader;

    @FindBy(className = "addbenefit-card")
    private List<WebElement> additionalBenefitItems;

    @FindBy(className = "online-discount-tag")
    private WebElement discountTag;

    @FindBy(className = "strike-amnt")
    private WebElement originalPrice;

    @FindBy(xpath = "//a[@href='/']")
    private WebElement homeLogo;

    // Helpers
    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", el);
    }

    private void safeClick(WebElement el) {
        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    private int parsePrice(String raw) {
        if (raw == null) return 0;
        String digits = raw.replaceAll("[^0-9]", "");
        return digits.isEmpty() ? 0 : Integer.parseInt(digits);
    }

    // Actions & getters
    public boolean isOnCuratedPlansPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(curatedHeader));
            return curatedHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<Plan> extractAllCuratedPlans() {
        List<Plan> plans = new ArrayList<>();
        int safety = 10;

        while (safety-- > 0) {
            for (WebElement tile : planTiles) {
                try {
                    String name    = tile.findElement(By.xpath(".//h4")).getText().trim();
                    String tagline = tile.findElement(By.xpath(".//p")).getText().trim();
                    String priceStr = tile.findElement(By.xpath(".//span[contains(@class,'premium-amnt')]")).getText().trim();
                    String cover   = tile.findElement(By.xpath(".//input[contains(@id,'jobsummery-alldate')]")).getText().trim();

                    Plan p = new Plan(name, tagline, parsePrice(priceStr), cover);
                    if (plans.stream().noneMatch(x -> x.getName().equalsIgnoreCase(name))) {
                        plans.add(p);
                    }
                } catch (Exception ignored) {}
            }

            try {
                if (carouselNextArrow != null && carouselNextArrow.isDisplayed()
                        && carouselNextArrow.isEnabled()) {
                    scrollIntoView(carouselNextArrow);
                    safeClick(carouselNextArrow);
                    Thread.sleep(800);
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        return plans;
    }

    public int getTotalPayable() {
        wait.until(ExpectedConditions.visibilityOf(totalPayable));
        return parsePrice(totalPayable.getText());
    }

    public void changeMedicalCover(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(medicalCoverDropdown));
        scrollIntoView(medicalCoverDropdown);
        String tag = medicalCoverDropdown.getTagName();

        safeClick(medicalCoverDropdown);
        By optionLocator = By.xpath("//span[normalize-space(text())='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();

    }

    public List<Plan> getTopNLowestPlans(List<Plan> allPlans, int n) {
        return allPlans.stream()
                .filter(p -> p.getPriceInRupees() > 0)
                .sorted(Comparator.comparingInt(Plan::getPriceInRupees))
                .limit(n)
                .collect(Collectors.toList());
    }

    public void expandAdditionalBenefits() {
        wait.until(ExpectedConditions.elementToBeClickable(additionalBenefitsHeader));
        scrollIntoView(additionalBenefitsHeader);
        safeClick(additionalBenefitsHeader);
    }

    public List<String> extractAdditionalBenefits() {
        List<String> benefits = new ArrayList<>();
        for (WebElement item : additionalBenefitItems) {
            String txt = item.findElement(By.xpath("./div/div/h5")).getText().trim();
            if (!txt.isEmpty()) benefits.add(txt);
        }
        return benefits;
    }

    public boolean isDiscountTagVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(discountTag));
            return discountTag.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getOriginalPrice() {
        wait.until(ExpectedConditions.visibilityOf(originalPrice));
        return parsePrice(originalPrice.getText());
    }

    public void clickHomeLogo() {
        wait.until(ExpectedConditions.visibilityOf(homeLogo));
        scrollIntoView(homeLogo);
        safeClick(homeLogo);
    }
}