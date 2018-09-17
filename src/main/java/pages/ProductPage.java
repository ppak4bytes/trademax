package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;


import java.util.Set;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage extends Page {


    private SelenideElement addToCart = $x("//div[@id='productBuySection']/descendant::button[1]"),
                            confirmationPopUp = $x("//div[@id='confirmationPopup']//a");

    public ProductPage addItemToCart(){
        addToCart.waitUntil(appear, waitTimeout()).doubleClick();
        switchTo().activeElement();
        Set<String> wh = WebDriverRunner.getWebDriver().getWindowHandles();
        System.out.println("!!!!!!!!!!!!---------------" + wh.size() + "---------------!!!!!!!!!!!!!!!!");
        confirmationPopUp.waitUntil(appear, waitTimeout()).click();
        return this;
    }

}
