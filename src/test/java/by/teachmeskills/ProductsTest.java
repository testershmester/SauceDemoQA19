package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsCount() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsStandardUser();
        List<WebElement> products = productsPage.getProducts();
        Assertions.assertThat(products)
                  .isNotNull()
                  .hasSize(6)
                  .as("Products page should have 6 products");
    }
}
