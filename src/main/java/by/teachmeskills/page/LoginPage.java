package by.teachmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USER_NAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.xpath("//h3[@data-test='error']");

    public static final String PASSWORD_ERROR = "Epic sadface: Password is required";
    public static final String USER_NAME_ERROR = "Epic sadface: Username is required";
    public static final String USER_LOCKED_OUT_ERROR = "Epic sadface: Sorry, this user has been locked out.";

    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    public static final String STANDARD_PASSWORD = "secret_sauce";

    public static final String DEFAULT_USER = STANDARD_USER;
    public static final String DEFAULT_PASSWORD = STANDARD_PASSWORD;

    public static final String BASE_URL = "https://www.saucedemo.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public boolean isOpened() {
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    public ProductsPage loginAsDefaultUser() {
        loginAs(DEFAULT_USER, DEFAULT_PASSWORD);
        return new ProductsPage(driver);
    }

    public void loginAs(String userName, String password) {
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public ProductsPage loginAsStandardUser() {
        loginAs(STANDARD_USER, STANDARD_PASSWORD);
        return new ProductsPage(driver);
    }

    public LoginPage loginAsLockedOutUser() {
        loginAs(LOCKED_OUT_USER, STANDARD_PASSWORD);
        return this;
    }

    public ProductsPage loginAsPerformanceGlitchUser() {
        loginAs(PERFORMANCE_GLITCH_USER, STANDARD_PASSWORD);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.isOpened();
        return productsPage;
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }
}