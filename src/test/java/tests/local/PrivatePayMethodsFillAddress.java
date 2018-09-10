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

//@Listeners({TestExecutionListener.class})
public class PrivatePayMethodsFillAddress extends BaseTest {

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
    public void availablePayMethodsNoAddress(){
        checkOutPage = new CheckOutPage()
                       .openAddressFormPrivate()
                       .getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
    }

    @Description("Fill address details")
    @Test(dependsOnMethods = "availablePayMethodsNoAddress", dataProvider = "userAddress")
    public void fillAddress(String s1, String s2){
        WebDriverRunner.getWebDriver().navigate().refresh();
        checkOutPage.scrollToAddressFormPrivate();
        checkOutPage.fillFormFieldPrivate(s1, s2);
    }

    @Description("Validates payment methods for Private customer with filled address")
    @Test(dependsOnMethods = "fillAddress")
    public void availablePayMethodsFilledAddress(){
        checkOutPage.getPayMethodValues();

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_CARD"), "Should contain Svea card value");
        sf.assertTrue(checkOutPage.payMethodsList.contains("SVEA_DIRECT_BANK"), "Should contain Svea direct value");
        sf.assertAll();
    }

    @DataProvider
    public Object[][] userAddress(){
        return new Object[][]{{"Universitetsvägen 2B", "10691"},};
    }
}
