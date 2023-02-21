package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    private static final By CART_LOCATOR = By.xpath("//div[@id='shopping_cart_container']/a");
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");

    public Header(WebDriver driver) {
        super(driver);
    }

    public CartPage openCart() {
        driver.findElement(CART_LOCATOR).click();
        return new CartPage(driver);
    }

    public Menu openBurgerMenu() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        return new Menu(driver);
    }
}
