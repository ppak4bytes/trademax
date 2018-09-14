package PaymethodsTests;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;

//@Listeners({TestExecutionListener.class})
public class PrivateNoAndFillAddress extends BaseTest {

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

    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethodsNoAddress(){
        checkOutPage = new CheckOutPage()
                       .openAddressFormPrivate()
                       .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
    }

    @Parameters({"address", "postcode"})
    @Test(dependsOnMethods = "availablePayMethodsNoAddress")
    public void fillAddress(String address, String postcode){
        WebDriverRunner.getWebDriver().navigate().refresh();
        checkOutPage.scrollToAddressFormPrivate();
        checkOutPage.fillFormFieldPrivate(address, postcode);
    }

    @Test(dependsOnMethods = "fillAddress")
    public void availablePayMethodsFilledAddress(){
        checkOutPage.getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
    }
}
