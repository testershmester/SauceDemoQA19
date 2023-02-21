package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import by.teachmeskills.page.ProductSortFilter;
import by.teachmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductFilterTest extends BaseTest {

    private final List<String> expProducts = List.of("Sauce Labs Backpack",
                                                     "Sauce Labs Bike Light",
                                                     "Sauce Labs Bolt T-Shirt",
                                                     "Sauce Labs Fleece Jacket",
                                                     "Sauce Labs Onesie",
                                                     "Test.allTheThings() T-Shirt (Red)");

    @Test
    public void checkProductsSorting() {
        ProductsPage productsPage = new LoginPage(driver)
                .open()
                .loginAsStandardUser()
                .sortProducts(ProductSortFilter.NameAtoZ);
        List<String> actProducts = productsPage.getAllProductNames();
        assertThat(actProducts).isSorted();

        productsPage.sortProducts(ProductSortFilter.NameZtoA);
        actProducts = productsPage.getAllProductNames();
        assertThat(actProducts).isSortedAccordingTo(Comparator.reverseOrder());
    }
}
