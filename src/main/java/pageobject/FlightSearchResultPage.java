package pageobject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FlightSearchResultPage extends InitPage {

    @AndroidFindBy(id = "com.traveloka.android:id/view_pager")
    private AndroidElement resultSection;

    public FlightSearchResultPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return waitUntilPageLoaded(resultSection);
    }

    public void chooseFlight(int idx) {  // start from 1
        MobileElement result = resultSection.findElementByXPath("(//android.widget.LinearLayout[@content-desc"
            + "=\"flight_result_container_view\"])[2]");
        result.click();
    }
}
