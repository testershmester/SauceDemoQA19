package by.teachmeskills;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected Faker faker;

    private Logger log = LogManager.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) {
        String isHeadlessOnJenkins = System.getenv("isHeadless");
        log.info("The parameter isHeadless is set as {}", isHeadlessOnJenkins);
        boolean isHeadlessRes = false;
        if (StringUtils.isNotEmpty(isHeadlessOnJenkins)) {
            isHeadlessRes = Boolean.parseBoolean(isHeadlessOnJenkins);
        }
        if (StringUtils.equals(browser, "edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (isHeadlessRes) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver(options);
        } else if (StringUtils.equals(browser, "firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadlessRes) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadlessRes) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        faker = new Faker();
        testContext.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
