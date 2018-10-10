package PaymethodsTests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class CompanySsn extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;

    @Description("Validates if Item can be found and added to cart on specified channel")
    @Parameters({"url", "item"})
    @Test
    public void searchForItemAndAddToCart(String url, String item) {
        homePage = new IndexPage(url);
        productPage = homePage.searchForItem(item);
        productPage.addItemToCart();
    }

    @Description("Validates if proposed address can be selected from ssn pop-up and if actual paymethods correspond to expected ones based on provided ssn")
    @Parameters({"ssn"})
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void fillSsn(String ssn) {
        checkOutPage = new CheckOutPage()
                .selectCompanyTab()
                .fillSsnAndSelectAddress(ssn)
                .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_INVOICE"), "Should contain Svea invoice value");
        sf.assertAll();
        testPayMethods = new ArrayList<>(checkOutPage.payMethodsList);
    }
}
