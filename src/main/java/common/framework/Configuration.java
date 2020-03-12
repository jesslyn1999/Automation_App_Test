package common.framework;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Properties;

public class Configuration {
    //private Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static final String PROPS_FILE = "base.properties";
    private Properties configFile;

    Configuration() {
        configFile = new Properties();

        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream(PROPS_FILE));
        } catch (Exception exc) {
            //logger.error("Failed to execute Configuration Framework.", exc);
        }
    }

    String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
