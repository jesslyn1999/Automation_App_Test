package pageobject;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pageobject.POJO.Children;
import pageobject.POJO.Contact;
import pageobject.POJO.Traveler;

public class FillDetailsPage extends InitPage {

    @AndroidFindBy(id = "com.traveloka.android:id/widget_contact_detail")
    private AndroidElement contactDetails;

    @AndroidFindBy(id = "com.traveloka.android:id/widget_traveler_details")
    private AndroidElement travelerDetailsSection;

    @AndroidFindBy(id = "com.traveloka.android:id/text_view_see_below_view")
    private AndroidElement continueButton;

    @AndroidFindBy(id = "com.traveloka.android:id/text_view_simple_add_ons_title")
    private AndroidElement freqAdded;


    public FillDetailsPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return waitUntilPageLoaded(contactDetails);
    }

    public void fillForm(Contact contact, List<Traveler> travelers, List<Children> children) {
        setContactDetails(contact);
        setTravelerDetails(travelers, children);
        continueButton.click();
    }

    private void setContactDetails(Contact contact) {
        contactDetails.click();
        MobileElement form = (MobileElement) driver.findElementById("com.traveloka.android:id/layout_view_description");
        MobileElement saveButton = (MobileElement) driver.findElementById("com.traveloka.android:id/button_save");
        waitUntilPageLoaded(form);

        List<MobileElement> editTextElements = form.findElementsByClassName("android.widget.EditText");
        editTextElements.forEach(element -> {
            element.clear();
        });
        editTextElements.get(0).sendKeys(contact.getFullName());
        editTextElements.get(1).sendKeys(contact.getCountryCode());
        editTextElements.get(2).sendKeys(contact.getMobilePhone());
        editTextElements.get(3).sendKeys(contact.getEmail());
        saveButton.click();

        waitUntilPageLoaded(contactDetails);
    }

    private void setTravelerDetails(List<Traveler> travelers, List<Children> children) {
        try {
            scrollToElement("new UiSelector().resourceId(\"" + travelerDetailsSection.getId() + "\")");
        } catch (Exception exc) { }
        waitUntilPageLoaded(travelerDetailsSection);
        List<MobileElement> travelerForms =
            travelerDetailsSection.findElementsByXPath("//*[@content-desc=\"trip_booking_view_traveleremptycontainer\"]");
        List<Children> temp = travelers.stream().map(Children::new).collect(Collectors.toList());
        children.addAll(0, temp);

        for (int i = 0; i < children.size(); i++) {
            travelerForms.get(0).click();

            MobileElement form = (MobileElement) driver.findElementById("com.traveloka.android:id/layout_view_description");
            MobileElement saveButton = (MobileElement) driver.findElementById("com.traveloka.android:id/button_save");

            waitUntilPageLoaded(form);

            MobileElement titleElmt =
                (MobileElement) driver.findElementByClassName("android.widget.Spinner");
            List<MobileElement> editTextElmnts =
                driver.findElementsByClassName("android.widget.EditText");
            MobileElement fullNameElmt = editTextElmnts.get(0);

            titleElmt.click();
            driver.findElementByAndroidUIAutomator(".textContains(\""+ children.get(i).getTitle() +"\")").click();
            fullNameElmt.clear();
            fullNameElmt.sendKeys(children.get(i).getFullName());

            if (i >= travelers.size()) {
                MobileElement dateOfBirthElmt = editTextElmnts.get(1);
                dateOfBirthElmt.click();
                MobileElement buttonOK = (MobileElement) driver.findElementById("android:id/button1");
                //dateOfBirthElmt.sendKeys(children.get(i).getDateOfBirth());
                buttonOK.click();
            }

            saveButton.click();

            waitUntilPageLoaded(travelerDetailsSection);
            try {
                scrollToElement("new UiSelector().resourceId(\"" + travelerDetailsSection.getId() + "\")");
            } catch (Exception exc) { }
            travelerForms =
                travelerDetailsSection.findElementsByXPath("//*[@content-desc=\"trip_booking_view_traveleremptycontainer\"]");
        }
    }
}
