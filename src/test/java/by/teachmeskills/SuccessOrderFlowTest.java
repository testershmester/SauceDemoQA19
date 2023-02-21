package by.teachmeskills;

import by.teachmeskills.page.CheckoutCompletePage;
import by.teachmeskills.page.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessOrderFlowTest extends BaseTest {

    public static final String SAUCE_LABS_ONESIE = "Sauce Labs Onesie";

    @Test
    public void checkSuccessOneProductOrderFlow() {
        CheckoutCompletePage finalPage = new LoginPage(driver).open()
                                                              .loginAsStandardUser()
                                                              .addProductToCart(SAUCE_LABS_ONESIE)
                                                              .openCart()
                                                              .checkout()
                                                              .fillInCheckoutInfo(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode())
                                                              .finish();
        assertThat(finalPage.isOpened()).as("Final page has not been opened after checkout")
                                        .isTrue();
    }
}
