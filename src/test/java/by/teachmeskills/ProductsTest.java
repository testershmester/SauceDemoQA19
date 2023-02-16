package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductsPage;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductOnThePage() {
        final String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new LoginPage(driver).open()
                                                         .loginAsStandardUser();
        List<WebElement> products = productsPage.getAllProducts();
        assertThat(products)
                .as("Product page should exist on the page")
                .isNotNull()
                .as("Products page should have 6 products")
                .hasSize(6);

        List<WebElement> productNames = productsPage.getAllProductNames();
        List<String> actProductName = productNames.stream()
                                              .map(WebElement::getText)
                                              .collect(Collectors.toList());

        assertThat(actProductName).as("Products on the contains \"" + productName + "\"")
                                  .contains(productName);

        Assert.assertNotNull(products, "Product page should exist on the page");
        Assert.assertEquals(CollectionUtils.size(products), 6, "Products page should have 6 products");
    }
}
