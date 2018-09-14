package PaymethodsTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;

//@Listeners({TestExecutionListener.class})
public class CompanySsn extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;

    @Parameters({"url", "item"})
    @Test
    public void searchForItemAndAddToCart(String url, String item){
        homePage = new IndexPage(url);
        productPage = homePage.searchForItem(item);
        productPage.addItemToCart();
    }

    @Parameters({"ssn"})
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void fillSsn(String ssn){
        checkOutPage = new CheckOutPage()
                       .selectCompanyTab()
                       .fillSsnAndSelectAddress(ssn)
                       .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_INVOICE"), "Should contain Svea invoice value");
        sf.assertAll();
    }
}
