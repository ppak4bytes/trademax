package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class CheckOutPage implements Page {

     public List<String> payMethodsList = new ArrayList<>();

     private ElementsCollection payMethods = $$x("//form[@id='paymentMethodsForm']/descendant::input");

     private SelenideElement  companyTab = $x("//li[contains(text(), 'Företag')]"),
                              emailFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='email']"),
                              addressLink = $x("//div[@id='checkout']//form[@id='sveaSsnFormPrivate']/a"),
                              addressFormSSNButton = $x("//div[@id='checkout']//form[@id='sveaSsnFormPrivate']/button"),
                              addressFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='address']"),
                              postcodeFieldPrivate = $x("//form[@id='sveaManuallyAddressFormPrivate']//input[@id='postcode']"),
                              paymentAgreement = $x("//div[@class='payment--agreement']"); // bottom page element for scrolling



      public CheckOutPage getPayMethodValues(){
          paymentAgreement.waitUntil(appear, waitTimeout()).scrollIntoView(false);
          final List<SelenideElement> temp = new ArrayList<>();
          payMethods.iterator().forEachRemaining(temp::add);
          payMethodsList = temp.stream().map(el->el.getAttribute("value")).collect(Collectors.toList());
          payMethodsList.forEach(System.out::println);
          return this;
      }

     public CheckOutPage openAddressForm(){
        addressLink.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        addressLink.click();
        emailFieldPrivate.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        sleep(3000);
        return this;
      }

      public CheckOutPage selectCompanyTab(){
          companyTab.waitUntil(appear, waitTimeout()).click();
          return this;
      }

      public CheckOutPage fillFormField(String value1, String value2){
          addressFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value1).pressTab();
          postcodeFieldPrivate.waitUntil(appear, waitTimeout()).setValue(value2).pressTab();
          sleep(3000);
          return this;
      }

      private String userItemToBuyDetails(String details, String date, String userName){
        StringJoiner sj = new StringJoiner("\n", "Hi, "+userName+"\n", "\n"+"Regards, TradeMax Customer Service");
        sj.add("Your purchase "+details);
        sj.add("Will be delivered "+date);
        return sj.toString();
      }


}
