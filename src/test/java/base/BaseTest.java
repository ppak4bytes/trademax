package base;

import browsers.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import pages.Page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static config.BrowserStackConfig.CONFIG;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

public abstract class BaseTest {

    private WebDriver driver;
    private Browser browser;
    protected Map<String, String> parameters;
    protected Map<String, String> suiteParameters;
    private static final String URL = "https://" + CONFIG.userName() + ":" + CONFIG.key() + "@hub-cloud.browserstack.com/wd/hub";
    private Map<String, List<String>> test;
    protected static final List<Map<String, List<String>>> TEST_CONTAINER = new ArrayList<>();
    protected static final Map<String, Map<String, String>> PARAMS_CONTAINER = new HashMap();
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

//    @BeforeSuite
//    public void beforeSuite(ITestContext context){
//        final String suiteName = context.getSuite().getName();
//        suiteParameters = new HashMap<>();
//        suiteParameters.putAll(context.getSuite().getXmlSuite().getParameters());
//        PARAMS_CONTAINER.put(suiteName, suiteParameters);
//    }

    @BeforeClass
    public void beforeClass(XmlTest xmlTest){
        test = new HashMap<>();
    }

    @AfterClass
    public void afterTestClass(XmlTest xmlTest){
        final String testName = xmlTest.getName();
        test.put(testName, testPayMethods);
        TEST_CONTAINER.add(test);

    }

    @AfterSuite
    public void afterSuite(ITestContext context){
        final String suiteName = context.getSuite().getName();
        suiteParameters = new HashMap<>();
        suiteParameters.putAll(context.getSuite().getXmlSuite().getParameters());
        PARAMS_CONTAINER.put(suiteName, suiteParameters);
        System.out.println(">>>!!!>>TestContainer-size in AFTERSUITE   "+ TEST_CONTAINER.size()+"<<!!!<<<");
        System.out.println(">>>!!!>>ParamsContainer-size in AFTERSUITE   "+ PARAMS_CONTAINER.size()+"<<!!!<<<");
        TEST_CONTAINER.forEach(System.out::println);
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


}

