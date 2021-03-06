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
import java.util.List;

public class CompanyNoAddress extends BaseTest {

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

    @Description("Validates if actual paymethods correspond to expected ones with no address provided and selected Company tab")
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethodsNoAddress() {
        checkOutPage = new CheckOutPage()
                .selectCompanyTab()
                .openAddressFormCompany()
                .getPayMethodValues()
                .getZipCodeValue();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertTrue(zipCodeLengthValidationRule(homePage).equals(checkOutPage.zipCodeFieldLength), "ZipCodeField length does not match expected value");
        sf.assertAll();
        testPayMethods = new ArrayList<>(checkOutPage.payMethodsList);
    }

}
