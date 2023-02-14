package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import static by.teachmeskills.page.LoginPage.PASSWORD_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void checkLoginForStandardUser() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsStandardUser();
        assertThat(productsPage.isOpened())
                .isTrue()
                .as("Products page should be opened after user logged in with valid credentials");
    }

    @Test
    public void checkLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .loginAs("standard_user", "");
        assertThat(loginPage.getErrorText()).isEqualTo(PASSWORD_ERROR)
                                            .as("The error \"" + PASSWORD_ERROR + "\" should be displayed if password has not been entered");

    }
}
