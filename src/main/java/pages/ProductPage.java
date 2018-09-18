package pages;

import com.codeborne.selenide.WebDriverRunner;

import java.util.Set;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static repository.ElementSelectors.ProductPageSelectors.*;

public class ProductPage extends Page {


    public ProductPage addItemToCart() {
        addToCart.waitUntil(appear, waitTimeout()).doubleClick();
        sleep(4000);
        Set<String> wh = WebDriverRunner.getWebDriver().getWindowHandles();
        wh.stream().forEach(System.out::println);
        confirmationPopUp.waitUntil(appear, waitTimeout()).click();
        return this;
    }

}
