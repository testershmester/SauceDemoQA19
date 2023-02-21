package by.teachmeskills.page;

public enum ProductSortFilter {
    NameAtoZ("Name (A to Z)"),
    NameZtoA("Name (Z to A)"),
    PriceLowToHigh("Price (low to high)"),
    PriceHighToLow("Price (high to low)");

    private final String option;

    ProductSortFilter(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
