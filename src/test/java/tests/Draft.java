package tests;

import PaymethodsTests.BaseTest;
import org.testng.annotations.Test;
import pages.IndexPage;
import pages.ProductPage;

public class Draft extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;

    @Test
    public void searchForItemAndAddToCart(){
        homePage = new IndexPage("https://www.furniturebox.se");
        productPage = homePage.searchForItem("Soffor");
        productPage.addItemToCart();
    }
}
