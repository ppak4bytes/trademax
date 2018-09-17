package tests;

import PaymethodsTests.BaseTest;
import listeners.AllureScreenShotListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

@Listeners({AllureScreenShotListener.class})
public class Draft extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;

    @Test
    public void searchForItemAndAddToCart(){
        homePage = new IndexPage("https://www.trademax.com");
        productPage = homePage.searchForItem("Soffor");
        productPage.addItemToCart();
    }

}
