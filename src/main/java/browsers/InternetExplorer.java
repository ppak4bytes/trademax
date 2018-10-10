package browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class InternetExplorer implements Browser {

    @Override
    public Name name()  {
        return Name.InternetExplorer;
    }


    @Override
    public Capabilities capabilities(Map<String, String> parameters) {

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("internet explorer");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setVersion(parameters.get("browserVersion"));
        capabilities.setCapability("resolution", "1920x1080");
        capabilities.setCapability("browserstack.ie.arch", "x64");
        capabilities.setCapability("browserstack.ie.compatibility", "11001");
        capabilities.setCapability("browserstack.ie.enablePopups", true);


        capabilities.setCapability("browserstack.console", "verbose");
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.networkLogs", "true");

        return capabilities;
    }
}
