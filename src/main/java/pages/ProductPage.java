package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage implements Page {

    private SelenideElement addToCart = $x("//div[@id='productBuySection']//button"),
                            confirmationPopUp = $x("//div[@id='confirmationPopup']//a"),
                            addressLink = $x("//div[@id='checkout']//form[@id='sveaSsnFormPrivate']/a");



    public ProductPage addItemToCart(){
        addToCart.waitUntil(appear, waitTimeout()).click();
        confirmationPopUp.waitUntil(appear, waitTimeout()).click();
        return this;
    }

}
