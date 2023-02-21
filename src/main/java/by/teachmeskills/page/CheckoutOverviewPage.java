package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    private static final By CHECKOUT_OVERVIEW_TITLE = By.xpath("//span[text()='Checkout: Overview']");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_OVERVIEW_TITLE));
        return driver.findElement(CHECKOUT_OVERVIEW_TITLE).isDisplayed();
    }

    public CheckoutCompletePage finish() {
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }
}
