package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private static final By BURGER_MENU_BUTTON = By.id("back-to-products");
    private static final By CHECKOUT_COMPLETE_TITLE = By.xpath("//span[text()='Checkout: Complete!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage backHome() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        return new ProductsPage(driver);
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_COMPLETE_TITLE));
        return driver.findElement(CHECKOUT_COMPLETE_TITLE).isDisplayed();
    }
}
