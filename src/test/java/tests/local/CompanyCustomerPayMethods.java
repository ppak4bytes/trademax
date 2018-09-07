package tests.local;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import listeners.TestExecutionListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;

@Listeners({TestExecutionListener.class})
public class CompanyCustomerPayMethods extends BaseTest {

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

    @Test
    public void fillSsn(){
        checkOutPage = new CheckOutPage();
        checkOutPage.selectCompanyTab()
                    .fillSsnAndSelectAddress("")
                    .getPayMethodValues();
        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"));
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"));
        sf.assertTrue(c);
    }

    @Description("Validates default payment methods for Private customer with empty address")
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethodsNoAddress(){
        checkOutPage = new CheckOutPage();
        checkOutPage.selectCompanyTab()
                    .openAddressFormCompany()
                    .getPayMethodValues();
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"));
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"));
    }

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
