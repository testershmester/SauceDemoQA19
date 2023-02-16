package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {

    @Test
    public void checkProductPriceInTheCart() {
        final String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsDefaultUser()
                                                         .addProductToCart(productName);
        String expectedPrice = productsPage.getProductPrice(productName);
        String actualPrice = productsPage.openCartPage()
                                         .getProductPrice(productName);

        assertThat(actualPrice).as("Product should be added with correct price")
                               .isEqualTo(expectedPrice);
    }
}
