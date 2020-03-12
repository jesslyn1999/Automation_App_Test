package pageobject;

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

    @AndroidFindBy(id = "com.traveloka.android:id/button_continue")
    private AndroidElement continueButton2;

    public FillDetailsPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return waitUntilPageLoaded(contactDetails);
    }

    public void fillForm(Contact contact, List<Traveler> travelers, List<Children> children) {
        //setContactDetails(contact);
        setTravelerDetails(travelers, children);
        continueButton.click();
        continueButton2.click();
        MobileElement verifContinueElmnt =
            (MobileElement) driver.findElementById("com.traveloka.android:id/widget_button_blue");
        waitUntilPageLoaded(verifContinueElmnt);
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
        scrollToElement("new UiSelector().resourceId(\"" + continueButton.getId() + "\").index(6)");
        waitUntilPageLoaded(travelerDetailsSection);
        System.out.println("AHAAA Scroll finished");
        List<MobileElement> travelerForms =
            travelerDetailsSection.findElementsByClassName("android.widget.FrameLayout");
        List<Children> temp = travelers.stream().map(Children::new).collect(Collectors.toList());
        children.addAll(0, temp);

        MobileElement form = (MobileElement) driver.findElementById("com.traveloka.android:id/layout_view_description");
        MobileElement saveButton = (MobileElement) driver.findElementById("com.traveloka.android:id/button_save");

        for (int i = 0; i < travelerForms.size(); i++) {
            travelerForms.get(i).click();
            waitUntilPageLoaded(form);

            MobileElement titleElmt =
                (MobileElement) travelerForms.get(i).findElementByClassName("android.widget.Spinner");
            List<MobileElement> editTextElmnts =
                travelerForms.get(i).findElementsByClassName("android.widget.EditText");
            MobileElement fullNameElmt = editTextElmnts.get(0);

            titleElmt.click();
            driver.findElementByXPath("//*[@title=\"" + children.get(i).getTitle() + "\"]").click();
            fullNameElmt.clear();
            fullNameElmt.sendKeys(children.get(i).getFullName());

            if (i >= travelers.size()) {
                MobileElement dateOfBirthElmt = editTextElmnts.get(1);
                dateOfBirthElmt.clear();
                dateOfBirthElmt.sendKeys(children.get(i).getDateOfBirth());
            }

            saveButton.click();
            waitUntilPageLoaded(travelerForms.get(i));
        }
    }
}
