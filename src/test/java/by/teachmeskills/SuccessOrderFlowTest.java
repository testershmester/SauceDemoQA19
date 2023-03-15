package by.teachmeskills;

import by.teachmeskills.page.CheckoutCompletePage;
import by.teachmeskills.page.LoginPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessOrderFlowTest extends BaseTest {

    public static final String SAUCE_LABS_ONESIE = "Sauce Labs Onesie";

    @Test
    public void checkSuccessOneProductOrderFlow() {
        String productForTest = System.getenv("TEST_PRODUCT");
        productForTest = StringUtils.isEmpty(productForTest) ? SAUCE_LABS_ONESIE : productForTest;
        CheckoutCompletePage finalPage = new LoginPage(driver).open()
                                                              .loginAsStandardUser()
                                                              .addProductToCart(productForTest)
                                                              .openCart()
                                                              .checkout()
                                                              .fillInCheckoutInfo(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode())
                                                              .finish();
        assertThat(finalPage.isOpened()).as("Final page has not been opened after checkout")
                                        .isTrue();
    }
}
