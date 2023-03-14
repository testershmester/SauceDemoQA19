package by.teachmeskills.page;

import by.teachmeskills.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private static final By PRODUCTS_TITLE = By.xpath("//span[text()='Products']");
    private static final By CART = By.id("shopping_cart_container");
    private static final By ALL_PRODUCTS = By.xpath("//div[@class='inventory_item']");
    private static final By ALL_PRODUCTS_NAMES = By.xpath("//div[@class='inventory_item_name']");
    private static final By SORT_FILTER = By.xpath("//select[@data-test='product_sort_container']");

    private static final String PRODUCT_CARD_LOCATOR = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
    private static final String PRODUCT_PRICE_LOCATOR = PRODUCT_CARD_LOCATOR + "//div[@class='inventory_item_price']";
    private static final String ADD_TO_CART_BUTTON_LOCATOR = PRODUCT_CARD_LOCATOR + "//button[text()='Add to cart']";

    Logger log = LogManager.getLogger(ProductsPage.class);


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage open() {
        driver.get(PropertiesLoader.loadProperties().getProperty("base.url") + "/inventory.html");
        isOpened();
        waitForPageLoaded();
        return this;
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
        return driver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getAllProducts() {
        return driver.findElements(ALL_PRODUCTS);
    }

    public List<String> getAllProductNames() {
        return driver.findElements(ALL_PRODUCTS_NAMES)
                     .stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }

    public ProductsPage addProductToCart(String productName) {
        log.info("Add product {} to cart", productName);
        By fullLocator = By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName));
        driver.findElement(fullLocator).click();
        return this;
    }

    public CartPage openCart() {
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    public Menu openMenu() {
        return new Header(driver).openBurgerMenu();
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_PRICE_LOCATOR, productName));
        return driver.findElement(fullLocator).getText();
    }

    public ProductsPage sortProducts(ProductSortFilter sortOption) {
        Select select = new Select(driver.findElement(SORT_FILTER));
        select.selectByVisibleText(sortOption.getOption());
        return new ProductsPage(driver);
    }
}
