package com.policy.pages;

import com.policy.models.Plan;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        this.wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ---------- Locators ----------
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

    // ---------- Helpers ----------
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

    /**
     * Wait until the price element becomes stable (its text stops changing).
     * Used after triggering a dropdown change or carousel scroll where the
     * DOM re-renders.
     */
    private void waitForPriceToStabilize(WebElement priceElement, String previousValue) {
        try {
            wait.until(driver -> {
                try {
                    String current = priceElement.getText().trim();
                    return current != null
                            && !current.isEmpty()
                            && !current.equals(previousValue);
                } catch (Exception ignored) {
                    return false;
                }
            });
        } catch (Exception ignored) {
            // Fall through — value may not have changed (that's still valid signal)
        }
    }

    // ---------- Actions ----------
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
            int planCountBefore = plans.size();

            for (WebElement tile : planTiles) {
                try {
                    String name     = tile.findElement(By.xpath(".//h4")).getText().trim();
                    String tagline  = tile.findElement(By.xpath(".//p")).getText().trim();
                    String priceStr = tile.findElement(By.xpath(".//span[contains(@class,'premium-amnt')]")).getText().trim();
                    String cover    = tile.findElement(By.xpath(".//input[contains(@id,'jobsummery-alldate')]")).getText().trim();

                    Plan p = new Plan(name, tagline, parsePrice(priceStr), cover);
                    if (plans.stream().noneMatch(x -> x.getName().equalsIgnoreCase(name))) {
                        plans.add(p);
                    }
                } catch (Exception ignored) {
                    // Skip tiles missing expected child elements
                }
            }

            // Try to advance the carousel; break if not possible
            try {
                if (carouselNextArrow != null
                        && carouselNextArrow.isDisplayed()
                        && carouselNextArrow.isEnabled()) {

                    scrollIntoView(carouselNextArrow);
                    safeClick(carouselNextArrow);

                    final int previousCount = planCountBefore;
                    wait.until(d -> {
                        try {
                            int currentTileCount = planTiles.size();
                            // Wait for either new tile OR arrow disabled state
                            boolean moreTilesRendered = currentTileCount > previousCount;
                            boolean arrowGone = !carouselNextArrow.isEnabled()
                                    || !carouselNextArrow.isDisplayed();
                            return moreTilesRendered || arrowGone;
                        } catch (Exception e) {
                            return true;
                        }
                    });
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

        // Snapshot current payable value so we can wait for it to change
        String previousPayable = "";
        try {
            previousPayable = totalPayable.getText().trim();
        } catch (Exception ignored) {}

        safeClick(medicalCoverDropdown);

        By optionLocator = By.xpath("//span[normalize-space(text())='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();

        waitForPriceToStabilize(totalPayable, previousPayable);
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

        try {
            wait.until(d -> !additionalBenefitItems.isEmpty()
                    && additionalBenefitItems.get(0).isDisplayed());
        } catch (Exception ignored) {}
    }

    public List<String> extractAdditionalBenefits() {
        List<String> benefits = new ArrayList<>();
        for (WebElement item : additionalBenefitItems) {
            try {
                String txt = item.findElement(By.xpath("./div/div/h5")).getText().trim();
                if (!txt.isEmpty()) benefits.add(txt);
            } catch (Exception ignored) {
                // Skip malformed cards
            }
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