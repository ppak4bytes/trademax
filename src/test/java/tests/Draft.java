package tests;

import PaymethodsTests.BaseTest;
import listeners.AllureScreenShotListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.IndexPage;
import pages.ProductPage;

@Listeners({AllureScreenShotListener.class})
public class Draft extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;

    @Test
    public void searchForItemAndAddToCart(){
        homePage = new IndexPage("https://www.chilli.se");
        productPage = homePage.searchForItem("Soffor");
        productPage.addItemToCart();
    }
}
