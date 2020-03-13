import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.framework.AppiumController;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import pageobject.BookingSummary;
import pageobject.FillDetailsPage;
import pageobject.FlightPage;
import pageobject.FlightSearchResultPage;
import pageobject.Homepage;
import pageobject.POJO.Children;
import pageobject.POJO.Contact;
import pageobject.POJO.Traveler;

public class SampleTest extends AppiumController {

    private Homepage homepage;
    private FlightPage flightPage;
    private FlightSearchResultPage flightSearchResultPage;
    private BookingSummary bookingSummary;
    private FillDetailsPage fillDetailsPage;

    private Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Before
    public void setUp() throws Exception {
        startAppium();
        homepage = new Homepage(driver);
        flightPage = new FlightPage(driver);
        flightSearchResultPage = new FlightSearchResultPage(driver);
        bookingSummary = new BookingSummary(driver);
        fillDetailsPage = new FillDetailsPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Verify Click Flight Button")
    @Feature("Homepage")
    @Story("Icon Flight is clicked")
    @Description("User clicked flight button")
    public void flightButtonClickedTest() {
        try {
            logger.info("Start executing flightButtonCLickedTest.");

            getScreenshot("Launch Screen");
            Assert.assertTrue(homepage.isDisplayed());
            getScreenshot("Home Page");

            homepage.clickFlightIcon();
            Assert.assertTrue(flightPage.isDisplayed());

            getScreenshot("Flight Page");

            flightPage.setFlight("Jakarta", "Surabaya");
            //flightPage.setWheels("1", "1", "1");
            flightPage.setSeatClass(0);
            flightPage.searchForFlight();

            Assert.assertTrue(flightSearchResultPage.isDisplayed());
            getScreenshot("Search Result Page");
            flightSearchResultPage.chooseFlight(2);

            Assert.assertTrue(bookingSummary.isDisplayed());
            getScreenshot("Booking Summary");
            bookingSummary.clickSelect();

            Assert.assertTrue(fillDetailsPage.isDisplayed());
            getScreenshot("Fill In Details");
            List<Traveler> travelers = Stream.of(new Traveler("Mr.", "Jimmy Hendrix")).collect(Collectors.toList());
            List<Children> children = Stream.of(
                new Children("Ms.", "adelyn rihana", "2 March 2010"),
                new Children("Mr.", "ray marco", "10 April 2019")
                ).collect(Collectors.toList());
            fillDetailsPage.fillForm(
                new Contact("Jidapa Ornec", "+62", "81234314123", "JidapaOrc3241321@gmail.com"),
                travelers, children
            );

            getScreenshot("Finish");

            logger.info("Done executing flightButtonCLickedTest.");
        } catch (Exception exc) {
            logger.error("Error in executing flightButtonCLickedTest.", exc);
        }
    }

    @Attachment(value = "screenshots", type = "image/png")
    private void getScreenshot(String attachmentName) throws Exception {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("screenshots/" + attachmentName + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);
    }
}
