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
        homePage = new IndexPage("https://www.trademax.se");
        productPage = homePage.searchForItem("Soffor");
        productPage.addItemToCart();
    }

    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void fillSsn(){
        checkOutPage = new CheckOutPage()
                .selectCompanyTab()
                .fillSsnAndSelectAddress("556780-9685")
                .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_INVOICE"), "Should contain Svea invoice value");
        sf.assertAll();
    }
}
