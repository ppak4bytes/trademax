package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static repository.ElementSelectors.CheckOutPageSelectors.*;

@SuppressWarnings("never used")
public class CheckOutPage extends Page {

    public List<String> payMethodsList = new ArrayList<>();


    public CheckOutPage getPayMethodValues() {
        paymentAgreement.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        final List<SelenideElement> temp = new ArrayList<>();
        payMethods.iterator().forEachRemaining(temp::add);
        payMethodsList = temp.stream().map(el -> el.getAttribute("value")).collect(Collectors.toList());
        payMethodsList.forEach(System.out::println);
        listOfPaymentMethods(payMethodsList);
        sleep(2000);
        return this;
    }

    public CheckOutPage openAddressFormPrivate() {
        addressLinkPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
        emailFieldPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        sleep(2000);
        return this;
    }

    public CheckOutPage selectCompanyTab() {
        companyTab.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
        return this;
    }

    public CheckOutPage fillFormFieldCompany(String value1, String value2) {
        addressFieldCompany.waitUntil(appear, waitTimeout()).setValue(value1).pressTab();
        postcodeFieldCompany.waitUntil(appear, waitTimeout()).setValue(value2).pressTab();
        sleep(2000);
        return this;
    }

    public CheckOutPage fillFormFieldPrivate(String value1, String value2) {
        addressFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value1).pressTab();
        postcodeFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value2).pressTab();
        sleep(2000);
        return this;
    }

    public CheckOutPage scrollToAddressFormPrivate() {
        emailFieldPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(true);
        return this;
    }

    public CheckOutPage scrollToAddressFormCompany() {
        emailFieldCompany.waitUntil(appear, waitTimeout()).scrollIntoView(true);
        return this;
    }

    public CheckOutPage openAddressFormCompany() {
        addressLinkCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
        emailFieldCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        sleep(2000);
        return this;
    }

    public CheckOutPage fillSsnAndSelectAddress(String ssn) {
        addressLinkCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        ssnField.waitUntil(appear, waitTimeout()).scrollIntoView(false).setValue(ssn).pressEnter();
        ssnPopUpFrame.waitUntil(appear, waitTimeout());
        ssnAddressButton.waitUntil(appear, waitTimeout()).click();
        return this;
    }

    public CheckOutPage kodin1FillSsn(String ssn) {
        final SelenideElement kodin1SsnField = $x("//input[@id='ssn']").append("[1]");
        kodin1SsnField.waitUntil(appear, waitTimeout()).scrollIntoView(false).setValue(ssn).pressEnter();
        return this;
    }

    @Attachment
    private String listOfPaymentMethods(List<String> list) {
        return list.stream().collect(Collectors.joining("\n" + "-", "The list of paymethods is:" + "\n" + "-", ""));
    }


}
