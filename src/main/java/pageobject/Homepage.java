package pageobject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Homepage extends InitPage {

    @AndroidFindBy(id = "com.traveloka.android:id/recycler_view_favorite_product")
    private AndroidElement iconElements;

    private MobileElement flightIconElement;

    public Homepage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return waitUntilPageLoaded(iconElements);
    }

    public void clickFlightIcon() {
        flightIconElement = iconElements.findElementsByClassName("android.view.View").get(0);
        flightIconElement.click();
    }

    public void scroll() {
        scrollToElement("new UiSelector().textContains(\"Leave Calendar 2020\")");
    }
}
