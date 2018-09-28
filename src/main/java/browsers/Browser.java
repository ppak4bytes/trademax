package browsers;

import org.openqa.selenium.Capabilities;

import java.util.Map;

public interface Browser {

    enum Name{
        Chrome("chrome"),
        Safari("safari"),
        InternetExplorer("internet explorer"),
        Edge("edge");

        private String browser;

        Name(String browserName) {
            browser = browserName;
        }

        public String getBrowser(){
            return browser;
        }
    }

    Name name();

    Capabilities capabilities(final Map<String, String> parameters);


}
