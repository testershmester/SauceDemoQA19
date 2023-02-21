package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutTest extends BaseTest {

    @Test
    public void checkLogOutFromBurgerMenu() {
        LoginPage loginPage = new LoginPage(driver).open()
                                                   .loginAsDefaultUser()
                                                   .openMenu()
                                                   .logOut();
        assertThat(loginPage.isOpened()).as("Logout from burger menu doesn't return user on Login page")
                                        .isTrue();
    }
}
