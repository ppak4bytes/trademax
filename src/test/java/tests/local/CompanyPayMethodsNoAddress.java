package tests.local;

import io.qameta.allure.Description;
import listeners.TestExecutionListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

//@Listeners({TestExecutionListener.class})
public class CompanyPayMethodsNoAddress extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;

    @Description("Search for category and put first item to cart")
    @Test
    public void searchForItemAndAddToCart(){
        homePage = new IndexPage();
        productPage = homePage.searchForItem("Soffor");
        productPage.addItemToCart();
    }

    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethodsNoAddress(){
        checkOutPage = new CheckOutPage()
                       .selectCompanyTab()
                       .openAddressFormCompany()
                       .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
    }
}
