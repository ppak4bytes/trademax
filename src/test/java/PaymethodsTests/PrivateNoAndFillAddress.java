package PaymethodsTests;

import base.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PrivateNoAndFillAddress extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;
    List<String> payMthds;

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
                .openAddressFormPrivate()
                .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
        testPayMethods = new ArrayList<>(checkOutPage.payMethodsList);

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
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
        testPayMethods = new ArrayList<>(checkOutPage.payMethodsList);
    }
}
