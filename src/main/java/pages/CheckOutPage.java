package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

@SuppressWarnings("never used")
public class CheckOutPage extends Page {

     public List<String> payMethodsList = new ArrayList<>();
     final public Map<String, Map<String, String>> parametersList = new HashMap<>();
     final private Map<String, String> params = new HashMap<>();




     private ElementsCollection payMethods = $$x("//form[@id='paymentMethodsForm']/descendant::input");

     private SelenideElement  companyTab = $x("//ul[contains(@class,'tabs')]/li[2]"),
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


      public CheckOutPage getPayMethodValues(){
          paymentAgreement.waitUntil(appear, waitTimeout()).scrollIntoView(false);
          final List<SelenideElement> temp = new ArrayList<>();
          payMethods.iterator().forEachRemaining(temp::add);
          payMethodsList = temp.stream().map(el->el.getAttribute("value")).collect(Collectors.toList());
          payMethodsList.forEach(System.out::println);
          sleep(2000);
          return this;
      }

      public CheckOutPage openAddressFormPrivate(){
        addressLinkPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
        emailFieldPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        sleep(2000);
        return this;
      }

      public CheckOutPage selectCompanyTab(){
          companyTab.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
          return this;
      }

      public CheckOutPage fillFormFieldCompany(String value1, String value2){
          addressFieldCompany.waitUntil(appear, waitTimeout()).setValue(value1).pressTab();
          postcodeFieldCompany.waitUntil(appear, waitTimeout()).setValue(value2).pressTab();
          sleep(2000);
          return this;
      }

      public CheckOutPage fillFormFieldPrivate(String value1, String value2){
        params.put("Adrress", value1);
        params.put("Postcode", value2);
        addressFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value1).pressTab();
        postcodeFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value2).pressTab();
        sleep(2000);
        return this;
      }

      public CheckOutPage scrollToAddressFormPrivate(){
          emailFieldPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(true);
          return this;
      }

      public CheckOutPage scrollToAddressFormCompany(){
          emailFieldCompany.waitUntil(appear, waitTimeout()).scrollIntoView(true);
          return this;
      }

      public CheckOutPage openAddressFormCompany(){
        addressLinkCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false).click();
        emailFieldCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        sleep(2000);
        return this;
    }

      public CheckOutPage fillSsnAndSelectAddress(String ssn){
          addressLinkCompany.waitUntil(appear, waitTimeout()).scrollIntoView(false);
          ssnField.waitUntil(appear, waitTimeout()).scrollIntoView(false).setValue(ssn).pressEnter();
          ssnPopUpFrame.waitUntil(appear, waitTimeout());
          ssnAddressButton.waitUntil(appear,waitTimeout()).click();
          return this;
      }

      public void finalizeParameters(){
          params.put("Item", getCurrentItem());
          parametersList.put(url(), params);
      }

      private String userItemToBuyDetails(String details, String date, String userName){
        StringJoiner sj = new StringJoiner("\n", "Hi, "+userName+"\n", "\n"+"Regards, TradeMax Customer Service");
        sj.add("Your purchase "+details);
        sj.add("Will be delivered "+date);
        return sj.toString();
      }


}
