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

    @Test(dependsOnMethods = "searchForItemAndAddToCart", dataProvider = "ssn")
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

//    @Description("Validates default payment methods for Private customer with empty address")
//    @Test(dependsOnMethods = "searchForItemAndAddToCart")
//    public void availablePayMethodsNoAddress(){
//        checkOutPage = new CheckOutPage();
//        checkOutPage.selectCompanyTab()
//                    .openAddressFormCompany()
//                    .getPayMethodValues();
//        assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"));
//        assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"));
//    }

//    @Description("Fill address details")
//    @Test(dependsOnMethods = "availablePayMethodsNoAddress", dataProvider = "userAddress")
//    public void fillAddress(String s1, String s2){
//        WebDriverRunner.getWebDriver().navigate().refresh();
//        checkOutPage.scrollToAddressFormCompany()
//                    .fillFormFieldCompany(s1, s2);
//    }


    @DataProvider
    public Object[][] ssn(){ return new Object[][]{new Object[]{"556780-9685"}};
    }

    @DataProvider
    public Object[][] userAddress(){
        return new Object[][]{{"Universitetsvägen 2B", "10691"},};
    }
}
