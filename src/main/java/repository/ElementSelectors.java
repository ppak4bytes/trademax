package repository;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ElementSelectors {

    public static class IndexPageSelectors {
        public static final SelenideElement searchField = $x("//div[@id='search']/descendant::input"),
                cookieButton = $x("//div[@id='cookiesPolicy']/descendant::button"),
                iFrame = $x("//div[@id='PopupSignupForm_0']"),
                iFrameCloseButton = iFrame.$(By.className("mc-closeModal")),
                categoriesTab = $x("//li[contains(text(), 'Kategor')]"),
                firstProductInCategoryList = $x("//*[@id='productListExtended']/div/div[1]//article/div/div/h3/a");
    }

    public static class ProductPageSelectors {
        public static final SelenideElement addToCart = $x("//div[@id='productBuySection']/descendant::button[1]"),
                confirmationPopUp = $x("//div[@id='confirmationPopup']//a"),
                iFrameCloseButton = $x("//div[@id='ve-panel-iframe-close']");

    }

    public static class CheckOutPageSelectors {
        public static final ElementsCollection payMethods = $$x("//form[@id='paymentMethodsForm']/descendant::input");

        public static final SelenideElement companyTab = $x("//ul[contains(@class,'tabs')]/li[2]"),
                ssnField = $x("//*[@id='sveaSsnFormCompany']/div/input"),
                emailFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='email']"),
                emailFieldCompany = $x("//form[@id='sveaManuallyAddressFormCompany']//input[@id='email']"),
                addressLinkPrivate = $x("//div[@id='checkout']//form[@id='sveaSsnFormPrivate']/a"),
                addressLinkCompany = $x("//div[@id='checkout']//form[@id='sveaSsnFormCompany']/a"),
                addressFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='address']"),
                postcodeFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='postcode']"),
                addressFieldCompany = $x("//form[@id='sveaManuallyAddressFormCompany']//input[@id='address']"),
                postcodeFieldCompany = $x("//form[@id='sveaManuallyAddressFormCompany']//input[@id='postcode']"),
                ssnPopUpFrame = $x("//div[contains(text(), 'Välj din leveransadress')]"),
                ssnAddressButton = $x("//div[contains(text(), 'Välj din leveransadress')]/following-sibling::div[1]/descendant::button[1]"),
                paymentAgreement = $x("//div[@class='payment--agreement']"); // bottom page element for scrolling
    }
}
