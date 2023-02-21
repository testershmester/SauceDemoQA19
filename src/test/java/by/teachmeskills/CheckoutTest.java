package by.teachmeskills;

import by.teachmeskills.page.CheckoutPage;
import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkContinueShoppingButtonReturnsUserOnProductsPage() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsDefaultUser()
                                                         .openCart()
                                                         .continueShopping();
        assertThat(productsPage.isOpened()).as("The \"Continue shopping\" button doesn't open Products page")
                                        .isTrue();
    }

    @Test
    public void checkCheckoutButtonOpensCheckoutPage() {
        CheckoutPage checkout = new LoginPage(driver).open()
                                                     .loginAsDefaultUser()
                                                     .openCart()
                                                     .checkout();
        assertThat(checkout.isOpened()).as("The \"Checkout button\" does not open checkout page")
                                           .isTrue();
    }
}
