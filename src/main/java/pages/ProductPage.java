package pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage extends Page {


    private SelenideElement addToCart = $x("//div[@id='productBuySection']/descendant::button[1]"),
                            confirmationPopUp = $x("//div[@id='confirmationPopup']//a");

    public ProductPage addItemToCart(){
        addToCart.shouldBe(visible).doubleClick();
        switchTo().activeElement();
        confirmationPopUp.shouldBe(visible).click();
        return this;
    }

}
