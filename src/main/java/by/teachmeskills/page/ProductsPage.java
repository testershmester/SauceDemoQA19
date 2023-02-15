package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private By PRODUCTS_TITLE = By.xpath("//span[text()='Products']");
    private By CART = By.id("shopping_cart_container");
    private By ALL_PRODUCTS = By.xpath("//div[@class='inventory_item']");

    private String PRODUCT_CARD_LOCATOR = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
    private String PRODUCT_PRICE_LOCATOR = PRODUCT_CARD_LOCATOR + "//div[@class='inventory_item_price']";
    private String ADD_TO_CART_BUTTON_LOCATOR = PRODUCT_CARD_LOCATOR + "//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return driver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getAllProducts() {
        return driver.findElements(ALL_PRODUCTS);
    }

    public ProductsPage addProductToCart(String productName) {
        By fullLocator = By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName));
        driver.findElement(fullLocator).click();
        return this;
    }

    public CartPage openCartPage() {
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_PRICE_LOCATOR, productName));
        return driver.findElement(fullLocator).getText();
    }
}
