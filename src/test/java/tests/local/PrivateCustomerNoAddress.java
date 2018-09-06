package tests.local;


import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;

public class PrivateCustomerNoAddress extends BaseTest{

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

    @Description("Validates default payment methods for Private customer with empty address")
    @Test(dependsOnMethods = "searchForItemAndAddToCart")
    public void availablePayMethods(){
        checkOutPage = new CheckOutPage();
        checkOutPage.openAddressForm();
        checkOutPage.getPayMethodValues();
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"));
        assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"));
    }

}
