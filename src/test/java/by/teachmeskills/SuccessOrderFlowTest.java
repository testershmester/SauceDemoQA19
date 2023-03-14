package by.teachmeskills;

import by.teachmeskills.page.CheckoutCompletePage;
import by.teachmeskills.page.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessOrderFlowTest extends BaseTest {

    public static final String PRODUCT = System.getenv("product") == null ? "Sauce Labs Onesie" :
            System.getenv("product");

    @Test
    public void checkSuccessOneProductOrderFlow() {
        CheckoutCompletePage finalPage = new LoginPage(driver).open()
                                                              .loginAsStandardUser()
                                                              .addProductToCart(PRODUCT)
                                                              .openCart()
                                                              .checkout()
                                                              .fillInCheckoutInfo(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode())
                                                              .finish();
        assertThat(finalPage.isOpened()).as("Final page has not been opened after checkout")
                                        .isTrue();
    }
}
