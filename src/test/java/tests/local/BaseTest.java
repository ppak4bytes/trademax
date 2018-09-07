package tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void beforeRun(){
        Configuration.browser = "lynx";
        //Configuration.headless = true;
        //Configuration.startMaximized = true;
        Configuration.screenshots = false;
    }

    @AfterClass
    public void afterRun(){
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }
}
