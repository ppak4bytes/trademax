package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static repository.ElementSelectors.IndexPageSelectors.*;


public class IndexPage extends Page {


    public IndexPage(String url) {

        open(url);
        currentUrl = url;
    }

    public ProductPage searchForItem(String itemID) {

        currentItem = itemID;
        final SelenideElement foundCategory = $x("//div[@id='searchPageTabs']//a[@title='" + itemID + "']");
        if(iFrame.exists()) iFrameCloseButton.click();
        if(cookieButton.isDisplayed()) cookieButton.click();
        searchField.waitUntil(appear, waitTimeout()).setValue(itemID).pressEnter();
        categoriesTab.shouldBe(visible).click();
        foundCategory.shouldBe(visible).doubleClick();
        if (!firstProductInCategoryList.is(visible)) {firstProductInCategoryList.scrollIntoView(false).doubleClick();}
        else firstProductInCategoryList.doubleClick();
        return new ProductPage();
    }

}
