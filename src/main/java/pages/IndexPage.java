package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
//import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;


public class IndexPage extends Page {


    private SelenideElement searchField = $x("//div[@id='search']/descendant::input"),
                            cookieButton = $x("//div[@id='cookiesPolicy']/descendant::button"),
                            categoriesTab = $x("//li[contains(text(), 'Kategor')]"),
                            firstProductInCategoryList = $x("//*[@id='productListExtended']/div/div[1]//article/div/div/h3/a");


    public IndexPage (String url){

        open(url);
        currentUrl = url;
    }

    public ProductPage searchForItem(String itemID){

        currentItem = itemID;
        final SelenideElement foundCategory = $x("//div[@id='searchPageTabs']//a[@title='"+itemID+"']");
        if(cookieButton.isDisplayed()) cookieButton.click();
        searchField.waitUntil(appear, waitTimeout()).click();
        searchField.setValue(itemID).pressEnter();
        categoriesTab.waitUntil(appear, waitTimeout()).click();
        foundCategory.waitUntil(appear, waitTimeout()).doubleClick();
        if(!firstProductInCategoryList.is(visible)) firstProductInCategoryList.scrollIntoView(false).doubleClick();
        else firstProductInCategoryList.doubleClick();
        return new ProductPage();
    }

}
