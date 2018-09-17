package PaymethodsTests.Kodin1;

import PaymethodsTests.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.IndexPage;
import pages.ProductPage;

public class CompanySsn extends BaseTest {

    private IndexPage homePage;
    private ProductPage productPage;
    private CheckOutPage checkOutPage;

    @Description("Validates if Item can be found and added to cart on specified channel")
    @Parameters({"url", "item"})
    @Test
    public void searchForItemAndAddToCart(String url, String item){
        homePage = new IndexPage(url);
        productPage = homePage.searchForItem(item);
        productPage.addItemToCart();
    }
}
