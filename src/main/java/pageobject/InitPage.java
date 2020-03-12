package pageobject;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class InitPage {
    protected AndroidDriver driver;

    private static final long TIMEOUT_SECONDS = 5;
    private static final long TIMEOUT_PAGE_LOADED_SECONDS = 15;

    public InitPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(TIMEOUT_SECONDS)), this);
    }

    public boolean waitUntilPageLoaded(MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_PAGE_LOADED_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public String getPlatform() {
        return driver.getCapabilities().getCapability("platform").toString().toUpperCase();
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    protected void scrollToElement(String uiSelectorStr){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
            ".scrollIntoView("+ uiSelectorStr +");");
    }


}
