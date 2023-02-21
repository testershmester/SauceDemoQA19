package by.teachmeskills;

import by.teachmeskills.page.CartPage;
import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    public static final String SAUCE_LABS_BACKPACK = "Sauce Labs Backpack";
    public static final String SAUCE_LABS_BOLT_T_SHIRT = "Sauce Labs Bolt T-Shirt";

    @Test
    public void cookies() {
        new LoginPage(driver).open();
        Cookie standardUser = new Cookie("session-username", "standard_user");
        driver.manage().addCookie(standardUser);
        new ProductsPage(driver).open();
    }

    @Test
    public void checkProductOnThePage() {
        final String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new LoginPage(driver).open().loginAsDefaultUser();

        List<String> actProductName = productsPage.getAllProductNames();
        assertThat(actProductName).as("Product \"" + productName + "\" should be displayed on the page")
                                  .contains(productName);
    }

    @Test
    public void checkProductsCountPage() {
        ProductsPage productsPage = new LoginPage(driver).open().loginAsDefaultUser();
        List<WebElement> products = productsPage.getAllProducts();
        assertThat(products).as("Products should be displayed on the page").isNotNull().as("Products page should have 6 products").hasSize(6);
    }

    @Test(groups = "cart")
    public void checkProductCanBeAddedToCart() {
        CartPage cartPage = new LoginPage(driver).open()
                                                 .loginAsDefaultUser()
                                                 .addProductToCart(SAUCE_LABS_BACKPACK)
                                                 .openCart();
        List<WebElement> allProductsInCart = cartPage.getAllProductsInCart();

        assertThat(allProductsInCart).as("One product should be in the cart")
                                     .hasSize(1);
        assertThat(allProductsInCart.get(0).getText())
                .as("Product \"" + SAUCE_LABS_BACKPACK + "\"should be in the cart")
                .isEqualTo(SAUCE_LABS_BACKPACK);
    }

    @Test(groups = "cart")
    public void checkProductCanBeRemovedFromCart() {
        CartPage cartPage = new LoginPage(driver).open()
                                                 .loginAsDefaultUser()
                                                 .addProductToCart(SAUCE_LABS_BACKPACK)
                                                 .addProductToCart(SAUCE_LABS_BOLT_T_SHIRT)
                                                 .openCart();

        List<WebElement> allProductsInCart = cartPage.getAllProductsInCart();
        assertThat(allProductsInCart).as("Two products should be in the cart")
                                     .hasSize(2);

        List<String> actProductNames = allProductsInCart.stream()
                                                        .map(WebElement::getText)
                                                        .collect(Collectors.toList());
        assertThat(actProductNames)
                .as("Product \"" + SAUCE_LABS_BOLT_T_SHIRT + "\"should be in the cart")
                .contains(SAUCE_LABS_BOLT_T_SHIRT)
                .as("Product \"" + SAUCE_LABS_BACKPACK + "\"should be in the cart")
                .contains(SAUCE_LABS_BACKPACK);
        cartPage.removeProductFromCart(SAUCE_LABS_BOLT_T_SHIRT);

        List<WebElement> allProductsInCartAfterRemove = cartPage.getAllProductsInCart();
        assertThat(allProductsInCartAfterRemove).as("Only one product should left in the cart after removing")
                                                .hasSize(1);
        assertThat(allProductsInCartAfterRemove.get(0).getText())
                .as("Product \"" + SAUCE_LABS_BACKPACK + "\"should be in the cart")
                .isEqualTo(SAUCE_LABS_BACKPACK);
    }
}
