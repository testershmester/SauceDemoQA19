package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Menu extends BasePage {

    private static final By BURGER_MENU_LOCATOR = By.id("bm-menu-wrap");
    private static final String MENU_ITEM_LOCATOR = "//div[@class='bm-menu']//a[text()='%s']";
    private static final By LOGOUT = By.xpath(String.format(MENU_ITEM_LOCATOR, "Logout"));
    private static final By ALL_ITEMS = By.xpath(String.format(MENU_ITEM_LOCATOR, "All Items"));

    public Menu(WebDriver driver) {
        super(driver);
    }

    public LoginPage logOut() {
        driver.findElement(LOGOUT).click();
        return new LoginPage(driver);
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BURGER_MENU_LOCATOR));
        return driver.findElement(BURGER_MENU_LOCATOR).isDisplayed();
    }
}
