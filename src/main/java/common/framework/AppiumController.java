package common.framework;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class AppiumController {
    private static Configuration configuration = new Configuration();

    private static final String DEVICE_NAME_KEY = "deviceName";
    private static final String PLATFORM_NAME_KEY = "platformName";
    private static final String APP_PACKAGE_KEY = "appPackage";
    private static final String APP_ACTIVITY_KEY = "appActivity";
    private static final String APPIUM_REMOTE_URL_PROPS_KEY = "appium.remote.url";
    private static final String DEVICE_NAME_PROPS_KEY = "android.device.name";
    private static final String PLATFORM_NAME_PROPS_KEY = "android.platform.name";
    private static final String APP_PACKAGE_PROPS_KEY = "android.app.package";
    private static final String APP_ACTIVITY_PROPS_KEY = "android.app.activity";

    private static final int TIMEOUT_SECONDS = 5;

    protected AndroidDriver driver;


    public void startAppium() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(DEVICE_NAME_KEY, configuration.getProperty(DEVICE_NAME_PROPS_KEY));
        desiredCapabilities.setCapability(PLATFORM_NAME_KEY, configuration.getProperty(PLATFORM_NAME_PROPS_KEY));
        desiredCapabilities.setCapability(APP_PACKAGE_KEY, configuration.getProperty(APP_PACKAGE_PROPS_KEY));
        desiredCapabilities.setCapability(APP_ACTIVITY_KEY, configuration.getProperty(APP_ACTIVITY_PROPS_KEY));
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL(configuration.getProperty(APPIUM_REMOTE_URL_PROPS_KEY));
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public void stopAppium() {
        if (driver != null) {
            driver.quit();
        }
    }
}

