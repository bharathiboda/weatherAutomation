package com.weather.pages;

import com.weather.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageInit extends BasePage {

    public HomePageInit() {
            PageFactory.initElements(driver, this);
        }

    //look and feel
    @FindBy(xpath = "//button[@id=\"LocationSearch_listbox-0\"]")
    WebElement listOfLocations;

    @FindBy(css = "h1[class*=\"CurrentConditions\"]")
    WebElement nameOfLocation;

    @FindBy(css = "button[class=\"_1r7mf r__KU _2yeqQ\"]")
    WebElement desiredLocation;

    @FindBy(css = "span[class=\"GkM1S\"]")
    WebElement desiredLocationName;

    @FindBy(css = "div[class=\"_28jvQ\"]")
    WebElement negativeLocationName;

    @FindBy(css ="svg[name=\"the-weather-company\"]")
    WebElement weatherLogo;

    @FindBy(css = "svg[name=\"twc-logo\"]")
    WebElement weatherLogoIcon;

    private By weatherLogoPresent = By.cssSelector("svg[name=\"twc-logo\"]");

    @FindBy(id = "MainMenuTrigger")
    private WebElement menuId;

    //temp icon elements
    @FindBy(css = "div[data-testid=\"languageSelectorSection\"]")
    private WebElement globe;

    @FindBy(name = "triangle-down")
    private WebElement tempArrow;

    @FindBy(css = "span[class=\"_170nP\"]")
    private WebElement fahrenheit;

    @FindBy(xpath = "//span[@class=\"_2X1ap\"]")
    private WebElement celsius;

    @FindBy(css = "span[class=\"eg0E7\"]")
    private WebElement tempDisplay;

    @FindBy(css = "a[class=\"zzd7M\"]")
    private WebElement socialMediaIcons;

    //forecast menu items
    @FindBy(xpath = "//a[@class=\"_1r7mf _2JY_Z _2yeqQ\"]")
    private WebElement foreCastMenu;

    //functional test locators
    @FindBy(css = "span[class=\"_9LEtl\"]")
    private WebElement menuItems;

    @FindBy(id = "MainMenuTrigger")
    private WebElement menuIcon;

    //clicking on today locators
    @FindBy(css = "section[data-testid=\"TodayWeatherModule\"]")
    private WebElement todayForecast;

    //clicking on hourly locators
    @FindBy(css = "h1[class=\"_3bC6K _2DTAD\"]")
    private WebElement textHourly;

    //clicking on 10 Day locators
    @FindBy(css = "h1[class=\"_3bC6K _1IoG-\"]")
    private WebElement text10_Day;

    //clicking on monthly locators
    @FindBy(css = "div[class=\"locations-title monthly-page-title\"]")
    private WebElement textMonthly;


    //getting text from weekend locator
    @FindBy(css = "div[classname=\"locations-title weekend-page-title\"]")
    private WebElement textWeekend;

    //getting text from radar page to check if radar tab is landed on desired page
    @FindBy(css = "div[classname=\"styles__Title__1hEzx\"]")
    private WebElement textRadar;

    //locator for menu item weather
    @FindBy(css = "div[class=\"styles__navigationListItems__RAsa2\"]")
    private WebElement menuWeatherTab;

    //locator for continents display
    @FindBy(css = "div[class=\"_1z_mF\"]")
    private WebElement continentsList;

    //locator for wind and hourly tabs
    @FindBy(css = "a[data-from-string=\"localsuiteNav_2_Hourly\"]")
    private WebElement hourly;

    @FindBy(css = "span[data-testid=\"Wind\"]")
    private WebElement wind;

    /**
     * checks if the logo is displayed
     * @return - boolean, true if displayed otherwise false
     */
    public boolean logoVisibility() {
        if(!isAvailable(weatherLogoPresent))
            scrollToLocation("130", "11", weatherLogoIcon);
        return weatherLogoIcon.isDisplayed();
    }


}

