package tests.local;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;


public class PrivateCustomerFillAddress extends BaseTest {

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

    @Description("Fill address details")
    @Test(dependsOnMethods = "searchForItemAndAddToCart", dataProvider = "userAddress")
    public void fillAddress(String s1, String s2){
        checkOutPage = new CheckOutPage();
        checkOutPage.openAddressForm();
        checkOutPage.fillFormField(s1, s2);
    }

    @Description("Validates payment methods for Private customer with filled address")
    @Test(dependsOnMethods = "fillAddress")
    public void availablePayMethods(){
        checkOutPage.getPayMethodValues();
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"));
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"));
    }

    @DataProvider
    public Object[][] userAddress(){
        return new Object[][]{{"Universitetsvägen 2B", "106 91"},};
        }
}
