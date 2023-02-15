package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsCount() {
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsStandardUser();
        List<WebElement> products = productsPage.getAllProducts();
        assertThat(products)
                .as("Product page should exist on the page")
                .isNotNull()
                .as("Products page should have 6 products")
                .hasSize(6);

        Assert.assertNotNull(products, "Product page should exist on the page");
        Assert.assertEquals(CollectionUtils.size(products), 6,"Products page should have 6 products");
    }
}
