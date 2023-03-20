package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static by.teachmeskills.page.LoginPage.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(priority = 0)
    public void checkLoginForStandardUser() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsStandardUser();
        assertThat(productsPage.isOpened())
                .isTrue()
                .as("Products page should be opened after user logged in with valid credentials");
    }

    @Test(priority = 3)
    public void checkLoginForPerformanceGlitchUser() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsPerformanceGlitchUser();
        assertThat(productsPage.isOpened())
                .isTrue()
                .as("Products page should be opened after user logged in with valid credentials");
    }

    @Test(priority = 1)
    public void checkLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .loginAs(LoginPage.getDefaultUserName(), "");
        assertThat(loginPage.getErrorText()).isEqualTo(PASSWORD_ERROR)
                                            .as("The error \"" + PASSWORD_ERROR + "\" should be displayed if password has not been entered");

    }

    @Test(priority = 2)
    public void checkLoginWithoutUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .loginAs("", LoginPage.getDefaultPassword());
        assertThat(loginPage.getErrorText()).isEqualTo(USER_NAME_ERROR)
                                            .as("The error \"" + USER_NAME_ERROR + "\" should be displayed if password has not been entered");

    }

    @Test(priority = 4)
    public void checkLoginForLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver).open()
                                                   .loginAsLockedOutUser();
        assertThat(loginPage.getErrorText()).isEqualTo(USER_LOCKED_OUT_ERROR)
                                            .as("The error \"" + USER_LOCKED_OUT_ERROR + "\" should be displayed if password has not been entered");
    }
}
