package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private static final String PRODUCT_IN_THE_CART_LOCATOR = "//div[text()='%s']/ancestor::div[@class='cart_item']";
    private static final String PRODUCT_PRICE_LOCATOR = PRODUCT_IN_THE_CART_LOCATOR + "//div[@class='inventory_item_price']";
    private static final String REMOVE_BUTTON_LOCATOR = PRODUCT_IN_THE_CART_LOCATOR + "//button[text()='Remove']";

    private static final By CART_TITLE = By.xpath("//span[text()='YOUR CART']");
    private static final By CONTINUE_SHOPPING_BUTTON_LOCATOR = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON_LOCATOR = By.id("checkout");

    private static final By ALL_PRODUCTS_IN_CART = By.xpath("//div[@class='inventory_item_name']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
        return driver.findElement(CART_TITLE).isDisplayed();
    }

    public List<WebElement> getAllProductsInCart() {
        return driver.findElements(ALL_PRODUCTS_IN_CART);
    }

    public WebElement getProduct(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_IN_THE_CART_LOCATOR, productName));
        return driver.findElement(fullLocator);
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_PRICE_LOCATOR, productName));
        return driver.findElement(fullLocator).getText();
    }

    public CheckoutPage checkout() {
        driver.findElement(CHECKOUT_BUTTON_LOCATOR).click();
        return new CheckoutPage(driver);
    }

    public CartPage removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON_LOCATOR, productName))).click();
        return this;
    }

    public ProductsPage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON_LOCATOR).click();
        return new ProductsPage(driver);
    }
}
