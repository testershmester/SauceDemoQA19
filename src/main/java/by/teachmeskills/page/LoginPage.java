package by.teachmeskills.page;

import by.teachmeskills.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BasePage {

    private static final By USER_NAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.xpath("//h3[@data-test='error']");

    public static final String PASSWORD_ERROR = "Epic sadface: Password is required";
    public static final String USER_NAME_ERROR = "Epic sadface: Username is required";
    public static final String USER_LOCKED_OUT_ERROR = "Epic sadface: Sorry, this user has been locked out.";

    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";

    private Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url"));
        return this;
    }

    public boolean isOpened() {
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    public ProductsPage loginAsDefaultUser() {
        String defaultUserName = getDefaultUserName();
        String defaultPassword = getDefaultPassword();
        log.info("Login with default user: {}, {}", defaultUserName, defaultPassword);
        loginAs(defaultUserName, defaultPassword);
        return new ProductsPage(driver);
    }

    public void loginAs(String userName, String password) {
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public ProductsPage loginAsStandardUser() {
        String userName = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        log.info("Login as standard user: {}, {}", userName, password);
        loginAs(userName, password);
        return new ProductsPage(driver);
    }

    public LoginPage loginAsLockedOutUser() {
        loginAs(LOCKED_OUT_USER, System.getenv("PASSWORD"));
        return this;
    }

    public ProductsPage loginAsPerformanceGlitchUser() {
        loginAs(PERFORMANCE_GLITCH_USER, System.getenv("PASSWORD"));
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.isOpened();
        return productsPage;
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }

    public static String getDefaultUserName() {
        return System.getenv("USERNAME");
    }

    public static String getDefaultPassword() {
        return System.getenv("PASSWORD");
    }
}