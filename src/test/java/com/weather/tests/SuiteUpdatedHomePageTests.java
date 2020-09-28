package com.weather.tests;

import com.weather.base.test.BaseTest;
import com.weather.commons.WeatherForecast;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import com.weather.pages.HomePage;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuiteUpdatedHomePageTests extends BaseTest {
    HomePage homePage;

    @BeforeMethod()
    public void initializeBrowser() {
        driver("chrome");
        //TestCaseID: TCTWC0001, //checks if the website is launching
        openUrl("https://weather.com/");
        homePage = new HomePage();
    }

    @Test(description = "Verify all the header elements are present ")
    public void testHeaderElementsVisibility() {
        SoftAssert thisAssert = new SoftAssert();

        //TestCase ID: TCTWC0002, Check if the logo is present
        thisAssert.assertTrue(homePage.logoVisibility(), " Logo is not displayed");

        //TestCase ID: TCTWC0003, Check if the menu is displayed or not.
        thisAssert.assertTrue(homePage.menuLocation(), " Menu is not displayed on the right side of the screen");

        //TestCase ID: TCTWC004, Check if the temperature is displayed
        thisAssert.assertTrue(homePage.globeAndTemperatureDisplay(), "Globe and temperature is not displayed on the left side of the menu");

        //TestCase ID: TCTWC005, check if the search bar is displayed.
        thisAssert.assertTrue(homePage.isSearchBarPresent(), " Search bar is not present");

        //TestCase ID: TCTWC009, check if the default text is displayed
        thisAssert.assertEquals(homePage.isDefaultTextDisplayed(), "Search City or Zip Code", "Default text" + homePage.isDefaultTextDisplayed() + "is not displayed");
        thisAssert.assertAll();
    }

    @Test(description = "verify if the forecast of periods are displayed")
    public void testOnForeCastMenuItems() {
        SoftAssert thisAssert = new SoftAssert();
        List<String> expectedValues = Arrays.asList("Today", "Hourly", "10 Day", "Weekend", "Monthly", "Radar");
        thisAssert.assertEquals(homePage.areForaCastMenuItemsDisplay(), expectedValues, "ForeCast tabs are not displayed as expected");
        thisAssert.assertAll();
    }

    @Test(description = "Verify if the social media icons displayed")
    public void testSocialMediaIconsDisplay() {
        SoftAssert thisAssert = new SoftAssert();
        if (driver.getClass() == InternetExplorerDriver.class) {
            throw new SkipException("Social media icons are not present in this browser" + driver.getClass());
        }
        scrollToLocation("341", "3008", driver.findElement(homePage.getSocialMediaIcons()));
        List<String> expectedValues = Arrays.asList("facebook", "twitter", "instagram", "youtube");
        thisAssert.assertEquals(homePage.isSocialMediaIconDisplayed(), expectedValues, "Social media icons are not displayed as expected");
        thisAssert.assertAll();
    }

    // functional test cases
    //TCTWC011, 12, 13
    //TC Case Id : TCTWC021     //TC Id: TCTWC022, 23, TCTWC015
    @Test(description = "verify if the clicking on header elements is working as expected and displayed desired text ")
    public void testOnHeaderElements() throws IOException, InterruptedException {
        SoftAssert thisAssert = new SoftAssert();

        homePage.isClickingOnLogoReturnsHomePage();
        takeAScreenShot();

        // this is positive test
        //TC Case Id: TCTWC012
        thisAssert.assertEquals(homePage.isLandingOnDesiredLocationPositive("Pune, Maharashtra, India"), "Pune, Maharashtra, India", "It is not landed on desired location");

        // this is negative test
        // TC Case Id: TCTWC013
        thisAssert.assertEquals(homePage.isLandingOnDesiredLocationNegative("fjksdfjksdjkf"), "No results found", "It should display No results found, but found differently");

        thisAssert.assertEquals(homePage.isFahrenheitSelected(), "°F", "Fahrenheit is not selected");
        //hard coded
        Thread.sleep(2000);

        List<String> actualList = Arrays.asList("WEATHER", "RADAR", "SEVERE", "VIDEO & PHOTOS", "HEALTH & ACTIVITIES", "TV", "WEATHER PRODUCTS", "PRIVACY");
        thisAssert.assertEquals(homePage.menuItemsDisplayed(), actualList, "Menu content not displayed as expected");

        thisAssert.assertEquals(homePage.isCelsiusSelected(), "°C", "Celsius is not selected");
        thisAssert.assertAll();
    }

    //TC id: TCTWC016, 17, 18, 19, 20, 21
    @Test(description = "Verify clicking on the main forecast menu tabs and verifying if it is directed to correct page", enabled = false)
    public void testOnSelectingForecastTabAndVerifyText() {

        SoftAssert thisAssert = new SoftAssert();

        String[][] parameters =
                {
                        {"Today", "Today's Forecast for East Windsor Township, NJ", "Today forecast is not displayed as expected"},
                        {"Hourly", "Hourly Weather-East Windsor Township, NJ", "Hourly forecast is not displayed as expected"},
                        {"10 Day", "10 Day Weather-East Windsor Township, NJ", "10_Day forecast is not displayed as expected"},
                        {"Weekend", "East Windsor Township, NJ Weekend Weather", "Weekend forecast is not displayed as expected"},
                        {"Monthly", "East Windsor Township, NJ Monthly Weather", "Monthly forecast is not displayed as expected"},
                        {"Radar", "Radar and Weather Maps for East Windsor Township, NJ", "Radar forecast is not displayed as expected"}
                };
        for (int i = 0; i < parameters.length; i++) {
            thisAssert.assertEquals(homePage.selectForecastTabAndVerifyText(i + 1, parameters[i][0]), parameters[i][1], parameters[i][2]);
        }
        thisAssert.assertAll();
    }

    @Test(description = "running test using enums")
    /**
     * This is same above test case but using enums
     */
    public void testEnums() {
        SoftAssert thisAssert = new SoftAssert();
        for (WeatherForecast forecast : WeatherForecast.values()) {
            thisAssert.assertEquals(homePage.selectForecastTabAndVerifyText(forecast.ordinal() + 1, forecast.getForeCast()), forecast.getExpectedValue(), forecast.getActualValue());
        }
        thisAssert.assertAll();
    }

    @Test(description = "goes to menu weather tab and check if the list is displayed as desired", enabled = false)
    public void testOnMenuWeatherTabListSelectAndVerifyText() {
        System.out.println(homePage.selectMenuWeatherTabListItemAndVerifyText());
    }
     //TCTWC024
    @Test(description = "verify if the continent list is displayed as desired in the temperature arrow")
    public void testOnDisplayContinents() {
        SoftAssert thisAssert = new SoftAssert();
        List<String> actualList = Arrays.asList("AMERICAS", "AFRICA", "ASIA PACIFIC", "EUROPE", "MIDDLE EAST");
        thisAssert.assertEquals(homePage.displayContinents(), actualList, "continents list is not displayed as desired");
        thisAssert.assertAll();
    }

    @Test
    public void testOn() {
        SoftAssert thisAssert = new SoftAssert();
        if(homePage.isFahrenheitSelected().equals("°F")){
           thisAssert.assertEquals(homePage.wind(), "SSW 6 mph", "Correct temperature and wind measure is not displayed");
        }
        thisAssert.assertAll();
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}


