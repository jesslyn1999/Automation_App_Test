package pageobject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FlightPage extends InitPage {

    @AndroidFindBy(id = "com.traveloka.android:id/text_field_origin")
    private AndroidElement origin;

    @AndroidFindBy(id = "com.traveloka.android:id/text_field_destination")
    private AndroidElement destination;

    @AndroidFindBy(id = "com.traveloka.android:id/text_field_departure_date")
    private AndroidElement datepicker;

    @AndroidFindBy(id = "com.traveloka.android:id/text_field_passenger")
    private AndroidElement passengers;

    @AndroidFindBy(id = "com.traveloka.android:id/text_field_seat_class")
    private AndroidElement seatClass;

    @AndroidFindBy(id = "com.traveloka.android:id/btn_search")
    private AndroidElement buttonSearch;

    public FlightPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return origin.isDisplayed() && destination.isDisplayed();
    }

    public void setFlight(String fromCity, String toCity) {
        flightCityClicked(origin, fromCity);
        flightCityClicked(destination, toCity);
    }

    public void setWheels(String adult, String child, String infant) {
        passengers.click();
        System.out.println("HUHHH");
        MobileElement buttons = (MobileElement) driver.findElementById("com.traveloka.android:id/trayButtonGroup");
        MobileElement selectButton = buttons.findElementsByClassName("android.widget.Button").get(1);
        AndroidElement adultWheels =
            (AndroidElement) driver.findElementById("com.traveloka.android:id/wheel_passenger_adult");
        AndroidElement childWheels =
            (AndroidElement) driver.findElementById("com.traveloka.android:id/wheel_passenger_child");
        AndroidElement infantWheels =
            (AndroidElement) driver.findElementById("com.traveloka.android:id/wheel_passenger_infant");

        waitUntilPageLoaded(buttons);
        scrollTillText("com.traveloka.android:id/wheel_passenger_adult", adult);
        scrollTillText("com.traveloka.android:id/wheel_passenger_child", child);
        scrollTillText("com.traveloka.android:id/wheel_passenger_infant", infant);
        selectButton.click();
        System.out.println("HUHHH22");

        waitUntilPageLoaded(passengers);
    }

    public void setSeatClass(int category) {
        seatClass.click();
        MobileElement optionsElement = (MobileElement) driver.findElementById("com.traveloka.android:id/rbg_seat");
        MobileElement doneButton = (MobileElement) driver.findElementById("com.traveloka.android:id/trayButtonGroup");

        waitUntilPageLoaded(optionsElement);
        optionsElement.findElementsByClassName("android.widget.RadioButton").get(category).click();
        doneButton.click();

        waitUntilPageLoaded(seatClass);
    }

    public void setDepartureDataForward(int days) {
        datepicker.click();

        MobileElement today = (MobileElement) driver.findElementById("com.traveloka.android:id/info_top_text_view");
        waitUntilPageLoaded(today);

        String currDateStr = today.getText();

        waitUntilPageLoaded(datepicker);
    }

    public void searchForFlight() {
        buttonSearch.click();
    }

    private void flightCityClicked(AndroidElement element, String city) {
        element.click();
        MobileElement searchBar =
            (MobileElement) driver.findElementById("com.traveloka.android:id/edit_text_search_constraint");
        MobileElement searchResult =
            (MobileElement) driver.findElementById("com.traveloka.android:id/list_view_flight_search");
        MobileElement toChoose;

        waitUntilPageLoaded(searchBar);
        searchBar.sendKeys(city);
        waitUntilPageLoaded(searchResult);
        toChoose = searchResult.findElementsById("com.traveloka.android:id/text_view_geo_name").get(0);
        toChoose.click();
        waitUntilPageLoaded(element);
    }

    public void scrollTillText(String resourceId, String step) {
        //driver.findElementByAndroidUIAutomator(
        //    "new UiScrollable(new UiSelector().resourceId(\""+ resourceId +"\")).scrollForward("+step+")"
        //).click();
        try {
            driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"" + resourceId + "\")).scrollForward()"
            );
        } catch (Exception exc) {
            System.out.println("YOO ERR");
            exc.printStackTrace();
        }

        //element.findElementByAndroidUIAutomator(
        //    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+text+"\").instance(0))"
        //).click();
    }

  //  (new TouchAction(driver))
  //      .press({x: 174, y: 1485})
  //      .moveTo({x: 204: y: 1232})
  //      .release()
  //.perform()
}
