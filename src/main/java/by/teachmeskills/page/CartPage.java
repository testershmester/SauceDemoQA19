package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private String PRODUCT_IN_THE_CART_LOCATOR = "//div[text()='%s']/ancestor::div[@class='cart_item']";
    private String PRODUCT_PRICE_LOCATOR = PRODUCT_IN_THE_CART_LOCATOR + "//div[@class='inventory_item_price']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProduct(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_IN_THE_CART_LOCATOR, productName));
        return driver.findElement(fullLocator);
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(PRODUCT_PRICE_LOCATOR, productName));
        return driver.findElement(fullLocator).getText();
    }
}
