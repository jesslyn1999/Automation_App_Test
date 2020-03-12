package pageobject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BookingSummary extends InitPage{

    @AndroidFindBy(id = "com.traveloka.android:id/button_select")
    private AndroidElement buttonSelect;

    public BookingSummary(AndroidDriver driver){
        super(driver);
    }

    public boolean isDisplayed() {
        return waitUntilPageLoaded(buttonSelect);
    }

    public void clickSelect() {  // start from 1
        buttonSelect.click();
    }
}
