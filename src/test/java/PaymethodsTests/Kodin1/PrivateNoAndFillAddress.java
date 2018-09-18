package PaymethodsTests.Kodin1;

import PaymethodsTests.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

public class PrivateNoAndFillAddress extends BaseTest {

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

    @Description("Validates if actual paymethods correspond to expected ones with no address provided")
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethodsNoAddress() {
        checkOutPage = new CheckOutPage()
                .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_INVOICE"), "Should contain Svea invoice value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_PAYMENT_PLAN"), "Should contain Svea payment plan value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("PAYTRAIL_DIRECT_BANK"), "Should contain Paytrail direct value");
        sf.assertAll();
    }

    @Description("Validates if Private customer Address form can be filled with provided address details")
    @Parameters({"address", "postcode"})
    @Test(dependsOnMethods = "availablePayMethodsNoAddress")
    public void fillAddress(String address, String postcode) {
        WebDriverRunner.getWebDriver().navigate().refresh();
        checkOutPage.scrollToAddressFormPrivate();
        checkOutPage.fillFormFieldPrivate(address, postcode);
    }

    @Description("Validates if actual paymethods correspond to expected ones based on Private customer address details")
    @Test(dependsOnMethods = "fillAddress")
    public void availablePayMethodsFilledAddress() {
        checkOutPage.getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_INVOICE"), "Should contain Svea invoice value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_PAYMENT_PLAN"), "Should contain Svea payment plan value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("PAYTRAIL_DIRECT_BANK"), "Should contain Paytrail direct value");
        sf.assertAll();
    }

}
