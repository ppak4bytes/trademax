package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources("classpath:browserstack.properties")
public interface BrowserStackConfig extends Config {

    BrowserStackConfig CONFIG = ConfigFactory.create(BrowserStackConfig.class);

    @Key("username")
    String userName();

    @Key("key")
    String key();
}

