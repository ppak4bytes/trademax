package base;

import browsers.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import java.net.MalformedURLException;
import java.net.URL;
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
}

