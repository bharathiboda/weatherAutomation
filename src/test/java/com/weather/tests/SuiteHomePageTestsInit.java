package com.weather.tests;

import com.weather.base.testBase.BaseTest;
import com.weather.pages.HomePageInit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuiteHomePageTestsInit extends BaseTest {

    private HomePageInit homePage;

    @BeforeMethod
    public void openURL() {
        driver("chrome");
        openUrl("https://weather.com/");
        homePage = new HomePageInit();

    }

    @Test
    public void testOnWeatherLogoVisibility() {
        SoftAssert thisAssert = new SoftAssert();

        thisAssert.assertTrue(homePage.logoVisibility(), "Logo is not displayed as desired");

        thisAssert.assertAll();
    }

    @AfterMethod
    public void closeURl() {
        closeDriver();
    }
}
