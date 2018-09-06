package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;


public class IndexPage implements Page {


    private SelenideElement searchField = $x("//div[@id='search']/descendant::input"),
                            cookieButton = $x("//div[@id='cookiesPolicy']/descendant::button"),
                            categoriesTab = $x("//li[@class='tabs--button ']"),
                            foundCategory = $x("//*[@id='searchPageTabs']/div/div/div[2]/div/div/div[1]/a"),
                            firstProductInCategoryList = $x("//*[@id='productListExtended']/div/div[1]/article/div/div[1]/h3/a");


    public ProductPage searchForItem(String itemID){
        open(url());
        if(cookieButton.isDisplayed()) cookieButton.shouldBe(visible).click();
        searchField.waitUntil(appear, waitTimeout()).click();
        searchField.setValue(itemID).pressEnter();
        categoriesTab.waitUntil(appear, waitTimeout()).click();
        foundCategory.waitUntil(appear, waitTimeout()).click();
        firstProductInCategoryList.waitUntil(appear, waitTimeout()).click();
        return new ProductPage();
    }


}
