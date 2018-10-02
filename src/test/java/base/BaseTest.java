package base;

import browsers.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import pages.Page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static config.BrowserStackConfig.CONFIG;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

public abstract class BaseTest {

    private WebDriver driver;
    private Browser browser;
    private Map<String, String> parameters;
    private static final String URL = "https://" + CONFIG.userName() + ":" + CONFIG.key() + "@hub-cloud.browserstack.com/wd/hub";
    protected List<String> testPayMethods;




    @BeforeTest(alwaysRun = true)
    public void setUpDriver(XmlTest xmlTest) {
        parameters = xmlTest.getAllParameters();
        browser = getBrowser(parameters.get("browser").toLowerCase());
        configureDriver(browser,parameters);
    }

    @AfterTest(alwaysRun = true)
    public void killDriver(){
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().quit();
    }


    private Browser getBrowser(String browserName){
        return Match(browserName).of(
                Case($("chrome"), ()->new Chrome()),
                Case($("safari"), ()->new Safari()),
                Case($("ie"), ()->new InternetExplorer()),
                Case($("edge"), ()->new Edge())
        );
    }

    private void configureDriver(final Browser browser, Map<String, String> params){
        try {
            driver = new RemoteWebDriver(new URL(URL), browser.capabilities(params));
            driver.manage().window().maximize();

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        WebDriverRunner.setWebDriver(driver);
    }

    protected String zipCodeLengthValidationRule(final Page indexPage){
        final String validationRule;
        if(indexPage.url().endsWith("se") || indexPage.url().endsWith("fi") || indexPage.url().endsWith("com")) validationRule = "5";
        else validationRule = "4";
        return validationRule;
    }

    @BeforeSuite
    public void beforeSuite(ITestContext context){
        System.out.println("********** suite"+context.getSuite().getName()+"name **********");
    }

    @BeforeClass
    public void afterTestMethod(XmlTest test){
        System.out.println("********** test"+test.getName()+"name **********");
    }

    @AfterClass
    public void afterClass(){
        testPayMethods.forEach(System.out::println);
    }


}

