package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static repository.ElementSelectors.CheckOutPageSelectors.*;

@SuppressWarnings("never used")
public class CheckOutPage extends Page {

    public List<String> payMethodsList;
    public String zipCodeFieldLength;

    public CheckOutPage getPayMethodValues() {
        paymentAgreement.waitUntil(appear, waitTimeout()).scrollIntoView(false);
        final List<SelenideElement> temp = new ArrayList<>();
        payMethods.iterator().forEachRemaining(temp::add);
        payMethodsList = temp.stream().map(el -> el.getAttribute("value")).collect(Collectors.toList());
//        payMethodsList.forEach(System.out::println);
        listOfPaymentMethods(payMethodsList);
        makeScreenshot(SCREENSHOT_FILEPATH);
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

    public CheckOutPage getZipCodeValue(){
        zipCodeFieldLength = postcodeFieldCompany.getAttribute("maxLength");
        return this;
    }

    @Attachment
    private String listOfPaymentMethods(List<String> list) {
        return list.stream().collect(Collectors.joining("\n" + "-", "The list of paymethods is:" + "\n" + "-", ""));
    }

    private void makeScreenshot(String outputFolder){
        DateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
        Date date = new Date();
        File srcFile = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        try { FileUtils.copyFile(srcFile, new File(outputFolder + "-" + dateFormat.format(date) + ".png")); }
        catch (IOException e){e.printStackTrace();}
    }

}
