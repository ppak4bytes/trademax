package PaymethodsTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void beforeRun() {
        //Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.startMaximized = true;
        Configuration.screenshots = false;
        /////////////////
        Configuration.timeout = 6000;
    }

    @AfterClass(alwaysRun = true)
    public void afterRun() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }

}
