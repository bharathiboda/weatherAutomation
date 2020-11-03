package com.weather.tests;

import com.weather.commons.WeatherForecast;
import com.weather.pages.HomePageInit;
import com.weather.webDriver.Wd;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SuiteHomePageTestsInit extends Wd {

    HomePageInit homePage ;

    /**
     * this is to initiate browser and open desired url
     */
    @BeforeMethod
    @org.testng.annotations.Parameters(value = {"browser", "url"})
    public void openURL(String browser, String url) {
        super.initializeDriver(browser, url);
        homePage = new HomePageInit();
    }

    /**
     * test any url if you want it is sample test
     */
    @Test(dataProviderClass = ExternalData.ExcelReader.class, dataProvider = "browserAndUrl", enabled = false)
    public void test(String browser, String url) {
        openURL(browser, url);
        //HomePageInit homePage = PageFactory.initElements(driver.get(), HomePageInit.class);
       // new Wd().initializeDriver(browser).findElement(By.xpath("//a[@data-from-string=\"localsuiteNav_4_Weekend\"]")).click();
        //System.out.println(new Wd().initializeDriver(browser).findElement(By.cssSelector("")).getAttribute(""));

    }

    @Test(description = "test on header elements "/*dataProviderClass =ExternalData.ExcelReader.class, dataProvider = "browserAndUrl"*/, enabled = true)
    public void testHeaderElementsVisibility(/*String browser, String url*/) {
       // openURL(browser, url);

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

    @Test(description = "This test to verify if forecast menu items are present", dataProviderClass =ExternalData.ExcelReader.class, dataProvider = "browserAndUrl", enabled = true , groups = {"regression","sanity"})
    public void testOnForeCastMenuItems(String browser, String url) {
        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();

        List<String> expectedValues = Arrays.asList("Today", "Hourly", "10 Day", "Weekend", "Monthly", "Radar");
        thisAssert.assertEquals(homePage.areForaCastMenuItemsDisplay(), expectedValues, "ForeCast tabs are not displayed as expected");
        thisAssert.assertAll();
    }

    @Test(description = "This is to verify if the all social icons are present", dataProviderClass =ExternalData.ExcelReader.class, dataProvider = "browserAndUrl", enabled = true)
    public void testSocialMediaIconsDisplay(String browser, String url) {
        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();
        if (threadDriver.get().getClass() == InternetExplorerDriver.class) {
            throw new SkipException("Social media icons are not present in this browser" + threadDriver.get().getClass());
        }
        List<String> expectedValues = Arrays.asList("facebook", "twitter", "instagram", "youtube");
        thisAssert.assertEquals(homePage.isSocialMediaIconDisplayed(), expectedValues, "Social media icons are not displayed as expected");
        thisAssert.assertAll();
    }

    // functional test casesrsx
    //TCTWC011, 12, 13
    //TC Case Id : TCTWC021     //TC Id: TCTWC022, 23, TCTWC015
    @Test(description = "verify if the clicking on header elements is working as expected and displayed desired text " +
            "tests in this method as follows " +
            "Checking on landing in desired location based on inout given in the search bar" +
            "Checking if any random text in the search bar displays results not found" +
            "Checking main headings of the menu items is displayed or not", dataProviderClass = ExternalData.ExcelReader.class, dataProvider = "LocationData", enabled = true)

    public void testOnSearchBar(String browser, String url, String location) throws IOException{

        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();

        // this is positive test
        //TC Case Id: TCTWC012
        if(!location.equals("fjsnjfhsdjfhsdjkfh")) {
            thisAssert.assertEquals(homePage.isLandingOnDesiredLocationPositive(location), location + " Weather", "It is not landed on desired location");
        } else {
            // this is negative test
            // TC Case Id: TCTWC013
            thisAssert.assertTrue(homePage.getTextLandingOnDesiredLocationNegative(location), "It should display No results found or You have no recent locations, but found different results");
        }

        thisAssert.assertAll();
    }

    @Test(description = "Checking if the clicking on weather logo is directing to home page" +
            "In the temperature display in the main page on the upper right hand corner of the page check if the temperature measure changes based up on selection",
            dataProviderClass = ExternalData.ExcelReader.class, dataProvider = "browserAndUrl")
    public void testOnHeaderElements(String browser, String url) throws IOException {
        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();
        //TCTWC011
        homePage.isClickingOnLogoReturnsHomePage();
        takeAScreenShot();

        List<String> temps = Arrays.asList("°F", "°C");
        thisAssert.assertEquals(homePage.getTextFahrenheitOrCelsiusSelected(), temps, "Fahrenheit/celsius is not selected as desired");

        List<String> actualList = Arrays.asList("WEATHER", "RADAR", "SEVERE", "VIDEO & PHOTOS", "HEALTH & ACTIVITIES", "TV", "WEATHER PRODUCTS", "PRIVACY");
        thisAssert.assertEquals(homePage.menuItemsDisplayed(), actualList, "Menu content not displayed as expected");
        thisAssert.assertAll();
    }

    //TC id: TCTWC016,
    //             17,
    //             18,
    //             19,
    //             20,
    //             21.
    @Test(description = "running test using enums for the verification of forecast tabs clickable and verifying landing page",
          dataProviderClass =ExternalData.ExcelReader.class,
          dataProvider = "browserAndUrl")
    /**
     * This is to verify if the forecast elements are clickable and landing on desired page
     */
    public void testOnSelectingForecastTabAndVerifyText(String browser, String url) {
        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();
        for (WeatherForecast forecast : WeatherForecast.values()) {
            thisAssert.assertTrue(homePage.selectForecastTabAndVerifyText(forecast.ordinal() + 1, forecast.getForeCast()), forecast.getActualValue());
        }
        thisAssert.assertAll();

    }

    @Test(description = "verify if the continent list is displayed as desired in the temperature arrow" +
            "this is to test if measurement type is changing if we change temperature measurement (" +
            "" +
            "temp = fahrenheit then wind = mph temp = celsius and wind = km/h)", dataProviderClass = ExternalData.ExcelReader.class, dataProvider = "browserAndUrl")
    public void testOnDisplayContinentsAndChangeInWindUnitIfTemperatureChanges(String browser, String url) {
        //openURL(browser, url);

        SoftAssert thisAssert = new SoftAssert();

        // this is for test case TCTWC026
        List<String> actualList = Arrays.asList("AMERICAS", "AFRICA", "ASIA PACIFIC", "EUROPE", "MIDDLE EAST");
        thisAssert.assertEquals(homePage.displayContinents(), actualList, "continents list is not displayed as desired");

        //this is to test TCTWC024
        thisAssert.assertTrue(homePage.changeInUnitsIfTemperatureChangesFahrenheit().endsWith("mph"), "Fahrenheit temperature and wind unit mph is not displayed");

        //this is for test case TCTWC025
        thisAssert.assertTrue(homePage.changeInWindUnitIfTemperatureChangesCelsius().endsWith("km/h"), "Celsius temperature and wind unit km/h is not displayed ");
        thisAssert.assertAll();

    }
    @AfterMethod
    public void closeBrowser() {
        threadDriver.get().quit();
        threadDriver.set(null);
    }
}
