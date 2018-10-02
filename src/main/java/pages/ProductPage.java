package pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static repository.ElementSelectors.ProductPageSelectors.*;

public class ProductPage extends Page {

    public ProductPage addItemToCart() {
        addToCart.shouldBe(visible).doubleClick();
        sleep(4000);
        if(iFrameCloseButton.is(visible)) iFrameCloseButton.click();
        if(!confirmationPopUp.is(visible)) addToCart.click();
        confirmationPopUp.waitUntil(appear, waitTimeout()).click();
        return this;
    }

}
