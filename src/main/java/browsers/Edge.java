package browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class Edge implements Browser {

    @Override
    public Name name() { return Name.Edge; }

    @Override
    public Capabilities capabilities(Map<String, String> parameters) {

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("MicrosoftEdge");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setCapability("browser_version", "17.0");
        capabilities.setCapability("resolution", "1920x1080");
        capabilities.setCapability("browserstack.edge.enablePopups", true);


        capabilities.setCapability("browserstack.console", "verbose");
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.networkLogs", "true");

        return capabilities;
    }
}
